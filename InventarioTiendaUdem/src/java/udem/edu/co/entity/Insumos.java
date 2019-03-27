/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udem.edu.co.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jordana Apolinario
 */
@Entity
@Table(name = "insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumos.findAll", query = "SELECT i FROM Insumos i"),
    @NamedQuery(name = "Insumos.findById", query = "SELECT i FROM Insumos i WHERE i.id = :id"),
    @NamedQuery(name = "Insumos.findByNombre", query = "SELECT i FROM Insumos i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Insumos.findByCantidad", query = "SELECT i FROM Insumos i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Insumos.findByPrecio", query = "SELECT i FROM Insumos i WHERE i.precio = :precio"),
    @NamedQuery(name = "Insumos.findByTipo", query = "SELECT i FROM Insumos i WHERE i.tipo = :tipo")})
public class Insumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Precio")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tipo")
    private String tipo;

    public Insumos() {
    }

    public Insumos(Integer id) {
        this.id = id;
    }

    public Insumos(Integer id, String nombre, int cantidad, double precio, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumos)) {
            return false;
        }
        Insumos other = (Insumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "udem.edu.co.entity.Insumos[ id=" + id + " ]";
    }
    
}
