package io.schinzel.basicutils.collections.namedvalues;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.Thrower;

import java.util.*;
import java.util.stream.Stream;

/**
 * The purpose of this class is offer a collection that stores values that hold their own unique
 * names.
 *
 * @param <V> The type of element to store in the collection.
 * @author schinzel
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class NamedValues<V extends INamedValue> implements Iterable<V> {
    /** The internal storage. Set sort order to be compareToIgnoreCase. */
    private final TreeMap<String, V> mValues = new TreeMap<>(String::compareToIgnoreCase);
    /** Holds mapping between aliases and names. */
    private final Map<String, String> mAliasMap = new HashMap<>();
    /** The name of this collection. */
    final String mCollectionName;
    //*************************************************************************
    //* CONSTRUCTION
    //*************************************************************************


    public NamedValues(String collectionName) {
        mCollectionName = collectionName;
    }


    /**
     * @param collectionName The name of the collection. Useful for error messages and debugging.
     * @param <Q>            The class that will be added to this collection.
     * @return A freshly minted instance.
     */
    public static <Q extends INamedValue> NamedValues<Q> create(String collectionName) {
        return new NamedValues<>(collectionName);
    }
    //*************************************************************************
    //* ADD
    //*************************************************************************


    /**
     * @param value A value to addChild to collection.
     * @return This for chaining.
     */
    public NamedValues<V> add(V value) {
        Thrower.throwIfVarNull(value, "value");
        String name = value.getName();
        Thrower.throwIfTrue(mAliasMap.containsKey(name), "Value cannot be added as there exists an alias with the same name.", "name", name, "collectionName", mCollectionName);
        Thrower.throwIfTrue(this.has(name), "Value cannot be added as a value with that name already exists", "name", name, "collectionName", mCollectionName);
        mValues.put(name, value);
        return this;
    }


    /**
     * Adds an alias for a name. For example: Lets assume there is an object
     * "thing" that returns the name "myFunkyName". The thing is added to the collection
     * with a couple of aliases
     * coll.addChild(thing).addAlias("myFunkyName", "aliasA").addAlias("myFunkyName", "aliasB");
     * <p>
     * Then all three below would return the thing:
     * coll.get("myFunkyName")
     * coll.get("aliasA")
     * coll.get("aliasB")
     *
     * @param name  The name for which to associate the argument alias.
     * @param alias The alias to addChild for argument name.
     * @return This for chaining.
     */
    public NamedValues<V> addAlias(String name, String alias) {
        Thrower.throwIfTrue(!this.has(name), "Alias cannot be added as there exist no value with this name in collection.", "alias", alias, "name", name, "collectionName", mCollectionName);
        //if the argument value exists in the alias set
        Thrower.throwIfTrue(mAliasMap.containsKey(alias), "Alias cannot be added as there already exists such an alias.", "alias", alias, "collectionName", mCollectionName);
        Thrower.throwIfTrue(this.has(alias), "Alias cannot be added as there exists a value with the same name.", "alias", alias, "collectionName", mCollectionName);
        mAliasMap.put(alias, name);
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
     * Removes the value with argument name from collection. If no value with argument name exists,
     * an error is thrown.
     *
     * @param name The name of the value to remove.
     * @return This for chaining.
     */
    public NamedValues<V> remove(String name) {
        Thrower.throwIfTrue(!this.has(name), "Cannot remove value as no such value exists.", "name", name, "collectionName", mCollectionName);
        //Remove all entries in alias map with argument name.
        while (mAliasMap.values().remove(name)) ;
        mValues.remove(name);
        return this;
    }


    /**
     * Removes all values and clears the aliases.
     *
     * @return This for chaining.
     */
    public NamedValues<V> clear() {
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
     * @param name The name to lookup.
     * @return True if value with argument name is present in set.
     */
    public boolean has(String name) {
        return mValues.containsKey(name);
    }


    @Override
    public Iterator<V> iterator() {
        return mValues.values().iterator();
    }
    //*************************************************************************
    //* GET VALUES
    //*************************************************************************


    /**
     * @param name The name to look up.
     * @return The value with the argument name.
     * @throws RuntimeException If there is no value with argument name.
     */
    public V get(String name) {
        Thrower.throwIfVarEmpty(name, "name");
        //If argument name is the alias for an actual name
        if (mAliasMap.containsKey(name)) {
            //Set the actual name
            name = mAliasMap.get(name);
        }
        //Get the value of the argument name
        V value = mValues.get(name);
        //If no value was found, throw error. 
        Thrower.throwIfTrue(value == null, "No value with argument name in collection.", "name", name, "collectionName", mCollectionName);
        return value;
    }


    /**
     * @param names The names to find values for.
     * @return A list of values that have the argument names. The elements are returned in the order
     * of the argument list.
     * @throws RuntimeException If one or more of the argument names do not exist.
     */
    public List<V> get(List<String> names) {
        if (Checker.isEmpty(names)) {
            return Collections.emptyList();
        }
        List<V> values = new ArrayList<>(names.size());
        for (String name : names) {
            V val = this.get(name);
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
        String regex = "(?i)" + stringWithWildCards.replace("*", "\\w*");
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
