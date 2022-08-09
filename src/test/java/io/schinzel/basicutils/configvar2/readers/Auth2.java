package io.schinzel.basicutils.configvar2.readers;

import io.javalin.core.security.AccessManager;

public class Auth2 {


    public static final AccessManager accessManager = (handler, ctx, permittedRoles) -> {
        String app_username = "my_username";
        String app_password = "my_password";
        String username = ctx.basicAuthCredentials().getUsername();
        String password = ctx.basicAuthCredentials().getPassword();
        if (app_username.equals(username) && app_password.equals(password)) {
            handler.handle(ctx);
        } else {
            ctx.status(401).result("Unauthorized");
        }
    };
}
