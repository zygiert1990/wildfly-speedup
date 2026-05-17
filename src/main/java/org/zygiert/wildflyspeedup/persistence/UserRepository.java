package org.zygiert.wildflyspeedup.persistence;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<UserEntity> findById(UUID id);

    Collection<UserEntity> findAll();

    UserEntity save(UserEntity user);

}
