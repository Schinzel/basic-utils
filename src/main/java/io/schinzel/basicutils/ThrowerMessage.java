package io.schinzel.basicutils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The purpose of this class is to allow chaining of Thrower.
 * <p>
 * E.g.
 * Thrower.throwIfTrue(myExpression)
 * .message("an error message");
 * <p>
 * Created by schinzel on 2017-05-03.
 */
@AllArgsConstructor
public class ThrowerMessage {
    /**
     * A thrower message that never goes of. This can be used so that no objects are created
     * if there is no exception to throw. This to be easier on the garbage collector.
     */
    public static ThrowerMessage THROWER_DUD = new ThrowerMessage(false);
    /** If true an exception will be thrown. */
    @Getter(AccessLevel.PACKAGE) final boolean error;


    /**
     * @param message The message that will be thrown if there is an exception to throw.
     */
    public void message(String message) {
        if (this.isError()) {
            throw new RuntimeException(message);
        }
    }


}
