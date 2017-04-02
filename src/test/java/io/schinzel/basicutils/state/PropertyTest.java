package io.schinzel.basicutils.state;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {
    @Test
    public void getString() throws Exception {
        Property prop = new Property("my_key", "val_as_string", "str", "my_unit");
        assertEquals("my_key:val_as_stringmy_unit", prop.getString());
    }


    @Test
    public void getValueAsObject() throws Exception {
        Property prop = new Property("my_key", "val_as_string", 123d, "my_unit");
        assertEquals(123d, prop.getValueAsObject());
    }


    @Test
    public void getKey() throws Exception {
        Property prop = new Property("my_key", "val_as_string", "str", "my_unit");
        assertEquals("my_key", prop.getKey());
    }


    @Test
    public void getValueAsString() throws Exception {
        Property prop = new Property("my_key", "val_as_string", "str", "my_unit");
        assertEquals("val_as_string", prop.getValueAsString());
    }




    @Test
    public void getUnit() throws Exception {
        Property prop = new Property("my_key", "val_as_string", "str", "my_unit");
        assertEquals("my_unit", prop.getUnit());
    }

}