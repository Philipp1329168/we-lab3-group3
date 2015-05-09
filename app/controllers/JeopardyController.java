package controllers;

import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;

import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyGame;
import model.LoginData;
import play.cache.Cache;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.jeopardy;

import static model.LoginData.authenticate;
import static play.data.Form.form;

/**
 * Created by root on 08/05/15.
 */
@play.db.jpa.Transactional
public class JeopardyController extends Controller {

    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result showJeopardyPage() {
        JeopardyGame game = createGame();
        Cache.set(session("username"), game);
        return ok(jeopardy.render(game));
    }

    private static JeopardyGame createGame(){
        JeopardyFactory factory = new PlayJeopardyFactory(Messages.get("json.file"));
        JeopardyGame game = new SimpleJeopardyGame(factory);
        LoginData user = JPA.em().find(LoginData.class, session().get("username"));
        game.getHuman().setName(user.getName());
        game.getHuman().setAvatar(user.getAvatar());
        return game;
    }

}
