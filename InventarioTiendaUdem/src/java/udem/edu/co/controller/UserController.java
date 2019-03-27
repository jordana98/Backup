/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udem.edu.co.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Pablo
 */

@ManagedBean
@SessionScoped
//se encarga del manejo de los files importados
public class UserController {
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    Connection cx;
    String bd ="tiendaudem";
    String url = "jdbc:mysql://localhost:3306/"+bd;
    String user = "root";
    String pass = "mariadb";
    
    private Part file;
    //se cargan el archivo para generar uno en un repositorio local 
    public void uploadFile(){
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        
            try{
                InputStream input=file.getInputStream();
                File f=new File("ArchivoCsv.txt");//destino donde vamos a guardar esa copia
                if(!f.exists()){
                    f.createNewFile();
                }
                FileOutputStream output=new FileOutputStream(f);
                byte[] buffer=new byte[1024];
                int length;
                //Se escribe en el archivo Kali(local) la informacion del archivo F(importado)
                while((length=input.read(buffer))>0){
                    output.write(buffer, 0, length);  
                }
                //Implementamos los metodos para leer el archivo que fue cargado (f)___________________________________________
                try (FileReader reader = new FileReader(f)) {
                BufferedReader buufr = new BufferedReader(reader);
                //esta variable tendra la linea del archivo txt
                String line = "";
                //se realiza el ciclo hasta que ya no hayan mas datos en el txt
                while ( (line=buufr.readLine()) != null) {
                    String lineaUsu = line;
                    String [] vect = lineaUsu.split(",");
                //****************************************************
                    Connection con = conectar();    
                    //se hacen las 
                    ps = con.prepareStatement("INSERT INTO insumos(Nombre,Cantidad,Precio,Tipo) VALUES(?,?,?,?) ");
                    //ps.setInt(1, 20);
                    ps.setString(1, vect[0]);
                    ps.setInt(2,Integer.valueOf(vect[1]));
                    ps.setDouble(3, Double.valueOf(vect[2]));
                    ps.setString(4, vect[3]);
                    resp = ps.executeUpdate();
                    System.out.println(ps);
                }//****************************************************
                //cerramos el buffer
                buufr.close();
            }catch(IOException e){
                System.out.println("Error de lectura en disco (borraron el archivo, archivo pirata)");
            }
                ///______________________________________________
                input.close();
                output.close();
            }catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
         FacesMessage msg = new FacesMessage("Succesfu is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    //se encarga de establecer la conexion con la BD
     public Connection conectar(){
         try {
             Class.forName("org.mariadb.jdbc.Driver");
             cx = DriverManager.getConnection(url,user,pass);
             } 
         catch (ClassNotFoundException  | SQLException ex) {
             System.out.println("no logro conectar hp");
            }
         return cx;
    }
    
}