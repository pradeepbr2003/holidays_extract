package org.example.company.holiday;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface IPrintHolidays {

    static void printHolidays(Map<Date, String> holidayMap, String holidayType) {
        System.out.printf("%n %s", holidayType);
        Map<Integer, List<Map.Entry<Date, String>>> holidays = holidayMap.entrySet().stream()
                .filter(e -> isWeekDayAndType(holidayType, e))
                .collect(Collectors.groupingBy(e -> e.getKey().getMonth()));

        holidays.forEach(IPrintHolidays::printHolidays);
    }

    static void printHolidays(Integer k, List<Map.Entry<Date, String>> v) {
        System.out.printf("%n-------------------------------------------");
        System.out.printf("%n%s %n", Month.of(k + 1));
        System.out.printf("-------------------------------------------%n");
        v.stream().sorted(Comparator.comparingInt(key -> key.getKey().getDate())).forEach(entry -> {
            System.out.printf("%s : %s%n", entry.getKey().getDate(), entry.getValue());
        });
    }

    static boolean isWeekDayAndType(String holidayType, Map.Entry<Date, String> e) {
        return e.getValue().contains(holidayType) && !(e.getValue().toUpperCase().contains(DayOfWeek.SATURDAY.name()) || e.getValue().toUpperCase().contains(DayOfWeek.SUNDAY.name()));
    }

}
