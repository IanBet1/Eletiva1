/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "diasemana_has_estrategia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiasemanaHasEstrategia.findAll", query = "SELECT d FROM DiasemanaHasEstrategia d")
    , @NamedQuery(name = "DiasemanaHasEstrategia.findByDiasemanaIddiasemana", query = "SELECT d FROM DiasemanaHasEstrategia d WHERE d.diasemanaHasEstrategiaPK.diasemanaIddiasemana = :diasemanaIddiasemana")
    , @NamedQuery(name = "DiasemanaHasEstrategia.findByEstrategiaIdestrategia", query = "SELECT d FROM DiasemanaHasEstrategia d WHERE d.diasemanaHasEstrategiaPK.estrategiaIdestrategia = :estrategiaIdestrategia")
    , @NamedQuery(name = "DiasemanaHasEstrategia.findByPlanoaulaIdplanoaula", query = "SELECT d FROM DiasemanaHasEstrategia d WHERE d.diasemanaHasEstrategiaPK.planoaulaIdplanoaula = :planoaulaIdplanoaula")})
public class DiasemanaHasEstrategia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiasemanaHasEstrategiaPK diasemanaHasEstrategiaPK;
    @JoinColumn(name = "diasemana_iddiasemana", referencedColumnName = "iddiasemana", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Diasemana diasemana;
    @JoinColumn(name = "estrategia_idestrategia", referencedColumnName = "idestrategia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estrategia estrategia;
    @JoinColumn(name = "planoaula_idplanoaula", referencedColumnName = "idplanoaula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planoaula planoaula;

    public DiasemanaHasEstrategia() {
    }

    public DiasemanaHasEstrategia(DiasemanaHasEstrategiaPK diasemanaHasEstrategiaPK) {
        this.diasemanaHasEstrategiaPK = diasemanaHasEstrategiaPK;
    }

    public DiasemanaHasEstrategia(int diasemanaIddiasemana, int estrategiaIdestrategia, int planoaulaIdplanoaula) {
        this.diasemanaHasEstrategiaPK = new DiasemanaHasEstrategiaPK(diasemanaIddiasemana, estrategiaIdestrategia, planoaulaIdplanoaula);
    }

    public DiasemanaHasEstrategiaPK getDiasemanaHasEstrategiaPK() {
        return diasemanaHasEstrategiaPK;
    }

    public void setDiasemanaHasEstrategiaPK(DiasemanaHasEstrategiaPK diasemanaHasEstrategiaPK) {
        this.diasemanaHasEstrategiaPK = diasemanaHasEstrategiaPK;
    }

    public Diasemana getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(Diasemana diasemana) {
        this.diasemana = diasemana;
    }

    public Estrategia getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public Planoaula getPlanoaula() {
        return planoaula;
    }

    public void setPlanoaula(Planoaula planoaula) {
        this.planoaula = planoaula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diasemanaHasEstrategiaPK != null ? diasemanaHasEstrategiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiasemanaHasEstrategia)) {
            return false;
        }
        DiasemanaHasEstrategia other = (DiasemanaHasEstrategia) object;
        if ((this.diasemanaHasEstrategiaPK == null && other.diasemanaHasEstrategiaPK != null) || (this.diasemanaHasEstrategiaPK != null && !this.diasemanaHasEstrategiaPK.equals(other.diasemanaHasEstrategiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.DiasemanaHasEstrategia[ diasemanaHasEstrategiaPK=" + diasemanaHasEstrategiaPK + " ]";
    }
    
}
