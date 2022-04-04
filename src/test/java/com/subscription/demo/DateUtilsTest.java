package com.subscription.demo;

import com.subscription.demo.common.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Yen Han Sern
 * Created on: 3:37 pm, 04/04/2022
 */
@RunWith(MockitoJUnitRunner.class)
public class DateUtilsTest {

    // test data
    private LocalDate dailyStartDate;
    private LocalDate dailyEndDate;
    private List<LocalDate> expectedDailyDates;

    private LocalDate weeklyStartDate;
    private LocalDate weeklyEndDate;
    private List<LocalDate> expectedWeeklyDates;

    private LocalDate monthlyStartDate;
    private LocalDate monthlyEndDate;
    private List<LocalDate> expectedMonthlyDates;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.format);

    @Before
    public void startUp() {
        // setup daily recurring date expected results
        dailyRecurringTestDataInit();

        // setup weekly recurring date expected results
        weeklyRecurringTestDataInit();

        // setup monthly recurring date expected results
        monthlyRecurringTestDataInit();
    }

    @Test
    public void dailyRecurringDatesTest() {
        List<LocalDate> dailyRecurringDates = DateUtils.getDailyRecurringDates(dailyStartDate, dailyEndDate);
        System.out.println("result: " + dailyRecurringDates);
        System.out.println("expected result: "+ expectedDailyDates);

        Assert.assertEquals(expectedDailyDates.size(), dailyRecurringDates.size());
        Assert.assertTrue(dailyRecurringDates.containsAll(expectedDailyDates));
    }

    @Test
    public void weeklyRecurringDatesTest() {
        List<LocalDate> weeklyRecurringDates = DateUtils.getWeeklyRecurringDates(weeklyStartDate, weeklyEndDate);
        System.out.println("result: " + weeklyRecurringDates);
        System.out.println("expected result: "+ expectedWeeklyDates);

        Assert.assertEquals(expectedWeeklyDates.size(), weeklyRecurringDates.size());
        Assert.assertTrue(weeklyRecurringDates.containsAll(expectedWeeklyDates));
    }

    @Test
    public void monthlyRecurringDatesTest() {
        List<LocalDate> monthlyRecurringDates = DateUtils.getMonthlyRecurringDates(monthlyStartDate, monthlyEndDate);
        System.out.println("result: " + monthlyRecurringDates);
        System.out.println("expected result: "+ expectedMonthlyDates);

        Assert.assertEquals(expectedMonthlyDates.size(), monthlyRecurringDates.size());
        Assert.assertTrue(monthlyRecurringDates.containsAll(expectedMonthlyDates));
    }

    @Test
    public void convertDateToStringListTest() {
        LocalDate date1 = LocalDate.of(2022, 5, 1);
        LocalDate date2 = LocalDate.of(2022, 9, 30);
        LocalDate date3 = LocalDate.of(2022, 11, 9);

        List<LocalDate> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);

        List<String> dateStrings = DateUtils.convertDateToStringInList(dates, DateUtils.format);
        List<String> expectedResult = generateExpectedDateStringList();

        System.out.println("result: " + dateStrings);
        System.out.println("expected result: "+ expectedResult);

        Assert.assertEquals(dateStrings.size(), expectedResult.size());
        Assert.assertTrue(dateStrings.containsAll(expectedResult));
    }

    private List<String> generateExpectedDateStringList() {
        return Stream.of("01/05/2022", "30/09/2022", "09/11/2022").collect(Collectors.toList());
    }

    private void dailyRecurringTestDataInit() {
        String dailyStartDateStr = "01/01/2022";
        String dailyEndDateStr = "05/01/2022";

        dailyStartDate = LocalDate.parse(dailyStartDateStr, formatter);
        dailyEndDate = LocalDate.parse(dailyEndDateStr, formatter);
        expectedDailyDates = generateExpectedRecurringDates(generateDailyRecurringDateStrings(), formatter);
    }

    private void weeklyRecurringTestDataInit() {
        String weeklyStartDateStr = "06/02/2022";
        String weeklyEndDateStr = "27/02/2022";

        weeklyStartDate = LocalDate.parse(weeklyStartDateStr, formatter);
        weeklyEndDate = LocalDate.parse(weeklyEndDateStr, formatter);
        expectedWeeklyDates = generateExpectedRecurringDates(generateWeeklyRecurringDateStrings(), formatter);
    }

    private void monthlyRecurringTestDataInit() {
        String monthlyStartDateStr = "01/01/2022";
        String monthlyEndDateStr = "07/06/2022";

        monthlyStartDate = LocalDate.parse(monthlyStartDateStr, formatter);
        monthlyEndDate = LocalDate.parse(monthlyEndDateStr, formatter);
        expectedMonthlyDates = generateExpectedRecurringDates(generateMonthlyRecurringDateStrings(), formatter);
    }

    private List<LocalDate> generateExpectedRecurringDates(List<String> dateStrings, DateTimeFormatter formatter) {
        List<LocalDate> dates = new ArrayList<>();

        for(String dateStr: dateStrings) {
            dates.add(LocalDate.parse(dateStr, formatter));
        }

        return dates;
    }

    private List<String> generateDailyRecurringDateStrings() {
        return Stream.of("01/01/2022", "02/01/2022", "03/01/2022", "04/01/2022", "05/01/2022")
                .collect(Collectors.toList());
    }

    private List<String> generateWeeklyRecurringDateStrings() {
        return Stream.of("06/02/2022", "13/02/2022", "20/02/2022", "27/02/2022")
                .collect(Collectors.toList());
    }

    private List<String> generateMonthlyRecurringDateStrings() {
        return Stream.of("01/01/2022", "01/02/2022", "01/03/2022", "01/04/2022", "01/05/2022", "01/06/2022")
                .collect(Collectors.toList());
    }
}
