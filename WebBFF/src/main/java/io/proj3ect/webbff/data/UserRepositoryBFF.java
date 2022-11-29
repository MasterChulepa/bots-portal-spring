package io.proj3ect.webbff.data;

import io.proj3ect.webbff.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryBFF extends CrudRepository<UserModel, Long> {
}
