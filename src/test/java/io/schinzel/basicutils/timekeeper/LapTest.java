package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;
import io.schinzel.basicutils.collections.valueswithkeys.ValuesWithKeys;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;


/**
 * @author schinzel
 */
public class LapTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void childLaps_AddChildLaps_InsertionOrder() {
        ValuesWithKeys<Lap> childLaps = new Lap("myLap", null)
                .start("B").stop()
                .start("A").stop()
                .start("D").stop()
                .start("C").stop()
                .mChildLaps;
        List<String> actual = childLaps.stream().map(Lap::getKey).collect(Collectors.toList());
        List<String> expected = Arrays.asList("B", "A", "D", "C");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void testNodeCreation() {
        Lap node = new Lap("root", null);
        Assert.assertEquals("root", node.getKey());
        node = node.start("A");
        Assert.assertEquals("A", node.getKey());
        Assert.assertEquals("root", node.mParentLap.getKey());
        node = node.start("AA");
        Assert.assertEquals("AA", node.getKey());
        Assert.assertEquals("A", node.mParentLap.getKey());
        Assert.assertEquals("root", node.mParentLap.mParentLap.getKey());
        node = node.stop();
        Assert.assertEquals("A", node.getKey());
        node = node.stop();
        Assert.assertEquals("root", node.getKey());
    }


    @Test
    public void testGetRoot() {
        Lap node = new Lap("root", null);
        node = node.start("A");
        node = node.start("AA");
        node = node.getRoot();
        Assert.assertEquals("root", node.getKey());
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
