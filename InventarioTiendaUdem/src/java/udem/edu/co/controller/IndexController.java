/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udem.edu.co.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import udem.edu.co.entity.Usuarios;
import udem.edu.co.facade.UsuariosFacade;

/**
 *
 * @author Maritza
 */
@Named(value = "indexController")
@ViewScoped
public class IndexController implements Serializable{

    private Usuarios current;
    @EJB
    private udem.edu.co.facade.UsuariosFacade ejbFacade;
    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
        
    }
    @PostConstruct
    public void init (){
        current = new Usuarios();
    }
    public String iniciarSesion(){
        
        String redireccion = null;
        Usuarios logeo;
        try {
            logeo = ejbFacade.iniciarSesion(current);
            //Acceso a pagina despues de logearse
            if (logeo != null) {
                
                //almacenar en la sesión JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", logeo);
                redireccion = "/protegido/FaceLetPrincipal?faces-redirect=true";
            } 
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Datos incorrectos"));
              }
            
        }
        catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(redireccion));
        }
        return redireccion;
    }

    public Usuarios getCurrent() {
        return current;
    }

    public void setCurrent(Usuarios current) {
        this.current = current;
    }

    public UsuariosFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuariosFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    
    
}
