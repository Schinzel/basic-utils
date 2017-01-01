package se.schinzel.util;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author schinzel
 */
public class MiscUtil {

    /**
     * Same as snooze but in seconds for more readable code. 
     * @param seconds 
     */
    public static void snoozeSeconds(int seconds) {
        MiscUtil.snooze(seconds*1000);
    }


    /**
     * Puts calling thread to sleep for the argument amount of milliseconds
     *
     * @param millisToSnooze
     */
    public static void snooze(int millisToSnooze) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisToSnooze);
        } catch (final InterruptedException e) {
            throw new RuntimeException("InterruptedException " + e.toString());
        }
    }

}
