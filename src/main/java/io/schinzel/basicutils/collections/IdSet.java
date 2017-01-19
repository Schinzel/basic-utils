package io.schinzel.basicutils.collections;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.Thrower;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The purpose of this class is offer a collection that stores values where the
 * values have unique identifiers.
 *
 * A more succinct and easier-on-the-eyes version of storing values with
 * identifiers.
 *
 * Map<String, MyValue> myMap = new HashMap<<(); MyValue myValue = new
 * MyValue("ABC"); myMap.add(myValue.getStringId, myValue);
 *
 * IdSet<MyValue> mySet = IdSet.create().add(new MyValue("ABC");
 *
 * @author schinzel
 * @param <V> The type of element to store in the collection.
 */
public class IdSet<V extends IValue> implements Iterable<V> {

    /**
     * The internal storage. Set key sort order to be compareToIgnoreCase.
     */
    private final TreeMap<String, V> mMap = new TreeMap<>(String::compareToIgnoreCase);


    //*************************************************************************
    //* CONSTURCTION
    //*************************************************************************
    private IdSet() {
    }


    /**
     *
     * @return A freshly minted instance.
     */
    public static IdSet create() {
        return new IdSet();
    }


    //*************************************************************************
    //* ADD REMOVE
    //*************************************************************************
    /**
     *
     * @param value A value to add to set.
     * @return This for chaining.
     */
    public IdSet add(V value) {
        Thrower.throwIfEmpty(value, "value");
        String id = value.getid();
        Thrower.throwIfTrue(this.contains(id), "A value with id '" + id + "' cannot be added as one already exists");
        mMap.put(id, value);
        return this;
    }


    /**
     *
     * @param value
     * @return The argument id if it was added.
     */
    public V addAndReturn(V value) {
        this.add(value);
        return value;
    }


    /**
     * Removes argument id from collection. If no value with argument id exists
     * and error is thrown.
     *
     * @param id
     * @return This of chaining.
     */
    public IdSet remove(String id) {
        Thrower.throwIfFalse(this.contains(id), "Cannot remove value with id '" + id + "' as no such value exists.");
        mMap.remove(id);
        return this;
    }


    //*************************************************************************
    //* UTIL
    //*************************************************************************
    /**
     *
     * @return The number of values in this set
     */
    public int size() {
        return mMap.size();
    }


    /**
     *
     * @param id
     * @return True if value with argument id is present in set.
     */
    public boolean contains(String id) {
        return mMap.containsKey(id);
    }


    @Override
    public Iterator<V> iterator() {
        return mMap.values().iterator();
    }


    //*************************************************************************
    //* GET VALUES
    //*************************************************************************
    /**
     *
     * @param id
     * @return The value with the argument id.
     */
    public V get(String id) {
        return this.get(id, false);
    }


    /**
     *
     * @param id
     * @param throwErrorIfNotFound If true, an exception is thrown if argument
     * id is not present.
     * @return The value with the argument id.
     */
    public V get(String id, boolean throwErrorIfNotFound) {
        Thrower.throwIfEmpty(id, "name");
        V value = mMap.get(id);
        if (throwErrorIfNotFound) {
            Thrower.throwIfEmpty(value, "'" + id + "' not found in set.");
        }
        return value;
    }


    /**
     *
     * @param ids
     * @return A list of values that have the argument identifiers. If one or
     * several of the argument names are not present in this collection, no
     * value are returned for these. The elements are returned in the order of
     * the argument list.
     */
    public List<V> get(List<String> ids) {
        return this.get(ids, false);
    }


    /**
     *
     * @param ids
     * @param throwErrorIfNotFound If true, throws an error is one or more
     * argument identifiers are missing.
     * @return A list of values that have the argument identifiers. If one or
     * several of the argument names are not present in this collection, no
     * value are returned for these. The elements are returned in the order of
     * the argument list.
     */
    public List<V> get(List<String> ids, boolean throwErrorIfNotFound) {
        if (Checker.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<V> values = new ArrayList<>(ids.size());
        for (String id : ids) {
            V val = this.get(id, throwErrorIfNotFound);
            if (val != null) {
                values.add(val);
            }
        }
        return values;
    }


    /**
     * Example: Collection: "A1, A2, B1, B2, C1, C"" Argument: "B*" Output: "B1,
     * B2"
     *
     * @param idWithWildCards
     * @return A list of values in alphabetical order that matches the argument.
     */
    public List<V> getUsingWildCards(String idWithWildCards) {
        String regex = idWithWildCards.replace("*", "\\w*");
        List<V> values = new ArrayList<>();
        for (Map.Entry<String, V> entry : mMap.entrySet()) {
            if (entry.getKey().matches(regex)) {
                values.add(entry.getValue());
            }
        }
        return values;
    }


    /**
     *
     * @return The values held.
     */
    public Collection<V> getValues() {
        return mMap.values();
    }

}
