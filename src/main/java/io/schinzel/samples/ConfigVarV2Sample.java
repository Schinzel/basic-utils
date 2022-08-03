package io.schinzel.samples;

import io.schinzel.basicutils.configvar2.ConfigVarV2;
import io.schinzel.basicutils.configvar2.PropertyFileReader;

public class ConfigVarV2Sample {
    public static void main(String[] args) {
        final ConfigVarV2 configVarV2 = ConfigVarV2.builder()
                .enableCache(true)
                .configVarReader(new PropertyFileReader("src/main/resources/io/schinzel/samples/sample_properties.txt"))
                .build();
        final String apeValue = configVarV2.getValue("ape");
        System.out.println(apeValue);

    }

}