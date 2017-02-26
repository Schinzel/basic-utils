package io.schinzel.basicutils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.within;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class MiscUtilTest extends MiscUtil {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testSnooze() {
        int snoozeTimeInMillis = 100;
        //Test the snooze 10 times
        for (int i = 0; i < 10; i++) {
            LocalDateTime start = LocalDateTime.now();
            MiscUtil.snooze(snoozeTimeInMillis);
            //Check that the snooze does not differ more than 20 ms of the requested snooze time.
            assertThat(LocalDateTime.now(),
                    within(50, ChronoUnit.MILLIS, start.plus(snoozeTimeInMillis, ChronoUnit.MILLIS)));
        }
    }


    @Test
    public void testSnoozeSeconds() {
        int snoozeTimeInSeconds = 1;
        LocalDateTime start = LocalDateTime.now();
        MiscUtil.snoozeSeconds(snoozeTimeInSeconds);
        //Check that the snooze does not differ more than 20 ms of the requested snooze time.
        assertThat(LocalDateTime.now(),
                within(50, ChronoUnit.MILLIS, start.plus(snoozeTimeInSeconds, ChronoUnit.SECONDS)));
    }


    @Test
    public void testSnoozeWithInterrupt() {
        Thread t1 = new Thread(() -> {
            exception.expect(RuntimeException.class);
            exception.expectMessage("Snooze interrupted");
            MiscUtil.snooze(100);
        });
        t1.start();
        //Interrupt the snooze in the thread and thus trigger exception
        t1.interrupt();

    }
}
