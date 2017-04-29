/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author ianbe
 */
@Entity
@Table(name = "planoaula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planoaula.findAll", query = "SELECT p FROM Planoaula p")
    , @NamedQuery(name = "Planoaula.findByIdplanoaula", query = "SELECT p FROM Planoaula p WHERE p.idplanoaula = :idplanoaula")
    , @NamedQuery(name = "Planoaula.findByDatainicio", query = "SELECT p FROM Planoaula p WHERE p.datainicio = :datainicio")
    , @NamedQuery(name = "Planoaula.findByDatafim", query = "SELECT p FROM Planoaula p WHERE p.datafim = :datafim")
    , @NamedQuery(name = "Planoaula.findByStatus", query = "SELECT p FROM Planoaula p WHERE p.status = :status")})
public class Planoaula implements Serializable {

    @Column(name = "status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planoaula")
    private List<DiasemanaHasEstrategia> diasemanaHasEstrategiaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplanoaula")
    private Integer idplanoaula;
    @Basic(optional = false)
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Basic(optional = false)
    @Column(name = "datafim")
    @Temporal(TemporalType.DATE)
    private Date datafim;
    @JoinColumn(name = "classe_idclasse", referencedColumnName = "idclasse")
    @ManyToOne(optional = false)
    private Classe classeIdclasse;
    @JoinColumn(name = "usuario_login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario usuarioLogin;

    public Planoaula() {
    }

    public Planoaula(Integer idplanoaula) {
        this.idplanoaula = idplanoaula;
    }

    public Planoaula(Integer idplanoaula, Date datainicio, Date datafim, String status) {
        this.idplanoaula = idplanoaula;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.status = status;
    }

    public Integer getIdplanoaula() {
        return idplanoaula;
    }

    public void setIdplanoaula(Integer idplanoaula) {
        this.idplanoaula = idplanoaula;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }


    public Classe getClasseIdclasse() {
        return classeIdclasse;
    }

    public void setClasseIdclasse(Classe classeIdclasse) {
        this.classeIdclasse = classeIdclasse;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanoaula != null ? idplanoaula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planoaula)) {
            return false;
        }
        Planoaula other = (Planoaula) object;
        if ((this.idplanoaula == null && other.idplanoaula != null) || (this.idplanoaula != null && !this.idplanoaula.equals(other.idplanoaula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Planoaula[ idplanoaula=" + idplanoaula + " ]";
    }

    @XmlTransient
    public List<DiasemanaHasEstrategia> getDiasemanaHasEstrategiaList() {
        return diasemanaHasEstrategiaList;
    }

    public void setDiasemanaHasEstrategiaList(List<DiasemanaHasEstrategia> diasemanaHasEstrategiaList) {
        this.diasemanaHasEstrategiaList = diasemanaHasEstrategiaList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
