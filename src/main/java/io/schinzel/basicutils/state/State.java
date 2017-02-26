package io.schinzel.basicutils.state;

import com.google.common.base.Strings;
import io.schinzel.json.JsonOrdered;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;

/**
 * The purpose of this class give a snapshot state of the object returning this
 * object and all its children.
 *
 * @author schinzel
 */
public class State {

    /**
     * A list of properties.
     */
    final List<Property> mProperties;
    /**
     * Named children of this state object.
     */
    final Map<String, State> mChildren;
    /**
     * Named lists of children of this state object.
     */
    final Map<String, List<State>> mChildLists;
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
     * @param stateBuilder A state builder.
     */
    State(StateBuilder stateBuilder) {
        mProperties = stateBuilder.mProperties;
        mChildren = stateBuilder.mChildren;
        mChildLists = stateBuilder.mChildLists;
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
    public JsonOrdered getJson() {
        JsonOrdered json = JsonOrdered.create();
        //Add all properties to return
        mProperties.forEach((prop) -> {
            json.put(prop.getKey(), prop.getObject());
        });
        //Add all children and their children recursivly
        mChildren.forEach((key, childState) -> {
            json.put(key, childState.getJson());
        });
        //Add all child-lists and their children recursivly
        mChildLists.forEach((key, siblings) -> {
            JSONArray ja = new JSONArray();
            siblings.forEach((childState) -> {
                ja.put(childState.getJson());
            });
            json.put(key, ja);
        });
        return json;
    }
    //------------------------------------------------------------------------
    // PACKAGE PRIVATE 
    //------------------------------------------------------------------------


    /**
     * @return A snapshot of the state of this object and its children.
     */
    Str getStr() {
        return Str.create().a(this.getPropertiesAsString()).aws(Str.WS.NL)
                .a(this.getChildrenAsString(0));
    }


    /**
     * @return The properties - but not its children - as a string.
     */
    String getPropertiesAsString() {
        return mProperties
                .stream()
                .map(Property::getString)
                .collect(Collectors.joining(" "));
    }


    /**
     * @param depth The depth in the tree.
     * @return A string representation of this node's children.
     */
    Str getChildrenAsString(int depth) {
        Str str = Str.create();
        //Add all children and their children recursively
        mChildren.forEach((key, child) -> {
            str.a(indentation(depth)).a(key).asp()
                    //Get the str representation of current child's preoperties
                    .a(child.getPropertiesAsString()).anl()
                    //Get the str representation of current child's children
                    .a(child.getChildrenAsString(depth + 1));
        });
        //Add all child-lists and their children recursively
        mChildLists.forEach((key, childList) -> {
            //Add key for current child list
            str.a(indentation(depth)).a(key).anl();
            //For each list in child list
            childList.forEach(child -> {
                str.a(indentation(depth + 1))
                        //Get the str representation of current child's preoperties
                        .a(child.getPropertiesAsString()).anl()
                        //Get the str represention of current child's children
                        .a(child.getChildrenAsString(depth + 2));
            });
        });
        return str;
    }


    /**
     * @param depth The depth to get an indentation for.
     * @return The indentation of the argument depth.
     */
    static String indentation(int depth) {
        return depth == 0 ? "" : Strings.repeat("   ", depth - 1) + "┗━ ";
    }

}
