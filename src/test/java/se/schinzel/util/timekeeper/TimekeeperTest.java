package se.schinzel.util.timekeeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class TimekeeperTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before()
    public void before() {
        Timekeeper.getSingleton().reset();

    }


    @Test
    public void testGetResults_nonStoppedNode() {
        Timekeeper tk = Timekeeper.getSingleton();
        tk.startLap("A");
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot get results for");
        tk.toString();
    }


    @Test
    public void testToString() {
        Timekeeper tk = Timekeeper.getSingleton();
        tk.startLap("A");
        tk.stopLap();
        tk.startLap("B");
        tk.stopLap();
        tk.startLap("C");
        tk.stopLap();
        tk.stop();
        String str = tk.toString();
        int result = str.split("\n").length;
        int expected = 4;
        Assert.assertEquals(result, expected);
        tk.reset();
        tk = Timekeeper.getSingleton();
        tk.startLap("A");
        tk.stopLap();
        tk.startLap("B");
        tk.stopLap();
        tk.stop();
        str = tk.toString();
        result = str.split("\n").length;
        expected = 3;
        Assert.assertEquals(result, expected);
    }

}
