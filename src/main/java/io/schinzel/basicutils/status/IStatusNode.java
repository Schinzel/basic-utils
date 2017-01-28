package io.schinzel.basicutils.status;

import com.google.common.base.Joiner;
import io.schinzel.json.JsonOrdered;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;

/**
 * The purpose of this interface ...
 *
 * @author schinzel
 */
public interface IStatusNode {

    //*************************************************************************
    //* METHODS TO IMPLEMENT
    //*************************************************************************
    Status getStatus();


    default List<IStatusNode> getChildren() {
        return Collections.EMPTY_LIST;
    }


    //*************************************************************************
    //* DEFAULT METHODS
    //*************************************************************************
    /**
     *
     * @return The status of this object and all it's sub-objects as a human
     * readable hierarchical string.
     */
    default String getStatusAsString() {
        return this.getStatusAsString(0);
    }


    /**
     *
     * @param depth How many level down in the tree is this request.
     * @return
     */
    default String getStatusAsString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(" ")
                .append(Joiner.on(" ")
                        .withKeyValueSeparator(":")
                        .join(this.getStatus().getProps()))
                .append("\n");
        for (IStatusNode statusNode : this.getChildren()) {
            sb.append(statusNode.getStatusAsString(depth + 1));
        }
        return sb.toString();
    }


    default JsonOrdered getStatusAsJson() {
        JsonOrdered json = new JsonOrdered(this.getStatus().getProps());
        if (!this.getChildren().isEmpty()) {
            JSONArray ja = new JSONArray();
            for (IStatusNode statusNode : this.getChildren()) {
                ja.put(statusNode.getStatus().getProps());
            }
        }
        return json;
    }


}
