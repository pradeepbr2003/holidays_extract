package org.example.company.helper;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public enum DateEnum {
    DATE_FORMAT("dd-MMM-yyyy"),
    DATE_OTHER_FORMAT("MMMM dd, yyyy"),
    DATE_REGEX("[0-9]+-[aA-zZ]{3}-[0-9]{4}"),
    DATE_OTHER_REGEX("\\w+\\s\\d+.+\\d{4}");

    private String val;
    private SimpleDateFormat sdf;
    private Pattern pattern;

    DateEnum(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }

    public SimpleDateFormat formatter() {
        if (this.sdf == null) {
            this.sdf = new SimpleDateFormat(value());
        }
        return this.sdf;
    }

    public Pattern pattern() {
        if (this.pattern == null) {
            this.pattern = Pattern.compile(this.value());
        }
        return this.pattern;
    }
}
