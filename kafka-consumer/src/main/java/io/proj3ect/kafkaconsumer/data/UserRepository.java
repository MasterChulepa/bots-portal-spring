package io.proj3ect.kafkaconsumer.data;

import io.proj3ect.kafkaconsumer.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
}
