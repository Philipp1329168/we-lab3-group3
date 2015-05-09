package model;

import at.ac.tuwien.big.we15.lab2.api.Avatar;
import at.ac.tuwien.big.we15.lab2.api.User;
import controllers.RegistrationController;
import play.data.validation.Constraints;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by root on 07/05/15.
 */
@Entity
public class LoginData implements User{

    public enum Gender{male,female}


    @Id
    @Constraints.MinLength(value = 4)
    @Constraints.MaxLength(value = 8)
    @Column(nullable = false)
    private String name;

    @Constraints.MinLength(value = 4)
    @Constraints.MaxLength(value = 8)
    @Column(nullable = false)
    private String password;


    private String firstname;


    private String lastname;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Avatar avatar;

    private boolean noValidBirthdate(Date birthdate) {
        Date rightNow = new Date();
        if (birthdate == null) {
            return false;
        } else {
            if (!birthdate.before(rightNow)) {
                return true;
            } else {
                return false;
            }
        }
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Avatar getAvatar() {
        return avatar;
    }


    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @play.db.jpa.Transactional
    public static LoginData authenticate(String username, String password) {
        LoginData foundUser = JPA.em().find(LoginData.class, username);
        if (foundUser != null) {
            if (foundUser.getPassword().equals(password)) {
                //auth OK
                return foundUser;
            }
            //auth failed
            return null;
        } else {
            //auth failed
            return null;
        }
    }

    @play.db.jpa.Transactional
    public static boolean checkUserExists(String username) {

        System.out.println(username);

        LoginData foundUser = JPA.em().find(LoginData.class, username);

        System.out.println(foundUser);

        if (foundUser == null) {
                return true;
            }
        else {
            return false;
        }
    }

    private static LoginData getUserByName(String name) {
        return JPA.em().find(LoginData.class, name);
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", avatar=" + avatar +
                '}';
    }
}
