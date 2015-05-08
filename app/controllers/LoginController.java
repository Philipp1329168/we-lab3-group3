package controllers;


import model.LoginData;
import play.api.mvc.Result;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Security;
import views.html.authentication;

import static model.LoginData.authenticate;
import static play.data.Form.form;

/**
 * Created by Matthias on 08.05.2015.
 */
public class LoginController extends Controller {

    public static class LoginForm {

        @Constraints.MinLength(value = 4)
        @Constraints.MaxLength(value = 8)
        @Constraints.Required
        public String username;

        @Constraints.MinLength(value = 4)
        @Constraints.MaxLength(value = 8)
        @Constraints.Required
        public String password;

        public String validate() {
            if (authenticate(username, password) == null) {
                return Messages.get("authentication.msg.invalid");
            }
            return null;
        }
    }

    public static Result showLoginPage() {
        return ok(authentication.render(form(LoginData.class)));
    }

    @play.db.jpa.Transactional
    public static Result postLogin() {
        Form<LoginForm> formData = form(LoginForm.class).bindFromRequest();

        if(formData.hasErrors()) {
            return badRequest(authentication.render(formData));
        } else {
            session().clear();
            session("username",formData.get().username);
            return redirect(routes.QuizController.showStartPage());
        }
    }


    @Security.Authenticated(SecurityAuthenticator.class)
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.LoginController.showLoginPage());
    }
}
