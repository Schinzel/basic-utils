package io.schinzel.samples;

import io.schinzel.basicutils.Sandman;
import io.schinzel.basicutils.timekeeper.Timekeeper;

/**
 * The purpose of this file is to show sample Timekeeper code.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */
public class TimekeeperSample {
    //Get the timekeeper. There is also a create method if one does not want
    //to use a singleton.
    public static void main(String[] args) {
        Timekeeper timekeeper = Timekeeper.getSingleton();
        timekeeper.startLap("A");
        //Some code runs here that will be measured as lap A
        timekeeper.stopLap();
        timekeeper.startLap("B");
        for (int i = 0; i < 10; i++) {
            //Start lab B1. As lap B is running, B1 will be a sub-lap to B.
            timekeeper.startLap("B1");
            Sandman.snoozeMillis(1);
            timekeeper.stopLap();
        }
        for (int i = 0; i < 5; i++) {
            //Start lab B2. As lap B is running, B2 will be a sub-lap to B.
            timekeeper.startLap("B2");
            Sandman.snoozeMillis(20);
            timekeeper.stopLap();
        }
        timekeeper.stopLap();
        timekeeper.startLap("C");
        //Some code runs here that will be measured as lap C
        Sandman.snoozeMillis(10);
        timekeeper.stopLap();
        //Stop the whole stopwatch
        timekeeper.stop();
        System.out.println(timekeeper.toString());
    }
}
