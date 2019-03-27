/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udem.edu.co.controller;


import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import udem.edu.co.entity.Usuarios;

/**
 *
 * @author Pablo
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable{
    //este metodo se encarga de redireccionar a la pagina "permisos" cuando intentan acceder directamente al login
    public void verificarSesion(){
        Usuarios log=null;
        try{
            //usuario es un alias en idexcontroller
            log =(Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
           //valida si se logeo o no, para redirigirlo al inicio
            if (log==null) {
                System.out.println("UUUUUUPS ERROR AL VERIFICAR SESION");
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/index.xhtml");
            }
        
        }catch(Exception e){
            System.out.println("UUUUUUPS ERROR AL VERIFICAR SESION");
        }
    }
    
}
