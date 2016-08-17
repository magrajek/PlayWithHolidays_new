package pl.raiffeisensolutions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String INPUT_FILE = ".\\src\\main\\resources\\holidays.txt";


    public static void main(String[] args) {

        PlayWithHolidays playWithHolidays = new PlayWithHolidays(INPUT_FILE);
        LocalDate dateToCheck = LocalDate.parse("2016-02-02");
try {
    if(playWithHolidays.checkHoliday(dateToCheck))
        LOGGER.info("Given day is a holiday.");
    else LOGGER.info("Given day is not a holiday.");

    LOGGER.info("Last holiday was " + playWithHolidays.checkWhenWasHoliday(dateToCheck)+ " days ago.");

    LOGGER.info("Next working day is " + playWithHolidays.checkNextWorkingDay(dateToCheck, 10));

    LOGGER.info("Next holiday is going to be in " + playWithHolidays.checkWhenWillBeHoliday(dateToCheck) + " days.");


}catch(DateTimeParseException exp){
    LOGGER.info("There is unparseable date in the file using yyyy-MM-dd format");
}
    }

}
