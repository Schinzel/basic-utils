package io.schinzel.basicutils.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to provide a less verbose way to set up maps.
 *
 * @author schinzel
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class MapBuilder<K, V> {
    protected final Map<K, V> mMap;


    public static MapBuilder create() {
        return new MapBuilder();
    }


    public MapBuilder() {
        mMap = new HashMap<>();
    }


    public MapBuilder add(K key, V value) {
        mMap.put(key, value);
        return this;
    }


    public Map getMap() {
        return mMap;
    }

}
