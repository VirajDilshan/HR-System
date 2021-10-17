package com.epsychiatry.model.enums;

public enum Country {
    AUSTRALIA("Australia"),
    SRI_LANKA("Sri Lanka"),
    INDIA("India");

    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
