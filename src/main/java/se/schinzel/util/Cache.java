package se.schinzel.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The purpose of this class is to cache data.
 * Modified to be able to have keys of K type. /Jorgen
 *
 * @author schinzel
 * @param <K>
 * @param <V>
 */
public class Cache<K, V> {
    private final Map<K, V> mCache = new ConcurrentHashMap<>();
    private long mHits;


    /**
     *
     * @param key
     * @return
     */
    public V get(K key) {
        if (has(key)) {
            mHits++;
            return mCache.get(key);
        }

        return null;
    }


    public Set<K> getKeys() {
        return mCache.keySet();
    }


    /**
     * Check if map contains key
     *
     * @param key
     * @return
     */
    public boolean has(K key) {
        return mCache.containsKey(key);
    }


    /**
     *
     * @param key
     * @param content
     */
    public void put(K key, V content) {
        mCache.put(key, content);
    }


    /**
     * function to clear map
     */
    public void invalidate() {
        mCache.clear();
        mHits = 0;
    }


    /**
     * Get number of key/value pair in map
     *
     * @return
     */
    public int cacheSize() {
        return mCache.size();
    }


    /**
     * Number of cache hits
     *
     * @return
     */
    public long cacheHits() {
        return mHits;
    }

}
