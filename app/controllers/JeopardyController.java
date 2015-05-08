package controllers;

import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;

import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyGame;
import play.i18n.Messages;
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
        JeopardyFactory factory = new PlayJeopardyFactory(Messages.get("json.file"));
        JeopardyGame game = new SimpleJeopardyGame(factory);
        return ok(jeopardy.render(game));
    }
}
