package org.example.company;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;

import static org.example.company.helper.LeavePattern.*;
import static org.example.company.helper.LeaveRegExp.HOLIDAY_ALL_LOCATIONS_LOG;

@RunWith(MockitoJUnitRunner.class)
public class HolidayTest {

    private List<String> holidayList;

    @Before
    public void setUp() throws Exception {
        holidayList = Files.readAllLines(Paths.get(HOLIDAY_ALL_LOCATIONS_LOG.value())).stream().toList();
    }

    @Test
    public void extractHolidayEvent() {
        holidayList.forEach(str -> {
            Matcher matcher = holidayEventPattern.value().matcher(str);
            System.out.println("\n--------------------------------------------------");
            if (matcher.find()) {
                System.out.printf("%n Found Holiday Event : %n %s", matcher.group());
            } else {
                System.out.printf("%n Not found any event");
            }
        });
    }

    @Test
    public void extractCities() {
        holidayList.forEach(str -> {
            Matcher matcher = holidayCitiesPattern.value().matcher(str);
            System.out.println("\n--------------------------------------------------");
            if (matcher.find()) {
                System.out.printf("%n Found Cities : %n %s", matcher.group());
            } else {
                System.out.printf("%n Not found any cities");
            }
        });
    }

    @Test
    public void extractWeekDay() {
        holidayList.forEach(str -> {
            Matcher matcher = weekDayPattern.value().matcher(str.toUpperCase());
            System.out.println("\n--------------------------------------------------");
            if (matcher.find()) {
                System.out.printf("%n Found Week day is : %s", matcher.group());
            } else {
                System.out.printf("%n Week day not found from givens string");
            }
        });
    }

    @Test
    public void extractDate() {
        holidayList.forEach(str -> {
            Matcher matcher = datePattern.value().matcher(str);
            System.out.println("\n--------------------------------------------------");
            if (matcher.find()) {
                System.out.printf("%n Extracted Date : %s", matcher.group());
            } else {
                System.out.printf("%n Date not found");
            }
        });
    }
}