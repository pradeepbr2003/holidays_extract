package org.example.company.holiday1;

import java.util.Date;
import java.util.Map;

import static org.example.company.helper.HolidayEnum.*;
import static org.example.company.holiday1.IAccentureHolidaysFromBangalore.extractHolidays;
import static org.example.company.holiday1.IAccentureHolidaysFromBangalore.printHolidays;


public class AccentureHolidaysForBangalore {

    public static void main(String[] args) {
        Map<Date, String> holidayMap = extractHolidays(MANDATE_BANG_HOLIDAYS_LOG.value());
        printHolidays(holidayMap, MANDATE_HOLIDAY_MSG.value());

        holidayMap = extractHolidays(FLOAT_BANG_HOLIDAYS_LOG.value());
        printHolidays(holidayMap, FLOAT_HOLIDAY_MSG.value());

    }

}