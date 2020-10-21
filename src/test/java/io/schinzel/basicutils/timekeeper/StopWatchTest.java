package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author schinzel
 */
public class StopWatchTest {


    @Test
    public void testGetLaps() {
        StopWatch sw = StopWatch.create();
        Assert.assertEquals(0, sw.getNumberOfLaps());
        sw.start().stop();
        Assert.assertEquals(1, sw.getNumberOfLaps());
        sw.start().stop();
        sw.start().stop();
        Assert.assertEquals(3, sw.getNumberOfLaps());
    }


    @Test
    public void testIsStarted() {
        StopWatch sw = StopWatch.create();
        Assert.assertFalse(sw.isStarted());
        sw.start();
        Assert.assertTrue(sw.isStarted());
        sw.stop();
        Assert.assertFalse(sw.isStarted());
        sw.start();
        Assert.assertTrue(sw.isStarted());
    }


    @Test
    public void testDoubleStart() {
        StopWatch sw = StopWatch.create();
        sw.start();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(sw::start)
                .withMessageStartingWith("Cannot start");
    }


    @Test
    public void testDoubleStop() {
        StopWatch sw = StopWatch.create();
        sw.start();
        sw.stop();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(sw::stop)
                .withMessageStartingWith("Cannot stop");
    }


    @Test
    public void testStopNotStarted() {
        StopWatch sw = StopWatch.create();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(sw::stop);
    }


    @Test
    public void testGetTotTimeInMs() {
        StopWatch sw = StopWatch.create();
        sw.start();
        Sandman.snoozeMillis(20);
        sw.stop();
        assertThat(sw.getTotTimeInMs()).isBetween(20d, 30d);
        sw.start();
        Sandman.snoozeMillis(10);
        sw.stop();
        sw.start();
        Sandman.snoozeMillis(10);
        sw.stop();
        assertThat(sw.getTotTimeInMs()).isBetween(40d, 60d);
    }


    @Test
    public void testGetAvgInMs() {
        StopWatch sw = StopWatch.create();
        sw.start();
        Sandman.snoozeMillis(20);
        sw.stop();
        assertThat(sw.getTotTimeInMs()).isBetween(20d, 30d);
        sw.start();
        Sandman.snoozeMillis(10);
        sw.stop();
        assertThat(sw.getAvgInMs()).isBetween(15d, 20d);
    }


}
