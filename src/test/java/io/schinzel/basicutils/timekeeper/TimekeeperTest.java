package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author schinzel
 */
public class TimekeeperTest {


    @Before()
    public void before() {
        Timekeeper.getSingleton().reset();

    }


    @Test
    public void testToString() {
        Timekeeper tk = Timekeeper
                .create()
                .start("A").stop()
                .start("B").stop()
                .start("C").stop();
        String str = tk.getResults().toString();
        int result = str.split("\n").length;
        int expected = 5;
        Assert.assertEquals(expected, result);
        tk.reset();
        tk = Timekeeper.create();
        tk.start("A").stop().start("B").stop();
        str = tk.getResults().toString();
        result = str.split("\n").length;
        expected = 4;
        Assert.assertEquals(expected, result);
    }


    @Test
    public void getResults_StartAndStopTwoLaps_FourLines() {
        String str = Timekeeper
                .create()
                .start("A").stop()
                .start("B").stop()
                .getResults().toString();
        int actual = str.split("\n").length;
        int expected = 4;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getResults_RootLapIsNotStopped_FourLines() {
        String str = Timekeeper
                .create()
                .getResults().toString();
        int actual = str.split("\n").length;
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getResults_RootLapIsStopped_FourLines() {
        String str = Timekeeper
                .create()
                .stop()
                .getResults().toString();
        int actual = str.split("\n").length;
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }


    /**
     * Test that JSON return has all attributes.
     */
    @Test
    public void testToJson() {
        Timekeeper tk = Timekeeper.getSingleton();
        tk.start("A");
        Sandman.snoozeMillis(10);
        tk.stop().start("B");
        Sandman.snoozeMillis(10);
        tk.stop().start("C");
        Sandman.snoozeMillis(10);
        tk.stop();
        JSONObject json = tk.getResults().getJson();
        //Check that are 5 attributes "name", "tot", "avg" and so forth
        Assert.assertEquals(5, json.length());
        //Check that has 3 children
        Assert.assertEquals(3, json.getJSONArray("sublaps").length());
        //Check that has 3 children
        Assert.assertEquals(3, json.getJSONArray("sublaps").length());
        JSONObject firstChild = json.getJSONArray("sublaps").getJSONObject(0);
        //Check that child has 6 attributes. Same as root not plus "root"
        Assert.assertEquals(6, firstChild.length());
        Assert.assertEquals("'A'", firstChild.getString("Name"));
        Assert.assertEquals(1, firstChild.getInt("Hits"));
        Assert.assertTrue(firstChild.has("Root"));
        Assert.assertTrue(firstChild.has("Parent"));
        Assert.assertTrue(firstChild.has("Tot"));
        Assert.assertTrue(firstChild.has("Avg"));
    }


    @Test
    public void testHits() {
        Timekeeper timekeeper = TestInstanceWithDataUtil.getTimekeeper();
        JSONObject json = timekeeper.getResults().getJson();
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
        JSONObject json = TestInstanceWithDataUtil.getTimekeeper()
                .getResults().getJson();
        //Extract children
        JSONArray firstLevelChildren = json.getJSONArray("sublaps");
        JSONObject B = firstLevelChildren.getJSONObject(1);
        JSONArray secondLevelChildren = B.getJSONArray("sublaps");
        JSONObject B1 = secondLevelChildren.getJSONObject(0);
        JSONObject B2 = secondLevelChildren.getJSONObject(1);
        //Check that B1's average is close to 10
        Double b1Avg = B1.getDouble("Avg");
        assertThat(b1Avg).isBetween(10d, 15d);
        //Check that B2's average is close to 20
        Double b2Avg = B2.getDouble("Avg");
        assertThat(b2Avg).isBetween(20d, 35d);
    }


    @Test
    public void testTot() {
        JSONObject json = TestInstanceWithDataUtil.getTimekeeper().getResults().getJson();
        //Extract children
        JSONArray firstLevelChildren = json.getJSONArray("sublaps");
        JSONObject B = firstLevelChildren.getJSONObject(1);
        JSONArray secondLevelChildren = B.getJSONArray("sublaps");
        JSONObject B1 = secondLevelChildren.getJSONObject(0);
        JSONObject B2 = secondLevelChildren.getJSONObject(1);
        //Check that B1's total time is between 100 and 130 ms
        Double b1Tot = B1.getDouble("Tot");
        assertThat(b1Tot).isBetween(100d, 130d);
        //Check that B2's total times is between 100 and 130 ms
        Double b2Tot = B2.getDouble("Tot");
        assertThat(b2Tot).isBetween(100d, 130d);
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


    @Test
    public void toString_RootLap_CurrentLapShouldBeRoot() {
        String actual = Timekeeper.create().toString();
        assertThat(actual).contains("Current lap: 'root'");
    }


    @Test
    public void toString_LapAAA_CurrentLapShouldBeRoot() {
        String actual = Timekeeper.create()
                .start("AAA")
                .toString();
        assertThat(actual).contains("Current lap: 'AAA'");
    }

}
