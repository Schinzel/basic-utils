package io.schinzel.basicutils.collections.namedvalues;

import lombok.Getter;

/**
 * The purpose of this object is to offer name and description via composition instead of inheritance.
 * <p>
 * <p>
 * Created by schinzel on 2017-03-01.
 */
public class NamedValue {
    private static final String NO_DESCRIPTION = "NO DESCRIPTION SET";
    @Getter
    final String name;
    @Getter
    final String description;


    /**
     * @param name The name of this object.
     */
    public NamedValue(String name) {
        this(name, NO_DESCRIPTION);
    }


    /**
     * @param name        The name of this object.
     * @param description A description of this object.
     */
    public NamedValue(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
