package io.schinzel.basicutils.collections.valueswithkeys;

import io.schinzel.basicutils.RandomUtil;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

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
                .add(new MyVal("A"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.add(new MyVal("A")));
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
    @Parameters({"10, 9", "5, 5", "10, 1"})
    public void size_AddAndRemoveValues_AddedValuesMinusRemoved(int numValuesToAdd, int numValuesToRemove) {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("AnyName");
        IntStream.range(0, numValuesToAdd)
                .mapToObj(String::valueOf)
                .forEach(i -> coll.add(new MyVal(i)));
        IntStream.range(0, numValuesToRemove)
                .mapToObj(String::valueOf)
                .forEach(coll::remove);
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
    public void getWithWildCards_WildcardAtEnd_MatchingValuesInAlphabeticalOrder() {
        List<MyVal> values = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("Ma*");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactly("Man1", "Man2");
    }


    @Test
    public void getWithWildCards_WildcardInBeginning_MatchingValuesInAlphabeticalOrder() {
        List<MyVal> values = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("*1");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactly("Bird1", "Man1", "Moon1");
    }


    @Test
    public void getWithWildCards_WildcardInMiddle_MatchingValuesInAlphabeticalOrder() {
        List<MyVal> values = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("M*1");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactly("Man1", "Moon1");
    }


    @Test
    public void getWithWildCards_WildcardInMiddleLinkedHashMap_MatchingValuesInInsertionOrder() {
        List<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"))
                .getWithWildCards("*1");
        assertThat(values.stream().map(MyVal::getKey))
                .containsExactly("Moon1", "Man1", "Bird1");
    }


    @Test
    public void get_EmptyList_EmptyList() {
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"));
        assertThat(values.get(Collections.emptyList())).isEmpty();
    }


    @Test
    public void get_NullList_EmptyList() {
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"));
        assertThat(values.get((List<String>) null)).isEmpty();
    }


    @Test
    public void get_ListWithNonExistingValues_Exception() {
        ValuesWithKeys<MyVal> values = new ValuesWithKeys<MyVal>("MyCollName", new LinkedHashMap<>())
                .add(new MyVal("Moon1"))
                .add(new MyVal("Man2"))
                .add(new MyVal("Man1"))
                .add(new MyVal("Bird2"))
                .add(new MyVal("Bird1"));
        List<String> arg = Collections.singletonList("I_do_not_exist");
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
        List<String> arg = Collections.singletonList("Bird1");
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
    public void iterator_AddValues_SameValuesButInAlphaNumericOrder() {
        MyVal c = new MyVal("C");
        MyVal a = new MyVal("A");
        MyVal b = new MyVal("B");
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(c)
                .add(a)
                .add(b);
        List<String> addedValues = coll.values().stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        assertThat(addedValues).containsExactly("A", "B", "C");
    }


    @Test
    public void add_AddValues_ExactlyThoseThreeValuesAreInCollection() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"));
        List<String> addedValues = coll.values().stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        assertThat(addedValues).containsExactly("A", "B", "C");
    }


    @Test
    public void get_ArgumentIsAnAlias_ValueWithAliasReturned() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"))
                .addAlias("B", "bee");
        String key = coll.get("bee").getKey();
        assertThat(key).isEqualTo("B");
    }


    @Test
    public void get_ArgumentIsListWith2AliasesAndKey_ValueReturnedThrice() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"))
                .addAlias("B", "bee1")
                .addAlias("B", "bee2");
        List<String> collect = coll.get(Arrays.asList("B", "bee1", "bee2"))
                .stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        assertThat(collect).containsExactly("B", "B", "B");
    }


    @Test
    public void addAlias_ArgumentAliasAlreadyExistsAsKey_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.addAlias("B", "A"));
    }


    @Test
    public void addAlias_ArgumentAliasAlreadyAssignedToOtherValue_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        coll.addAlias("B", "alias1");
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.addAlias("A", "alias1"));
    }


    @Test
    public void addAlias_ArgumentKeyDoesNotExist_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                coll.addAlias("no_such_key", "alias1")
        );
    }


    @Test
    public void remove_RemoveValue_ValueGone() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        coll.remove("A");
        assertThat(coll.has("A")).isFalse();
    }


    @Test
    public void remove_RemoveNonExistingValue_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.remove("C"));
    }

    @Test
    public void remove_NullArgument_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.remove(null));
    }


    @Test
    public void remove_EmptyStringArgument_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.remove(""));
    }

    @Test
    public void remove_ValueWithAlias_AliasGoneFromAliasCollection() {
        boolean aliasCollectionContainsKeyB = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .addAlias("B", "alias_for_b")
                .remove("B")
                .mAliasToKeyMap
                .containsKey("B");
        assertThat(aliasCollectionContainsKeyB).isFalse();
    }


    @Test
    public void remove_EmptyKeyValueCollection_Exception() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName");
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> coll.remove("AnyKey"));
    }


    @Test
    public void isEmpty_NewInstance_True() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("MyCollName");
        assertThat(coll.isEmpty())
                .isTrue();
    }


    @Test
    public void isEmpty_ElementAdded_False() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"));
        assertThat(coll.isEmpty())
                .isFalse();
    }


    @Test
    public void isEmpty_ElementAddedAndThenRemoved_True() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .remove("A");
        assertThat(coll.isEmpty())
                .isTrue();
    }


    @Test
    public void addAndGet_ValueAdded_Value() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.create("MyCollName");
        MyVal myVal = new MyVal("A");
        MyVal myVal2 = coll.addAndGet(myVal);
        assertThat(myVal2).isEqualTo(myVal);
    }


    @Test
    public void clear_EmptyCollection_WorksFine() {
        assertThatCode(() -> ValuesWithKeys.create("MyCollName").clear())
                .doesNotThrowAnyException();
    }


    @Test
    public void clear_CollectionWithValues_EmptyCollection() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .clear();
        assertThat(coll.isEmpty()).isTrue();
    }


    @Test
    public void stream_CollectionHasValues_AllValues() {
        ValuesWithKeys<MyVal> coll = ValuesWithKeys.<MyVal>create("MyCollName")
                .add(new MyVal("A"))
                .add(new MyVal("B"))
                .add(new MyVal("C"));
        List<String> collect = coll.stream()
                .map(MyVal::getKey)
                .collect(Collectors.toList());
        assertThat(collect).containsExactly("A", "B", "C");
    }

}
