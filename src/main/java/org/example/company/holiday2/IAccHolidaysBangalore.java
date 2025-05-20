package org.example.company.holiday2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.example.company.helper.CommonEnum.EMPTY_STR;
import static org.example.company.helper.DateEnum.DATE_FORMAT;
import static org.example.company.helper.DateEnum.DATE_REGEX;

public interface IAccHolidaysBangalore {

    static void printHolidays(Map<Date, String> holidayMap, String holidayType) {
        System.out.printf("%n %s", holidayType);
        Map<Integer, List<Map.Entry<Date, String>>> holidays = holidayMap.entrySet().stream()
                .filter(e -> isWeekDayAndType(holidayType, e)).collect(Collectors.groupingBy(e -> e.getKey().getMonth()));

        holidays.forEach((k, v) -> {
            System.out.printf("%n-------------------------------------------");
            System.out.printf("%n%s %n", Month.of(k + 1));
            System.out.printf("-------------------------------------------%n");
            v.stream().sorted(Comparator.comparingInt(key -> key.getKey().getDate())).forEach(entry -> {
                System.out.printf("%s : %s%n", entry.getKey().getDate(), entry.getValue());
            });
        });
    }

    static boolean isWeekDayAndType(String holidayType, Map.Entry<Date, String> e) {
        return e.getValue().contains(holidayType) && !(e.getValue().toUpperCase().contains(DayOfWeek.SATURDAY.name()) || e.getValue().toUpperCase().contains(DayOfWeek.SUNDAY.name()));
    }


    static Map<Date, String> extractHolidays(String holidayLog) {
        Map<Date, String> holidayMap = new HashMap<>();
        try {
            List<String> leaves = Files.readAllLines(Paths.get(holidayLog));
            leaves.forEach(leave -> {
                Date date = extractDate(leave);
                holidayMap.put(date, leave.replaceAll(DATE_REGEX.value(), EMPTY_STR.value()));
            });
        } catch (IOException e) {
            System.out.printf("Exception occurred during extract holidays : %s%n", e.getMessage());
        }
        return holidayMap;
    }

    public static Date extractDate(String str) {
        Matcher matcher = DATE_REGEX.pattern().matcher(str);
        if (matcher.find()) {
            String group = matcher.group();
            try {
                return DATE_FORMAT.formatter().parse(group);
            } catch (ParseException e) {
                System.out.printf("Exception occurred : %s", e.getMessage());
            }
        }
        throw new RuntimeException("Date Not found");
    }

}
