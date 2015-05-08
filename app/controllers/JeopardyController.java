package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.jeopardy;

import static model.LoginData.authenticate;
import static play.data.Form.form;

/**
 * Created by root on 08/05/15.
 */
public class JeopardyController extends Controller {
    public static Result showJeopardyPage() {
        return ok(jeopardy.render());
    }
}
