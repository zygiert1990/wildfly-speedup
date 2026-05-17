package org.zygiert.wildflyspeedup.controller;

public class UserDTO {

    public UserDTO() {
    }

    public UserDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", name=" + name + '}';
    }
}
