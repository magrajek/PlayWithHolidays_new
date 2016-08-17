package pl.raiffaisensolutions;

import org.junit.Before;
import org.junit.Test;
import pl.raiffeisensolutions.PlayWithHolidays;

import java.time.LocalDate;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayWithHolidaysTest {
    private static final String INPUT_FILE = ".\\src\\main\\resources\\holidays.txt";
    PlayWithHolidays playWithHolidays;
    @Before
        public void setUp() throws Exception {
        playWithHolidays = new PlayWithHolidays(INPUT_FILE);
        }

    @Test
        public void checkIfDayIsAHoliday() {

            LocalDate dateToCheck = LocalDate.parse("2016-02-02");
            boolean testResult = playWithHolidays.checkHoliday(dateToCheck);
            assertFalse(testResult);
        }

    @Test
    public void checkIfDayIsAHoliday2() {

        LocalDate dateToCheck = LocalDate.parse("2016-01-01");
        boolean testResult = playWithHolidays.checkHoliday(dateToCheck);
        assertTrue(testResult);
    }

    @Test
    public void checkWhenWillBeAHoliday() {

        LocalDate dateToCheck = LocalDate.parse("2016-01-01");
        long testResult = playWithHolidays.checkWhenWillBeHoliday(dateToCheck);
        assertEquals(0, testResult);
    }

    @Test
    public void checkWhenWillBeAHoliday2() {

        LocalDate dateToCheck = LocalDate.parse("2016-03-01");
        long testResult = playWithHolidays.checkWhenWillBeHoliday(dateToCheck);
        assertEquals(61, testResult);
    }

    @Test
    public void checkWhenWasAHoliday() {

        LocalDate dateToCheck = LocalDate.parse("2016-03-01");
        long testResult = playWithHolidays.checkWhenWasHoliday(dateToCheck);
        assertEquals(55, testResult);
    }

    @Test
    public void checkWhenWasAHoliday2() {

        LocalDate dateToCheck = LocalDate.parse("2016-03-07");
        long testResult = playWithHolidays.checkWhenWasHoliday(dateToCheck);
        assertEquals(61, testResult);
    }

    @Test
    public void checkNextWorkingDay() {

        LocalDate dateToCheck = LocalDate.parse("2016-03-07");
        LocalDate testResult = playWithHolidays.checkNextWorkingDay(dateToCheck, 20);
        assertEquals(LocalDate.parse("2016-03-27"), testResult);
    }

    @Test
    public void checkNextWorkingDay2() {

        LocalDate dateToCheck = LocalDate.parse("2016-03-07");
        LocalDate testResult = playWithHolidays.checkNextWorkingDay(dateToCheck, 10);
        assertEquals(LocalDate.parse("2016-03-17"), testResult);
    }





}
