package pl.raiffaisensolutions;

import org.junit.Before;
import org.junit.Test;
import pl.raiffeisensolutions.ParsingAndReadingHolidays;
import pl.raiffeisensolutions.PlayWithHolidays;

import java.time.LocalDate;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayWithHolidaysTest {
    private static final String INPUT_FILE = ".\\src\\main\\resources\\holidays.txt";
    private PlayWithHolidays playWithHolidays;
    private ParsingAndReadingHolidays parsingAndReadingHolidays;
    private LocalDate isHoliday;
    private LocalDate isHoliday2;
    private LocalDate isHoliday3;
    private List<String> listString;
    private List<LocalDate> listDate;

    @Before
        public void setUp() throws Exception {

        parsingAndReadingHolidays = new ParsingAndReadingHolidays(INPUT_FILE);
        listString = parsingAndReadingHolidays.readLines(INPUT_FILE);
        listDate = parsingAndReadingHolidays.parseHolidays(listString);
        playWithHolidays = new PlayWithHolidays();
        isHoliday = LocalDate.of(2016, 2, 2);
        isHoliday2 = LocalDate.of(2016, 1, 1);
        isHoliday3 = LocalDate.of(2016, 3, 1);
        }

    @Test
        public void checkIfDayIsAHoliday() {
            boolean testResult = playWithHolidays.checkHoliday(isHoliday, listDate);
            assertFalse(testResult);
        }

    @Test
    public void checkIfDayIsAHoliday2() {

        boolean testResult = playWithHolidays.checkHoliday(isHoliday2, listDate);
        assertTrue(testResult);
    }

    @Test
    public void checkWhenWillBeAHoliday() {

        long testResult = playWithHolidays.checkWhenWillBeHoliday(isHoliday, listDate);
        assertEquals(89, testResult);
    }

    @Test
    public void checkWhenWillBeAHoliday2() {

        long testResult = playWithHolidays.checkWhenWillBeHoliday(isHoliday3, listDate);
        assertEquals(61, testResult);
    }

    @Test
    public void checkWhenWasAHoliday() {

        long testResult = playWithHolidays.checkWhenWasHoliday(isHoliday, listDate);
        assertEquals(27, testResult);
    }

    @Test
    public void checkWhenWasAHoliday2() {

        long testResult = playWithHolidays.checkWhenWasHoliday(isHoliday3, listDate);
        assertEquals(55, testResult);
    }

    @Test
    public void checkNextWorkingDay() {

        LocalDate testResult = playWithHolidays.checkNextWorkingDay(isHoliday, listDate, 20);
        assertEquals(LocalDate.of(2016, 3, 1), testResult);
    }

    @Test
    public void checkNextWorkingDay2() {

        LocalDate testResult = playWithHolidays.checkNextWorkingDay(isHoliday3, listDate, 10);
        assertEquals(LocalDate.of(2016, 3, 15), testResult);
    }





}
