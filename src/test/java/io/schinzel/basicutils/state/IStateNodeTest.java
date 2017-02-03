package io.schinzel.basicutils.state;

import io.schinzel.json.JsonOrdered;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class IStateNodeTest {

    /**
     * Method tests that the JSON returned has all the values
     * and that are returned in the right order.
     */
    @Test
    public void testGetStateAsJson() {
        TestClass a1 = new TestClass("A1");
        a1.mChildren.add(new TestClass(("B1")));
        TestClass b2 = new TestClass("B2");
        a1.mChildren.add(b2);
        b2.mChildren.add(new TestClass("C1"));
        b2.mChildren.add(new TestClass("C2"));
        b2.mChildren.add(new TestClass("C3"));
        b2.mChildren.add(new TestClass("C4"));
        a1.mChildren.add(new TestClass(("B3")));
        JsonOrdered json = a1.getState().getJson();
        Assert.assertEquals("A1", json.get("Name"));
        JSONArray a1Children = json.getJSONArray("children");
        Assert.assertEquals(3, a1Children.length());
        Assert.assertEquals("B1", a1Children.getJSONObject(0).get("Name"));
        Assert.assertEquals("B2", a1Children.getJSONObject(1).get("Name"));
        Assert.assertEquals("B3", a1Children.getJSONObject(2).get("Name"));
        JSONArray b2children = a1Children.getJSONObject(1).getJSONArray("children");
        Assert.assertEquals("C1", b2children.getJSONObject(0).get("Name"));
        Assert.assertEquals("C2", b2children.getJSONObject(1).get("Name"));
        Assert.assertEquals("C3", b2children.getJSONObject(2).get("Name"));
        Assert.assertEquals("C4", b2children.getJSONObject(3).get("Name"));
    }


}
