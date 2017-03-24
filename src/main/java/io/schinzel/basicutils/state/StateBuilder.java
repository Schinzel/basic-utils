package io.schinzel.basicutils.state;

import com.google.common.collect.Iterables;
import com.google.common.collect.Streams;
import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.str.Str;
import org.json.JSONArray;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to build state instances.
 *
 * @author schinzel
 */
public class StateBuilder {
    private static final String NO_VALUE = "";

    /**
     * A list of properties.
     */
    List<Property> mProperties = new ArrayList<>();
    /**
     * Named children of state being built.
     */
    Map<String, State> mChildren = new LinkedHashMap<>();
    /**
     * Named lists of children of the state being built.
     */
    Map<String, List<State>> mChildLists = new LinkedHashMap<>();
    //------------------------------------------------------------------------
    // CONSTRUCTION
    //------------------------------------------------------------------------


    /**
     * Package private constructor so that this cannot be used outside this
     * package.
     */
    StateBuilder() {
    }


    /**
     * @param state A seed to copy. Typically a state from a superclass that you want to append to in a sub class.
     */

    StateBuilder(State state) {
        mProperties = new ArrayList<>(state.mProperties);
        mChildren = new HashMap<>(mChildren);
        mChildLists = new HashMap<>(mChildLists);
    }


    /**
     * @return An instance of the class state.
     */
    public State build() {
        return new State(this);
    }
    //------------------------------------------------------------------------
    // ADD PROPERTIES
    //------------------------------------------------------------------------


    /**
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, String val) {
        if (val == null) {
            this.add(key, NO_VALUE);
        }
        mProperties.add(new Property(key, val, val));
        return this;
    }


    /**
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, int val) {
        return this.add(key, (long) val);
    }


    /**
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, long val) {
        String valAsStr = Str.create().a(val).toString();
        mProperties.add(new Property(key, valAsStr, val));
        return this;
    }


    /**
     * @param key           The key of the argument value.
     * @param val           The value to add.
     * @param numOfDecimals The number of decimals to display in the string
     *                      representation of the argument value.
     * @return This for chaining.
     */
    public StateBuilder add(String key, float val, int numOfDecimals) {
        return this.add(key, (double) val, numOfDecimals);
    }


    /**
     * @param key           The key of the argument value.
     * @param val           The value to add.
     * @param numOfDecimals The number of decimals to display in the string
     *                      representation of the argument value.
     * @return This for chaining.
     */
    public StateBuilder add(String key, double val, int numOfDecimals) {
        String valAsStr = Str.create().a(val, numOfDecimals).toString();
        mProperties.add(new Property(key, valAsStr, val));
        return this;
    }


    /**
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, boolean val) {
        String valAsStr = String.valueOf(val);
        mProperties.add(new Property(key, valAsStr, val));
        return this;
    }


    public StateBuilder add(String key, String[] values) {
        if (Checker.isEmpty(values)) {
            return this.add(key, NO_VALUE);
        }
        mProperties.add(new Property(key, String.join(", ", values), new JSONArray(values)));
        return this;
    }


    public StateBuilder add(String key, List<String> values) {
        if (Checker.isEmpty(values)) {
            return this.add(key, NO_VALUE);
        }
        return this.add(key, values.toArray(EmptyObjects.EMPTY_STRING_ARRAY));
    }
    //------------------------------------------------------------------------
    // ADD CHILDREN
    //------------------------------------------------------------------------


    /**
     * Adds a child state. This so that an hierarchy of state can be
     * constructed.
     *
     * @param key   The key of the argument child.
     * @param child The child to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, IStateNode child) {
        if (child == null) {
            return this;
        }
        mChildren.put(key, child.getState());
        return this;
    }


    /**
     * Adds a collection of children. This so that an hierarchy of state can be
     * constructed.
     *
     * @param key      The key of the argument children.
     * @param children The children to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, Iterable<? extends IStateNode> children) {
        if (children == null || Iterables.size(children) == 0) {
            return this;
        }
        List<State> childList = Streams.stream(children)
                .map(IStateNode::getState)
                .collect(Collectors.toList());
        if (!childList.isEmpty()) {
            mChildLists.put(key, childList);
        }
        return this;
    }
}
