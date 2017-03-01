package io.schinzel.basicutils.collections.idset;

import lombok.Getter;

/**
 * Created by schinzel on 2017-03-01.
 */
public class IdObj {
    private static final String NO_DESCRIPTION = "NO DESCRIPTION SET";
    @Getter
    final String id;
    @Getter
    final String description;

    public IdObj(String id) {
        this(id, NO_DESCRIPTION);
    }

    public IdObj(String id, String description) {
        this.id = id;
        this.description = description;
    }

}
