package io.schinzel.basicutils.status;

import io.schinzel.json.JsonOrdered;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;

/**
 * The purpose of this interface is to generate a 
 *
 * @author schinzel
 */
public interface IStatusNode {

    
    Status getStatus();


    
    default List<IStatusNode> getStatusChildren() {
        return Collections.EMPTY_LIST;
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
        if (!this.getStatusChildren().isEmpty()) {
            JSONArray ja = new JSONArray();
            for (IStatusNode statusNode : this.getStatusChildren()) {
                ja.put(statusNode.getStatusAsJson());
            }
            json.put("children", ja);
        }
        return json;
    }


}
