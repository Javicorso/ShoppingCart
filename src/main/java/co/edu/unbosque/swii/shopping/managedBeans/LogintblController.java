/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.managedBeans;

import co.edu.unbosque.swii.shopping.model.LoginDAO;
import co.edu.unbosque.swii.shopping.model.TblUser;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huber
 */
@ManagedBean(name = "logintblController")
@SessionScoped
public class LogintblController implements Serializable {

    public String user;
    public String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String validateLogin() {
        System.out.println("user: " + user);
        System.out.println("pass: " + pass);
        Map<String, Object> userInfo = LoginDAO.validateLogin(user, pass);
        TblUser tblUser = (TblUser) userInfo.get("user");
        String res = tblUser.getUsername();
        switch (res) {
            case "0":
                showMessage("Usuario y/o contraseña incorrectos");
                return "index";
            case "-":
                showMessage("El intento de conexión falló");
                return "index";
            default:
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> sessionMap = externalContext.getSessionMap();
                sessionMap.put("userInfo", userInfo);
                return "shoppingList";
        }

    }

    public void showMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
    }
}
