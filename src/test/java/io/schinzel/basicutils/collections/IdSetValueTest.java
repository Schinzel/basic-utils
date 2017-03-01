package io.schinzel.basicutils.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by schinzel on 2017-02-26.
 */
public class IdSetValueTest {
    class MyClass implements IdSetValue{
        int mOrder;
        String mId;

        MyClass(int order, String id){
            mOrder = order;
            mId = id;
        }

        @Override
        public String getId() {
            return mId;
        }
    }

    @Test
    public void compareTo() throws Exception {
        List<MyClass> list = Arrays.asList(
                new MyClass(2, "Ab"),
                new MyClass(1, "aa"),
                new MyClass(3, "B"),
                new MyClass(9, "tB"),
                new MyClass(4, "C"),
                new MyClass(5, "D"),
                new MyClass(7, "OB"),
                new MyClass(6, "oA"),
                new MyClass(8, "TA"),
                new MyClass(10, "Ö"),
                new MyClass(0, "-")
        );
        list = list.stream().sorted().collect(toList());
        int prev = -100;
        for(MyClass myClass: list){
            int current = myClass.mOrder;
            Assert.assertThat(current, greaterThan(prev));
            prev = current;
        }

    }

}