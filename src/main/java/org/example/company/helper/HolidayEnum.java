package org.example.company.helper;

public enum HolidayEnum {
    COMMA(","),
    PIPELINE("|"),
    EMPTY_STR(""),
    DATE_FORMAT("dd-MMM-yyyy"),
    DATE_OTHER_FORMAT("MMMM dd, yyyy"),
    DATE_REGEX("[0-9]+-[aA-zZ]{3}-[0-9]{4}"),
    DATE_OTHER_REGEX("\\w+\\s\\d+.+\\d{4}"),
    MANDATE_BANG_HOLIDAYS_LOG("mandatory_bangalore_holidays.log"),
    FLOAT_BANG_HOLIDAYS_LOG("floating_bangalore_holidays.log"),
    HOLIDAY_BANGALORE_LOG("holidays_bangalore.log"),
    HOLIDAY_ALL_LOCATIONS_LOG("holidays_all_locations.log"),
    MANDATE_HOLIDAY_MSG("Mandatory holiday"),
    FLOAT_HOLIDAY_MSG("Floating holiday");
    private String val;

    HolidayEnum(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }
}
