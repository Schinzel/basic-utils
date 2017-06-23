package io.schinzel.basicutils.state;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


/**
 * @author schinzel
 */
public class StateTest {

    @Test
    public void testStringProperty() {
        List<Property> props = State.getBuilder()
                .addProp().key("string").val("a string").buildProp().build()
                .getProperties();
        Assert.assertEquals("string", props.get(0).getKey());
        Assert.assertEquals("a string", props.get(0).getValueAsString());
        Assert.assertEquals("string:a string", props.get(0).getString());
        Assert.assertEquals("a string", props.get(0).getObject());
    }


    @Test
    public void testIntProperty() {
        List<Property> props = State.getBuilder()
                .addProp().key("the_int").val(123456789).buildProp()
                .build().getProperties();
        Assert.assertEquals("the_int", props.get(0).getKey());
        Assert.assertEquals("123,456,789", props.get(0).getValueAsString());
        Assert.assertEquals("the_int:123,456,789", props.get(0).getString());
        Assert.assertEquals(new Long(123456789), props.get(0).getObject());
    }


    @Test
    public void testLongProperty() {
        List<Property> props = State.getBuilder()
                .addProp().key("the_long").val(1234567890l).buildProp()
                .build().getProperties();
        Assert.assertEquals("the_long", props.get(0).getKey());
        Assert.assertEquals("1,234,567,890", props.get(0).getValueAsString());
        Assert.assertEquals("the_long:1,234,567,890", props.get(0).getString());
        Assert.assertEquals(new Long(1234567890), props.get(0).getObject());
    }


    @Test
    public void testDoubleProperty() {
        List<Property> props = State.getBuilder()
                .addProp().key("the_double").val(0.4444d).decimals(2).buildProp()
                .build().getProperties();
        Assert.assertEquals("the_double", props.get(0).getKey());
        Assert.assertEquals("0.44", props.get(0).getValueAsString());
        Assert.assertEquals("the_double:0.44", props.get(0).getString());
        Assert.assertEquals(new Double(0.4444d), props.get(0).getObject());
    }


    @Test
    public void testFloatProperty() {
        List<Property> props = State.getBuilder()
                .addProp().key("the_float").val(12356.6666f).decimals(3).buildProp()
                .build().getProperties();
        Assert.assertEquals("the_float", props.get(0).getKey());
        Assert.assertEquals("12,356.667", props.get(0).getValueAsString());
        Assert.assertEquals("the_float:12,356.667", props.get(0).getString());
        Assert.assertEquals(new Double(12356.6666f), props.get(0).getObject());
    }


    @Test
    public void testBooleanProperty() {
        List<Property> props = State.getBuilder()
                .addProp().key("the_true").val(true).buildProp()
                .addProp().key("the_false").val(false).buildProp()
                .build().getProperties();
        Assert.assertEquals("true", props.get(0).getValueAsString());
        Assert.assertEquals("false", props.get(1).getValueAsString());
    }


    @Test
    public void testOrder() {
        List<Property> props = State.getBuilder()
                .addProp().key("A").val(1).buildProp()
                .addProp().key("B").val(2).buildProp()
                .addProp().key("C").val(3).buildProp()
                .addProp().key("D").val(4).buildProp()
                .addProp().key("E").val(5).buildProp()
                .build().getProperties();
        Long prevValue = 0l;
        for (Property prop : props) {
            assertThat((Long) prop.getObject()).isGreaterThan(prevValue);
            prevValue = (Long) prop.getObject();
        }
    }


    @Test
    public void testGetPropsAsString() {
        String result = State.getBuilder()
                .addProp().key("A").val(1).buildProp()
                .addProp().key("B").val(2).buildProp()
                .build()
                .getString();
        String expected = "A:1 B:2\n";
        Assert.assertEquals(expected, result);
    }


    @Test
    public void toString_AddSomeData_SameAsGetString() {
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
        String result = a1.getState().toString();
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
        JSONObject a1json = a1.getState().getJson();
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
