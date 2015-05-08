package controllers;

import models.LoginData;
import play.api.mvc.Result;
import play.data.Form;
import play.db.jpa.JPA;
import views.html.registration;

import static play.data.Form.form;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by Matthias on 08.05.2015.
 */
public class RegistrationController {


    public static Result showRegistrationPage() {
        return ok(registration.render(form(LoginData.class),checkSession()));
    }

    public static Result postRegistration() {
        Form<LoginData> formData = Form.form(LoginData.class).bindFromRequest();

        if(formData.hasErrors()) {
            return badRequest(registration.render(formData,checkSession());
        } else {
            LoginData newData = formData.get();
            persistUser(newData);
            return redirect(routes.LoginController.showLoginPage());
        }
    }


    private static boolean checkSession() {
        if(session("username")==null)
            return false;
        else
            return true;
    }

    private static boolean persistUser(LoginData data) {
        JPA.em().persist(data);
        return true;
    }
}
