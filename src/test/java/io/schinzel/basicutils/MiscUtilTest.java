package io.schinzel.basicutils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.within;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class MiscUtilTest extends MiscUtil {

    @Test
    public void testSnooze() {
        int snoozeTime = 100;
        //Test the snooze 10 times
        for (int i = 0; i < 10; i++) {
            LocalDateTime start = LocalDateTime.now();
            MiscUtil.snooze(snoozeTime);
            //Check that the snooze does not differ more than 20% of the request snooze time.
            assertThat(LocalDateTime.now(),
                    within(20, ChronoUnit.MILLIS, start.plus(snoozeTime, ChronoUnit.MILLIS)));
        }
    }

}
