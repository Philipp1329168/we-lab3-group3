package controllers;

import model.LoginData;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.defaultpages.error;
import views.html.registration;


import static model.LoginData.checkUserExists;
import static play.data.Form.form;

/**
 * Created by Matthias on 08.05.2015.
 */
public class RegistrationController extends Controller {

    public static class RegForm {

        public String name;

        public String validate() {
            if (!checkUserExists(name)) {
                return Messages.get("registration.msg.invalid");
            }
            return null;
        }

    }

    public static Result showRegistrationPage() {
        return ok(registration.render(form(LoginData.class),form(RegForm.class),checkSession()));
    }

    @Transactional
    public static Result postRegistration() {
        Form<RegForm> regData = form(RegForm.class).bindFromRequest();
        Form<LoginData> formData = form(LoginData.class).bindFromRequest();
        if(formData.hasErrors() || regData.hasErrors())
            return badRequest(registration.render(formData,regData,checkSession()));
        else {
            LoginData newData = formData.get();
            JPA.em().persist(newData);
            System.out.println("pers done");
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

    @Transactional
    private static void persistUser(LoginData data) {
            JPA.em().persist(data);
    }
}