package io.schinzel.basicutils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by schinzel on 2017-02-28.
 */
public class IThrowerTest {

    private class MyTestClass implements IThrower {

        void bapp() {
            this.throwIfTrue(true, "My Message is here.", "k1", "v1", "k2", "v2");
        }
    }


    @Test
    public void testError(){
        new MyTestClass().bapp();
    }
}