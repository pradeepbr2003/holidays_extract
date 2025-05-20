package org.example.company.holiday3;

import java.util.Date;
import java.util.Map;

import static org.example.company.helper.HolidayEnum.HOLIDAY_ALL_LOCATIONS_LOG;
import static org.example.company.helper.HolidayEnum.MANDATE_HOLIDAY_MSG;
import static org.example.company.holiday3.ITestAccentureHolidays.extractHolidays;
import static org.example.company.holiday3.ITestAccentureHolidays.printHolidays;

public class TestAccentureHolidays {

    public static void main(String[] args) {
        Map<Date, String> holidayMap = extractHolidays(HOLIDAY_ALL_LOCATIONS_LOG.value());
        printHolidays(holidayMap, MANDATE_HOLIDAY_MSG.value());
    }
}
