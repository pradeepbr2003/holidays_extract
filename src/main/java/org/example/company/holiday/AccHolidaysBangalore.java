package org.example.company.holiday;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.example.company.helper.HolidayEnum.*;
import static org.example.company.holiday.IAccHolidaysBangalore.extractHolidays;
import static org.example.company.holiday.IPrintHolidays.printHolidays;

public class AccHolidaysBangalore {

    public static void main(String[] args) {
        try {
            List<String> leaves = Files.readAllLines(Paths.get(HOLIDAY_BANGALORE_LOG.value()));
            Map<Date, String> holidayMap = extractHolidays(leaves);
            printHolidays(holidayMap, MANDATE_HOLIDAY_MSG.value());
            printHolidays(holidayMap, FLOAT_HOLIDAY_MSG.value());
        } catch (IOException e) {
            System.out.printf("%n Error when reading holiday file : %s %n", e.getMessage());
        }

    }
}
