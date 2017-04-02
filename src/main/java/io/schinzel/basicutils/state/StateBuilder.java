package io.schinzel.basicutils.state;

import com.google.common.collect.Iterables;
import com.google.common.collect.Streams;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.*;
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
    private List<Property> mProperties = new ArrayList<>();
    /** Named children of state being built. */
    @Getter(AccessLevel.PACKAGE)
    private Map<String, State> mChildren = new LinkedHashMap<>();
    /** Named lists of children of the state being built. */
    @Getter(AccessLevel.PACKAGE)
    private Map<String, List<State>> mChildLists = new LinkedHashMap<>();
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


    public PropKey addProp() {
        return new PropKey(this);
    }


    StateBuilder addProperty(Property property) {
        mProperties.add(property);
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
     * @param child The child to addChild.
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
     * Adds a collection of children. This so that an hierarchy of state can be
     * constructed.
     *
     * @param key      The key of the argument children.
     * @param children The children to addChild.
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
}
