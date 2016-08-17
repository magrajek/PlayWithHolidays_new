package pl.raiffeisensolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.*;

import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;

public class PlayWithHolidays {
    private static final Logger LOGGER = Logger.getLogger(PlayWithHolidays.class.getName());
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");


    public boolean checkHoliday(LocalDate dateToCheck, List<LocalDate> listDate){
        //Function to check if given date is a holiday or is not.
        boolean testResult=false;
        for (LocalDate item : listDate)
            if (item.equals(dateToCheck)) {
           //     LOGGER.info("Date " + dateToCheck.format(formatter) + " is a holiday");
                    testResult=true;
            }
        if (!testResult){
            LOGGER.info("Date " + dateToCheck.format(formatter) + " is not a holiday");
        }
        return testResult;
    }

    public LocalDate checkNextWorkingDay(LocalDate dateToCheck, List<LocalDate> listDate, int workDays){
        //Function to add workDays to given date.
        //Create copy of dateToCheck in nextWorkingDay.
        LocalDate nextWorkingDay = dateToCheck.plusDays(0);

        for (int i=0;i<workDays;i++) {
            nextWorkingDay = nextWorkingDay.plusDays(1);
            if(nextWorkingDay.getDayOfWeek().name().equals("SATURDAY"))
                nextWorkingDay = nextWorkingDay.plusDays(1);
            if(nextWorkingDay.getDayOfWeek().name().equals("SUNDAY"))
                nextWorkingDay = nextWorkingDay.plusDays(1);
            if(listDate.contains(nextWorkingDay)) {
                nextWorkingDay = nextWorkingDay.plusDays(1);
            }    }
        return nextWorkingDay;

    }

    public long checkWhenWasHoliday(LocalDate dateToCheck, List<LocalDate> listDate){
        //Function to check when was a holiday.
        List<LocalDate> sortedListDate = new ArrayList<>();
        long numberOfDaysBetweenGivenDayAndPreviousHoliday = 0;
        sortedListDate.addAll(listDate);
        sortedListDate.add(dateToCheck);
        Collections.sort(sortedListDate);
        //Checking if given day is a holiday.
        boolean testResult = checkHoliday(dateToCheck, listDate);
        if(testResult)
            return 0;

        int indexOfGivenDate = sortedListDate.indexOf(dateToCheck);

        if (indexOfGivenDate==0)
            LOGGER.info("There are not any holidays defined before the given date.");
        else {
            numberOfDaysBetweenGivenDayAndPreviousHoliday = ChronoUnit.DAYS.between(sortedListDate.get(indexOfGivenDate-1), sortedListDate.get(indexOfGivenDate));
            LOGGER.info("Last holiday was " + numberOfDaysBetweenGivenDayAndPreviousHoliday + " days before.");
        }
        return numberOfDaysBetweenGivenDayAndPreviousHoliday;
    }

    public long checkWhenWillBeHoliday(LocalDate dateToCheck, List<LocalDate> listDate){
        //Function to check when will be a holiday.
        List<LocalDate> sortedListDate = new ArrayList<>();
        long numberOfDaysBetweenGivenDayAndNextHoliday = 0;
        sortedListDate.addAll(listDate);
        sortedListDate.add(dateToCheck);
        Collections.sort(sortedListDate);

        //Checking if given day is a holiday.
        boolean testResult = checkHoliday(dateToCheck, listDate);
        if(testResult)
            return 0;


        int indexOfGivenDate = sortedListDate.indexOf(dateToCheck);

        if (indexOfGivenDate==(sortedListDate.size()-1))
            LOGGER.info("There are not any holidays defined after the given date.");
        else {
            numberOfDaysBetweenGivenDayAndNextHoliday = ChronoUnit.DAYS.between(sortedListDate.get(indexOfGivenDate), sortedListDate.get(indexOfGivenDate + 1));
            LOGGER.info("Next holiday is going to be in " + numberOfDaysBetweenGivenDayAndNextHoliday + " days.");
        }
        return numberOfDaysBetweenGivenDayAndNextHoliday;
    }






}
