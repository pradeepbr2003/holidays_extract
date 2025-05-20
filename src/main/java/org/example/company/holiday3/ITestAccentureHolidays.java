package org.example.company.holiday3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.example.company.helper.CommonEnum.EMPTY_STR;
import static org.example.company.helper.DateEnum.DATE_OTHER_FORMAT;
import static org.example.company.helper.DateEnum.DATE_OTHER_REGEX;

public interface ITestAccentureHolidays {

    static Map<Date, String> extractHolidays(String holidayLog) {
        Map<Date, String> holidayMap = new HashMap<>();
        try {
            List<String> leaves = Files.readAllLines(Paths.get(holidayLog));
            leaves.forEach(leave -> {
                Date date = extractDate(leave);
                holidayMap.put(date, leave.replaceAll(DATE_OTHER_REGEX.value(), EMPTY_STR.value()));
            });
        } catch (IOException e) {
            System.out.printf("Exception occurred during extract holidays : %s%n", e.getMessage());
        }
        return holidayMap;
    }

    static void printHolidays(Map<Date, String> holidayMap, String holidayType) {
        System.out.printf("%n %s", holidayType);
        Map<Integer, List<Map.Entry<Date, String>>> holidays = holidayMap.entrySet().stream()
                .filter(ITestAccentureHolidays::isWeekDay)
                .collect(Collectors.groupingBy(e -> e.getKey().getMonth()));
        holidays.forEach((k, v) -> {
            System.out.printf("%n-------------------------------------------");
            System.out.printf("%n%s %n", Month.of(k + 1));
            System.out.printf("-------------------------------------------%n");
            v.stream().sorted(Comparator.comparingInt(key -> key.getKey().getDate())).forEach(entry -> {
                System.out.printf("%s : %s%n", entry.getKey().getDate(), entry.getValue());
            });
        });
    }

    static Date extractDate(String str) {
        try {
            Matcher matcher = DATE_OTHER_REGEX.pattern().matcher(str);
            if (matcher.find()) return DATE_OTHER_FORMAT.formatter().parse(matcher.group());
        } catch (Exception e) {
            String errorMessage = String.format("%n Exception occurred parsing date : %s", e.getMessage());
            System.out.printf(errorMessage);
        }
        return null;
    }

    static boolean isWeekDay(Map.Entry<Date, String> e) {
        return !(e.getValue().toUpperCase().contains(DayOfWeek.SATURDAY.name())
                || e.getValue().toUpperCase().contains(DayOfWeek.SUNDAY.name()));
    }

}
