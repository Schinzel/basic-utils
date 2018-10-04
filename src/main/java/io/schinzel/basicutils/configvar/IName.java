package io.schinzel.basicutils.configvar;

/**
 * The purpose of this interface is to let enums implement it as to get less verbose code when
 * working with enums and ConfigVars. As enums have a method "name()", no code has to be added to an enum
 * to extend the interface.
 * I.e.
 * instead of
 * configVar.getValue(MyEnum.BEAR.name())
 * we have
 * configVar.getValue(MyEnum.BEAR)
 */
public interface IName {

    String name();
}
