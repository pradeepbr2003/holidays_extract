package org.example.company.holiday2;

import java.util.Date;
import java.util.Map;

import static org.example.company.helper.HolidayEnum.*;
import static org.example.company.holiday2.IAccHolidaysBangalore.extractHolidays;
import static org.example.company.holiday2.IAccHolidaysBangalore.printHolidays;

public class AccHolidaysBangalore {

    public static void main(String[] args) {
        Map<Date, String> holidayMap = extractHolidays(HOLIDAY_BANGALORE_LOG.value());
        printHolidays(holidayMap, MANDATE_HOLIDAY_MSG.value());
        printHolidays(holidayMap, FLOAT_HOLIDAY_MSG.value());
    }
}
