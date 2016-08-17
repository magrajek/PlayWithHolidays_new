package pl.raiffeisensolutions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String INPUT_FILE = ".\\src\\main\\resources\\holidays.txt";


    public static void main(String[] args) {

        ParsingAndReadingHolidays parsingAndReadingHolidays = new ParsingAndReadingHolidays(INPUT_FILE);
        List<String> listString = parsingAndReadingHolidays.readLines(INPUT_FILE);
        List<LocalDate> listDate = parsingAndReadingHolidays.parseHolidays(listString);

        PlayWithHolidays playWithHolidays = new PlayWithHolidays();

        LocalDate isHoliday = LocalDate.of(2016, 2, 2);

        try {
            if (playWithHolidays.checkHoliday(isHoliday, listDate))
                LOGGER.info("Given day is a holiday.");
            else
                LOGGER.info("Given day is not a holiday.");

            LOGGER.info("Last holiday was " + playWithHolidays.checkWhenWasHoliday(isHoliday, listDate) + " days ago.");

            LOGGER.info("Next working day is " + playWithHolidays.checkNextWorkingDay(isHoliday, listDate, 10));

            LOGGER.info("Next holiday is going to be in " + playWithHolidays.checkWhenWillBeHoliday(isHoliday, listDate) + " days.");

        } catch (DateTimeParseException exp) {
            LOGGER.info("There is unparseable date in the file using yyyy-MM-dd format");
        }
    }

}
