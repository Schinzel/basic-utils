package io.schinzel.basicutils;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.exparity.hamcrest.date.LocalDateTimeMatchers.within;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author schinzel
 */
public class SandmanTest extends Sandman {



    @Test
    public void testSnooze() {
        int snoozeTimeInMillis = 100;
        //Test the snooze 10 times
        for (int i = 0; i < 10; i++) {
            LocalDateTime start = LocalDateTime.now();
            Sandman.snoozeMillis(snoozeTimeInMillis);
            //Check that the snooze does not differ more than 20 ms of the requested snooze time.
            assertThat(LocalDateTime.now(),
                    within(50, ChronoUnit.MILLIS, start.plus(snoozeTimeInMillis, ChronoUnit.MILLIS)));
        }
    }


    @Test
    public void testSnoozeSeconds() {
        int snoozeTimeInSeconds = 1;
        LocalDateTime start = LocalDateTime.now();
        Sandman.snoozeSeconds(snoozeTimeInSeconds);
        //Check that the snooze does not differ more than 20 ms of the requested snooze time.
        assertThat(LocalDateTime.now(),
                within(50, ChronoUnit.MILLIS, start.plus(snoozeTimeInSeconds, ChronoUnit.SECONDS)));
    }


    @Test
    public void testSnoozeWithInterrupt() {
        Thread t1 = new Thread(() -> {
            boolean gotException = false;
            try {
                Sandman.snoozeMillis(100);
            }catch (RuntimeException e){
                gotException = true;
            }
            assertTrue("Got an interrupted exception", gotException);
        });
        t1.start();
        //Interrupt the snooze in the thread and thus trigger exception
        t1.interrupt();
    }
}
