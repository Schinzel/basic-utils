package io.schinzel.basicutils.collections.namedvalues;

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

/**
 * The purpose of this class is offer a collection that stores values where the
 * values have unique names.
 * <p>
 * A more succinct and easier-on-the-eyes version of storing values with
 * names. Plus that the values know their names - which is handy or required at times.
 * <p>
 * {@literal (Map<String, MyValue> myMap = new HashMap<<();
 * MyValue myValue = new MyValue("ABC");
 * myMap.add(myValue.getStringId, myValue);
 * <p>
 * NamedValues<MyValue> mySet = NamedValues.create().add(new MyValue("ABC");}
 *
 * @param <V> The type of element to store in the collection.
 * @author schinzel
 */
public class NamedValues<V extends INamedValue> implements Iterable<V> {
    /**
     * The internal storage. Set key sort order to be compareToIgnoreCase.
     */
    private final TreeMap<String, V> mMap = new TreeMap<>(String::compareToIgnoreCase);
    /**
     * Holds a set of aliases. I.e. more than one name can be used to look up a
     * value.
     */
    private final Map<String, String> mAliasMap = new HashMap<>();
    /**
     * The name of this collection.
     */
    final String mCollectionName;
    //*************************************************************************
    //* CONSTRUCTION
    //*************************************************************************


    protected NamedValues(String collectionName) {
        mCollectionName = collectionName;
    }


    /**
     * @return A freshly minted instance.
     */
    public static <Q extends INamedValue> NamedValues<Q> create() {
        return new NamedValues<>(EmptyObjects.EMPTY_STRING);
    }


    /**
     * @param collectionName The name of the collection. Useful for error messages and debugging.
     * @return A freshly minted instance.
     */
    public static <Q extends INamedValue> NamedValues<Q> create(String collectionName) {
        return new NamedValues<Q>(collectionName);
    }
    //*************************************************************************
    //* ADD
    //*************************************************************************


    /**
     * @param value A value to add to collection.
     * @return This for chaining.
     */
    public NamedValues<V> add(V value) {
        Thrower.throwIfEmpty(value, "value");
        String name = value.getName();
        Thrower.throwIfTrue(mAliasMap.containsKey(name), "Value cannot be added as there exists an alias with the same name.", "name", name, "collectionName", mCollectionName);
        Thrower.throwIfTrue(this.has(name), "Value cannot be added as a value with that name already exists", "name", name, "collectionName", mCollectionName);
        mMap.put(name, value);
        return this;
    }


    /**
     * Adds an alias for an name. For example: Lets assume there is an object
     * "thing" that returns the name "theName". The thing is added to the collection
     * with a couple of aliases
     * coll.add(thing).addAlias("theName", "a").addAlias("theName", "b");
     * <p>
     * Then all three below would return the thing: coll.get("theName")
     * coll.get("a") coll.get("b")
     *
     * @param name  The name for which to add the argument alias.
     * @param alias The alias to add for argument name.
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
     * @param value The value to add and return.
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
     * Removes argument name from collection. If no value with argument name exists an error is thrown.
     *
     * @param name The name of the value to remove.
     * @return This for chaining.
     */
    public NamedValues<V> remove(String name) {
        Thrower.throwIfTrue(!this.has(name), "Cannot remove value as no such value exists.", "name", name, "collectionName", mCollectionName);
        //Remove all entries in alias map with argument name.
        while (mAliasMap.values().remove(name)) ;
        mMap.remove(name);
        return this;
    }


    /**
     * Removed all values and clears the aliases. Keeps the name though.
     *
     * @return This for chaining.
     */
    public NamedValues<V> clear() {
        mAliasMap.clear();
        mMap.clear();
        return this;
    }
    //*************************************************************************
    //* UTIL
    //*************************************************************************


    /**
     * @return The number of values in this set
     */
    public int size() {
        return mMap.size();
    }


    /**
     * @return True if there are no values in this set.
     */
    public boolean isEmpty() {
        return mMap.isEmpty();
    }


    /**
     * @param name The name to lookup.
     * @return True if value with argument name is present in set.
     */
    public boolean has(String name) {
        return mMap.containsKey(name);
    }


    @Override
    public Iterator<V> iterator() {
        return mMap.values().iterator();
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
        Thrower.throwIfEmpty(name, "name");
        //If there exists an alias for argument name
        if (mAliasMap.containsKey(name)) {
            //Set the name to be the alias
            name = mAliasMap.get(name);
        }
        //Get the value of the argument name
        V value = mMap.get(name);
        //If no value was found, throw error. 
        Thrower.throwIfTrue(value == null, "No value with argument name in collection.", "name", name, "collectionName", mCollectionName);
        return value;
    }


    /**
     * @param names The names to find values for.
     * @return A list of values that have the argument names. The elements
     * are returned in the order of the argument list.
     * @throws RuntimeException If one or more of the argument names does
     *                          not exist exist.
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
     * Example: Collection: "A1, A2, B1, B2, C1, C""
     * Argument: "B*"
     * Output: "B1,B2"
     *
     * @param stringWithWildCards String to look up. Wildcard is an astrix, "*"
     * @return A list of values in alphabetical order that matches the argument.
     */
    public List<V> getUsingWildCards(String stringWithWildCards) {
        String regex = stringWithWildCards.replace("*", "\\w*");
        List<V> values = new ArrayList<>();
        for (Map.Entry<String, V> entry : mMap.entrySet()) {
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
        return mMap.values();
    }
}
