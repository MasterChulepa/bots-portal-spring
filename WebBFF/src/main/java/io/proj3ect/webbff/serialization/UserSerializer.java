package io.proj3ect.webbff.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.proj3ect.webbff.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class UserSerializer implements Serializer<UserModel> {
    ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, UserModel data) {
        try {
            if (data == null) {
                log.info("Null received at serializing");
                return null;
            }
            log.info("Serializing: " + data);
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            log.error("Error when serializing MessageDto to byte[]");
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
