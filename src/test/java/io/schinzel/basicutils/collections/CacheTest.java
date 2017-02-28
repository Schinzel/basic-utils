package io.schinzel.basicutils.collections;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class CacheTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testOverwrite() {
        Cache<String, String> cache = new Cache<>();
        cache.put("key1", "val1");
        assertEquals("val1", cache.get("key1"));
        cache.put("key1", "val2");
        assertEquals("val2", cache.get("key1"));
    }


    @Test
    public void testHits() {
        Cache<String, String> cache = new Cache<>();
        assertEquals(0, cache.cacheHits());
        cache.put("key1", "val1");
        cache.get("key1");
        assertEquals(1, cache.cacheHits());
        cache.get("key1");
        cache.get("key1");
        cache.get("key1");
        assertEquals(4, cache.cacheHits());
    }


    @Test
    public void testHas() {
        Cache<String, String> cache = new Cache<>();
        assertFalse(cache.has("key1"));
        cache.put("key1", "val1");
        assertTrue(cache.has("key1"));
    }


    @Test
    public void testInvalidate() {
        Cache<String, String> cache = new Cache<>();
        cache.put("key1", "val1");
        cache.get("key1");
        assertEquals(1, cache.cacheHits());
        assertEquals(1, cache.cacheSize());
        assertTrue(cache.has("key1"));
        cache.invalidate();
        assertEquals(0, cache.cacheHits());
        assertEquals(0, cache.cacheSize());
        assertFalse(cache.has("key1"));
    }


    @Test
    public void testDifferentTypes() {
        Cache<String, byte[]> byteArrCache = new Cache<>();
        byte[] val1 = "chimp".getBytes(Charset.forName("UTF-8"));
        byte[] val2 = "bird".getBytes(Charset.forName("UTF-8"));
        byte[] val3 = "bear".getBytes(Charset.forName("UTF-8"));
        byteArrCache.put("key1", val1);
        byteArrCache.put("key2", val2);
        byteArrCache.put("key3", val3);

        assertArrayEquals(val1, byteArrCache.get("key1"));
        assertArrayEquals(val2, byteArrCache.get("key2"));
        assertArrayEquals(val3, byteArrCache.get("key3"));
        //
        Cache<String, Integer> intCache = new Cache<>();
        intCache.put("key1", 1);
        Integer integer = Integer.valueOf(2);
        intCache.put("key2", integer);
        intCache.put("key3", 3);
        assertEquals(1, intCache.get("key1").intValue());
        assertEquals(2, intCache.get("key2").intValue());
        assertEquals(3, intCache.get("key3").intValue());
    }


    @Test
    public void testIntKeys() {
        Cache<Integer, Integer> IntIntCache = new Cache<>();
        IntIntCache.put(1, 1);
        IntIntCache.put(2, 2);
        IntIntCache.put(3, 3);

        assertEquals(1, IntIntCache.get(1).intValue());
        assertEquals(2, IntIntCache.get(2).intValue());
        assertEquals(3, IntIntCache.get(3).intValue());
        assertEquals(3, IntIntCache.cacheSize());
        assertEquals(3, IntIntCache.cacheHits());
        //
        Cache<Integer, String> intStringCache = new Cache<>();
        intStringCache.put(1, "value1");
        intStringCache.put(2, "value2");
        intStringCache.put(3, "value3");
        assertEquals("value1", intStringCache.get(1));
        assertEquals("value2", intStringCache.get(2));
        assertEquals("value3", intStringCache.get(3));
        assertEquals(3, intStringCache.cacheSize());
        assertEquals(3, intStringCache.cacheHits());
    }


    @Test
    public void testGetKeys() {
        Cache<String, Integer> cache = new Cache<>();
        cache.put("monkey", 1);
        cache.put("bear", 2);
        cache.put("bird", 2);
        Set<String> set = new HashSet<>();
        set.add("bear");
        set.add("monkey");
        set.add("bird");
        assertEquals(set, cache.getKeys());
        exception.expect(RuntimeException.class);
        cache.get("I_do_not_exist");
    }


    @Test
    public void testPutAndGet() {
        Cache<String, String> cache = new Cache<>();
        assertEquals("val1", cache.putAndGet("key1", "val1"));
        assertEquals(1, cache.cacheSize());
        assertEquals(0, cache.cacheHits());
        assertEquals("val1", cache.get("key1"));
        assertEquals(1, cache.cacheHits());
    }


}
