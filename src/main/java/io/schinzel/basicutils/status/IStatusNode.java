package io.schinzel.basicutils.status;

import java.util.Collections;
import java.util.List;

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

}
