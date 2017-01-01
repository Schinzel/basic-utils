package se.schinzel.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author schinzel
 */
public class MiscUtilTest extends MiscUtil {
    

    @Test
    public void testSnooze() {
        int nrOfItterations = 20;
        int snoozeTime = 100;
        long start = System.nanoTime();
        for (int i = 0; i < nrOfItterations; i++) {
            MiscUtil.snooze(snoozeTime);            
        }
        long end = System.nanoTime();
        //Calc the time to do all iterations
        long executionTimeInMS = (end-start)/1000000;
        //Calc the actual average snooze time
        long averageSnoozeTime = executionTimeInMS/nrOfItterations;
        //True if average snooze time is less than requestion snooze time +10%
        boolean notTenPercentOver = (averageSnoozeTime < (snoozeTime+snoozeTime/10));
        //True if average snooze time is more than requestion snooze time -10%
        boolean notTenPercentUnder = (averageSnoozeTime > (snoozeTime-snoozeTime/10));
        //Assert that snooze time is withint plus minus 10%
        assertTrue(notTenPercentOver && notTenPercentUnder);
        
    }
    
}
