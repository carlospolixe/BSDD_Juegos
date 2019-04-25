/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "DESARROLLADORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desarrolladora.findAll", query = "SELECT d FROM Desarrolladora d")
    , @NamedQuery(name = "Desarrolladora.findById", query = "SELECT d FROM Desarrolladora d WHERE d.id = :id")
    , @NamedQuery(name = "Desarrolladora.findByCodigoDesarrolladora", query = "SELECT d FROM Desarrolladora d WHERE d.codigoDesarrolladora = :codigoDesarrolladora")
    , @NamedQuery(name = "Desarrolladora.findByFechaCreacionEmpresa", query = "SELECT d FROM Desarrolladora d WHERE d.fechaCreacionEmpresa = :fechaCreacionEmpresa")
    , @NamedQuery(name = "Desarrolladora.findByNombre", query = "SELECT d FROM Desarrolladora d WHERE d.nombre = :nombre")})
public class Desarrolladora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CODIGO_DESARROLLADORA")
    private int codigoDesarrolladora;
    @Column(name = "FECHA_CREACION_EMPRESA")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionEmpresa;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoDesarrolladora")
    private Collection<Juego> juegoCollection;

    public Desarrolladora() {
    }

    public Desarrolladora(Integer id) {
        this.id = id;
    }

    public Desarrolladora(Integer id, int codigoDesarrolladora, String nombre) {
        this.id = id;
        this.codigoDesarrolladora = codigoDesarrolladora;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoDesarrolladora() {
        return codigoDesarrolladora;
    }

    public void setCodigoDesarrolladora(int codigoDesarrolladora) {
        this.codigoDesarrolladora = codigoDesarrolladora;
    }

    public Date getFechaCreacionEmpresa() {
        return fechaCreacionEmpresa;
    }

    public void setFechaCreacionEmpresa(Date fechaCreacionEmpresa) {
        this.fechaCreacionEmpresa = fechaCreacionEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Juego> getJuegoCollection() {
        return juegoCollection;
    }

    public void setJuegoCollection(Collection<Juego> juegoCollection) {
        this.juegoCollection = juegoCollection;
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
        if (!(object instanceof Desarrolladora)) {
            return false;
        }
        Desarrolladora other = (Desarrolladora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.sql.Desarrolladora[ id=" + id + " ]";
    }
    
}
