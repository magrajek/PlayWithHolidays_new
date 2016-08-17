package pl.raiffeisensolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ParsingAndReadingHolidays {
    //Class responsible for reading file with list of holidays and returning List<LocalDate>.
    private static final Logger LOGGER = Logger.getLogger(ParsingAndReadingHolidays.class.getName());
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private String inputFile;

    public ParsingAndReadingHolidays(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<LocalDate> parseHolidays(List<String> listString) {
        // Parsing dates to List of Dates
        List<LocalDate> listDate = new ArrayList<>();
        for (String item : listString) {
            try {
                LocalDate dateStr = LocalDate.parse(item, formatter);
                listDate.add(dateStr);
            }catch (DateTimeParseException e) {
                //   LOGGER.info("There is unparseable date in the file using yyyy-MM-dd format");
                System.exit(2);
            }
        }
        return listDate;
    }


    public List<String> readLines(String inputFile) {
        // Reading lines with dates from file to List<String> object.
        List<String> listString = new ArrayList<>();
        try {
            Scanner scanner = getScanner(inputFile);
            getLines(listString, scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        checkEmptyFile(listString);
        return listString;
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
