package io.schinzel.basicutils.state;

import io.schinzel.basicutils.collections.MapBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class StateTest {

    @Test
    public void testSomeMethod() {
        State state = State.getBuilder()
                .add("string", "a string")
                .add("the_int", 123456789)
                .add("the_long", 1234567890l)
                .add("the_double", 0.4444d, 2)
                .add("the_float", 12356.6666f, 3)
                .build();
        List<Property> props = state.mProperties;
        //Check string
        Assert.assertEquals("string", props.get(0).mKey);
        Assert.assertEquals("a string", props.get(0).mValueAsString);
        Assert.assertEquals("string:a string", props.get(0).getString());
        Assert.assertEquals("a string", props.get(0).getObject());
        //Check int        
        Assert.assertEquals("the_int", props.get(1).mKey);
        Assert.assertEquals("123456789", props.get(1).mValueAsString);
        Assert.assertEquals("the_int:123456789", props.get(1).getString());
        Assert.assertEquals(new Long(123456789), props.get(1).getObject());
        //Check long
        Assert.assertEquals("the_long", props.get(2).mKey);
        Assert.assertEquals("1234567890", props.get(2).mValueAsString);
        Assert.assertEquals("the_long:1234567890", props.get(2).getString());
        Assert.assertEquals(new Long(1234567890), props.get(2).getObject());
        //Check double
        Assert.assertEquals("the_double", props.get(3).mKey);
        Assert.assertEquals("0.44", props.get(3).mValueAsString);
        Assert.assertEquals("the_double:0.44", props.get(3).getString());
        Assert.assertEquals(new Double(0.4444d), props.get(3).getObject());
        //Check float
        Assert.assertEquals("the_float", props.get(4).mKey);
        Assert.assertEquals("12 356.667", props.get(4).mValueAsString);
        Assert.assertEquals("the_float:12 356.667", props.get(4).getString());
        Assert.assertEquals(new Double(12356.6666f), props.get(4).getObject());
 }


    @Test
    public void testOrder() {
        Map<String, Object> props = State.create()
                .add("A", 1)
                .add("B", 2)
                .add("C", 3)
                .add("D", 4)
                .add("E", 5).mProperties;
        String prevValue = "0";
        for (Object o : props.values()) {
            Assert.assertThat("value", Integer.valueOf(o.toString()),
                    greaterThan(Integer.valueOf(prevValue)));
            prevValue = o.toString();
        }
    }


    @Test
    public void testGetPropsAsString() {
        String result = State.create()
                .add("A", 1)
                .add("B", 2)
                .getPropsAsString();
        String expected = "A:1 B:2";
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testAddChild() {
        TestClass a1 = new TestClass("A1");
        TestClass b2 = new TestClass("B2");
        State state = State.create()
                .add("A", 1)
                .add("B", 2)
                .addChild(a1)
                .addChild(b2);
        Assert.assertEquals(a1, state.mChildren.get(0));
        Assert.assertEquals(b2, state.mChildren.get(1));
    }


    @Test
    public void testToString() {
        TestClass a1 = new TestClass("A1");
        a1.mChildren.add(new TestClass(("B1")));
        TestClass b2 = new TestClass("B2");
        b2.mChildren.add(new TestClass(("B2X")));
        a1.mChildren.add(b2);
        String expected = " Name:A1\n"
                + "-- Name:B1\n"
                + "-- Name:B2\n"
                + "---- Name:B2X\n";
        String result = a1.toString();
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testAddMap() {
        Map<String, Integer> map = MapBuilder.create()
                .add("A", 1)
                .add("B", 2)
                .add("C", 3)
                .getMap();
        String result = State.create().add("akey", map).toString();
        String expected = "{A:1,B:2,C:3}";
        Assert.assertThat(result, CoreMatchers.containsString(expected));
    }


    @Test
    public void testAddList() {
        List<String> list = Arrays.asList(new String[]{"A", "B", "C"});
        String result = State.create().add("a_key", list).toString();
        String expected = "{A,B,C}";
        Assert.assertThat(result, CoreMatchers.containsString(expected));
    }


}
