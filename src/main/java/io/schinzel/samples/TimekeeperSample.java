package io.schinzel.samples;

import io.schinzel.basicutils.Sandman;
import io.schinzel.basicutils.timekeeper.Timekeeper;

/**
 * The purpose of this file is to show sample Timekeeper code.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */
class TimekeeperSample {
    public static void main(String[] args) {
        //Get the timekeeper. There is also a create method if one does not want to use a singleton.
        Timekeeper timekeeper = Timekeeper.getSingleton();
        timekeeper.start("A");
        //Some code runs here that will be measured as lap A
        Sandman.snoozeMillis(5);
        timekeeper.stopAndStart("B");
        for (int i = 0; i < 10; i++) {
            //Start lab B1. As lap B is running, B1 will be a sub-lap to B.
            timekeeper.start("B2");
            Sandman.snoozeMillis(1);
            timekeeper.stop();
        }
        for (int i = 0; i < 5; i++) {
            //Start lab B2. As lap B is running, B2 will be a sub-lap to B.
            timekeeper.start("B1");
            Sandman.snoozeMillis(20);
            timekeeper.stop();
        }
        for (int i = 0; i < 5; i++) {
            //Start lap B2. As lap B is running, B2 will be a sub-lap to B.
            timekeeper.start("B3");
            Sandman.snoozeMillis(20);
            timekeeper.stop();
        }
        timekeeper.stopAndStart("C");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        timekeeper.stopAndStart("D");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        timekeeper.stopAndStart("E");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        timekeeper.stopAndStart("F");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        timekeeper.stopAndStart("CCC");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        //Stop current lap, i.e. lap C
        timekeeper.stop();
        //Get results and render
        timekeeper.getResults().getStr().writeToSystemOut();
    }
}
