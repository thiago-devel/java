package br.com.rubyit.resseler.core.enums;

public enum ErrorType {
    SYSTEM, BUSINESS, VALIDATION;

    public String value() {
        return name();
    }

    public static ErrorType fromValue(String v) {
        return valueOf(v);
    }
}
