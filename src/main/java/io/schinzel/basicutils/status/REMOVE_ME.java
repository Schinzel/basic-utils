package io.schinzel.basicutils.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
public class REMOVE_ME {

    class TestClass implements IStatusNode {

        final String mName;
        List<IStatusNode> mChildren = new ArrayList<>();

        TestClass(String name) {
            mName = name;
        }


        @Override
        public Status getStatus() {
            return Status.getBuilder().add("Name", mName).build();
        }


        @Override
        public Iterator<IStatusNode> getStatusChildren() {
            return mChildren.iterator();
        }


    }

    void doIt() {
        TestClass t = new TestClass("A1");
        t.mChildren.add(new TestClass(("B1")));
        TestClass b2 = new TestClass("B2");
        t.mChildren.add(b2);
        b2.mChildren.add(new TestClass("C1"));
        b2.mChildren.add(new TestClass("C2"));
        b2.mChildren.add(new TestClass("C3"));
        b2.mChildren.add(new TestClass("C4"));
        t.mChildren.add(new TestClass(("B3")));
        String s = t.getStatusAsString();
        System.out.println(s);
        System.out.println(t.getStatusAsJson().toString(3));

    }


    public static void main(String[] args) {
        new REMOVE_ME().doIt();
    }


}
