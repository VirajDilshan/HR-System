package com.epsychiatry.model.enums;

public enum Department {
    FRONT_OFFICE("Front Office & On-boarding"),
    SOCIAL_MEDIA_MARKETING("Social Media Marketing"),
    MARKETING("Marketing"),
    ACCOUNT_BILLING("Account & Billing"),
    IT("IT"),
    COORDINATION("Coordination"),
    MANAGEMENT("Management");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
