package io.schinzel.basicutils.collections.valueswithkeys;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.thrower.Thrower;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The purpose of this class is offer a collection that stores values that hold their own keys.
 * The collection contains no duplicate keys.
 * The default order of the collection is alphabetically on the keys.
 * The collection has a name for clearer exception messages.
 *
 * @param <V> The type of element to store in the collection.
 * @author schinzel
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
@Accessors(prefix = "m")
public class ValuesWithKeys<V extends IValueWithKey> implements Iterable<V> {
    /** The internal storage. */
    private final Map<String, V> mValues;
    /** Holds mapping between aliases and keys. */
    final Map<String, String> mAliasToKeyMap = new HashMap<>();
    /** The name of this collection. */
    @Getter final String mCollectionName;
    /* Used to construct error messages. */
    private final String mErrorMessageSuffix;
    //*************************************************************************
    //* CONSTRUCTION
    //*************************************************************************


    public ValuesWithKeys(String collectionName) {
        /* Set internal storage to be an ordered map with sort order to be compareToIgnoreCase. */
        this(collectionName, new TreeMap<>(String::compareToIgnoreCase));
    }


    public ValuesWithKeys(String collectionName, Map<String, V> map) {
        Thrower.throwIfVarEmpty(collectionName, "collectionName");
        Thrower.throwIfVarNull(map, "map");
        mValues = map;
        mCollectionName = collectionName;
        mErrorMessageSuffix = " in collection named '" + mCollectionName + "'";
    }


    /**
     * @param collectionName The name of the collection. Useful for error messages and debugging.
     * @param <Q>            The class that will be added to this collection.
     * @return A freshly minted instance.
     */
    public static <Q extends IValueWithKey> ValuesWithKeys<Q> create(String collectionName) {
        return new ValuesWithKeys<>(collectionName);
    }
    //*************************************************************************
    //* ADD
    //*************************************************************************


    /**
     * @param value A value to addChild to collection.
     * @return This for chaining.
     */
    public ValuesWithKeys<V> add(V value) {
        Thrower.throwIfVarNull(value, "value");
        String key = value.getKey();
        Thrower.throwIfTrue(mAliasToKeyMap.containsKey(key), "Value with key '" + value.getKey() + "' cannot be added as there exists a value with this key as an alias" + mErrorMessageSuffix);
        Thrower.throwIfTrue(this.has(key), "Value with key '" + value.getKey() + "' cannot be added as collection already contains a value with this key" + mErrorMessageSuffix);
        mValues.put(key, value);
        return this;
    }


    /**
     * Adds an alias for a value with the argument key. For example: Lets assume there is an object
     * "thing" that has the key "myFunkyKey". The thing is added to the collection
     * with a couple of aliases
     * coll.addChild(thing).addAlias("myFunkyKey", "aliasA").addAlias("myFunkyKey", "aliasB");
     * <p>
     * Then all three below would return the thing:
     * coll.get("myFunkyKey")
     * coll.get("aliasA")
     * coll.get("aliasB")
     *
     * @param key   The key for which to associate the argument alias.
     * @param alias The alias to add for argument key.
     * @return This for chaining.
     */
    public ValuesWithKeys<V> addAlias(String key, String alias) {
        String errorMessagePrefix = "Alias '" + alias + "' cannot be added ";
        //If there exists no value with argument key
        Thrower.throwIfTrue(!this.has(key), errorMessagePrefix + "for value with key '" + key + "' as there exist no value with this key " + mErrorMessageSuffix);
        //if the argument value exists in the alias set
        Thrower.throwIfTrue(mAliasToKeyMap.containsKey(alias), errorMessagePrefix + "as another value already has this alias" + mErrorMessageSuffix);
        Thrower.throwIfTrue(this.has(alias), errorMessagePrefix + "as there already another value with this as key" + mErrorMessageSuffix);
        mAliasToKeyMap.put(alias, key);
        return this;
    }


    /**
     * @param value The value to add and return
     * @return The argument value
     */
    public V addAndGet(V value) {
        this.add(value);
        return value;
    }
    //*************************************************************************
    //* REMOVE
    //*************************************************************************


    /**
     * Removes the value with argument key from collection. If no value with argument key exists,
     * an error is thrown.
     *
     * @param key The key of the value to remove.
     * @return This for chaining.
     */
    public ValuesWithKeys<V> remove(String key) {
        Thrower.throwIfVarEmpty(key, "key");
        Thrower.throwIfTrue(!this.has(key), "Cannot remove value as there exists no value with key'" + key + "'" + mErrorMessageSuffix);
        //Remove all entries in alias map with argument key.
        //noinspection StatementWithEmptyBody
        while (mAliasToKeyMap.values().remove(key)) ;
        mValues.remove(key);
        return this;
    }


    /**
     * Removes all values and clears the aliases.
     *
     * @return This for chaining.
     */
    public ValuesWithKeys<V> clear() {
        mAliasToKeyMap.clear();
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
     * @param key The key to lookup
     * @return True if value with argument key is present in collection.
     */
    public boolean has(String key) {
        return mValues.containsKey(key);
    }


    @Nonnull
    @Override
    public Iterator<V> iterator() {
        return mValues.values().iterator();
    }
    //*************************************************************************
    //* GET VALUES
    //*************************************************************************


    /**
     * @param key The key of the value to return
     * @return The value with the argument key
     */
    public V get(String key) {
        Thrower.throwIfVarEmpty(key, "key");
        //Get the value of the argument key. If there is an alias for argument key
        V value = mAliasToKeyMap.containsKey(key)
                //Get the key of the alias and set the value with the retrieved key
                ? mValues.get(mAliasToKeyMap.get(key))
                //Set the value with argument key
                : mValues.get(key);
        //If no value was found, throw error. 
        Thrower.throwIfNull(value, "No value with key '" + key + "' in collection '" + mCollectionName + "'.");
        return value;
    }


    /**
     * @param keys The keys to find values for
     * @return A list of values that have the argument keys. The elements are returned in the order
     * of the argument list. If the argument list contains duplicates the elements returned will
     * contain duplicates.
     */
    public List<V> get(List<String> keys) {
        return Checker.isEmpty(keys)
                ? Collections.emptyList()
                : keys.stream().map(this::get).collect(Collectors.toList());
    }


    /**
     * Example: Collection: A1, A2, B1, B2, C1, C
     * Argument: "B*"
     * Output: B1,B2
     *
     * @param stringWithWildCards String to look up. Case insensitive. Wildcard is an astrix; "*".
     * @return A list of values that matches argument. In the order of the internal collection
     * which default is alphabetical.
     */
    public List<V> getWithWildCards(String stringWithWildCards) {
        String regex = "(?i)" + stringWithWildCards.replace("*", "\\w*");
        return this.stream()
                .filter(v -> v.getKey().matches(regex))
                .collect(Collectors.toList());
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
