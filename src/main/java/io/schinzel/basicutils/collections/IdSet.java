package io.schinzel.basicutils.collections;

import io.schinzel.basicutils.Checker;
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
 * values have unique identifiers.
 * <p>
 * A more succinct and easier-on-the-eyes version of storing values with
 * identifiers.
 * <p>
 * {@literal (Map<String, MyValue> myMap = new HashMap<<(); MyValue myValue = new
 * MyValue("ABC"); myMap.add(myValue.getStringId, myValue);
 * <p>
 * IdSet<MyValue> mySet = IdSet.create().add(new MyValue("ABC");}
 *
 * @param <V> The type of element to store in the collection.
 * @author schinzel
 */
public class IdSet<V extends IdSetValue> implements Iterable<V> {

    /**
     * The internal storage. Set key sort order to be compareToIgnoreCase.
     */
    private final TreeMap<String, V> mMap = new TreeMap<>(String::compareToIgnoreCase);
    /**
     * Holds a set of aliases. I.e. more than one id can be used to look up a
     * value.
     */
    private final Map<String, String> mAliasMap = new HashMap<>();


    //*************************************************************************
    //* CONSTRUCTION
    //*************************************************************************
    private IdSet() {
    }


    /**
     * @return A freshly minted instance.
     */
    public static IdSet create() {
        return new IdSet();
    }
    //*************************************************************************
    //* ADD REMOVE
    //*************************************************************************


    /**
     * @param value A value to add to set.
     * @return This for chaining.
     */
    public IdSet add(V value) {
        Thrower.throwIfEmpty(value, "value");
        String id = value.getid();
        Thrower.throwIfTrue(mAliasMap.containsKey(id), "A value with id '" + id + "' cannot be added as there exists an alias with the same id.");
        Thrower.throwIfTrue(this.has(id), "A value with id '" + id + "' cannot be added as one already exists");
        mMap.put(id, value);
        return this;
    }


    /**
     * Adds an alias for an id. For example: Lets assume there is an object
     * "thing" that returns the id "theId". The thing is added to the collection
     * with a couple of aliases idSet.add(thing).addAlias("a",
     * "theId").addAlias("b", "theId");
     * <p>
     * Then all three below would return the thing: idSet.get("theId")
     * idSet.get("a") idSet.get("b")
     *
     * @param id    The id for which to add the argument alias.
     * @param alias The alias to add for argument id.
     * @return This for chaining.
     */
    public IdSet addAlias(String id, String alias) {
        Thrower.throwIfTrue(!this.has(id), "An alias '" + alias + "' for a id '" + id + "' cannot be added as there exist no value with this id in collection.");
        //if the argument value exists in the alias set
        Thrower.throwIfTrue(mAliasMap.containsKey(alias), "An alias'" + alias + "' cannot be added as there already exists such an alias.");
        Thrower.throwIfTrue(this.has(alias), "An alias '" + alias + "' cannot be added as there exists a value with the same id.");
        mAliasMap.put(alias, id);
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


    /**
     * Removes argument id from collection. If no value with argument id exists an error is thrown.
     *
     * @param id The id find a value for and remove.
     * @return This of chaining.
     */
    public IdSet remove(String id) {
        Thrower.throwIfFalse(this.has(id), "Cannot remove value with id '" + id + "' as no such value exists.");
        //Remove all entries in alias map with argument id.
        while (mAliasMap.values().remove(id)) ;
        mMap.remove(id);
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
     * @param id The id to lookup.
     * @return True if value with argument id is present in set.
     */
    public boolean has(String id) {
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
     * @param id The id to look up.
     * @return The value with the argument id.
     * @throws RuntimeException If there is no value with argument id.
     */
    public V get(String id) {
        Thrower.throwIfEmpty(id, "id");
        //If there exists an alias for argument id
        if (mAliasMap.containsKey(id)) {
            //Set the id to be the alias
            id = mAliasMap.get(id);
        }
        //Get the value of the argument id
        V value = mMap.get(id);
        //If no value was found, throw error. 
        Thrower.throwIfEmpty(value, "'" + id + "' not found in set.");
        return value;
    }


    /**
     * @param ids The ids to find values for.
     * @return A list of values that have the argument identifiers. The elements
     * are returned in the order of the argument list.
     * @throws RuntimeException If one or more of the argument identifiers do
     *                          not exist exist.
     */
    public List<V> get(List<String> ids) {
        if (Checker.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<V> values = new ArrayList<>(ids.size());
        for (String id : ids) {
            V val = this.get(id);
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
     * @param idWithWildCards String to loop up.
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
     * @return The values held.
     */
    public Collection<V> values() {
        return mMap.values();
    }
}
