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
                .add("E", 5)
                .mProperties;
        String prevValue = "0";
        for (Object o : props.values()) {
            Assert.assertThat("value", Integer.valueOf(o.toString()),
                    greaterThan(Integer.valueOf(prevValue)));
            prevValue = o.toString();
        }
    }


}
