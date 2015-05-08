package controllers;

import model.LoginData;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.registration;


import static play.data.Form.form;

/**
 * Created by Matthias on 08.05.2015.
 */
public class RegistrationController extends Controller {

    public static Result showRegistrationPage() {
        return ok(registration.render(form(LoginData.class),checkSession()));
    }

    @Transactional
    public static Result postRegistration() {
        Form<LoginData> formData = form(LoginData.class).bindFromRequest();
        System.out.println(formData.toString());
        if(formData.hasErrors()) {
            return badRequest(registration.render(formData,checkSession()));
        } else {
            LoginData newData = formData.get();
            persistUser(newData);
            return redirect(routes.LoginController.showLoginPage());
        }
    }

    private static boolean checkSession() {
        if(session("username")==null) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean persistUser(LoginData data) {
        JPA.em().persist(data);
        return true;
    }
}