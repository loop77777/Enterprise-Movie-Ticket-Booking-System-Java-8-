package com.movieticket.model;

import com.movieticket.enums.UserRole;
import java.util.Arrays;
import java.util.List;

// # Class: Admin
// # Purpose: Handles admin logic in this module.
public class Admin extends User {
    private final List<String> permissions;

// # Method: Admin
// # Purpose: Executes the Admin workflow for this class.
    public Admin(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password, UserRole.ADMIN);
        this.permissions = Arrays.asList("MANAGE_MOVIES", "MANAGE_SHOWS", "VIEW_REPORTS");
    }

// # Method: hasPermission
// # Purpose: Executes the hasPermission workflow for this class.
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}