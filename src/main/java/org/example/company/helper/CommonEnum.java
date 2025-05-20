package org.example.company.helper;

public enum CommonEnum {
    COMMA(","), PIPELINE("|"), EMPTY_STR("");
    private String val;

    CommonEnum(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }
}
