package io.schinzel.basicutils.status;

import io.schinzel.json.JsonOrdered;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;

/**
 * The purpose of this interface is to generate and return the status of an
 * objects and its logical children as a human readable string or a JSON object.
 *
 * @author schinzel
 */
public interface IStatusNode {

    /**
     *
     * @return The status of the implementing object.
     */
    Status getStatus();


    /**
     * This methods should be overridden to return the logical status-children
     * of the implementing object if there are any. If the implementing object
     * does not have any logical status-children this method should not be
     * overridden.
     *
     * @return The logical status-children of the implementing object.
     */
    default Iterator<IStatusNode> getStatusChildren() {
        return Collections.EMPTY_LIST.iterator();
    }


    /**
     *
     * @return The status of this object and all it's sub-objects as a human
     * readable hierarchical string.
     */
    default String getStatusAsString() {
        return StatusStringCompiler.getStatusTreeAsString(this);
    }


    /**
     *
     * @return The status of this object and all it's sub-objects as a JSON
     * object.
     */
    default JsonOrdered getStatusAsJson() {
        JsonOrdered json = new JsonOrdered(this.getStatus().getProps());
        Iterator<IStatusNode> it = this.getStatusChildren();
        if (it.hasNext()) {
            JSONArray ja = new JSONArray();
            while (it.hasNext()) {
                ja.put(it.next().getStatusAsJson());
            }
            json.put("children", ja);
        }
        return json;
    }


}
