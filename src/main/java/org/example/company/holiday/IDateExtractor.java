package org.example.company.holiday;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;

import static org.example.company.helper.DateEnum.DATE_FORMAT;
import static org.example.company.helper.DateEnum.DATE_REGEX;

public interface IDateExtractor {

    static Date extractDate(String str) {
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
