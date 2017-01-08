package io.schinzel.basicutils;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class EmptyObjectsTest {


    @Test
    public void testSomeMethod() {
        Assert.assertArrayEquals(new byte[0], EmptyObjects.EMPTY_BYTE_ARRAY);
        Assert.assertArrayEquals(new int[0], EmptyObjects.EMPTY_INT_ARRAY);
        Assert.assertArrayEquals(new String[0], EmptyObjects.EMPTY_STRING_ARRAY);
        Assert.assertArrayEquals(new Object[0], EmptyObjects.EMPTY_OBJECT_ARRAY);
        Assert.assertEquals("", EmptyObjects.EMPTY_STRING);
        Assert.assertEquals(new HashMap<>().size(),EmptyObjects.EMPTY_MAP.size());
    }

}