package io.schinzel.basicutils.state;

import io.schinzel.json.JsonOrdered;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;

/**
 * The purpose of this interface is to generate and return the state of an
 * objects and its logical children as a human readable string or a JSON object.
 *
 * @author schinzel
 */
public interface IStateNode {

    /**
     *
     * @return The state of the implementing object.
     */
    State getState();


    /**
     * This methods should be overridden to return the logical state-children
     * of the implementing object, if there are any. If the implementing object
     * does not have any logical state-children this method should not be
     * overridden.
     *
     * @return The logical state-children of the implementing object.
     */
    default Iterator<IStateNode> getStateChildren() {
        return Collections.EMPTY_LIST.iterator();
    }


    /**
     *
     * @return The state of this object and all it's sub-objects as a human
     * readable hierarchical string.
     */
    default String getStateAsString() {
        return StateStringCompiler.getStateTreeAsString(this);
    }


    /**
     *
     * @return The state of this object and all it's sub-objects as a JSON
     * object.
     */
    default JsonOrdered getStateAsJson() {
        JsonOrdered json = new JsonOrdered(this.getState().getProperties());
        Iterator<IStateNode> it = this.getStateChildren();
        if (it.hasNext()) {
            JSONArray ja = new JSONArray();
            while (it.hasNext()) {
                ja.put(it.next().getStateAsJson());
            }
            json.put("children", ja);
        }
        return json;
    }


}
