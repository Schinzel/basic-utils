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
public class LapTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testNodeCreation() {
        Lap node = new Lap("root", null);
        Assert.assertEquals("root", node.getName());
        node = node.start("A");
        Assert.assertEquals("A", node.getName());
        Assert.assertEquals("root", node.mParentLap.getName());
        node = node.start("AA");
        Assert.assertEquals("AA", node.getName());
        Assert.assertEquals("A", node.mParentLap.getName());
        Assert.assertEquals("root", node.mParentLap.mParentLap.getName());
        node = node.stop();
        Assert.assertEquals("A", node.getName());
        node = node.stop();
        Assert.assertEquals("root", node.getName());
    }


    @Test
    public void testGetRoot() {
        Lap node = new Lap("root", null);
        node = node.start("A");
        node = node.start("AA");
        node = node.getRoot();
        Assert.assertEquals("root", node.getName());
    }


    @Test
    public void testGetParentPercent() {
        Lap root = new Lap("root", null);
        root.start();
        Sandman.snoozeMillis(20);
        Lap A = root.start("A");
        Lap AA = A.start("AA");
        Sandman.snoozeMillis(10);
        AA.stop();
        Sandman.snoozeMillis(10);
        A.stop();
        root.stop();

        double delta, result, expected;
        //
        delta = 0d;
        result = root.getPercentOfParent();
        expected = 0d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 0d;
        result = root.getPercentOfRoot();
        expected = 0d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 15d;
        result = A.getPercentOfParent();
        expected = 50d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 10d;
        result = A.getPercentOfRoot();
        expected = 50d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 10d;
        result = AA.getPercentOfParent();
        expected = 50d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 5d;
        result = AA.getPercentOfRoot();
        expected = 25d;
        Assert.assertEquals(expected, result, delta);
    }


    @Test
    public void testStart_StartedNode() {
        Lap root = new Lap("root", null);
        root.start();
        root.start("A");
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot start");
        root.start("A");
    }

    @Test
    public void testStop_StoppedNode() {
        Lap root = new Lap("root", null);
        root.start();
        Lap A = root.start("A");
        A.stop();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot stop");
        A.stop();
    }
}
