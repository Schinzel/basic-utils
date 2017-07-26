package io.schinzel.basicutils.state;

import com.google.common.collect.Iterables;
import com.google.common.collect.Streams;
import io.schinzel.basicutils.collections.namedvalues.NamedValues;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to build state instances.
 *
 * @author schinzel
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
@Accessors(prefix = "m")
public class StateBuilder {
    /** A list of properties. */
    @Getter(AccessLevel.PACKAGE)
    private final List<Property> mProperties;
    /** Named children of state being built. */
    @Getter(AccessLevel.PACKAGE)
    private final Map<String, State> mChildren;
    /** Named lists of children of the state being built. */
    @Getter(AccessLevel.PACKAGE)
    private final Map<String, List<State>> mChildLists;
    /** If true, the adding of properties is paused. */
    private boolean mAddingPaused;
    //------------------------------------------------------------------------
    // CONSTRUCTION
    //------------------------------------------------------------------------


    /**
     * Package private constructor so that this cannot be used outside this
     * package.
     */
    StateBuilder() {
        this(null);
    }


    /**
     * @param state A seed to copy. Typically a state from a superclass that you want to append to
     *              in a sub class.
     */

    StateBuilder(State state) {
        mProperties = (state != null)
                ? new ArrayList<>(state.getProperties())
                : new ArrayList<>();
        mChildren = new LinkedHashMap<>();
        mChildLists = new LinkedHashMap<>();
    }


    /**
     * @return An instance of the class state.
     */
    public State build() {
        return new State(this);
    }
    //------------------------------------------------------------------------
    // ADD PROPERTY
    //------------------------------------------------------------------------


    /**
     * Starts a chain of methods that adds a property to this builder.
     *
     * @return The key adder for the property.
     */
    public PropKey addProp() {
        return new PropKey(this);
    }


    /**
     * @param key   The key to add
     * @param value The value to add
     * @return This for chaining
     */
    public StateBuilder add(String key, String value) {
        return this.addProp().key(key).val(value).buildProp();
    }


    /**
     * @param key   The key to add
     * @param value The value to add
     * @return This for chaining
     */
    public StateBuilder add(String key, int value) {
        return this.addProp().key(key).val(value).buildProp();
    }


    /**
     * @param key   The key to add
     * @param value The value to add
     * @return This for chaining
     */
    public StateBuilder add(String key, boolean value) {
        return this.addProp().key(key).val(value).buildProp();
    }


    /**
     * @param key   The key to add
     * @param value The value to add
     * @return This for chaining
     */
    public StateBuilder add(String key, long value) {
        return this.addProp().key(key).val(value).buildProp();
    }


    /**
     * Adds a property to this builder.
     *
     * @param property The property to add.
     * @return This for chaining.
     */
    StateBuilder addProperty(Property property) {
        if (!mAddingPaused) {
            mProperties.add(property);
        }
        return this;
    }
    //------------------------------------------------------------------------
    // CONDITIONAL ADDING
    //------------------------------------------------------------------------


    /**
     * @param condition If false, strings added until and endIf request are ignored.
     * @return This for chaining
     */
    public StateBuilder ifTrue(boolean condition) {
        mAddingPaused = !condition;
        return this;
    }


    /**
     * Unpauses any potential pause in string adding set by ifTrue(true).
     *
     * @return This for chaining
     */
    public StateBuilder endIf() {
        mAddingPaused = false;
        return this;
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
    public StateBuilder addChild(String key, IStateNode child) {
        if (child == null) {
            return this;
        }
        mChildren.put(key, child.getState());
        return this;
    }


    /**
     * Adds a collection of children. This so that an hierarchy of states can be
     * constructed.
     *
     * @param key      The key of the argument children.
     * @param children The children to add.
     * @return This for chaining.
     */
    public StateBuilder addChildren(String key, Iterable<? extends IStateNode> children) {
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


    /**
     * Adds a collection of children. This so that an hierarchy of states can be
     * constructed. The key of the entry will be the name of the argument collection.
     *
     * @param collection The children to add.
     * @return This for chaining.
     */
    public StateBuilder addChildren(NamedValues<? extends IStateNode>  collection) {
        return this.addChildren(collection.getCollectionName(), collection.values());
    }

}
