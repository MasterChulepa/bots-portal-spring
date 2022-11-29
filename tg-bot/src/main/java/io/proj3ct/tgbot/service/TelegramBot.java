package io.proj3ct.tgbot.service;

import com.vdurmont.emoji.EmojiParser;
import io.proj3ct.tgbot.config.BotConfig;
import io.proj3ct.tgbot.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final KafkaProducer kafkaProducer;
    private final BotConfig botConfig;
    public static String HELP_TEXT = "HELP_TEXT";
    @Autowired
    public TelegramBot(BotConfig botConfig, KafkaProducer kafkaProducer) {
        this.botConfig = botConfig;
        this.kafkaProducer = kafkaProducer;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "start dialog with bot"));
        listOfCommands.add(new BotCommand("/createorder", "create a new order"));
        listOfCommands.add(new BotCommand("/deletedata", "erase your history"));
        listOfCommands.add(new BotCommand("/mydata", "get your data stored"));
        listOfCommands.add(new BotCommand("/help", "show info how to use this bot"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));

        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
           log.error("Error setting bot's menu commands: " + e.getMessage());
        }
    }
    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            Message msg = update.getMessage();
            switch (messageText) {
                case "/start":
                    startCommandReceived(msg, chatId);
                    break;
                case "/help":
                    sendMessage(HELP_TEXT ,chatId);
                    break;
                default:
                    someFunctionality(chatId);
            }
        }
    }

    private void startCommandReceived(Message msg, long chatId) {
        String username = msg.getChat().getFirstName();
        String answer = EmojiParser.parseToUnicode("Hello, " + username + ", nice to meet you! " + ":smile:");
        sendMessage(answer, chatId);
        log.info("Replied to user: " + username);
        registerUser(msg);
    }

    private void sendMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setReplyMarkup(createReplyKeyboard());
        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());;
        }
    }

    private void registerUser(Message msg){
            Chat chat = msg.getChat();
            UserDto user = new UserDto();
            user.setId(msg.getChatId());
            user.setUsername(chat.getUserName());
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
            kafkaProducer.sendMessage(user);
            log.info("New user was registered: " + chat.getUserName());
    }

    private void someFunctionality(long chatId) {
        String text = "Sorry, command was not recognised";
        sendMessage(text, chatId);
    }

    private ReplyKeyboardMarkup createReplyKeyboard(){
         ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
         List<KeyboardRow> keyboardRows = new ArrayList<>();
         KeyboardRow row = new KeyboardRow();
         row.add("/start");
         row.add("/help");
         keyboardRows.add(row);
         row = new KeyboardRow();
         row.add("/createorder");
         row.add("/option1");
         row.add("/deletedata");
         keyboardRows.add(row);
         replyKeyboardMarkup.setKeyboard(keyboardRows);
         return replyKeyboardMarkup;
    }
}
