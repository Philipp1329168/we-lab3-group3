package controllers;

import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;

import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyGame;
import model.LoginData;
import play.cache.Cache;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.question;

import static play.data.Form.form;

/**
 * Created by root on 08/05/15.
 */

public class QuestionController extends Controller {
    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result showQuestionPage(){
        JeopardyGame game = (JeopardyGame)Cache.get(session("username"));
        System.out.println(game.getHuman().getName());
        DynamicForm requ = Form.form().bindFromRequest();
        int number = Integer.parseInt(requ.get("question_selection"));
        game.chooseHumanQuestion(number);
        return ok(question.render(game, checkSession()));
    }

    private static boolean checkSession() {     // nur für temporäre Zwecke
        if(session("username")==null) {         // Lösnug vllt über Überprüfung des game-Objekts?
            return false;
        } else {
            return true;
        }
    }
}
