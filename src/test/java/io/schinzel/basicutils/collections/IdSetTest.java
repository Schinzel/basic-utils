package io.schinzel.basicutils.collections;

import java.util.Arrays;
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

    class MyVal implements IValue {

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
        actual = coll.get(Arrays.asList("Man1"), true);
        expected = Arrays.asList(man1);
        Assert.assertThat(actual, is(expected));
        //
        actual = coll.get(Arrays.asList("Bird2", "Man1"), true);
        expected = Arrays.asList(bird2, man1);
        Assert.assertThat(actual, is(expected));
    }


    @Test
    public void testGetList_throwErrorArg() {

    }
}
