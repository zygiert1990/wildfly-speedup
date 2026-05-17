package org.zygiert.wildflyspeedup.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.zygiert.wildflyspeedup.controller.UserDTO;
import org.zygiert.wildflyspeedup.persistence.UserEntity;
import org.zygiert.wildflyspeedup.persistence.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class DefaultUserService implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> findById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .map(this::toDTO);
    }

    @Override
    public Collection<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO user) {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setName(user.getName());
        return toDTO(userRepository.save(newUserEntity));
    }

    private UserDTO toDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getId().toString(), userEntity.getName());
    }
}
