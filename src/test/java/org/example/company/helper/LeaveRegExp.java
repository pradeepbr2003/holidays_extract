package org.example.company.helper;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum LeaveRegExp {
    DATE_REGEX("\\w+\\s\\d+.+\\d{4}"),
    HOLIDAY_EVENT_REG_EX("\\t\\w+\\s\\w+"),
    HOLIDAY_CITIES_REG_EX("\\(.+\\)"),
    WEEK_DAY_REG_EX(Arrays.stream(DayOfWeek.values()).map(DayOfWeek::name).collect(Collectors.joining("|"))),
    HOLIDAY_ALL_LOCATIONS_LOG("holidays_all_locations.log");

    private final String value;

    LeaveRegExp(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
