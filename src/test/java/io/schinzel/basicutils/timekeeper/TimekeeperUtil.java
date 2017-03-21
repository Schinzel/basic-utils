package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class TimekeeperUtil {

    /**
     * 
     * @return A set up timekeeper populated with values. 
     */
    static Timekeeper getTimekeeper() {
        //Get the timekeeper. There is also a create method if one does not want
        //to use a singleton.
        Timekeeper timekeeper = Timekeeper.getSingleton();
        timekeeper.startLap("A");
        //Some code runs here that will be measured as lap A
        timekeeper.stopLap();
        timekeeper.startLap("B");
        for (int i = 0; i < 10; i++) {
            //Start lab B1. As lap B is running, B1 will be a sub-lap to B.
            timekeeper.startLap("B1");
            Sandman.snooze(10);
            timekeeper.stopLap();
        }
        for (int i = 0; i < 5; i++) {
            //Start lab B2. As lap B is running, B2 will be a sub-lap to B.
            timekeeper.startLap("B2");
            Sandman.snooze(20);
            timekeeper.stopLap();
        }
        timekeeper.stopLap();
        timekeeper.startLap("C");
        //Some code runs here that will be measured as lap C
        Sandman.snooze(10);
        timekeeper.stopLap();
        //Stop the whole stopwatch
        timekeeper.stop();
        return timekeeper;
    }

}
