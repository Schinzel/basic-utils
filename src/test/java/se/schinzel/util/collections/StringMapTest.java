package se.schinzel.util.collections;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class StringMapTest {
    
    public StringMapTest() {
    }
    

    @BeforeClass
    public static void setUpClass() {
    }
    

    @AfterClass
    public static void tearDownClass() {
    }
    

    @Before
    public void setUp() {
    }
    

    @After
    public void tearDown() {
    }


    /**
     * Test of create method, of class StringMap.
     */
    @Test
    public void testCreate_0args() {
        StringMap map = StringMap.create();
        assertEquals(StringMap.class.getSimpleName(), map.getClass().getSimpleName());
        assertEquals(0, map.size());
    }


    /**
     * Test of create method, of class StringMap.
     */
    @Test
    public void testCreate_String_String() {
        String key = "theKey";
        String value = "theValue";
        StringMap map = StringMap.create(key, value);
        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }


    /**
     * Test of add method, of class StringMap.
     */
    @Test
    public void testAdd() {
        StringMap map = StringMap.create()
                .add("theKey", "theValue")
                .add("theKey2", "theValue2");
        assertEquals(2, map.size());
        assertEquals("theValue", map.get("theKey"));
        assertEquals("theValue2", map.get("theKey2"));
    }
    
}
