package io.schinzel.basicutils;

import java.util.concurrent.TimeUnit;

/**
 * The purpose of this class is to offer a one-line snooze/sleep instead to the normal five lines.
 *
 * @author schinzel
 */
@SuppressWarnings("WeakerAccess")
public class Sandman {
    Sandman() {
    }

    /**
     * Same as snooze but in seconds for more readable code.
     *
     * @param seconds The number of seconds to snooze.
     */
    public static void snoozeSeconds(int seconds) {
        Sandman.snoozeMillis(seconds * 1000);
    }

    /**
     * Puts calling thread to sleep for the argument amount of milliseconds
     *
     * @param millisToSnooze The number of milliseconds to snooze.
     */
    public static void snoozeMillis(int millisToSnooze) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisToSnooze);
        } catch (final InterruptedException e) {
            throw new RuntimeException("Snooze interrupted " + e.toString());
        }
    }

}
