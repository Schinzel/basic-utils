package io.schinzel.basicutils.collections;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.Thrower;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The purpose of this class is offer a collection that stores value with
 * identifiers.
 *
 * I.e. instead of the more succinct and easier on-the-eye-version of:
 *
 * Map<String, MyValue> myMap = new HashMap<<();
 * MyValue myValue = new MyValue("ABC");
 * myMap.add(myValue.getStringId, myValue);
 * MyValue has to have boilerplate getString-implementation
 *
 * IdSet<MyValue> mySet = IdSet.create()
 * .add(new MyValye("ABC");
 *
 * @author schinzel
 * @param <V>
 */
public class IdSet<V extends IValue> {

    /**
     * The internal storage.
     */
    private final TreeMap<String, V> mMap = new TreeMap<>();


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
     * value are returned for these. The elements are returned in the order
     * of the argument list.
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
     * value are returned for these. The elements are returned in the order
     * of the argument list.
     */
    public List<V> get(List<String> ids, boolean throwErrorIfNotFound) {
        if (Checker.isEmpty(ids)) {
            return new ArrayList<>(0);
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
}
