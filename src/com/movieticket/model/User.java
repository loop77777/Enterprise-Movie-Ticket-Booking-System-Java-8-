package com.movieticket.model;

import com.movieticket.enums.UserRole;

public abstract class User {
    protected final String id;
    protected final String name;
    protected final String email;
    protected final String phone;
    protected final String passwordHash;
    protected final UserRole role;

// # Method: User
// # Purpose: Executes the User workflow for this class.
    public User(String id, String name, String email, String phone, String passwordHash, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
    }

// # Method: getId
// # Purpose: Executes the getId workflow for this class.
    public String getId() { return id; }
// # Method: getName
// # Purpose: Executes the getName workflow for this class.
    public String getName() { return name; }
// # Method: getEmail
// # Purpose: Executes the getEmail workflow for this class.
    public String getEmail() { return email; }
// # Method: getPhone
// # Purpose: Executes the getPhone workflow for this class.
    public String getPhone() { return phone; }
// # Method: getRole
// # Purpose: Executes the getRole workflow for this class.
    public UserRole getRole() { return role; }

// # Method: validatePassword
// # Purpose: Executes the validatePassword workflow for this class.
    public boolean validatePassword(String password) {
        return this.passwordHash.equals(password);
    }
}