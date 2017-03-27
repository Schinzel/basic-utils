package io.schinzel.samples;

import io.schinzel.basicutils.collections.keyvalues.IValueKey;
import io.schinzel.basicutils.collections.keyvalues.KeyValues;

/**
 * Sample class to show how KeyValues can be used.
 * <p>
 * Created by schinzel on 2017-03-27.
 */
public class KeyValuesSample {

    public static void main(String[] args) {
        new KeyValuesSample().doIt();

    }


    public void doIt() {
        KeyValues<MyValue> mySet = KeyValues.<MyValue>create("My nice values")
                .add(new MyValue("A"))
                .add(new MyValue("B"));
        MyValue category = mySet.has("C")
                ? mySet.get("C")
                : mySet.addAndGet(new MyValue("C"));

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
