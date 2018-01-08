package io.schinzel.basicutils.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The purpose of this class is to cache data and offer hits statistics to
 * facilitate unit tests.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 * @author schinzel
 */
@SuppressWarnings("WeakerAccess")
public class Cache<K, V> {
    /** Holds the data cached. */
    private final Map<K, V> mCache = new ConcurrentHashMap<>();
    /** The number of times this cache has returned a cached value. Useful for testing. */
    private long mHits;


    /**
     * If no key exists an error is thrown.
     *
     * @param key The key for which to get a value.
     * @return The value with the argument key.
     */
    public V get(K key) {
        if (has(key)) {
            mHits++;
            return mCache.get(key);
        }
        throw new RuntimeException("Tried to get value for key '" + key + "' that does not exist in cache");
    }


    /**
     * @return The keys held by this cache
     */
    public Set<K> getKeys() {
        return mCache.keySet();
    }


    /**
     * @return The values held by this cache
     */
    public Collection<V> getValues() {
        return mCache.values();
    }


    /**
     * @param key The key to check if exits in collection.
     * @return True if contains argument key. Else false.
     */
    public boolean has(K key) {
        return mCache.containsKey(key);
    }


    /**
     * Add the argument key and value to cache.
     *
     * @param key   The key with which the argument value is to be associated
     * @param value The value to be associated with the specified key.
     * @return This for chaining.
     */
    public Cache<K, V> put(K key, V value) {
        mCache.put(key, value);
        return this;
    }


    /**
     * @param key   The key to check if exits in collection.
     * @param value The value to be associated with the specified key.
     * @return The argument value.
     */
    public V putAndGet(K key, V value) {
        this.put(key, value);
        return value;
    }


    /**
     * Clears all data from cache. Held values and statistics.
     */
    public void invalidate() {
        mCache.clear();
        mHits = 0;
    }


    /**
     * Get number of key/value pairs help by cache.
     *
     * @return The size of the cache.
     */
    public int cacheSize() {
        return mCache.size();
    }


    /**
     * Number of cache hits.
     *
     * @return The number of times a requested lookup found a value.
     */
    public long cacheHits() {
        return mHits;
    }


}
