package com.subscription.demo.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Yen Han Sern
 * Created on: 11:22 pm, 03/04/2022
 */
public class DateUtils {

    public static final String format = "dd/MM/yyyy";

    public static List<LocalDate> getDailyRecurringDates(LocalDate startDate, LocalDate endDate) {
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        return Stream
                .iterate(startDate, date -> date.plusDays(1))
                .limit(numOfDays+1)
                .collect(Collectors.toList());
    }

    public static List<LocalDate> getWeeklyRecurringDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        LocalDate currentDate = startDate;

        while(currentDate.isBefore(endDate) &&
                isBeforeOrEqualInDays(7, currentDate, endDate)) {
            currentDate = currentDate.plusDays(7);
            dates.add(currentDate);
        }
        return dates;
    }

    public static List<LocalDate> getMonthlyRecurringDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        LocalDate currentDate = startDate;

        while(currentDate.isBefore(endDate)
                && isBeforeOrEqualInMonths(1, currentDate, endDate)) {
            currentDate = currentDate.plusMonths(1);
            dates.add(currentDate);
        }
        return dates;
    }

    public static List<String> convertDateToStringInList(List<LocalDate> dates, String format) {
        List<String> dateStrList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        dates.forEach(date -> {
            dateStrList.add(date.format(formatter));
        });

        return dateStrList;
    }

    private static boolean isBeforeOrEqualInDays(int dayIntervals, LocalDate currentDate, LocalDate endDate) {
        return currentDate.plusDays(dayIntervals).isBefore(endDate) ||
                currentDate.plusDays(dayIntervals).isEqual(endDate);
    }

    private static boolean isBeforeOrEqualInMonths(int monthIntervals, LocalDate currentDate, LocalDate endDate) {
        return currentDate.plusMonths(monthIntervals).isBefore(endDate) ||
                currentDate.plusMonths(monthIntervals).isEqual(endDate);
    }

}
