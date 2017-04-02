package io.schinzel.basicutils.collections.keyvalues;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.Thrower;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * The purpose of this class is offer a collection that stores values that hold their own unique string-keys.
 *
 * @param <V> The type of element to store in the collection.
 * @author schinzel
 */
public class KeyValues<V extends IValueKey> implements Iterable<V> {
    /**
     * The internal storage. Set key sort order to be compareToIgnoreCase.
     */
    private final TreeMap<String, V> mValues = new TreeMap<>(String::compareToIgnoreCase);
    /**
     * Holds mapping between aliases and keys.
     */
    private final Map<String, String> mAliasMap = new HashMap<>();
    /**
     * The name of this collection.
     */
    final String mName;
    //*************************************************************************
    //* CONSTRUCTION
    //*************************************************************************


    protected KeyValues(String collectionName) {
        mName = collectionName;
    }


    /**
     * @return A freshly minted instance.
     */
    public static <Q extends IValueKey> KeyValues<Q> create() {
        return new KeyValues<>(EmptyObjects.EMPTY_STRING);
    }


    /**
     * @param collectionName The name of the collection. Useful for error messages and debugging.
     * @return A freshly minted instance.
     */
    public static <Q extends IValueKey> KeyValues<Q> create(String collectionName) {
        return new KeyValues<Q>(collectionName);
    }
    //*************************************************************************
    //* ADD
    //*************************************************************************


    /**
     * @param value A value to addChild to collection.
     * @return This for chaining.
     */
    public KeyValues<V> add(V value) {
        Thrower.throwIfNull(value, "value");
        String key = value.getKey();
        Thrower.throwIfTrue(mAliasMap.containsKey(key), "Value cannot be added as there exists an alias with the same key.", "key", key, "collectionName", mName);
        Thrower.throwIfTrue(this.has(key), "Value cannot be added as a value with that key already exists", "key", key, "collectionName", mName);
        mValues.put(key, value);
        return this;
    }


    /**
     * Adds an alias for an key. For example: Lets assume there is an object
     * "thing" that returns the key "myFunkyKey". The thing is added to the collection
     * with a couple of aliases
     * coll.addChild(thing).addAlias("myFunkyKey", "aliasA").addAlias("myFunkyKey", "aliasB");
     * <p>
     * Then all three below would return the thing:
     * coll.get("theKey")
     * coll.get("aliasA")
     * coll.get("aliasB")
     *
     * @param key   The key for which to associate the argument alias.
     * @param alias The alias to addChild for argument key.
     * @return This for chaining.
     */
    public KeyValues<V> addAlias(String key, String alias) {
        Thrower.throwIfTrue(!this.has(key), "Alias cannot be added as there exist no value with this key in collection.", "alias", alias, "key", key, "collectionName", mName);
        //if the argument value exists in the alias set
        Thrower.throwIfTrue(mAliasMap.containsKey(alias), "Alias cannot be added as there already exists such an alias.", "alias", alias, "collectionName", mName);
        Thrower.throwIfTrue(this.has(alias), "Alias cannot be added as there exists a value with the same key.", "alias", alias, "collectionName", mName);
        mAliasMap.put(alias, key);
        return this;
    }


    /**
     * @param value The value to addChild and return.
     * @return The argument value.
     */
    public V addAndGet(V value) {
        this.add(value);
        return value;
    }
    //*************************************************************************
    //* REMOVE
    //*************************************************************************


    /**
     * Removes the value with argument key from collection. If no value with argument key exists, an error is thrown.
     *
     * @param key The key of the value to remove.
     * @return This for chaining.
     */
    public KeyValues<V> remove(String key) {
        Thrower.throwIfTrue(!this.has(key), "Cannot remove value as no such value exists.", "key", key, "collectionName", mName);
        //Remove all entries in alias map with argument key.
        while (mAliasMap.values().remove(key)) ;
        mValues.remove(key);
        return this;
    }


    /**
     * Removes all values and clears the aliases.
     *
     * @return This for chaining.
     */
    public KeyValues<V> clear() {
        mAliasMap.clear();
        mValues.clear();
        return this;
    }
    //*************************************************************************
    //* UTIL
    //*************************************************************************


    /**
     * @return The number of values in this set
     */
    public int size() {
        return mValues.size();
    }


    /**
     * @return True if there are no values in this set.
     */
    public boolean isEmpty() {
        return mValues.isEmpty();
    }


    /**
     * @param key The key to lookup.
     * @return True if value with argument key is present in set.
     */
    public boolean has(String key) {
        return mValues.containsKey(key);
    }


    @Override
    public Iterator<V> iterator() {
        return mValues.values().iterator();
    }
    //*************************************************************************
    //* GET VALUES
    //*************************************************************************


    /**
     * @param key The key to look up.
     * @return The value with the argument key.
     * @throws RuntimeException If there is no value with argument key.
     */
    public V get(String key) {
        Thrower.throwIfEmpty(key, "key");
        //If there exists an alias for argument key
        if (mAliasMap.containsKey(key)) {
            //Set the key to be the alias
            key = mAliasMap.get(key);
        }
        //Get the value of the argument key
        V value = mValues.get(key);
        //If no value was found, throw error. 
        Thrower.throwIfTrue(value == null, "No value with argument key in collection.", "key", key, "collectionName", mName);
        return value;
    }


    /**
     * @param keys The keys to find values for.
     * @return A list of values that have the argument keys. The elements
     * are returned in the order of the argument list.
     * @throws RuntimeException If one or more of the argument keys does
     *                          not exist.
     */
    public List<V> get(List<String> keys) {
        if (Checker.isEmpty(keys)) {
            return Collections.emptyList();
        }
        List<V> values = new ArrayList<>(keys.size());
        for (String key : keys) {
            V val = this.get(key);
            if (val != null) {
                values.add(val);
            }
        }
        return values;
    }


    /**
     * Example: Collection: A1, A2, B1, B2, C1, C
     * Argument: "B*"
     * Output: B1,B2
     *
     * @param stringWithWildCards String to look up. Case insensitive. Wildcard is an astrix; "*".
     * @return A list of values in alphabetical order that matches the argument.
     */
    public List<V> getUsingWildCards(String stringWithWildCards) {
        String regex =  "(?i)" + stringWithWildCards.replace("*", "\\w*");
        List<V> values = new ArrayList<>();
        for (Map.Entry<String, V> entry : mValues.entrySet()) {
            if (entry.getKey().matches(regex)) {
                values.add(entry.getValue());
            }
        }
        return values;
    }


    /**
     * @return The values held.
     */
    public Collection<V> values() {
        return mValues.values();
    }


    /**
     * @return Returns a sequential Stream with this collection as its source.
     */
    public Stream<V> stream() {
        return mValues.values().stream();
    }
}
