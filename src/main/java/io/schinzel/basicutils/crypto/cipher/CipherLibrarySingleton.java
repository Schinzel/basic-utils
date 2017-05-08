package io.schinzel.basicutils.crypto.cipher;

/**
 * The purpose of this class to hold a CipherLibrary singleton instance. The rational for a
 * singleton is that typically encryption and decryption is used in so many places it can warrant
 * a singleton over passing a reference around.
 * <p>
 * Also, the singleton is practical for setting up a cipher enum, which can be useful for creating
 * more concise and readable code.
 * <p>
 * Created by Schinzel on 2017-05-08.
 */
public class CipherLibrarySingleton {
    private static class TheHolder {
        public static CipherLibrarySingleton INSTANCE = new CipherLibrarySingleton();
    }

    CipherLibrary mLibrary;
    CipherLibrary.CipherLibraryBuilder mBuilder = CipherLibrary.builder();


    /**
     * Package private so that the class cannot be instantiated outside the package.
     */
    CipherLibrarySingleton() {
    }


    /**
     * Note that once the method getLibrary has been used the instance has been created and this
     * method will throw an error.
     *
     * @return A cipher library builder.
     */
    public static CipherLibrary.CipherLibraryBuilder getBuilder() {
        //If builder is null, i.e. if the builder has already been used to create an instance.
        if (TheHolder.INSTANCE.mBuilder == null) {
            throw new RuntimeException("Cipher cannot be added as CipherLibrary already has been built.");
        }
        return TheHolder.INSTANCE.mBuilder;
    }


    /**
     * The first time this method is used, the cipher library is built with the ciphers given
     * to the builder.
     *
     * @return Returns a cipher library instance.
     */
    public static CipherLibrary getLibrary() {
        //If a library has not yet been build
        if (TheHolder.INSTANCE.mLibrary == null) {
            synchronized (TheHolder.INSTANCE) {
                TheHolder.INSTANCE.mLibrary = TheHolder.INSTANCE.mBuilder.build();
                TheHolder.INSTANCE.mBuilder = null;
            }
        }
        return TheHolder.INSTANCE.mLibrary;
    }

}

