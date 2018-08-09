package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to throw the string held as an exception.
 *
 * @author Schinzel
 */
interface IStrException<T extends IStr<T>> extends IStr<T> {

    /**
     * Throw the string held as a runtime exception. The stack trace is changed to exclude Str.
     *
     * @return This for chaining
     */
    default T throwRuntime() {
        RuntimeException runtimeException = new RuntimeException(this.asString());
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        StackTraceElement[] newStackTrace = removeFirstElement(stackTrace);
        runtimeException.setStackTrace(newStackTrace);
        throw runtimeException;
    }


    /**
     *
     * @param stackTrace
     * @return The argument stacktrace with the first element removed
     */
    static StackTraceElement[] removeFirstElement(StackTraceElement[] stackTrace){
        int newSize = stackTrace.length - 1;
        StackTraceElement[] newStackTrace = new StackTraceElement[newSize];
        System.arraycopy(stackTrace, 1, newStackTrace, 0, newSize);
        return newStackTrace;
    }

}
