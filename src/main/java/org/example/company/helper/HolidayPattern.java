package org.example.company.helper;

import java.util.regex.Pattern;

import static org.example.company.helper.LeaveRegExp.*;

public enum HolidayPattern {
    datePattern(Pattern.compile(DATE_REGEX.value())),
    weekDayPattern(Pattern.compile(WEEK_DAY_REG_EX.value())),
    holidayEventPattern(Pattern.compile(HOLIDAY_EVENT_REG_EX.value())),
    holidayCitiesPattern(Pattern.compile(HOLIDAY_CITIES_REG_EX.value()));

    private final Pattern pattern;

    HolidayPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern value() {
        return this.pattern;
    }
}
