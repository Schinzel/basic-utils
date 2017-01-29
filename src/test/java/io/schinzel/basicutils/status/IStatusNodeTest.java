package io.schinzel.basicutils.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class IStatusNodeTest {

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
        public List<IStatusNode> getStatusChildren() {
            return mChildren;
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
        String s = t.getStatusAsString();
        System.out.println(s);
        
    }


}
