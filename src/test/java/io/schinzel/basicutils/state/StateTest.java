package io.schinzel.basicutils.state;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;

import io.schinzel.json.JsonOrdered;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author schinzel
 */
public class StateTest {

    @Test
    public void testStringProperty() {
        List<Property> props = State.getBuilder()
                .add("string", "a string")
                .build().mProperties;
        Assert.assertEquals("string", props.get(0).mKey);
        Assert.assertEquals("a string", props.get(0).mValueAsString);
        Assert.assertEquals("string:a string", props.get(0).getString());
        Assert.assertEquals("a string", props.get(0).getObject());
    }


    @Test
    public void testIntProperty() {
        List<Property> props = State.getBuilder()
                .add("the_int", 123456789)
                .build().mProperties;
        Assert.assertEquals("the_int", props.get(0).mKey);
        Assert.assertEquals("123 456 789", props.get(0).mValueAsString);
        Assert.assertEquals("the_int:123 456 789", props.get(0).getString());
        Assert.assertEquals(new Long(123456789), props.get(0).getObject());
    }


    @Test
    public void testLongProperty() {
        List<Property> props = State.getBuilder()
                .add("the_long", 1234567890l)
                .build().mProperties;
        Assert.assertEquals("the_long", props.get(0).mKey);
        Assert.assertEquals("1 234 567 890", props.get(0).mValueAsString);
        Assert.assertEquals("the_long:1 234 567 890", props.get(0).getString());
        Assert.assertEquals(new Long(1234567890), props.get(0).getObject());
    }


    @Test
    public void testDoubleProperty() {
        List<Property> props = State.getBuilder()
                .add("the_double", 0.4444d, 2)
                .build().mProperties;
        Assert.assertEquals("the_double", props.get(0).mKey);
        Assert.assertEquals("0.44", props.get(0).mValueAsString);
        Assert.assertEquals("the_double:0.44", props.get(0).getString());
        Assert.assertEquals(new Double(0.4444d), props.get(0).getObject());
    }


    @Test
    public void testFloatProperty() {
        List<Property> props = State.getBuilder()
                .add("the_float", 12356.6666f, 3)
                .build().mProperties;
        Assert.assertEquals("the_float", props.get(0).mKey);
        Assert.assertEquals("12 356.667", props.get(0).mValueAsString);
        Assert.assertEquals("the_float:12 356.667", props.get(0).getString());
        Assert.assertEquals(new Double(12356.6666f), props.get(0).getObject());
    }


    @Test
    public void testOrder() {
        List<Property> props = State.getBuilder()
                .add("A", 1)
                .add("B", 2)
                .add("C", 3)
                .add("D", 4)
                .add("E", 5)
                .build().mProperties;
        Long prevValue = 0l;
        for (Property prop : props) {
            Assert.assertThat("value", (Long) prop.getObject(),
                    greaterThan(prevValue));
            prevValue = (Long) prop.getObject();
        }
    }


    @Test
    public void testGetPropsAsString() {
        String result = State.getBuilder()
                .add("A", 1)
                .add("B", 2)
                .build()
                .getString();
        String expected = "A:1 B:2\n";
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testGetString() {
        MyClass a1 = new MyClass("A1", 10);
        MyClass b1 = new MyClass("B1", 17);
        MyClass b2 = new MyClass("B2", 18);
        MyClass b2x = new MyClass("B2X", 99);
        a1.mChildren.add(b1);
        a1.mChildren.add(b2);
        b2.mChildren.add(b2x);
        b2.mLeft = new MyClass("Left", 111);
        b2.mRight = new MyClass("Right", 222);
        String expected = "Name:A1 Cost:10\n"
                + "mykids\n"
                + "┗━ Name:B1 Cost:17\n"
                + "┗━ Name:B2 Cost:18\n"
                + "   ┗━ Left Name:Left Cost:111\n"
                + "   ┗━ Right Name:Right Cost:222\n"
                + "   ┗━ mykids\n"
                + "      ┗━ Name:B2X Cost:99\n"
                + "";
        String result = a1.getState().getString();
        a1.getState().getStr().pln();
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testGetJson() {
        MyClass a1 = new MyClass("A1", 10);
        MyClass b1 = new MyClass("B1", 17);
        MyClass b2 = new MyClass("B2", 18);
        MyClass b2x = new MyClass("B2X", 99);
        a1.mChildren.add(b1);
        a1.mChildren.add(b2);
        b2.mChildren.add(b2x);
        b2.mLeft = new MyClass("Left", 111);
        b2.mRight = new MyClass("Right", 222);
        JsonOrdered a1json = a1.getState().getJson();
        Assert.assertEquals("A1", a1json.getString("Name"));
        Assert.assertEquals(10, a1json.getInt("Cost"));
        JSONArray a1kids = a1json.getJSONArray("mykids");
        Assert.assertEquals(2, a1kids.length());
        JSONObject b1json = a1kids.getJSONObject(0);
        Assert.assertEquals("B1", b1json.getString("Name"));
        Assert.assertEquals(17, b1json.getInt("Cost"));
        JSONObject b2json = a1kids.getJSONObject(1);
        Assert.assertEquals("B2", b2json.getString("Name"));
        Assert.assertEquals(18, b2json.getInt("Cost"));
        //Check a child list
        JSONArray b2kids = b2json.getJSONArray("mykids");
        Assert.assertEquals(1, b2kids.length());
        //Check a node in the child list
        JSONObject b2xjson = b2kids.getJSONObject(0);
        Assert.assertEquals("B2X", b2xjson.getString("Name"));
        Assert.assertEquals(99, b2xjson.getInt("Cost"));
        //Check individual children
        JSONObject leftie = b2json.getJSONObject("Left");
        Assert.assertEquals("Left", leftie.getString("Name"));
        Assert.assertEquals(111, leftie.getInt("Cost"));
        //Check individual children
        JSONObject righty = b2json.getJSONObject("Right");
        Assert.assertEquals("Right", righty.getString("Name"));
        Assert.assertEquals(222, righty.getInt("Cost"));

    }


}
