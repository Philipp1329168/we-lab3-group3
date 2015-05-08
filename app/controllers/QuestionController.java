package controllers;

import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;

import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyGame;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.question;

/**
 * Created by root on 08/05/15.
 */

public class QuestionController extends Controller {
    public static Result showQuestionPage(){
        SimpleJeopardyGame game = (SimpleJeopardyGame)Cache.get(session("username"));
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
