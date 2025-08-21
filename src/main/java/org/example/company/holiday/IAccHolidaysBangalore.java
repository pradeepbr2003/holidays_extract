package org.example.company.holiday;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.company.helper.CommonEnum.EMPTY_STR;
import static org.example.company.helper.DateEnum.DATE_REGEX;
import static org.example.company.holiday.IDateExtractor.extractDate;

public interface IAccHolidaysBangalore {
    Map<Date, String> holidayMap = new HashMap<>();

    static Map<Date, String> extractHolidays(List<String> leaves) {
        leaves.forEach(IAccHolidaysBangalore::extractHolidays);
        return holidayMap;
    }

    static void extractHolidays(String leave) {
        holidayMap.put(extractDate(leave), leave.replaceAll(DATE_REGEX.value(), EMPTY_STR.value()));
    }
}
