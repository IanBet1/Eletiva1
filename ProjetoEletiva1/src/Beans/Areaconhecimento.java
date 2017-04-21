/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianbe
 */
@Entity
@Table(name = "areaconhecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areaconhecimento.findAll", query = "SELECT a FROM Areaconhecimento a")
    , @NamedQuery(name = "Areaconhecimento.findByIdconhecimento", query = "SELECT a FROM Areaconhecimento a WHERE a.idconhecimento = :idconhecimento")
    , @NamedQuery(name = "Areaconhecimento.findByAreaconhecimento", query = "SELECT a FROM Areaconhecimento a WHERE a.areaconhecimento = :areaconhecimento")})
public class Areaconhecimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idconhecimento")
    private Integer idconhecimento;
    @Basic(optional = false)
    @Column(name = "areaconhecimento")
    private String areaconhecimento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaconhecimentoIdconhecimento")
    private List<Estrategia> estrategiaList;

    public Areaconhecimento() {
    }

    public Areaconhecimento(Integer idconhecimento) {
        this.idconhecimento = idconhecimento;
    }

    public Areaconhecimento(Integer idconhecimento, String areaconhecimento) {
        this.idconhecimento = idconhecimento;
        this.areaconhecimento = areaconhecimento;
    }

    public Integer getIdconhecimento() {
        return idconhecimento;
    }

    public void setIdconhecimento(Integer idconhecimento) {
        this.idconhecimento = idconhecimento;
    }

    public String getAreaconhecimento() {
        return areaconhecimento;
    }

    public void setAreaconhecimento(String areaconhecimento) {
        this.areaconhecimento = areaconhecimento;
    }

    @XmlTransient
    public List<Estrategia> getEstrategiaList() {
        return estrategiaList;
    }

    public void setEstrategiaList(List<Estrategia> estrategiaList) {
        this.estrategiaList = estrategiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconhecimento != null ? idconhecimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areaconhecimento)) {
            return false;
        }
        Areaconhecimento other = (Areaconhecimento) object;
        if ((this.idconhecimento == null && other.idconhecimento != null) || (this.idconhecimento != null && !this.idconhecimento.equals(other.idconhecimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Areaconhecimento[ idconhecimento=" + idconhecimento + " ]";
    }
    
}
