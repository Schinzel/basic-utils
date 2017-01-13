package io.schinzel.basicutils.collections;

import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Schinzel
 */
public class MapBuilderTest {

    @Test
    public void testSomeMethod() {
        Map<String, Long> map = MapBuilder
                .create()
                .add("a", 1l)
                .add("b", 2l)
                .add("c", 3l)
                .getMap();
        Assert.assertEquals(3, map.size());
        Assert.assertEquals(new Long(1), map.get("a"));
        Assert.assertEquals(new Long(2), map.get("b"));
        Assert.assertEquals(new Long(3), map.get("c"));
    }

}
