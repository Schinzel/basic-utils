package io.schinzel.basicutils.state;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import io.schinzel.basicutils.Thrower;
import io.schinzel.json.JsonOrdered;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * The purpose of this class to hold a set of properties.
 *
 * @author schinzel
 */
public class State {

    Map<String, Object> mProperties = new LinkedHashMap<>();
    List<IStateNode> mChildren = new ArrayList<>();


    private State() {
    }


    public static State create() {
        return new State();
    }


    public State add(String key, String val) {
        Thrower.throwIfEmpty(key, "key");
        Thrower.throwIfEmpty(val, "val");
        mProperties.put(key, val);
        return this;
    }


    public State add(String key, int value) {
        return this.add(key, NumberFormatter.format(value));
    }


    public State add(String key, long value) {
        return this.add(key, NumberFormatter.format(value));
    }


    public State add(String key, double value) {
        return this.add(key, value, 2);
    }


    public State add(String key, double value, int numOfDecimals) {
        return this.add(key, NumberFormatter.format(value, numOfDecimals));
    }


    public State add(String key, float value) {
        return this.add(key, value, 2);
    }


    public State add(String key, float value, int numOfDecimals) {
        return this.add(key, NumberFormatter.format(value, numOfDecimals));
    }


    public State addChild(IStateNode iStateNode) {
        mChildren.add(iStateNode);
        return this;
    }


    public State addChildren(Iterator children) {
        while (children.hasNext()) {
            mChildren.add((IStateNode) children.next());
        }
        return this;
    }


    //*************************************************************************
    //* GET PROPERTIES
    //*************************************************************************
    /**
     *
     * @return The properties held by this object.
     */
    Map<String, Object> getProperties() {
        return mProperties;
    }


    List<IStateNode> getChildren() {
        return mChildren;
    }


    /**
     *
     * @return The argument node and all its sub-nodes as a human readable
     * string.
     */
    @Override
    public String toString() {
        return this.getStateTreeAsString(0, new StringBuilder()).toString();
    }


    /**
     *
     * @param depth The current depth in the tree to render.
     * @param sb The generated string is added to this string builder.
     * @return The argument node and all its sub-nodes as a human readable
     * string.
     */
    private StringBuilder getStateTreeAsString(int depth, StringBuilder sb) {
        sb.append(Strings.repeat("--", depth))
                .append(" ")
                .append(this.getPropsAsString())
                .append("\n");
        this.getChildren().forEach(child
                -> child.getState().getStateTreeAsString(depth + 1, sb));
        return sb;
    }


    /**
     *
     * @return The properties as a string.
     */
    String getPropsAsString() {
        return Joiner.on(" ")
                .withKeyValueSeparator(":")
                .join(this.getProperties());

    }


    /**
     *
     * @return The state of this object and all it's sub-objects as a JSON
     * object.
     */
    public JsonOrdered getJson() {
        JsonOrdered json = new JsonOrdered(this.getProperties());
        List<IStateNode> children = this.getChildren();
        if (!children.isEmpty()) {
            JSONArray ja = new JSONArray();
            for (IStateNode child : children) {
                ja.put(child.getState().getJson());
            }
            json.put("children", ja);
        }
        return json;

    }

}
