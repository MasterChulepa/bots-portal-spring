package io.proj3ect.kafkaconsumer.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.proj3ect.kafkaconsumer.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class UserDeserializer implements Deserializer {
    ObjectMapper objectMapper;

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try{
            return objectMapper.readValue(data, UserModel.class);
        } catch (IOException e) {
            log.error("Deserialization error occurred: " + e.getMessage());
            throw new RuntimeException();
        }

    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
