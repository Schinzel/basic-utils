package io.schinzel.basicutils.collections.valueswithkeys;

import io.schinzel.basicutils.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ValuesWithKeysTest {

    @AllArgsConstructor
    private class MyVal implements IValueWithKey {

        @Getter
        private final String key;
    }


    @Test
    public void testSetCollectionName() {
        Assert.assertEquals("MyCollName", ValuesWithKeys.create("MyCollName").mCollectionName);
    }


    @Test
    public void add_SameKeyTwice_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.add(new MyVal("MyName2")));
    }


    @Test
    public void getCollectionName_SetNameWithConstructor_ConstructorSetName() {
        String expected = RandomUtil.getRandomString(50);
        String actual = ValuesWithKeys.create(expected).getCollectionName();
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void create_CollectionNameEmptyString_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                ValuesWithKeys.create("")
        );
    }


    @Test
    public void create_CollectionNameNull_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                ValuesWithKeys.create(null)
        );
    }


    @Test
    public void testSize() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("MyCollName");
        Assert.assertEquals(0, coll.size());
        coll.add(new MyVal("MyName1"));
        Assert.assertEquals(1, coll.size());
        coll.add(new MyVal("MyName2"));
        Assert.assertEquals(2, coll.size());
        coll.add(new MyVal("MyName3"));
        Assert.assertEquals(3, coll.size());
        coll.remove("MyName3");
        Assert.assertEquals(2, coll.size());
        coll.remove("MyName1");
        Assert.assertEquals(1, coll.size());
        coll.remove("MyName2");
        Assert.assertEquals(0, coll.size());
    }


    @Test
    public void get_NoValueWithKey_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.get("D"));
    }


    @Test
    public void get_ValueWithArgumentKeyExists_ValueWithArgumentKey() {
        MyVal b = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(b)
                .add(new MyVal("C"));
        assertThat(coll.get("B")).isEqualTo(b);
    }


    @Test
    public void testGetWithWildCards() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual;
        //
        actual = coll.getUsingWildCards("Ma*");
        assertThat(actual).containsExactly(man1, man2);
        //
        actual = coll.getUsingWildCards("Man*");
        assertThat(actual).containsExactly(man1, man2);
        //
        actual = coll.getUsingWildCards("man*");
        assertThat(actual).containsExactly(man1, man2);
        //
        actual = coll.getUsingWildCards("man2");
        assertThat(actual).containsExactly(man2);
        //
        actual = coll.getUsingWildCards("Man2*");
        assertThat(actual).containsExactly(man2);
        //
        actual = coll.getUsingWildCards("M*n*");
        assertThat(actual).containsExactly(man1, man2, moon1);
        //
        actual = coll.getUsingWildCards("*1");
        assertThat(actual).containsExactly(bird1, man1, moon1);
    }


    @Test
    public void testGetWithWildCards_AlphabeticalReturn() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual = coll.getUsingWildCards("*");
        assertThat(actual).containsExactly(bird1, bird2, man1, man2, moon1);
    }


    @Test
    public void testGetList() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual;
        //
        actual = coll.get(Collections.singletonList("Man1"));
        assertThat(actual).containsExactly(man1);
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"));
        assertThat(actual).containsExactly(bird2, man1);
    }


    @Test
    public void testGetList_throwErrorArg() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual;
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"));
        assertThat(actual).containsExactly(bird2, man1);
        //
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.get(Arrays.asList("Bird2", "I_DO_NOT_EXIST", "Man1", "NEITHER DO I")));
    }


    @Test
    public void testGet_emptyList() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        List<MyVal> result = coll.get((List<String>) null);
        Assert.assertTrue(result.isEmpty());
        result = coll.get(new ArrayList<>(0));
        Assert.assertTrue(result.isEmpty());
    }


    @Test
    public void values_ValuesAddedInNotAlphabeticOrder_AlphabeticallySorted() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("MyName2"))
                .add(new MyVal("myName1"))
                .add(new MyVal("MyName3"))
                .add(new MyVal("myName4"))
                .add(new MyVal("C"))
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        List<String> actualKeys = coll.values().stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        List<String> expectedKeys = Arrays.asList("A", "B", "C", "myName1", "MyName2", "MyName3", "myName4");
        assertThat(actualKeys).isEqualTo(expectedKeys);
    }


    @Test
    public void values_LinkedHashMapValuesAddedInNotAlphabeticOrder_InsertionOrder() {
        ValuesWithKeys<MyVal> coll = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("MyName2"))
                .add(new MyVal("myName1"))
                .add(new MyVal("MyName3"))
                .add(new MyVal("myName4"))
                .add(new MyVal("C"))
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        List<String> actualKeys = coll.values().stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        List<String> expectedKeys = Arrays.asList("MyName2", "myName1", "MyName3", "myName4", "C", "A", "B");
        assertThat(actualKeys).isEqualTo(expectedKeys);
    }


    @Test
    public void iterator_AddValues_SameValuesButInAlphanumOrder() {
        MyVal c = new MyVal("C");
        MyVal a = new MyVal("A");
        MyVal b = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(c)
                .add(a)
                .add(b);
        assertThat(coll.iterator()).containsExactly(a, b, c);
    }


    @Test
    public void testValues() {
        MyVal val1 = new MyVal("C");
        MyVal val2 = new MyVal("A");
        MyVal val3 = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(val1)
                .add(val2)
                .add(val3);
        Collection<MyVal> values = coll.values();
        Assert.assertEquals(3, values.size());
        Assert.assertTrue(values.contains(val1));
        Assert.assertTrue(values.contains(val2));
        Assert.assertTrue(values.contains(val3));
    }


    @Test
    public void testAlias() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(valC)
                .add(valA)
                .add(valB)
                .addAlias("A", "alias1")
                .addAlias("A", "alias2")
                .addAlias("B", "alias3");
        Assert.assertEquals(3, coll.values().size());
        Assert.assertEquals(valA, coll.get("A"));
        Assert.assertEquals(valA, coll.get("alias1"));
        Assert.assertEquals(valA, coll.get("alias2"));
        Assert.assertEquals(valB, coll.get("B"));
        Assert.assertEquals(valB, coll.get("alias3"));
        Assert.assertEquals(valC, coll.get("C"));
    }


    @Test
    public void testAlias_addAliasIdExists() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName").add(new MyVal("A")).add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.addAlias("B", "A"));
    }


    @Test
    public void testAlias_addSameAliasTwice() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName").add(new MyVal("A")).add(new MyVal("B"));
        coll.addAlias("B", "alias1");
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.addAlias("A", "alias1"));
    }


    @Test
    public void testAlias_addValueWhenThereExistsValueWithId() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName").add(new MyVal("A")).add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.addAlias("A", "B"));
    }


    @Test
    public void getAlias_differentGetMethods() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(valC)
                .add(valA)
                .add(valB)
                .addAlias("A", "alias1")
                .addAlias("A", "alias2")
                .addAlias("B", "alias3");
        Assert.assertEquals(valA, coll.get("A"));
        Assert.assertEquals(valA, coll.get("alias1"));
        Assert.assertEquals(valA, coll.get("alias1"));
        //
        List<MyVal> list = coll.get(Arrays.asList("A", "alias1", "B", "alias3"));
        assertThat(list).containsExactly(valA, valA, valB, valB);
        //
        list = coll.get(Arrays.asList("A", "alias1", "B", "alias3"));
        assertThat(list).containsExactly(valA, valA, valB, valB);
    }


    @Test
    public void alias_testRemove() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(valC)
                .add(valA)
                .add(valB)
                .addAlias("A", "alias1")
                .addAlias("A", "alias2")
                .addAlias("B", "alias3");
        //Should also remove alias1 and alias2
        coll.remove("A");
        //If alias1 and alias2 are remove, it should be possible to use them again
        coll.addAlias("C", "alias1");
        coll.addAlias("C", "alias2");
        //But adding the alias again should yield an error
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.addAlias("B", "alias1"));

    }


    @Test
    public void testIsEmpty() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("MyCollName");
        Assert.assertTrue(coll.isEmpty());
        coll.add(new MyVal("A"));
        Assert.assertFalse(coll.isEmpty());
        coll.remove("A");
        Assert.assertTrue(coll.isEmpty());
    }


    @Test
    public void testAddAndReturn() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("MyCollName");
        MyVal myVal = new MyVal("A");
        MyVal myVal2 = coll.addAndGet(myVal);
        Assert.assertEquals(myVal2, myVal);
    }


    @Test
    public void testClear() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(valC)
                .add(valA)
                .add(valB)
                .addAlias("A", "alias1")
                .addAlias("A", "alias2")
                .addAlias("B", "alias3");
        Assert.assertEquals(3, coll.size());
        Assert.assertEquals(false, coll.isEmpty());
        coll.clear();
        Assert.assertEquals(0, coll.size());
        Assert.assertEquals(true, coll.isEmpty());
    }


    @Test
    public void testStream() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(valC)
                .add(valA)
                .add(valB);
        Stream<MyVal> stream = coll.stream();
        Assert.assertEquals(3, stream.count());
    }
}
