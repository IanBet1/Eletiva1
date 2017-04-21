/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianbe
 */
@Entity
@Table(name = "estrategia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estrategia.findAll", query = "SELECT e FROM Estrategia e")
    , @NamedQuery(name = "Estrategia.findByIdestrategia", query = "SELECT e FROM Estrategia e WHERE e.idestrategia = :idestrategia")
    , @NamedQuery(name = "Estrategia.findByEstrategia", query = "SELECT e FROM Estrategia e WHERE e.estrategia = :estrategia")})
public class Estrategia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idestrategia")
    private Integer idestrategia;
    @Basic(optional = false)
    @Column(name = "estrategia")
    private String estrategia;
    @JoinColumn(name = "areaconhecimento_idconhecimento", referencedColumnName = "idconhecimento")
    @ManyToOne(optional = false)
    private Areaconhecimento areaconhecimentoIdconhecimento;

    public Estrategia() {
    }

    public Estrategia(Integer idestrategia) {
        this.idestrategia = idestrategia;
    }

    public Estrategia(Integer idestrategia, String estrategia) {
        this.idestrategia = idestrategia;
        this.estrategia = estrategia;
    }

    public Integer getIdestrategia() {
        return idestrategia;
    }

    public void setIdestrategia(Integer idestrategia) {
        this.idestrategia = idestrategia;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public Areaconhecimento getAreaconhecimentoIdconhecimento() {
        return areaconhecimentoIdconhecimento;
    }

    public void setAreaconhecimentoIdconhecimento(Areaconhecimento areaconhecimentoIdconhecimento) {
        this.areaconhecimentoIdconhecimento = areaconhecimentoIdconhecimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestrategia != null ? idestrategia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estrategia)) {
            return false;
        }
        Estrategia other = (Estrategia) object;
        if ((this.idestrategia == null && other.idestrategia != null) || (this.idestrategia != null && !this.idestrategia.equals(other.idestrategia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Estrategia[ idestrategia=" + idestrategia + " ]";
    }
    
}
