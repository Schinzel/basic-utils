package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class Property {
    private String mKey;
    private String mValueAsString;
    private Object mValueAsObject;
    private String mUnit;


}
