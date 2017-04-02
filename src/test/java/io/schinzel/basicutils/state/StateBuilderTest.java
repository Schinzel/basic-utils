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
        sb.addProp().key("key1").val(stringArray).buildProp();
        Property property = sb.getProperties().get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:", property.getString());
    }


    @Test
    public void testAdd_StringArray() {
        StateBuilder sb = new StateBuilder();
        String[] stringArray = {"a", "b"};
        sb.addProp().key("key1").val(stringArray).buildProp();
        Property property = sb.getProperties().get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:(a, b)", property.getString());
    }


    @Test
    public void testAdd_StringList_null() {
        StateBuilder sb = new StateBuilder();
        List<String> stringList = null;
        sb.addProp().key("key1").val(stringList).buildProp();
        Property property = sb.getProperties().get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:", property.getString());
    }


    @Test
    public void testAdd_StringList() {
        StateBuilder sb = new StateBuilder();
        List<String> stringList = Arrays.asList("A", "B");
        sb.addProp().key("key1").val(stringList).buildProp();
        Property property = sb.getProperties().get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:(A, B)", property.getString());
    }


    @Test
    public void testAddString_null() {
        StateBuilder sb = new StateBuilder();
        String string = null;
        sb.addProp().key("key1").val(string).buildProp();
        Property property = sb.getProperties().get(0);
        assertEquals("key1", property.getKey());
        assertEquals("key1:", property.getString());
    }


    @Test
    public void testAddIStateNode_null() {
        StateBuilder sb = new StateBuilder();
        IStateNode child = null;
        sb.addChild("key1", child);
        assertEquals(0, sb.getProperties().size());
    }


    @Test
    public void testAddIterableIStateNode_null() {
        StateBuilder sb = new StateBuilder();
        List<IStateNode> children = null;
        sb.addChildren("key1", children);
        assertEquals(0, sb.getProperties().size());
    }


    @Test
    public void testConstructor() {
        State superState = new StateBuilder()
                .addProp().key("key1").val("val1").buildProp()
                .addProp().key("key2").val("val2").buildProp()
                .build();
        State subState = State.getBuilder(superState)
                .addProp().key("key3").val("val3").buildProp()
                .build();
        List<Property> props = subState.mProperties;
        assertEquals(3, props.size());
        assertEquals("key1", props.get(0).getKey());
        assertEquals("key2", props.get(1).getKey());
        assertEquals("key3", props.get(2).getKey());
    }
}