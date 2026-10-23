package com.movieticket.model;

import com.movieticket.enums.UserRole;
import java.util.Arrays;
import java.util.List;

public class Admin extends User {
    private final List<String> permissions;

    public Admin(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password, UserRole.ADMIN);
        this.permissions = Arrays.asList("MANAGE_MOVIES", "MANAGE_SHOWS", "VIEW_REPORTS");
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}