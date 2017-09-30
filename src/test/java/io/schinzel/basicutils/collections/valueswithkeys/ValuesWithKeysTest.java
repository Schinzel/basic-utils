package io.schinzel.basicutils.collections.valueswithkeys;

import io.schinzel.basicutils.RandomUtil;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(JUnitParamsRunner.class)
public class ValuesWithKeysTest {

    @AllArgsConstructor
    private class MyVal implements IValueWithKey {

        @Getter
        private final String key;
    }


    @Test
    public void getCollectionName_SetNameWithConstructor_CollectionName() {
        String collectionName = RandomUtil.getRandomString(10);
        ValuesWithKeys<IValueWithKey> valueWithKeys = ValuesWithKeys.create(collectionName);
        assertThat(valueWithKeys.getCollectionName()).isEqualTo(collectionName);
    }


    @Test
    public void add_SameKeyTwice_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.add(new MyVal("B")));
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
    @Parameters({"0", "1", "5"})
    public void size_AddValues_AddedValuesCount(int numValuesToAdd) {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("AnyName");
        IntStream.range(0, numValuesToAdd)
                .mapToObj(String::valueOf)
                .forEach(i -> coll.add(new MyVal(i)));
        assertThat(coll.size())
                .isEqualTo(numValuesToAdd);
    }


    @Test
    @Parameters({"10,9", "5, 5", "10,1"})
    public void size_AddAndRemoveValues_AddValuesMinusRemoved(int numValuesToAdd, int numValuesToRemove) {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("AnyName");
        IntStream.range(0, numValuesToAdd)
                .mapToObj(String::valueOf)
                .forEach(i -> coll.add(new MyVal(i)));
        IntStream.range(0, numValuesToRemove)
                .mapToObj(String::valueOf)
                .forEach(i -> coll.remove(i));
        assertThat(coll.size())
                .isEqualTo(numValuesToAdd - numValuesToRemove);
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
    public void getUsingWildCards_WildcardAtEnd_MatchingValuesInAlphabeticalOrder() {
        List<MyVal> values = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("Ma*");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactlyInAnyOrder("Man1", "Man2");
    }


    @Test
    public void getUsingWildCards_WildcardInBeginning_MatchingValuesInAlphabeticalOrder() {
        List<MyVal> values = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("*1");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactlyInAnyOrder("Man1", "Bird1", "Moon1");
    }


    @Test
    public void getUsingWildCards_WildcardInMiddle_MatchingValuesInAlphabeticalOrder() {
        List<MyVal> values = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("M*1");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactlyInAnyOrder("Man1", "Moon1");
    }


    @Test
    public void getUsingWildCards_WildcardInMiddleLinkedHashMap_MatchingValuesInInsertionOrder() {
        List<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("*1");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactlyInAnyOrder("Moon1", "Man1", "Bird1");
    }


    @Test
    public void get_EmptyList_EmptyList() {
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"));
        assertThat(values.get(Collections.emptyList())).isEmpty();
    }


    @Test
    public void get_Null_EmptyList() {
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"));
        List<String> arg = null;
        assertThat(values.get(arg)).isEmpty();
    }


    @Test
    public void get_ListWithNonExistingValues_Exception() {
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"));
        List<String> arg = Arrays.asList("I_do_not_exist");
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> values.get(arg));
    }


    @Test
    public void get_List_ElementsInListInArgumentListOrder() {
        List<String> arg = Arrays.asList("Bird1", "Moon1", "Man1");
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"));
        List<String> actual = values
                .get(arg)
                .stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        assertThat(actual).containsSequence(arg);
    }


    @Test
    public void get_OneElemList_ElementsInListInArgumentListOrder() {
        List<String> arg = Arrays.asList("Bird1");
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"));
        List<String> actual = values
                .get(arg)
                .stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        assertThat(actual).containsSequence(arg);
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
