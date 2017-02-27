package io.schinzel.basicutils;

/**
 * The purpose of this class is to have sets of not the most common chars readily available for tests.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */
public enum FunnyChars {
    SWEDISH_LETTERS("åäö"),
    SPECIAL_CHARACTERS("! # € % & / ( ) = ,;.:-_'*^¨~+"),
    DANISH_LETTERS("ÆæØøÅå"),
    ICELANDIC_LETTER("ÐðÁáÉéÓóÚúÝýÞþ"),
    CYRILLIC_NUMERALS("АВГДЄЅЗИѲ"),
    ARABIC_LETTERS("غ 	ضظخثتشرعسجج"),
    POLISH_LETTERS("ĄąŁłÓóŚśŹźŻż"),
    PERSIAN_LETTERS("ا ب پ ت ث ج چ ح خ د ذ ر ز ژ سش ص ض ط ظ ع غ ف ق ک گ ل م"),
    SERBO_CROATION_GAJ("džĐđČčĆćŠš"),
    SERBO_CYRILLIC("Б бГ гД дЂ ђЖ жЗ зИ иЉ љЊ њП пЋ ћУ уФ фЦ цЧ чЏ џШ ш"),
    CYRILLIC_LETTERS("ДЂЙЩ"),
    SHORT_STRING("a"),
    LONG_STRING("0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789__0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789__0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789__0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789__0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789");

    private final String mString;

    FunnyChars(String str){
        mString = str;
    }

    public String getString(){
        return mString;
    }
}
