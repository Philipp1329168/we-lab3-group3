package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

/**
 * Created by Matthias on 08.05.2015.
 */
public class SecurityAuthenticator extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("username");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.LoginController.showLogin());
    }
}
