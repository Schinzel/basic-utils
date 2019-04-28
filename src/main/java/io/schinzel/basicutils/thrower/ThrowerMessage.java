package io.schinzel.basicutils.thrower;

/**
 * The purpose of this class is to allow chaining of Thrower.
 * <p>
 * E.g.
 * Thrower.throwIfTrue(myExpression)
 * .message("an error message");
 * <p>
 * Created by schinzel on 2017-05-03.
 */
public class ThrowerMessage {
    /**
     * A thrower message that never goes of. This can be used so that no objects are created
     * if there is no exception to throw. This to be easier on the garbage collector.
     */
    private static final ThrowerMessage THROWER_DUD = new ThrowerMessage(false);
    private final boolean mError;


    ThrowerMessage(boolean boo) {
        mError = boo;
    }


    public static ThrowerMessage create(boolean isException) {
        return isException ? new ThrowerMessage(true) : THROWER_DUD;
    }


    /**
     * @param message The message that will be thrown if there is an exception to throw.
     */
    public void message(String message) {
        if (mError) {
            throw new RuntimeException(message);
        }
    }


    /**
     * Formats the error message using String.format.
     *
     * @param format As in String.format
     * @param args   As in String.format
     */
    public void message(String format, Object... args) {
        if (mError) {
            throw new RuntimeException(String.format(format, args));
        }
    }

}
