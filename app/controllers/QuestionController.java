package controllers;

import at.ac.tuwien.big.we15.lab2.api.JeopardyGame;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.jeopardy;
import views.html.question;
import views.html.winner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08/05/15.
 */

public class QuestionController extends Controller {
    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result showQuestionPage(){
        JeopardyGame game = (JeopardyGame)Cache.get(session("username"));
        String[] postRequest = request().body().asFormUrlEncoded().get("question_selection");

        if(postRequest!=null) {
            int number = Integer.parseInt(postRequest[0]);
            game.chooseHumanQuestion(number);

            return ok(question.render(game, checkSession()));
        }

        return ok(jeopardy.render(game));
    }

    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result checkQuestion() {
        JeopardyGame game = (JeopardyGame)Cache.get(session("username"));
        List<Integer> intValues = new ArrayList<Integer>();

        if(game.isAnswerPending()) {
            String[] postRequest = request().body().asFormUrlEncoded().get("answers");

            for(int i=0;i<postRequest.length;i++)
                intValues.add(Integer.parseInt(postRequest[i]));

            game.answerHumanQuestion(intValues);
        }

       if(game.isGameOver()) {
            return ok(winner.render(game));
        } else {
            return ok(jeopardy.render(game));
        }
    }

    private static boolean checkSession() {     // nur für temporäre Zwecke
        if(session("username")==null) {         // Lösnug vllt über Überprüfung des game-Objekts?
            return false;
        } else {
            return true;
        }
    }
}
