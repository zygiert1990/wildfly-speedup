package org.zygiert.wildflyspeedup.controller;

public class UserDTO {

    public UserDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
