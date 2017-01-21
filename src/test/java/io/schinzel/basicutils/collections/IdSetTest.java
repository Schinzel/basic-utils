package io.schinzel.basicutils.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class IdSetTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    class MyVal implements IdSetValue {

        private final String mId;

        MyVal(String id) {
            mId = id;
        }


        @Override
        public String getid() {
            return mId;
        }


    }

    @Test
    public void testAdd_sameIdTwice() {
        IdSet<MyVal> coll = IdSet.create()
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        exception.expect(RuntimeException.class);
        coll.add(new MyVal("MyName2"));
    }


    @Test
    public void testSize() {
        IdSet<MyVal> coll = IdSet.create();
        assertEquals(0, coll.size());
        coll.add(new MyVal("MyName1"));
        assertEquals(1, coll.size());
        coll.add(new MyVal("MyName2"));
        assertEquals(2, coll.size());
        coll.add(new MyVal("MyName3"));
        assertEquals(3, coll.size());
        coll.remove("MyName3");
        assertEquals(2, coll.size());
        coll.remove("MyName1");
        assertEquals(1, coll.size());
        coll.remove("MyName2");
        assertEquals(0, coll.size());
    }


    @Test
    public void testGet() {
        IdSet<MyVal> coll = IdSet.create()
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        MyVal myValue = coll.get("MyName2");
        assertEquals("MyName2", myValue.getid());
        myValue = coll.get("no name");
        Assert.assertNull(myValue);
    }


    @Test
    public void testGetBooleanArg() {
        IdSet<MyVal> coll = IdSet.create()
                .add(new MyVal("MyName1"))
                .add(new MyVal("MyName2"))
                .add(new MyVal("MyName3"));
        MyVal myValue = coll.get("MyName2", true);
        assertEquals("MyName2", myValue.getid());
        myValue = coll.get("MyName2", false);
        assertEquals("MyName2", myValue.getid());
        myValue = coll.get("no name", false);
        Assert.assertNull(myValue);
        exception.expect(RuntimeException.class);
        coll.get("no name", true);
    }


    @Test
    public void testGetWithWildCards() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        //
        actual = coll.getUsingWildCards("Ma*");
        expected = Arrays.asList(man1, man2);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.getUsingWildCards("Man*");
        expected = Arrays.asList(man1, man2);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.getUsingWildCards("Man2*");
        expected = Arrays.asList(man2);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.getUsingWildCards("M*n*");
        expected = Arrays.asList(man1, man2, moon1);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.getUsingWildCards("*1");
        expected = Arrays.asList(bird1, man1, moon1);
        Assert.assertThat(actual, is(expected));
    }


    @Test
    public void testGetWithWildCards_AlphabeticalReturn() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        actual = coll.getUsingWildCards("*");
        expected = Arrays.asList(bird1, bird2, man1, man2, moon1);
        Assert.assertThat(actual, is(expected));
    }


    @Test
    public void testGetList() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        //
        actual = coll.get(Arrays.asList("Man1"));
        expected = Arrays.asList(man1);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"));
        expected = Arrays.asList(bird2, man1);
        Assert.assertThat(actual, is(expected));
    }


    @Test
    public void testGetList_throwErrorArg() {
        MyVal man1 = new MyVal("Man1");
        MyVal man2 = new MyVal("Man2");
        MyVal bird1 = new MyVal("Bird1");
        MyVal bird2 = new MyVal("Bird2");
        MyVal moon1 = new MyVal("Moon1");
        IdSet<MyVal> coll = IdSet.create()
                .add(man1)
                .add(man2)
                .add(bird1)
                .add(bird2)
                .add(moon1);
        List<MyVal> actual, expected;
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"), true);
        expected = Arrays.asList(bird2, man1);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.get(Arrays.asList("Bird2", "I_DO_NOT_EXIST", "Man1", "NEITHER DO I"), false);
        expected = Arrays.asList(bird2, man1);
        Assert.assertThat(actual, is(expected));
        //
        exception.expect(RuntimeException.class);
        coll.get(Arrays.asList("Bird2", "I_DO_NOT_EXIST", "Man1", "NEITHER DO I"), true);
    }


    @Test
    public void testGet_emptyList() {
        IdSet<MyVal> coll = IdSet.create()
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
        IdSet<MyVal> coll = IdSet.create()
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
            Assert.assertEquals(it.next(), myVal.getid());
        }
    }


    @Test
    public void testValues() {
        MyVal val1 = new MyVal("C");
        MyVal val2 = new MyVal("A");
        MyVal val3 = new MyVal("B");
        IdSet<MyVal> coll = IdSet.create()
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
        IdSet<MyVal> coll = IdSet.create()
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
        IdSet<MyVal> coll = IdSet.create().add(new MyVal("A")).add(new MyVal("B"));
        exception.expect(RuntimeException.class);
        coll.addAlias("B", "A");
    }


    @Test
    public void testAlias_addSameAliasTwice() {
        IdSet<MyVal> coll = IdSet.create().add(new MyVal("A")).add(new MyVal("B"));
        coll.addAlias("B", "alias1");
        exception.expect(RuntimeException.class);
        coll.addAlias("A", "alias1");
    }


    @Test
    public void testAlias_addValueWhenThereExistsValueWithId() {
        IdSet<MyVal> coll = IdSet.create().add(new MyVal("A")).add(new MyVal("B"));
        exception.expect(RuntimeException.class);
        coll.addAlias("A", "B");
    }


    @Test
    public void getAlias_differentGetMethods() {
        MyVal valC = new MyVal("C");
        MyVal valA = new MyVal("A");
        MyVal valB = new MyVal("B");
        IdSet<MyVal> coll = IdSet.create()
                .add(valC)
                .add(valA)
                .add(valB)
                .addAlias("A", "alias1")
                .addAlias("A", "alias2")
                .addAlias("B", "alias3");
        Assert.assertEquals(valA, coll.get("A", true));
        Assert.assertEquals(valA, coll.get("A", false));
        Assert.assertEquals(valA, coll.get("alias1", true));
        Assert.assertEquals(valA, coll.get("alias1", false));
        //
        List<MyVal> list = coll.get(Arrays.asList("A", "alias1", "B", "alias3"));
        List<MyVal> expected = Arrays.asList(valA, valA, valB, valB);
        Assert.assertThat(list, is(expected));
        //
        list = coll.get(Arrays.asList("A", "alias1", "B", "alias3"), true);
        expected = Arrays.asList(valA, valA, valB, valB);
        Assert.assertThat(list, is(expected));
    }


}
