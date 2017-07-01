package io.schinzel.basicutils.beta.configvar;

/**
 * The purpose of this class is to return Heroku config variables.
 * <p>
 * Created by schinzel on 2017-07-01.
 */
public class HerokuConfigVar extends ConfigVar {

    public static HerokuConfigVar create() {
        return new HerokuConfigVar();
    }


    HerokuConfigVar() {
        super(".env");
    }

}
