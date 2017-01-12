package io.schinzel.basicutils.collections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The purpose of this class is to cache data.
 *
 * @author schinzel
 * @param <K>
 * @param <V>
 */
public class Cache<K, V> {
    /**
     * Holds the data cached.
     */
    private final Map<K, V> mCache = new ConcurrentHashMap<>();
    /**
     * The number of times this cache has returned a cached value. Useful for 
     * testing. 
     */
    private long mHits;
    
    
    /**
     *
     * @param key
     * @return The value with the argument key.
     */
    public V get(K key) {
        if (has(key)) {
            mHits++;
            return mCache.get(key);
        }

        return null;
    }

    /**
     * 
     * @return Get the keys held by this cache. 
     */
    public Set<K> getKeys() {
        return mCache.keySet();
    }

    /**
     * Check if map contains key
     *
     * @param key
     * @return True if this contains argument key. Else false.
     */
    public boolean has(K key) {
        return mCache.containsKey(key);
    }

    /**
     * Add the argument key and value to cache.
     *
     * @param key
     * @param value
     * @return This for chaining.
     */
    public Cache<K, V> put(K key, V value) {
        mCache.put(key, value);
        return this;
    }

    /**
     * Clears all data from cache. Held values and stats.
     */
    public void invalidate() {
        mCache.clear();
        mHits = 0;
    }

    /**
     * Get number of key/value pairs help by cache.
     *
     * @return
     */
    public int cacheSize() {
        return mCache.size();
    }

    /**
     * Number of cache hits.
     *
     * @return
     */
    public long cacheHits() {
        return mHits;
    }

}
