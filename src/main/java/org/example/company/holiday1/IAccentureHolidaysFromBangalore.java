package org.example.company.holiday1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.company.helper.CommonEnum.COMMA;
import static org.example.company.helper.CommonEnum.PIPELINE;
import static org.example.company.helper.DateEnum.DATE_FORMAT;

public interface IAccentureHolidaysFromBangalore {

    static Map<Date, String> extractHolidays(String holidayLog) {
        Map<Date, String> holidayMap = new HashMap<>();
        try {
            List<String> leaves = Files.readAllLines(Paths.get(holidayLog));
            leaves.forEach(line -> {
                String[] words = line.split(COMMA.value());
                try {
                    int len = words.length - 1;
                    Date date = DATE_FORMAT.formatter().parse(words[len]);
                    StringBuffer buffer = new StringBuffer();
                    while (len > 0) {
                        buffer.append(words[--len]).append(PIPELINE.value());
                    }
                    holidayMap.put(date, buffer.toString());
                } catch (ParseException e) {
                    System.out.printf("%n Exception occurred during parsing: %s", e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.printf("%n Exception occurred extracting date: %s", e.getMessage());
        }
        return holidayMap;
    }


    static void printHolidays(Map<Date, String> holidayMap, String holidayType) {
        System.out.printf("%n %s", holidayType);
        Map<Integer, List<Map.Entry<Date, String>>> holidays = holidayMap.entrySet().stream()
                .filter(e -> isWeekDay(e)).collect(Collectors.groupingBy(e -> e.getKey().getMonth()));

        holidays.forEach((k, v) -> {
            System.out.printf("%n-------------------------------------------");
            System.out.printf("%n%s %n", Month.of(k + 1));
            System.out.printf("-------------------------------------------%n");
            v.stream().sorted(Comparator.comparingInt(key -> key.getKey().getDate())).forEach(entry -> {
                System.out.printf("%s : %s%n", entry.getKey().getDate(), entry.getValue());
            });
        });
    }

    static boolean isWeekDay(Map.Entry<Date, String> e) {
        return !(e.getValue().toUpperCase().contains(DayOfWeek.SATURDAY.name())
                || e.getValue().toUpperCase().contains(DayOfWeek.SUNDAY.name()));
    }
}
