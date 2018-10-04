package io.schinzel.basicutils.configvar;

/**
 * The purpose of this interface is to let enums implement it as to get less verbose code when
 * working with enums and ConfigVars.
 * <p>
 * I.e.
 * instead of
 * configVar.getValue(MyEnum.BEAR.getName())
 * we have
 * configVar.getValue(MyEnum.BEAR)
 * <p>
 * Did not choose method "name()" - which would require no implementation of code
 * in enums as enums have a name-method - as this triggers accidental override exceptiod in Kotlin.
 */
public interface IName {

    String getMyName();
}
