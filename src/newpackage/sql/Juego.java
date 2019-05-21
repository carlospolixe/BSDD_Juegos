/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "JUEGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j")
    , @NamedQuery(name = "Juego.findById", query = "SELECT j FROM Juego j WHERE j.id = :id")
    , @NamedQuery(name = "Juego.findByNombre", query = "SELECT j FROM Juego j WHERE j.nombre = :nombre")
    , @NamedQuery(name = "Juego.findByGenero", query = "SELECT j FROM Juego j WHERE j.genero = :genero")
    , @NamedQuery(name = "Juego.findByCodigo", query = "SELECT j FROM Juego j WHERE j.codigo = :codigo")
    , @NamedQuery(name = "Juego.findByDescripcion", query = "SELECT j FROM Juego j WHERE j.descripcion = :descripcion")
    , @NamedQuery(name = "Juego.findByPaisDesarrolador", query = "SELECT j FROM Juego j WHERE j.paisDesarrolador = :paisDesarrolador")
    , @NamedQuery(name = "Juego.findByFechaSalida", query = "SELECT j FROM Juego j WHERE j.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "Juego.findByNumDesarrolladores", query = "SELECT j FROM Juego j WHERE j.numDesarrolladores = :numDesarrolladores")
    , @NamedQuery(name = "Juego.findByPresupuesto", query = "SELECT j FROM Juego j WHERE j.presupuesto = :presupuesto")
    , @NamedQuery(name = "Juego.findByStock", query = "SELECT j FROM Juego j WHERE j.stock = :stock")})
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "GENERO")
    private String genero;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "PAIS_DESARROLADOR")
    private int paisDesarrolador;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "NUM_DESARROLLADORES")
    private Short numDesarrolladores;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRESUPUESTO")
    private BigDecimal presupuesto;
    @Column(name = "STOCK")
    private Boolean stock;
    @JoinColumn(name = "CODIGO_DESARROLLADORA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Desarrolladora codigoDesarrolladora;

    public Juego() {
    }

    public Juego(Integer id) {
        this.id = id;
    }

    public Juego(Integer id, String nombre, String genero, String codigo, int paisDesarrolador) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.codigo = codigo;
        this.paisDesarrolador = paisDesarrolador;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPaisDesarrolador() {
        return paisDesarrolador;
    }

    public void setPaisDesarrolador(int paisDesarrolador) {
        this.paisDesarrolador = paisDesarrolador;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Short getNumDesarrolladores() {
        return numDesarrolladores;
    }

    public void setNumDesarrolladores(Short numDesarrolladores) {
        this.numDesarrolladores = numDesarrolladores;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

    public Desarrolladora getCodigoDesarrolladora() {
        return codigoDesarrolladora;
    }

    public void setCodigoDesarrolladora(Desarrolladora codigoDesarrolladora) {
        this.codigoDesarrolladora = codigoDesarrolladora;
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
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.sql.Juego[ id=" + id + " ]";
    }
    
}
