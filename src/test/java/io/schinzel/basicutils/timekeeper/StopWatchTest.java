package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class StopWatchTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testGetLaps() {
        StopWatch sw = StopWatch.create();
        Assert.assertEquals(0, sw.getLaps());
        sw.start().stop();
        Assert.assertEquals(1, sw.getLaps());
        sw.start().stop();
        sw.start().stop();
        Assert.assertEquals(3, sw.getLaps());
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
    public void testToMillis() {
        long input = 1000000000l;
        double result = StopWatch.toMillis(input);
        double expected = 1000d;
        Assert.assertEquals(expected, result, 0d);
    }


    @Test
    public void testToMillis2() {
        double input = 1000000000d;
        double result = StopWatch.toMillis(input);
        double expected = 1000d;
        Assert.assertEquals(expected, result, 0d);
    }


    @Test
    public void testDoubleStart() {
        StopWatch sw = StopWatch.create();
        sw.start();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot start");
        sw.start();
    }


    @Test
    public void testDoubleStop() {
        StopWatch sw = StopWatch.create();
        sw.start();
        sw.stop();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot stop");
        sw.stop();
    }


    @Test
    public void testStopNotStarted() {
        StopWatch sw = StopWatch.create();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot stop");
        sw.stop();
    }


    @Test
    public void testGetTotTimeInMs() {
        StopWatch sw = StopWatch.create();
        sw.start();
        Sandman.snoozeMillis(100);
        sw.stop();
        double result = sw.getTotTimeInMs();
        double expected = 100;
        double delta = 10;
        Assert.assertEquals(result, expected, delta);
        sw.start();
        Sandman.snoozeMillis(100);
        sw.stop();
        sw.start();
        Sandman.snoozeMillis(100);
        sw.stop();
        result = sw.getTotTimeInMs();
        expected = 300;
        delta = 30;
        Assert.assertEquals(result, expected, delta);
    }


    @Test
    public void testGetAvgInMs() {
        StopWatch sw = StopWatch.create();
        sw.start();
        Sandman.snoozeMillis(100);
        sw.stop();
        double result = sw.getAvgInMs();
        double expected = 100;
        double delta = 10;
        Assert.assertEquals(result, expected, delta);
        sw.start();
        Sandman.snoozeMillis(200);
        sw.stop();
        result = sw.getAvgInMs();
        expected = 150;
        delta = 10;
        Assert.assertEquals(result, expected, delta);
    }


}
