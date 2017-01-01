package se.schinzel.util.collections;

import java.util.HashMap;

/**
 * The purpose of this class is to offer a HashMap that supports chaining.
 *
 * @author Schinzel
 */
public class ChainedHashMap<K, V>  extends HashMap<K, V> {
    /**
     * Is really the HashMap.put method, but allows chaining. Updating
     *
     * @param key Non empty key
     * @param value Non null value
     * @return A reference to this.
     */
    public ChainedHashMap add(K key, V value) {
        if (key == null) {
            throw new RuntimeException("Null key is not allowed.");
        }
        if (value == null) {
            throw new RuntimeException("Null values is not allowed.");
        }
        this.put(key, value);
        return this;
    }

}
