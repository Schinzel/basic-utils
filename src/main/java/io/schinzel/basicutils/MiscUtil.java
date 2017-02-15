package io.schinzel.basicutils;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author schinzel
 */
public class MiscUtil {

    /**
     * Same as snooze but in seconds for more readable code.
     *
     * @param seconds The number of seconds to snooze. 
     */
    public static void snoozeSeconds(int seconds) {
        MiscUtil.snooze(seconds * 1000);
    }

    /**
     * Puts calling thread to sleep for the argument amount of milliseconds
     *
     * @param millisToSnooze The number of milliseconds to snooze. 
     */
    public static void snooze(int millisToSnooze) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisToSnooze);
        } catch (final InterruptedException e) {
            throw new RuntimeException("Snooze interrupted " + e.toString());
        }
    }

}
