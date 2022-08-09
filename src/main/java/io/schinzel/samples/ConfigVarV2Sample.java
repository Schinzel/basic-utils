package io.schinzel.samples;

import io.schinzel.basicutils.configvar2.ConfigVarV2;
import io.schinzel.basicutils.configvar2.readers.PropertyFileConfigVarReader;

public class ConfigVarV2Sample {
    public static void main(String[] args) {
        final ConfigVarV2 configVarV2 = ConfigVarV2.builder()
                .configVarReader(new PropertyFileConfigVarReader("src/main/resources/io/schinzel/samples/sample_properties.txt"))
                .build();
        final String apeValue = configVarV2.getValue("ape");
        System.out.println(apeValue);

    }

}