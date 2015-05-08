package controllers;

import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by Matthias on 08.05.2015.
 */
public class QuizController {

    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result showStartPage() {
        return null;// ok(jeopardy.render());
    }
}
