package com.test.aop.config;

public enum Role {
    GUEST,
    USER,
    ADMIN;

    public String getRole() {
        return this.toString();
    }
}
