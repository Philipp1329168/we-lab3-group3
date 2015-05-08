package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.authentication;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Welcome to Jeopardy"));
    }
    public static Result authentication() {
        return ok(authentication.render());
    }

}
