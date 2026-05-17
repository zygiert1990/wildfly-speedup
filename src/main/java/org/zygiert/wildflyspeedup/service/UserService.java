package org.zygiert.wildflyspeedup.service;

import org.zygiert.wildflyspeedup.controller.UserDTO;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findById(String id);

    Collection<UserDTO> findAll();

    UserDTO save(UserDTO user);

}
