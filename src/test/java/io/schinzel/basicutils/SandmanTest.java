package io.schinzel.basicutils;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/**
 * @author schinzel
 */
public class SandmanTest extends Sandman {


    @Test
    public void testSnooze() {
        long start = System.nanoTime();
        Sandman.snoozeMillis(20);
        //Calc the time to do all iterations
        long executionTimeInMS = (System.nanoTime() - start) / 1000000;
        assertThat(executionTimeInMS).isBetween(20l, 30l);
    }


    @Test
    public void testSnoozeSeconds() {
        int snoozeTimeInSeconds = 1;
        LocalDateTime start = LocalDateTime.now();
        Instant oneSecondFromNow = Instant.now().plusSeconds(snoozeTimeInSeconds);
        Sandman.snoozeSeconds(snoozeTimeInSeconds);
        //Check that the snooze does not differ more than 20 ms of the requested snooze time.
        assertThat(Instant.now()).isBetween(oneSecondFromNow.minusMillis(50), oneSecondFromNow.plusMillis(50));
    }


    @Test
    public void testSnoozeWithInterrupt() {
        Thread t1 = new Thread(() -> {
            assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
                Sandman.snoozeMillis(100);
            });
        });
        t1.start();
        //Interrupt the snooze in the thread and thus trigger exception
        t1.interrupt();
    }
}
