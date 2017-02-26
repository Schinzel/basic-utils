package io.schinzel.basicutils.state;

import java.util.List;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.Assert;
import org.junit.Test;

/**
 *
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
        TestClass a1 = new TestClass("A1", 10);
        a1.mChildren.add(new TestClass("B1", 17));
        TestClass b2 = new TestClass("B2", 18);
        b2.mChildren.add(new TestClass("B2X", 99));
        a1.mChildren.add(b2);
        String expected = "Name:A1 Cost:10\n"
                + "mykids\n"
                + "┗━ Name:B1 Cost:17\n"
                + "┗━ Name:B2 Cost:18\n"
                + "   ┗━ mykids\n"
                + "      ┗━ Name:B2X Cost:99\n"
                + "";
        String result = a1.getState().getString();
        Assert.assertEquals(expected, result);
    }

}
