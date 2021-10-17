package com.epsychiatry.model.enums;

public enum Os {
    LINUX("Linux"),
    WINDOWS10("Windows 10");

    private final String displayName;

    Os(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
