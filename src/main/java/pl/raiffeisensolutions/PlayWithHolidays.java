package pl.raiffeisensolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.*;

public class PlayWithHolidays {
    private static final Logger LOGGER = Logger.getLogger(PlayWithHolidays.class.getName());

    private List<String> listString = new ArrayList<>();
    private List<LocalDate> listDate = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    public PlayWithHolidays(String inputFile) {
        //Constructor reads lines from files and parses its content to List<LocalDate>.
        readLines(inputFile);
        parseHolidays();

    }

    public boolean checkHoliday(LocalDate dateToCheck){
        //Function to check if given date is a holiday or is not.
        boolean testResult=false;
        for (LocalDate item : listDate)
            if (item.equals(dateToCheck)) {
                LOGGER.info("Date " + dateToCheck.format(formatter) + " is a holiday");
                    testResult=true;
            }
        if (!testResult){
            LOGGER.info("Date " + dateToCheck.format(formatter) + " is not a holiday");
        }
        return testResult;
    }

    public LocalDate checkNextWorkingDay(LocalDate dateToCheck, int workDays){
        //Function to add workDays to given date.
        //Create copy of dateToCheck in nextWorkingDay.
        LocalDate nextWorkingDay = dateToCheck.plusDays(0);

        for (int i=0;i<workDays;i++) {
            nextWorkingDay = nextWorkingDay.plusDays(1);
            if(nextWorkingDay.getDayOfWeek().equals("SUNDAY"))
                nextWorkingDay = nextWorkingDay.plusDays(1);
            if(nextWorkingDay.getDayOfWeek().equals("SATURDAY"))
                nextWorkingDay = nextWorkingDay.plusDays(1);
            if(listDate.contains(nextWorkingDay))
                nextWorkingDay = nextWorkingDay.plusDays(1);
        }
        return nextWorkingDay;

    }

    public long checkWhenWasHoliday(LocalDate dateToCheck){
        //Function to check when was a holiday.
        List<LocalDate> sortedListDate = new ArrayList<>();
        long numberOfDaysBetweenGivenDayAndPreviousHoliday;
                sortedListDate.addAll(listDate);
        sortedListDate.add(dateToCheck);
        Collections.sort(sortedListDate);

        //Checking if given day is a holiday.
        boolean testResult = checkHoliday(dateToCheck);
        if(testResult==true)
            return 0;

        int indexOfGivenDate = sortedListDate.indexOf(dateToCheck);

        if (indexOfGivenDate==0)
            LOGGER.info("There are not any holidays defined before the given date.");

        numberOfDaysBetweenGivenDayAndPreviousHoliday = ChronoUnit.DAYS.between(sortedListDate.get(indexOfGivenDate-1), sortedListDate.get(indexOfGivenDate));
        LOGGER.info("Last holiday was " + numberOfDaysBetweenGivenDayAndPreviousHoliday + " days before.");
        return numberOfDaysBetweenGivenDayAndPreviousHoliday;
    }

    public long checkWhenWillBeHoliday(LocalDate dateToCheck){
        //Function to check when will be a holiday.
        List<LocalDate> sortedListDate = new ArrayList<LocalDate>();
        long numberOfDaysBetweenGivenDayAndNextHoliday;
        sortedListDate.addAll(listDate);
        sortedListDate.add(dateToCheck);
        Collections.sort(sortedListDate);

        //Checking if given day is a holiday.
        boolean testResult = checkHoliday(dateToCheck);
        if(testResult==true)
            return 0;


        int indexOfGivenDate = sortedListDate.indexOf(dateToCheck);

        if (indexOfGivenDate==(sortedListDate.size()-1))
            LOGGER.info("There are not any holidays defined after the given date.");

        numberOfDaysBetweenGivenDayAndNextHoliday = ChronoUnit.DAYS.between(sortedListDate.get(indexOfGivenDate), sortedListDate.get(indexOfGivenDate+1));
        LOGGER.info("Next holiday is going to be in " + numberOfDaysBetweenGivenDayAndNextHoliday + " days.");
        return numberOfDaysBetweenGivenDayAndNextHoliday;
    }


    private void parseHolidays() {
        // Parsing dates to List of Dates

        for (String item : listString) {
        try {
            parseDates(item);

        }catch (DateTimeParseException e) {
            LOGGER.info("There is unparseable date in the file using yyyy-MM-dd format");
            System.exit(2);
        }
        }


    }

    private void parseDates(String item) throws DateTimeParseException {
        LocalDate dateStr = LocalDate.parse(item, formatter);
        listDate.add(dateStr);
    }

    private void readLines(String inputFile) {
        // Reading lines with dates from file to List<String> object.

        try {
            Scanner scanner = getScanner(inputFile);
            getLines(listString, scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        checkEmptyFile(listString);


    }

    private void checkEmptyFile(List<String> listString) {
        if(listString.size() < 1)
            throw new EmptyStackException();
    }

    private void getLines(List<String> listString, Scanner scanner) {
        while(scanner.hasNextLine()){
            listString.add(scanner.nextLine());
        }
    }

    private Scanner getScanner(String inputFile) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(inputFile);
        return new Scanner(fis);
    }



}
