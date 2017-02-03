package io.schinzel.basicutils.state;

import java.util.Map;
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
        Map<String, Object> props = State.create()
                .add("string", "a string")
                .add("int", 777)
                .add("large int", 123456789)
                .add("long", 1234567890l)
                .add("double not rounded", 0.3333d)
                .add("double rounded", 0.4444d, 3)
                .add("large double rounded", 123456.4444d, 2)
                .add("float not rounded", 0.5555f)
                .add("float rounded", 0.6666f, 3)
                .getProperties();
        Assert.assertEquals("a string", props.get("string"));
        Assert.assertEquals("777", props.get("int"));
        Assert.assertEquals("123 456 789", props.get("large int"));
        Assert.assertEquals("1 234 567 890", props.get("long"));
        Assert.assertEquals("0.33", props.get("double not rounded"));
        Assert.assertEquals("0.444", props.get("double rounded"));
        Assert.assertEquals("0.56", props.get("float not rounded"));
        Assert.assertEquals("0.667", props.get("float rounded"));
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
        System.out.println(a1.toString());
        String expected = " Name:A1\n"
                + "-- Name:B1\n"
                + "-- Name:B2\n"
                + "---- Name:B2X\n";
        String result = a1.toString();
        Assert.assertEquals(expected, result);
    }
}
