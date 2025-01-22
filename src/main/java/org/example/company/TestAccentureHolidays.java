package org.example.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.example.company.helper.HolidayEnum.*;

public class TestAccentureHolidays {
    private static final Pattern pattern = Pattern.compile(DATE_OTHER_REGEX.value());
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_OTHER_FORMAT.value());

    public static void main(String[] args) {
        Map<Date, String> holidayMap = extractHolidays(HOLIDAY_ALL_LOCATIONS_LOG.value());
        printHolidays(holidayMap, MANDATE_HOLIDAY_MSG.value());
    }

    private static Map<Date, String> extractHolidays(String holidayLog) {
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

    private static void printHolidays(Map<Date, String> holidayMap, String holidayType) {
        System.out.printf("%n %s", holidayType);
        Map<Integer, List<Map.Entry<Date, String>>> holidays = holidayMap.entrySet().stream()
                .filter(TestAccentureHolidays::isWeekDay)
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

    private static Date extractDate(String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String dateStr = matcher.group();
            try {
                Date date = sdf.parse(dateStr);
                return date;
            } catch (ParseException e) {
                System.out.printf("%n Exception occurred parsing date : %s", e.getMessage());
            }
        }
        throw new RuntimeException("Date not found");
    }

    private static boolean isWeekDay(Map.Entry<Date, String> e) {
        return !(e.getValue().toUpperCase().contains(DayOfWeek.SATURDAY.name())
                || e.getValue().toUpperCase().contains(DayOfWeek.SUNDAY.name()));
    }
}
