package com.epsychiatry.model.enums;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
