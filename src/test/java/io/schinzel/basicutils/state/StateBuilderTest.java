package io.schinzel.basicutils.state;

import com.google.common.collect.ImmutableList;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.RandomUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


public class StateBuilderTest {


    @Test
    public void ifTrue_False_ShouldNotAdd() {
        int actual = new StateBuilder()
                .add("k1", "v1")
                .ifTrue(false)
                .add("k2", "v2")
                .endIf()
                .add("k3", "v3")
                .build().getProperties().size();
        assertThat(actual).isEqualTo(2);
    }


    @Test
    public void ifTrue_True_ShouldAdd() {
        int actual = new StateBuilder()
                .add("k1", "v1")
                .ifTrue(true)
                .add("k2", "v2")
                .endIf()
                .add("k3", "v3")
                .build().getProperties().size();
        assertThat(actual).isEqualTo(3);
    }


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
    public void addChildren_OneChildList_OneChildListInCollection() {
        ImmutableList<IStateNode> children = new ImmutableList.Builder<IStateNode>()
                .add(new MyStateNode())
                .add(new MyStateNode())
                .build();
        int childSize = new StateBuilder()
                .addChildren("KeyForChildren", children)
                .getChildLists()
                .size();
        assertThat(childSize).isEqualTo(1);
    }


    @Test
    public void addChildren_EmptyChildList_CollectionUnchanged() {
        int childSize = new StateBuilder()
                .addChildren("KeyForChildren", EmptyObjects.emptyList())
                .getChildLists()
                .size();
        assertThat(childSize).isZero();
    }


    @Test
    public void addChildren_NullChildList_CollectionUnchanged() {
        int childSize = new StateBuilder()
                .addChildren("KeyForChildren", null)
                .getChildLists()
                .size();
        assertThat(childSize).isZero();
    }


    private class MyStateNode implements IStateNode {
        @Override
        public State getState() {
            return State.getBuilder()
                    .addProp().key("k2").val(RandomUtil.getRandomString(3)).buildProp()
                    .build();
        }
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
        List<Property> props = subState.getProperties();
        assertEquals(3, props.size());
        assertEquals("key1", props.get(0).getKey());
        assertEquals("key2", props.get(1).getKey());
        assertEquals("key3", props.get(2).getKey());
    }


    @Test
    public void testAdd_string() {
        StateBuilder sb = new StateBuilder().add("key1", "val1");
        assertEquals("key1", sb.getProperties().get(0).getKey());
        assertEquals("val1", sb.getProperties().get(0).getValueAsString());
    }


    @Test
    public void testAdd_int() {
        StateBuilder sb = new StateBuilder().add("key1", 123);
        assertEquals("key1", sb.getProperties().get(0).getKey());
        assertEquals(123L, sb.getProperties().get(0).getValueAsObject());
    }


    @Test
    public void testAdd_bool() {
        StateBuilder sb = new StateBuilder().add("key1", true);
        assertEquals("key1", sb.getProperties().get(0).getKey());
        assertEquals(true, sb.getProperties().get(0).getValueAsObject());
    }


    @Test
    public void testAdd_long() {
        StateBuilder sb = new StateBuilder().add("key1", 123L);
        assertEquals("key1", sb.getProperties().get(0).getKey());
        assertEquals(123L, sb.getProperties().get(0).getValueAsObject());
    }

}