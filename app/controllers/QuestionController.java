package controllers;

import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;

import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyGame;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.question;

/**
 * Created by root on 08/05/15.
 */

public class QuestionController extends Controller {
    public static Result showQuestionPage(){
        return ok(question.render(game));
    }
}
