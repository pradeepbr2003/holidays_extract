package org.example.company.helper;

public enum HolidayEnum {
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
