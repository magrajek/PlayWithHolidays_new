package pl.raiffeisensolutions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT = "C:\\Users\\rb21690\\IdeaProjects\\PlayWithHolidays\\src\\main\\resources\\holidays.txt";


    public static void main(String[] args) {

        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-02-02");
try {
    if(playWithHolidays.checkHoliday(dateToCheck))
        System.out.println("Given day is a holiday.");
    else System.out.println("Given day is not a holiday.");

    System.out.println("Last holiday was " + playWithHolidays.checkWhenWasHoliday(dateToCheck)+ " days ago.");

    System.out.println("Next working day is " + playWithHolidays.checkNextWorkingDay(dateToCheck, 10));

    System.out.println("Next holiday is going to be in " + playWithHolidays.checkWhenWillBeHoliday(dateToCheck) + " days.");


}catch(DateTimeParseException exp){
    LOGGER.info("There is unparseable date in the file using yyyy-MM-dd format");
}
    }

}
