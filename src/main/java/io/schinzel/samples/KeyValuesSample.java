package io.schinzel.samples;

import io.schinzel.basicutils.collections.keyvalues.IValueKey;
import io.schinzel.basicutils.collections.keyvalues.KeyValues;

/**
 * Sample class to show how KeyValues can be used.
 * <p>
 * Created by schinzel on 2017-03-27.
 */
class KeyValuesSample {

    public static void main(String[] args) {
        new KeyValuesSample().doIt();

    }


    void doIt() {
        KeyValues<MyValue> mySet = KeyValues.<MyValue>create("My nice values")
                .add(new MyValue("A"))
                .add(new MyValue("B"));
        MyValue category = mySet.has("C")
                ? mySet.get("C")
                : mySet.addAndGet(new MyValue("C"));
        //Will throw exception as there is no value with this key
        mySet.get("apa");

    }


    class MyValue implements IValueKey {
        final String key;


        MyValue(String key) {
            this.key = key;
        }


        public String getKey() {
            return this.key;
        }
    }
}
