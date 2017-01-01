package se.schinzel.util.collections;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Schinzel
 */
public class ChainedHashMapTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testBooleans(){
        ChainedHashMap<String, Boolean> map = new ChainedHashMap<>();
        map.add("k1", true);
        map.add("k2", false);
        assertTrue(map.get("k1"));
        assertFalse(map.get("k2"));
        //Change a value
        map.add("k1", false);
        assertFalse(map.get("k1"));
    }
 
    

    @Test
    public void testAdd() {
        ChainedHashMap<String, String> map = new ChainedHashMap<>();
        //test chaining
        assertEquals(map, map.add("k1", "v1"));

        //Test normal add
        map.add("k1", "v1");
        assertEquals("v1", map.get("k1"));

        //Test second add
        map.add("k2", "v2");
        assertEquals("v2", map.get("k2"));

        //Test over write
        map.add("k2", "v2b");
        assertEquals("v2b", map.get("k2"));
    }


    @Test
    public void testNullKey() {
        exception.expect(RuntimeException.class);
        ChainedHashMap map = new ChainedHashMap();
        map.add(null, "v1");
    }


    @Test
    public void testNullValue() {
        exception.expect(RuntimeException.class);
        ChainedHashMap map = new ChainedHashMap();
        map.add("k1", null);
    }

}
