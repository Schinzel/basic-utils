package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.timekeeper.Lap;
import io.schinzel.basicutils.MiscUtil;
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
        Assert.assertEquals("root", node.toString());
        node = node.start("A");
        Assert.assertEquals("A", node.toString());
        Assert.assertEquals("root", node.mParent.toString());
        node = node.start("AA");
        Assert.assertEquals("AA", node.toString());
        Assert.assertEquals("A", node.mParent.toString());
        Assert.assertEquals("root", node.mParent.mParent.toString());
        node = node.stop();
        Assert.assertEquals("A", node.toString());
        node = node.stop();
        Assert.assertEquals("root", node.toString());
    }


    @Test
    public void testGetRoot() {
        Lap node = new Lap("root", null);
        node = node.start("A");
        node = node.start("AA");
        node = node.getRoot();
        Assert.assertEquals("root", node.toString());
    }


    @Test
    public void testGetParentPercent() {
        Lap root = new Lap("root", null);
        root.start();
        MiscUtil.snooze(200);
        Lap A = root.start("A");
        Lap AA = A.start("AA");
        MiscUtil.snooze(100);
        AA.stop();
        MiscUtil.snooze(100);
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
        delta = 5d;
        result = A.getPercentOfParent();
        expected = 50d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 5d;
        result = A.getPercentOfRoot();
        expected = 50d;
        Assert.assertEquals(expected, result, delta);
        //
        delta = 5d;
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
