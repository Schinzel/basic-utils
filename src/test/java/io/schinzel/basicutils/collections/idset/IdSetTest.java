package io.schinzel.basicutils.collections.idset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author schinzel
 */
public class IdSetTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    class MyVal implements IdSetValue {

        @Getter
        private final IdObj idObj;


        MyVal(String id) {
            idObj = new IdObj(id);
        }
    }


    @Test
    public void testSetCollectionName() {
        Assert.assertEquals("MyCollName", IdSet.create("MyCollName").mCollectionName);
    }


    @Test
    public void testAdd_sameIdTwice() {
        IdSet<MyVal> coll1 = IdSet.<MyVal>create().add(new MyVal("MyName1"));
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        exception.expect(RuntimeException.class);
        coll.add(new MyVal("MyName2"));
    }


    @Test
    public void testSize() {
        IdSet<MyVal> coll = IdSet.<MyVal>create();
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
    public void testGet() {
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        MyVal myValue = coll.get("MyName2");
        Assert.assertEquals("MyName2", myValue.getId());
        exception.expect(RuntimeException.class);
        coll.get("no name");
    }


    @Test
    public void testGetWithWildCards() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        //
        actual = coll.getUsingWildCards("Ma*");
        expected = Arrays.asList(man1, man2);
        Assert.assertThat(actual, Matchers.is(expected));
        //
        actual = coll.getUsingWildCards("Man*");
        expected = Arrays.asList(man1, man2);
        Assert.assertThat(actual, Matchers.is(expected));
        //
        actual = coll.getUsingWildCards("Man2*");
        expected = Arrays.asList(man2);
        Assert.assertThat(actual, Matchers.is(expected));
        //
        actual = coll.getUsingWildCards("M*n*");
        expected = Arrays.asList(man1, man2, moon1);
        Assert.assertThat(actual, Matchers.is(expected));
        //
        actual = coll.getUsingWildCards("*1");
        expected = Arrays.asList(bird1, man1, moon1);
        Assert.assertThat(actual, Matchers.is(expected));
    }


    @Test
    public void testGetWithWildCards_AlphabeticalReturn() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        actual = coll.getUsingWildCards("*");
        expected = Arrays.asList(bird1, bird2, man1, man2, moon1);
        Assert.assertThat(actual, Matchers.is(expected));
    }


    @Test
    public void testGetList() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        //
        actual = coll.get(Arrays.asList("Man1"));
        expected = Arrays.asList(man1);
        Assert.assertThat(actual, Matchers.is(expected));
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"));
        expected = Arrays.asList(bird2, man1);
        Assert.assertThat(actual, Matchers.is(expected));
    }


    @Test
    public void testGetList_throwErrorArg() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"));
        expected = Arrays.asList(bird2, man1);
        Assert.assertThat(actual, Matchers.is(expected));
        //
        exception.expect(RuntimeException.class);
        coll.get(Arrays.asList("Bird2", "I_DO_NOT_EXIST", "Man1", "NEITHER DO I"));
    }


    @Test
    public void testGet_emptyList() {
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        List<MyVal> result = coll.get((List<String>) null);
        Assert.assertTrue(result.isEmpty());
        result = coll.get(new ArrayList<>(0));
        Assert.assertTrue(result.isEmpty());
    }


    /**
     * Test that the elements are returned in order.
     */
    @Test
    public void testOrder() {
        IdSet<MyVal> coll = IdSet.<MyVal>create()
                .add(new MyVal("MyName2"))
                .add(new MyVal("myName1"))
                .add(new MyVal("MyName3"))
                .add(new MyVal("myName4"))
                .add(new MyVal("C"))
                .add(new MyVal("A"))
                .add(new MyVal("B"));
        List<String> expected = Arrays.asList("A", "B", "C", "myName1", "MyName2", "MyName3", "myName4");
        Iterator<String> it = expected.iterator();
        for (MyVal myVal : coll) {
            Assert.assertEquals(it.next(), myVal.getId());
        }
    }


    @Test
    public void testValues() {
        MyVal val1 = new MyVal("C");
        MyVal val2 = new MyVal("A");
        MyVal val3 = new MyVal("B");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
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
        IdSet<MyVal> coll = IdSet.<MyVal>create()
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
        IdSet<MyVal> coll = IdSet.<MyVal>create().add(new MyVal("A")).add(new MyVal("B"));
        exception.expect(RuntimeException.class);
        coll.addAlias("B", "A");
    }


    @Test
    public void testAlias_addSameAliasTwice() {
        IdSet<MyVal> coll = IdSet.<MyVal>create().add(new MyVal("A")).add(new MyVal("B"));
        coll.addAlias("B", "alias1");
        exception.expect(RuntimeException.class);
        coll.addAlias("A", "alias1");
    }


    @Test
    public void testAlias_addValueWhenThereExistsValueWithId() {
        IdSet<MyVal> coll = IdSet.<MyVal>create().add(new MyVal("A")).add(new MyVal("B"));
        exception.expect(RuntimeException.class);
        coll.addAlias("A", "B");
    }


    @Test
    public void getAlias_differentGetMethods() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
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
        List<MyVal> expected = Arrays.asList(valA, valA, valB, valB);
        Assert.assertThat(list, Matchers.is(expected));
        //
        list = coll.get(Arrays.asList("A", "alias1", "B", "alias3"));
        expected = Arrays.asList(valA, valA, valB, valB);
        Assert.assertThat(list, Matchers.is(expected));
    }


    @Test
    public void alias_testRemove() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        IdSet<MyVal> coll = IdSet.<MyVal>create()
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
        exception.expect(RuntimeException.class);
        coll.addAlias("B", "alias1");
    }


    @Test
    public void testIsEmpty() {
        IdSet<MyVal> coll = IdSet.create();
        Assert.assertTrue(coll.isEmpty());
        coll.add(new MyVal("A"));
        Assert.assertFalse(coll.isEmpty());
        coll.remove("A");
        Assert.assertTrue(coll.isEmpty());
    }


    @Test
    public void testAddAndReturn() {
        IdSet<MyVal> coll = IdSet.create();
        MyVal myVal = new MyVal("A");
        MyVal myVal2 = coll.addAndGet(myVal);
        Assert.assertEquals(myVal2, myVal);
    }
}