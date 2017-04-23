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
@Table(name = "diasemana")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diasemana.findAll", query = "SELECT d FROM Diasemana d")
    , @NamedQuery(name = "Diasemana.findByIddiasemana", query = "SELECT d FROM Diasemana d WHERE d.iddiasemana = :iddiasemana")
    , @NamedQuery(name = "Diasemana.findByDia", query = "SELECT d FROM Diasemana d WHERE d.dia = :dia")
    , @NamedQuery(name = "Diasemana.findByPrincipalObj", query = "SELECT d FROM Diasemana d WHERE d.principalObj = :principalObj")
    , @NamedQuery(name = "Diasemana.findByAcolhida", query = "SELECT d FROM Diasemana d WHERE d.acolhida = :acolhida")
    , @NamedQuery(name = "Diasemana.findByAnexos", query = "SELECT d FROM Diasemana d WHERE d.anexos = :anexos")
    , @NamedQuery(name = "Diasemana.findByLicaodecasa", query = "SELECT d FROM Diasemana d WHERE d.licaodecasa = :licaodecasa")
    , @NamedQuery(name = "Diasemana.findByObservacoes", query = "SELECT d FROM Diasemana d WHERE d.observacoes = :observacoes")
    , @NamedQuery(name = "Diasemana.findByDatadiasemana", query = "SELECT d FROM Diasemana d WHERE d.datadiasemana = :datadiasemana")})
public class Diasemana implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diasemana")
    private List<DiasemanaHasEstrategia> diasemanaHasEstrategiaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddiasemana")
    private Integer iddiasemana;
    @Basic(optional = false)
    @Column(name = "dia")
    private String dia;
    @Basic(optional = false)
    @Column(name = "principal_obj")
    private String principalObj;
    @Basic(optional = false)
    @Column(name = "acolhida")
    private String acolhida;
    @Column(name = "anexos")
    private String anexos;
    @Column(name = "licaodecasa")
    private String licaodecasa;
    @Column(name = "observacoes")
    private String observacoes;
    @Basic(optional = false)
    @Column(name = "datadiasemana")
    @Temporal(TemporalType.DATE)
    private Date datadiasemana;

    public Diasemana() {
    }

    public Diasemana(Integer iddiasemana) {
        this.iddiasemana = iddiasemana;
    }

    public Diasemana(Integer iddiasemana, String dia, String principalObj, String acolhida, Date datadiasemana) {
        this.iddiasemana = iddiasemana;
        this.dia = dia;
        this.principalObj = principalObj;
        this.acolhida = acolhida;
        this.datadiasemana = datadiasemana;
    }

    public Integer getIddiasemana() {
        return iddiasemana;
    }

    public void setIddiasemana(Integer iddiasemana) {
        this.iddiasemana = iddiasemana;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPrincipalObj() {
        return principalObj;
    }

    public void setPrincipalObj(String principalObj) {
        this.principalObj = principalObj;
    }

    public String getAcolhida() {
        return acolhida;
    }

    public void setAcolhida(String acolhida) {
        this.acolhida = acolhida;
    }

    public String getAnexos() {
        return anexos;
    }

    public void setAnexos(String anexos) {
        this.anexos = anexos;
    }

    public String getLicaodecasa() {
        return licaodecasa;
    }

    public void setLicaodecasa(String licaodecasa) {
        this.licaodecasa = licaodecasa;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Date getDatadiasemana() {
        return datadiasemana;
    }

    public void setDatadiasemana(Date datadiasemana) {
        this.datadiasemana = datadiasemana;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddiasemana != null ? iddiasemana.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diasemana)) {
            return false;
        }
        Diasemana other = (Diasemana) object;
        if ((this.iddiasemana == null && other.iddiasemana != null) || (this.iddiasemana != null && !this.iddiasemana.equals(other.iddiasemana))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Diasemana[ iddiasemana=" + iddiasemana + " ]";
    }

    @XmlTransient
    public List<DiasemanaHasEstrategia> getDiasemanaHasEstrategiaList() {
        return diasemanaHasEstrategiaList;
    }

    public void setDiasemanaHasEstrategiaList(List<DiasemanaHasEstrategia> diasemanaHasEstrategiaList) {
        this.diasemanaHasEstrategiaList = diasemanaHasEstrategiaList;
    }
    
}
