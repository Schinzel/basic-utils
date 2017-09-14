package io.schinzel.samples;

import io.schinzel.basicutils.collections.namedvalues.IValueWithKey;
import io.schinzel.basicutils.collections.namedvalues.ValuesWithKeys;

/**
 * Sample class to show how ValuesWithKeys can be used.
 * <p>
 * Created by schinzel on 2017-03-27.
 */
class ValuesWithKeysSample {

    public static void main(String[] args) {
        new ValuesWithKeysSample().doIt();

    }


    void doIt() {
        ValuesWithKeys<MyValue> mySet = ValuesWithKeys.<MyValue>create("My nice values")
                .add(new MyValue("A"))
                .add(new MyValue("B"));
        MyValue category = mySet.has("C")
                ? mySet.get("C")
                : mySet.addAndGet(new MyValue("C"));
        //Will throw exception as there is no value with this key
        mySet.get("apa");

    }


    class MyValue implements IValueWithKey {
        final String key;


        MyValue(String key) {
            this.key = key;
        }


        public String getKey() {
            return this.key;
        }
    }
}
