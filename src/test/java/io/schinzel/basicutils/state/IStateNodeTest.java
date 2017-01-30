package io.schinzel.basicutils.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class IStateNodeTest {

    class TestClass implements IStateNode {

        final String mName;
        List<IStateNode> mChildren = new ArrayList<>();

        TestClass(String name) {
            mName = name;
        }


        @Override
        public State getState() {
            return State.getBuilder().add("Name", mName).build();
        }


        @Override
        public Iterator<IStateNode> getStateChildren() {
            return mChildren.iterator();
        }


    }

    @Test
    public void testSomeMethod() {
        TestClass t = new TestClass("A1");
        t.mChildren.add(new TestClass(("B1")));
        TestClass b2 = new TestClass("B2");
        t.mChildren.add(b2);
        b2.mChildren.add(new TestClass("C1"));
        b2.mChildren.add(new TestClass("C2"));
        b2.mChildren.add(new TestClass("C3"));
        b2.mChildren.add(new TestClass("C4"));
        t.mChildren.add(new TestClass(("B3")));
        String s = t.getStateAsString();
        System.out.println(s);
        
    }


}
