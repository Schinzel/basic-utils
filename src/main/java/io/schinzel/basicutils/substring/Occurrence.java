package io.schinzel.basicutils.substring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * The purpose of this enum it to represent an occurrence.
 */

@Accessors(prefix = "m")
@AllArgsConstructor
public enum Occurrence {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8),
    NINTH(9),
    TENTH(10),
    LAST(-1);


    @Getter final int mPosition;
}
