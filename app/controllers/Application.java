package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.authentication;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result authentication() {
        return ok(authentication.render(form(controllers.LoginController.LoginForm.class)));
    }

}
