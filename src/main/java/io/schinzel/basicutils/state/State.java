package io.schinzel.basicutils.state;

import com.google.common.base.Strings;
import io.schinzel.basicutils.str.Str;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The purpose of this class give a snapshot state of the object returning this
 * object and all its children.
 *
 * @author schinzel
 */
@Accessors(prefix = "m")
public class State {
    /** A list of properties. */
    @Getter(AccessLevel.PACKAGE)
    private final List<Property> mProperties;
    /** Named children of this state object. */
    private final Map<String, State> mChildren;
    /** Named lists of children of this state object. */
    private final Map<String, List<State>> mChildLists;
    //------------------------------------------------------------------------
    // CONSTRUCTION
    //------------------------------------------------------------------------


    /**
     * @return A builder to construct a state object.
     */
    public static StateBuilder getBuilder() {
        return new StateBuilder();
    }


    /**
     * @param state A state object to copy and seed the builder with. Typically a state from a
     *              super
     *              class that you
     *              want to append to in a sub class.
     * @return A builder with the argument seed state added.
     */
    @SuppressWarnings("WeakerAccess")
    public static StateBuilder getBuilder(State state) {
        return new StateBuilder(state);
    }


    /**
     * @param stateBuilder A state builder.
     */
    State(StateBuilder stateBuilder) {
        mProperties = stateBuilder.getProperties();
        mChildren = stateBuilder.getChildren();
        mChildLists = stateBuilder.getChildLists();
    }
    //------------------------------------------------------------------------
    // PUBLIC
    //------------------------------------------------------------------------


    /**
     * @return A snapshot of the state of this object and its children.
     */
    public String getString() {
        return this.getStr().toString();
    }


    /**
     * @return A snapshot of the state of this object and its children.
     */
    public String toString() {
        return this.getString();
    }


    /**
     * @return A snapshot of the state of this object and its children.
     */
    public Str getStr() {
        return Str.create().a(this.getPropertiesAsString()).aws(Str.WS.LF)
                .a(this.getChildrenAsString(0));
    }


    /**
     * @return A snapshot of the state of this object and its children.
     */
    public JSONObject getJson() {
        JSONObject json = new JSONObject(new LinkedHashMap<>());
        //Add all properties to return
        for (Property prop : mProperties) {
            json.put(prop.getKey(), prop.getObject());
        }
        //Add all children and their children recursively
        mChildren.forEach((key, childState) -> json.put(key, childState.getJson()));
        //Add all child-lists and their children recursively
        mChildLists.forEach((key, siblings) -> {
            JSONArray ja = new JSONArray();
            siblings.forEach((childState) -> ja.put(childState.getJson()));
            json.put(key, ja);
        });
        return json;
    }
    //------------------------------------------------------------------------
    // PACKAGE PRIVATE 
    //------------------------------------------------------------------------


    /**
     * @return The properties - but not its children - as a string.
     */
    private String getPropertiesAsString() {
        return mProperties
                .stream()
                .map(Property::getString)
                .collect(Collectors.joining(" "));
    }


    /**
     * @param depth The depth in the tree.
     * @return A string representation of this node's children.
     */
    private Str getChildrenAsString(int depth) {
        Str str = Str.create();
        //Add all children and their children recursively
        mChildren.forEach((key, child) -> str.a(indentation(depth)).a(key).asp()
                //Get the str representation of current child's properties
                .a(child.getPropertiesAsString()).anl()
                //Get the str representation of current child's children
                .a(child.getChildrenAsString(depth + 1)));
        //Add all child-lists and their children recursively
        mChildLists.forEach((key, childList) -> {
            //Add key for current child list
            str.a(indentation(depth)).a(key).anl();
            //For each list in child list
            childList.forEach(child -> str.a(indentation(depth + 1))
                    //Get the str representation of current child's properties
                    .a(child.getPropertiesAsString()).anl()
                    //Get the str representation of current child's children
                    .a(child.getChildrenAsString(depth + 2)));
        });
        return str;
    }


    /**
     * @param depth The depth to get an indentation for.
     * @return The indentation of the argument depth.
     */
    private static String indentation(int depth) {
        return depth == 0 ? "" : Strings.repeat("   ", depth - 1) + "┗━ ";
    }
}
