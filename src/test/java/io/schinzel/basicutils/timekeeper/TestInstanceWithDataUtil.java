package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Sandman;

class TestInstanceWithDataUtil {

    /**
     * @return A set up timekeeper populated with values.
     */
    static Timekeeper getTimekeeper() {
        //Get the timekeeper. There is also a create method if one does not want
        //to use a singleton.
        Timekeeper timekeeper = Timekeeper.getSingleton();
        timekeeper.start("A");
        //Some code runs here that will be measured as lap A
        timekeeper.stopAndStart("B");
        for (int i = 0; i < 10; i++) {
            //Start lab B1. As lap B is running, B1 will be a sub-lap to B.
            timekeeper.start("B1");
            Sandman.snoozeMillis(10);
            timekeeper.stop();
        }
        for (int i = 0; i < 5; i++) {
            //Start lab B2. As lap B is running, B2 will be a sub-lap to B.
            timekeeper.start("B2");
            Sandman.snoozeMillis(20);
            timekeeper.stop();
        }
        timekeeper.stopAndStart("c");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        timekeeper.stop();
        //Stop the whole stopwatch
        return timekeeper;
    }

}
