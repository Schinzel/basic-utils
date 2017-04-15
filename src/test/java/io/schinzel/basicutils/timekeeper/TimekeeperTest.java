package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;
import io.schinzel.json.JsonOrdered;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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
        Timekeeper tk = Timekeeper
                .getSingleton()
                .startLap("A").stopLap()
                .startLap("B").stopLap()
                .startLap("C").stopLap().stop();
        String str = tk.toString();
        int result = str.split("\n").length;
        int expected = 5;
        Assert.assertEquals(expected, result);
        tk.reset();
        tk = Timekeeper.getSingleton();
        tk.startLap("A");
        tk.stopLap();
        tk.startLap("B");
        tk.stopLap();
        tk.stop();
        str = tk.toString();
        result = str.split("\n").length;
        expected = 4;
        Assert.assertEquals(expected, result);
    }


    /**
     * Test that JSON return has all attributes.
     */
    @Test
    public void testToJson() {
        Timekeeper tk = Timekeeper.getSingleton();
        tk.startLap("A");
        Sandman.snoozeMillis(100);
        tk.stopLap().startLap("B");
        Sandman.snoozeMillis(100);
        tk.stopLap().startLap("C");
        Sandman.snoozeMillis(100);
        tk.stopLap().stop();
        JsonOrdered json = tk.toJson();
        //Check that are 5 attributes "name", "tot", "avg" and so forth
        Assert.assertEquals(5, json.length());
        //Check that has 3 children
        Assert.assertEquals(3, json.getJSONArray("sublaps").length());
        //Check that has 3 children
        Assert.assertEquals(3, json.getJSONArray("sublaps").length());
        JSONObject firstChild = json.getJSONArray("sublaps").getJSONObject(0);
        //Check that child has 6 attributes. Same as root not plus "root"
        Assert.assertEquals(6, firstChild.length());
        Assert.assertEquals("A", firstChild.getString("Name"));
        Assert.assertEquals(1, firstChild.getInt("Hits"));
        Assert.assertTrue(firstChild.has("Root"));
        Assert.assertTrue(firstChild.has("Parent"));
        Assert.assertTrue(firstChild.has("Tot"));
        Assert.assertTrue(firstChild.has("Avg"));
    }


    @Test
    public void testHits() {
        Timekeeper timekeeper = TestInstanceWithDataUtil.getTimekeeper();
        JsonOrdered json = timekeeper.toJson();
        //Extract children
        JSONArray firstLevelChildren = json.getJSONArray("sublaps");
        JSONObject A = firstLevelChildren.getJSONObject(0);
        JSONObject B = firstLevelChildren.getJSONObject(1);
        JSONObject C = firstLevelChildren.getJSONObject(2);
        JSONArray secondLevelChildren = B.getJSONArray("sublaps");
        JSONObject B1 = secondLevelChildren.getJSONObject(0);
        JSONObject B2 = secondLevelChildren.getJSONObject(1);
        //Check hits
        Assert.assertEquals(1, json.getInt("Hits"));
        Assert.assertEquals(1, A.getInt("Hits"));
        Assert.assertEquals(1, B.getInt("Hits"));
        Assert.assertEquals(1, C.getInt("Hits"));
        Assert.assertEquals(10, B1.getInt("Hits"));
        Assert.assertEquals(5, B2.getInt("Hits"));
    }


    @Test
    public void testAvg() {
        JsonOrdered json = TestInstanceWithDataUtil.getTimekeeper().toJson();
        //Extract children
        JSONArray firstLevelChildren = json.getJSONArray("sublaps");
        JSONObject B = firstLevelChildren.getJSONObject(1);
        JSONArray secondLevelChildren = B.getJSONArray("sublaps");
        JSONObject B1 = secondLevelChildren.getJSONObject(0);
        JSONObject B2 = secondLevelChildren.getJSONObject(1);
        //Check that B1's average is close to 10
        Double b1Avg = B1.getDouble("Avg");
        assertThat(b1Avg, greaterThan(10d));
        assertThat(b1Avg, lessThan(15d));
        //Check that B2's average is close to 20
        Double b2Avg = B2.getDouble("Avg");
        assertThat(b2Avg, greaterThan(20d));
        assertThat(b2Avg, lessThan(30d));
    }


    @Test
    public void testTot() {
        JsonOrdered json = TestInstanceWithDataUtil.getTimekeeper().toJson();
        //Extract children
        JSONArray firstLevelChildren = json.getJSONArray("sublaps");
        JSONObject B = firstLevelChildren.getJSONObject(1);
        JSONArray secondLevelChildren = B.getJSONArray("sublaps");
        JSONObject B1 = secondLevelChildren.getJSONObject(0);
        JSONObject B2 = secondLevelChildren.getJSONObject(1);
        //Check that B1's total time is between 100 and 130 ms
        Double b1Tot = B1.getDouble("Tot");
        assertThat(b1Tot, greaterThanOrEqualTo(100d));
        assertThat(b1Tot, lessThan(130d));
        //Check that B2's total times is between 100 and 130 ms
        Double b2Tot = B2.getDouble("Tot");
        assertThat(b2Tot, greaterThanOrEqualTo(100d));
        assertThat(b2Tot, lessThan(130d));
    }
    
    
    /**
     * Test that two instances created with create are indeed different
     * objects.
     */
    @Test
    public void testCreate() {
        Timekeeper t1 = Timekeeper.create();
        Timekeeper t2 = Timekeeper.create();
        Assert.assertNotEquals(t1, t2);
    }
}
