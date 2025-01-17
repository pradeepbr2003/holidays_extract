package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;

import static org.example.company.helper.HolidayPattern.*;
import static org.example.company.helper.LeaveRegExp.HOLIDAY_ALL_LOCATIONS_LOG;

public class TestRegExpression {
    public static void main(String[] args) {
        try {
            Files.readAllLines(Paths.get(HOLIDAY_ALL_LOCATIONS_LOG.value()))
                    .forEach(TestRegExpression::performRegExpression);
        } catch (IOException e) {
            System.err.printf("%n Exception reading holidays log file : %s", e.getMessage());
        }
    }

    private static void performRegExpression(String str) {
        System.out.printf("%n Input string :\t%s", str);
        extractDate(str);
        extractWeekDay(str);
        extractCities(str);
        extractHolidayEvent(str);
    }

    private static void extractHolidayEvent(String str) {
        Matcher matcher = holidayEventPattern.value().matcher(str);
        System.out.println("\n--------------------------------------------------");
        if (matcher.find()) {
            System.out.printf("%n Found Holiday Event : %n %s", matcher.group());
        } else {
            System.out.printf("%n Not found any event");
        }
    }

    private static void extractCities(String str) {
        Matcher matcher = holidayCitiesPattern.value().matcher(str);
        System.out.println("\n--------------------------------------------------");
        if (matcher.find()) {
            System.out.printf("%n Found Cities : %n %s", matcher.group());
        } else {
            System.out.printf("%n Not found any cities");
        }
    }

    private static void extractWeekDay(String str) {
        Matcher matcher = weekDayPattern.value().matcher(str.toUpperCase());
        System.out.println("\n--------------------------------------------------");
        if (matcher.find()) {
            System.out.printf("%n Found Week day is : %s", matcher.group());
        } else {
            System.out.printf("%n Week day not found from givens string");
        }
    }

    private static void extractDate(String str) {
        Matcher matcher = datePattern.value().matcher(str);
        System.out.println("\n--------------------------------------------------");
        if (matcher.find()) {
            System.out.printf("%n Extracted Date : %s", matcher.group());
        } else {
            System.out.printf("%n Date not found");
        }
    }
}
