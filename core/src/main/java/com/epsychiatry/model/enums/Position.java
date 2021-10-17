package com.epsychiatry.model.enums;

public enum Position {
    SE("Software Engineer"),
    ASE("Associate Software Engineer"),
    COORDINATOR("Coordinator"),
    MANAGER("Manger"),
    S_MANAGER("Senior Manager"),
    MARKETER("Marketer"),
    CLINICIAN("Clinician");


    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
