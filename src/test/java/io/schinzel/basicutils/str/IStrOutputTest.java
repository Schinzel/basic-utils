package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by schinzel on 2017-02-27.
 */
public class IStrOutputTest {
    private class MyTestClass extends MyClass implements IStrOutput<MyClass> {

    }

    @Test
    public void pln() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MyTestClass myTestClass = new MyTestClass();
        myTestClass.a("Monkey!");
        myTestClass.pln();
        Assert.assertEquals("Monkey!\n", outContent.toString());
        System.setOut(null);
    }


    @Test
    public void writeToFile() throws Exception {
    }


    @Test
    public void writeToFile1() throws Exception {

    }

}