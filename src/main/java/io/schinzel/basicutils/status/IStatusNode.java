package io.schinzel.basicutils.status;

import io.schinzel.json.JsonOrdered;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;

/**
 * The purpose of this interface is to generate a
 *
 * @author schinzel
 */
public interface IStatusNode {

    Status getStatus();


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
