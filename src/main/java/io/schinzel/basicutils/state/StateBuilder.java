package io.schinzel.basicutils.state;

import com.google.common.collect.Streams;
import io.schinzel.basicutils.Thrower;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to build state instances.
 *
 * @author schinzel
 */
public class StateBuilder {

    /**
     * A list of properties.
     */
    List<Property> mProperties = new ArrayList<>();
    /**
     * Named children of state being built.
     */
    Map<String, State> mChildren = new HashMap<>();
    /**
     * Named lists of children of the state being built.
     */
    Map<String, List<State>> mChildLists = new HashMap<>();


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
     *
     * @return An instance of the class state.
     */
    public State build() {
        return new State(this);
    }

    //------------------------------------------------------------------------
    // ADD PROPERTIES
    //------------------------------------------------------------------------

    /**
     *
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, String val) {
        Thrower.throwIfEmpty(val, "val");
        mProperties.add(new Property(key, val, val));
        return this;
    }


    /**
     *
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, int val) {
        return this.add(key, (long) val);
    }


    /**
     *
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, long val) {
        String valAsStr = NumberFormatter.format(val);
        mProperties.add(new Property(key, valAsStr, val));
        return this;
    }


    /**
     *
     * @param key The key of the argument value.
     * @param val The value to add.
     * @param numOfDecimals The number of decimals to display in the string
     * representation of the argument value.
     * @return This for chaining.
     */
    public StateBuilder add(String key, float val, int numOfDecimals) {
        return this.add(key, (double) val, numOfDecimals);
    }


    /**
     *
     * @param key The key of the argument value.
     * @param val The value to add.
     * @param numOfDecimals The number of decimals to display in the string
     * representation of the argument value.
     * @return This for chaining.
     */
    public StateBuilder add(String key, double val, int numOfDecimals) {
        String valAsStr = NumberFormatter.format(val, numOfDecimals);
        mProperties.add(new Property(key, valAsStr, val));
        return this;
    }


    /**
     *
     * @param key The key of the argument value.
     * @param val The value to add.
     * @return This for chaining.
     */
    public StateBuilder add(String key, boolean val) {
        String valAsStr = String.valueOf(val);
        mProperties.add(new Property(key, valAsStr, val));
        return this;
    }


    //------------------------------------------------------------------------
    // ADD CHILDREN
    //------------------------------------------------------------------------
    /**
     * Adds a child state. This so that an hierarchy of state can be
     * constructed.
     *
     * @param key The key of the argument child.
     * @param child The child to add.
     * @return This for chaining.
     */
    public StateBuilder addChild(String key, IStateNode child) {
        mChildren.put(key, child.getState());
        return this;
    }


    /**
     * Adds a collection of children. This so that an hierarchy of state can be
     * constructed.
     *
     * @param key The key of the argument children.
     * @param children The children to add.
     * @return This for chaining.
     */
    public StateBuilder addChildren(String key, Iterator<? extends IStateNode> children) {
        List<State> childList = Streams.stream(children)
                .map(IStateNode::getState)
                .collect(Collectors.toList());
        if (!childList.isEmpty()) {
            mChildLists.put(key, childList);
        }
        return this;
    }

}
