package pl.raiffaisensolutions;

import org.junit.Before;
import org.junit.Test;
import pl.raiffeisensolutions.PlayWithHolidays;

import java.time.LocalDate;


import static org.junit.Assert.assertEquals;

public class PlayWithHolidaysTest {
    private static final String PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT = "C:\\Users\\rb21690\\IdeaProjects\\PlayWithHolidays\\src\\main\\resources\\holidays.txt";

    @Before
        public void setUp() throws Exception {

        }

    @Test
        public void checkIfDayIsAHoliday() {
            PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
            LocalDate dateToCheck = LocalDate.parse("2016-02-02");
            boolean testResult = playWithHolidays.checkHoliday(dateToCheck);
            assertEquals(false, testResult);
        }

    @Test
    public void checkIfDayIsAHoliday2() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-01-01");
        boolean testResult = playWithHolidays.checkHoliday(dateToCheck);
        assertEquals(true, testResult);
    }

    @Test
    public void checkWhenWillBeAHoliday() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-01-01");
        long testResult = playWithHolidays.checkWhenWillBeHoliday(dateToCheck);
        assertEquals(0, testResult);
    }

    @Test
    public void checkWhenWillBeAHoliday2() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-03-01");
        long testResult = playWithHolidays.checkWhenWillBeHoliday(dateToCheck);
        assertEquals(61, testResult);
    }

    @Test
    public void checkWhenWasAHoliday() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-03-01");
        long testResult = playWithHolidays.checkWhenWasHoliday(dateToCheck);
        assertEquals(55, testResult);
    }

    @Test
    public void checkWhenWasAHoliday2() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-03-07");
        long testResult = playWithHolidays.checkWhenWasHoliday(dateToCheck);
        assertEquals(61, testResult);
    }

    @Test
    public void checkNextWorkingDay() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-03-07");
        LocalDate testResult = playWithHolidays.checkNextWorkingDay(dateToCheck, 20);
        assertEquals(LocalDate.parse("2016-03-27"), testResult);
    }

    @Test
    public void checkNextWorkingDay2() {
        PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT);
        LocalDate dateToCheck = LocalDate.parse("2016-03-07");
        LocalDate testResult = playWithHolidays.checkNextWorkingDay(dateToCheck, 10);
        assertEquals(LocalDate.parse("2016-03-17"), testResult);
    }





}
