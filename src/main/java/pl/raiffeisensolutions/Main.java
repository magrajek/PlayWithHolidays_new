package pl.raiffeisensolutions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static final String PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT = "C:\\Users\\rb21690\\IdeaProjects\\PlayWithHolidays\\src\\main\\resources\\holidays.txt";

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT,5, LocalDate.parse("1900-01-01",formatter));



    }



}
