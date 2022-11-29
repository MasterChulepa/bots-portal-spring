package io.proj3ect.data;

import io.proj3ect.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryBFF extends CrudRepository<UserModel, Long> {
}
