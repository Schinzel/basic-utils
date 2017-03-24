package io.schinzel.basicutils.state;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StateBuilderTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testAdd_StringArray_null() {
        StateBuilder sb = new StateBuilder();
        String[] stringArray = null;
        sb.add("key1", stringArray);
        Property property = sb.mProperties.get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:", property.getString());
    }


    @Test
    public void testAdd_StringArray() {
        StateBuilder sb = new StateBuilder();
        String[] stringArray = {"a", "b"};
        sb.add("key1", stringArray);
        Property property = sb.mProperties.get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:a, b", property.getString());
    }


    @Test
    public void testAdd_StringList_null() {
        StateBuilder sb = new StateBuilder();
        List<String> stringList = null;
        sb.add("key1", stringList);
        Property property = sb.mProperties.get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:", property.getString());
    }


    @Test
    public void testAdd_StringList() {
        StateBuilder sb = new StateBuilder();
        List<String> stringList = Arrays.asList("A", "B");
        sb.add("key1", stringList);
        Property property = sb.mProperties.get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:A, B", property.getString());
    }


    @Test
    public void testAddString_null() {
        StateBuilder sb = new StateBuilder();
        String stringArray = null;
        sb.add("key1", stringArray);
        Property property = sb.mProperties.get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:", property.getString());
    }


    @Test
    public void testAddIStateNode_null() {
        StateBuilder sb = new StateBuilder();
        IStateNode stringArray = null;
        sb.add("key1", stringArray);
        assertEquals(0, sb.mProperties.size());
    }


    @Test
    public void testAddIterableIStateNode_null() {
        StateBuilder sb = new StateBuilder();
        List<IStateNode> stringArray = null;
        sb.add("key1", stringArray);
        assertEquals(0, sb.mProperties.size());
    }


    @Test
    public void testConstructor() {
        State superState = new StateBuilder()
                .add("key1", "val1")
                .add("key2", "val2")
                .build();
        State subState = State.getBuilder(superState)
                .add("key3", "val3")
                .build();
        List<Property> props = subState.mProperties;
        assertEquals(3, props.size());
        assertEquals("key1", props.get(0).getKey());
        assertEquals("key2", props.get(1).getKey());
        assertEquals("key3", props.get(2).getKey());
    }
}