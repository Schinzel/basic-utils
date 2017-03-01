package io.schinzel.basicutils.collections.namedvalues;

import lombok.Getter;

/**
 * Created by schinzel on 2017-03-01.
 */
public class NamedValue {
    private static final String NO_DESCRIPTION = "NO DESCRIPTION SET";
    @Getter
    final String id;
    @Getter
    final String description;


    public NamedValue(String id) {
        this(id, NO_DESCRIPTION);
    }


    public NamedValue(String id, String description) {
        this.id = id;
        this.description = description;
    }

}
