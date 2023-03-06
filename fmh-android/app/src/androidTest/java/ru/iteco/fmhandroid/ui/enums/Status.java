package ru.iteco.fmhandroid.ui.enums;

public enum Status {
    OPEN ("Open"),
    IN_PROGRESS("In progress"),
    CANCELED("Canceled"),
    EXECUTED("Executed");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
