package com.genka.domain.enums;

public enum UserRole {
    ADMIN(1, "ROLE_ADMIN"),
    CUSTOMER(2, "ROLE_CUSTOMER");

    private int value;
    private String description;

    UserRole(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static UserRole toEnum(Integer value) {
        if (value == null) {
            return null;
        }

        for (UserRole userRole : UserRole.values()) {
            if (value.equals(userRole.getValue())) {
                return userRole;
            }
        }

        throw new IllegalArgumentException("Invalid user role: " + value);
    }
}
