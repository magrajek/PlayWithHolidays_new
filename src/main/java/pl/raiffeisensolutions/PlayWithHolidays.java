package pl.raiffeisensolutions;


import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PlayWithHolidays {

    private List<String> listString = new ArrayList<String>();
    private List<LocalDate> listDate = new ArrayList<LocalDate>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    public PlayWithHolidays(String inputFile, int i, LocalDate dateToCheck) {

        if(i==5)
            i=choosingAction();

        if (dateToCheck.equals(LocalDate.parse("1900-01-01",formatter)))
            dateToCheck=enteringDateToCheck();

        readLines(inputFile);
        parseHolidays();

        if (i == 0) {
            System.out.println("You have not done any choice");
            System.exit(0);
        }

        if (i == 1) {
            System.out.println("You have chosen checking date " + dateToCheck.format(formatter) + " if it is a holiday or not.");
            checkHoliday(dateToCheck);
        }

        if (i == 2) {
            System.out.println("You have chosen calculating when was a holiday");
            checkWhenWasHoliday(dateToCheck);
        }

        if (i == 3) {
            System.out.println("You have chosen calculating when will be a holiday");
            checkWhenWillBeHoliday(dateToCheck);
        }


    }

    public int checkHoliday(LocalDate dateToCheck){
        int i=0;

        for (LocalDate item : listDate)
            if (item.equals(dateToCheck)) {
                    System.out.println("Date " + dateToCheck.format(formatter) + " is a holiday");
                    i=1;
            }
        if (i==0){
            System.out.println("Date " + dateToCheck.format(formatter) + " is not a holiday");
        }

        return i;
    }


    public long checkWhenWasHoliday(LocalDate dateToCheck){

        List<LocalDate> sortedListDate = new ArrayList<LocalDate>();
        sortedListDate.addAll(listDate);
        sortedListDate.add(dateToCheck);
        Collections.sort(sortedListDate);

        int i = checkHoliday(dateToCheck);
        if(i==1){
            System.exit(0);
        }

        int k = sortedListDate.indexOf(dateToCheck);

        if (k==0)
            System.out.println("There are not any holidays defined before the given date.");

        long l = ChronoUnit.DAYS.between(sortedListDate.get(k-1), sortedListDate.get(k));
        System.out.println("Last holiday was " + l+ " days before.");
        return l;
    }

    public long checkWhenWillBeHoliday(LocalDate dateToCheck){

        List<LocalDate> sortedListDate = new ArrayList<LocalDate>();
        sortedListDate.addAll(listDate);
        sortedListDate.add(dateToCheck);
        Collections.sort(sortedListDate);

        int i = checkHoliday(dateToCheck);
        if(i==1){
            System.exit(0);
        }

        int k = sortedListDate.indexOf(dateToCheck);

        if (k==(sortedListDate.size()-1))
            System.out.println("There are not any holidays defined after the given date.");

        long l = ChronoUnit.DAYS.between(sortedListDate.get(k), sortedListDate.get(k+1));
        System.out.println("Next holiday is going to be in " + l + " days.");
        return l;
    }


    private void parseHolidays() {
        // Parsing dates to List of Dates

        for (String item : listString) {
        try {
            parseDates(item);

        }catch (DateTimeParseException e) {
            System.out.println("There is unparseable date in the file using yyyy-MM-dd format");
            System.exit(2);
        }
        }


    }

    private void parseDates(String item) throws DateTimeParseException {
        LocalDate dateStr = LocalDate.parse(item, formatter);
        listDate.add(dateStr);
    }

    public void readLines(String inputFile) {
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

    private int choosingAction() {
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice:\n");
        System.out.println("1 - checking if date is a holiday");
        System.out.println("2 - checking when was holiday" );
        System.out.println("3 - checking when will be holiday" );

        i = Integer.parseInt(scanner.next());
        i=1;

        return i;
    }


    private LocalDate enteringDateToCheck() {
        LocalDate dateStr = null;
        System.out.print("Enter date you wish to check in a format - yyyy-MM-dd:\n");
        Scanner scanner = new Scanner(System.in);
        String dateToCheck = scanner.next();

        try{
            dateStr = LocalDate.parse(dateToCheck.toString(), formatter);}
            catch (DateTimeParseException exc){
                System.out.println("Unparseable using yyyy-MM-dd format");
                System.exit(2);
            }
        return dateStr;
    }

}
