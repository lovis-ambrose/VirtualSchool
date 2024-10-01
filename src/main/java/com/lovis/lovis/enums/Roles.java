package com.lovis.lovis.enums;

public enum Roles {
    SUPER_ADMIN("super_admin"),
    ADMIN("admin"),
    TEACHER("teacher"),
    STUDENT("student"),;

    private final String role;
    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
