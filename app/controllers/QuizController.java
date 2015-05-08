package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by Matthias on 08.05.2015.
 */
public class QuizController extends Controller{

    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result showStartPage() {
        return null;// ok(jeopardy.render());
    }
}
