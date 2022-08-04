package io.schinzel.basicutils.configvar2;
import io.javalin.Javalin;

public class REMOVE_ME {

    public static void main(String[] args){
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World 2"));
        String apa = "dsffd";

    }
}
