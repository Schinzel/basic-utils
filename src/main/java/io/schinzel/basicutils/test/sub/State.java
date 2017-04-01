package io.schinzel.basicutils.test.sub;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class State {
    List<Property> mProperties;

    /**
     * @return The properties - but not its children - as a string.
     */
    public String getPropertiesAsString() {
        return mProperties
                .stream()
                .map(Property::getString)
                .collect(Collectors.joining(" "));
    }
}
