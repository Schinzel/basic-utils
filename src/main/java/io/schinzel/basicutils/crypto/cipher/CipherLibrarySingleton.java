package io.schinzel.basicutils.crypto.cipher;

/**
 * The purpose of this class
 * <p>
 * Created by Schinzel on 2017-05-08.
 */
public class CipherLibrarySingleton {
    private static class TheHolder {
        public static CipherLibrarySingleton INSTANCE = new CipherLibrarySingleton();
    }

    CipherLibrary mLibrary;
    CipherLibrary.CipherLibraryBuilder mBuilder = CipherLibrary.builder();


    CipherLibrarySingleton() {
    }


    public static CipherLibrary.CipherLibraryBuilder getBuilder() {
        if (TheHolder.INSTANCE.mBuilder == null) {
            throw new RuntimeException("Cipher cannot be added as CipherLibrary already has been built.");

        }
        return TheHolder.INSTANCE.mBuilder;
    }


    public static CipherLibrary getLibrary() {
        if (TheHolder.INSTANCE.mLibrary == null) {
            synchronized (TheHolder.INSTANCE) {
                TheHolder.INSTANCE.mLibrary = TheHolder.INSTANCE.mBuilder.build();
                TheHolder.INSTANCE.mBuilder = null;
            }
        }
        return TheHolder.INSTANCE.mLibrary;
    }

}

