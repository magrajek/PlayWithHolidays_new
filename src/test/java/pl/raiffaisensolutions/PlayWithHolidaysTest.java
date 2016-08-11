package pl.raiffaisensolutions;

import org.junit.Before;
import org.junit.Test;
import pl.raiffeisensolutions.PlayWithHolidays;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class PlayWithHolidaysTest {
    PlayWithHolidays playWithHolidays;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
    public static final String PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT = "C:\\Users\\rb21690\\IdeaProjects\\PlayWithHolidays\\src\\main\\resources\\holidays.txt";

    @Before
        public void setUp() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate checkingDate = LocalDate.parse("2016-01-01");
            PlayWithHolidays playWithHolidays = new PlayWithHolidays(PLAY_WITH_HOLIDAYS_SRC_MAIN_RESOURCES_HOLIDAYS_TXT,1,checkingDate);
        }

        @Test
        public void checkIfDayIsAHoliday() {
            // when
            LocalDate checkingDate = LocalDate.parse("2016-01-01");
            int i = playWithHolidays.checkHoliday(checkingDate);

            // then
            assertEquals(0, i);
        }









}
