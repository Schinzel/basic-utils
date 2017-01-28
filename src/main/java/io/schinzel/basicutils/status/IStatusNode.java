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

    Status getStatus();


    default List<IStatusNode> getChildren() {
        return Collections.EMPTY_LIST;
    }


    default Status getStatusTree() {
        Status status = this.getStatus();
        for (IStatusNode child : this.getChildren()) {
            status.addChild(child.getStatusTree());
        }
        return status;
    }


    default String getString() {
        return this.getResultTree(0);
    }


    /**
     *
     * @param intendation How many level the tree is this lap..
     * @return The result of this lap and all its sub laps.
     */
    default String getResultTree(int intendation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intendation; i++) {
            sb.append("--");
        }
        sb.append(" ")
                .append(Joiner.on(" ")
                        .withKeyValueSeparator(":")
                        .join(this.getStatus().getProps()))
                .append("\n");
        for (IStatusNode statusNode : this.getChildren()) {
            sb.append(statusNode.getResultTree(intendation + 1));
        }
        return sb.toString();
    }


    default JsonOrdered getJson() {
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
