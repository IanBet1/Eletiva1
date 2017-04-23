/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ianbe
 */
@Embeddable
public class DiasemanaHasEstrategiaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "diasemana_iddiasemana")
    private int diasemanaIddiasemana;
    @Basic(optional = false)
    @Column(name = "estrategia_idestrategia")
    private int estrategiaIdestrategia;
    @Basic(optional = false)
    @Column(name = "planoaula_idplanoaula")
    private int planoaulaIdplanoaula;

    public DiasemanaHasEstrategiaPK() {
    }

    public DiasemanaHasEstrategiaPK(int diasemanaIddiasemana, int estrategiaIdestrategia, int planoaulaIdplanoaula) {
        this.diasemanaIddiasemana = diasemanaIddiasemana;
        this.estrategiaIdestrategia = estrategiaIdestrategia;
        this.planoaulaIdplanoaula = planoaulaIdplanoaula;
    }

    public int getDiasemanaIddiasemana() {
        return diasemanaIddiasemana;
    }

    public void setDiasemanaIddiasemana(int diasemanaIddiasemana) {
        this.diasemanaIddiasemana = diasemanaIddiasemana;
    }

    public int getEstrategiaIdestrategia() {
        return estrategiaIdestrategia;
    }

    public void setEstrategiaIdestrategia(int estrategiaIdestrategia) {
        this.estrategiaIdestrategia = estrategiaIdestrategia;
    }

    public int getPlanoaulaIdplanoaula() {
        return planoaulaIdplanoaula;
    }

    public void setPlanoaulaIdplanoaula(int planoaulaIdplanoaula) {
        this.planoaulaIdplanoaula = planoaulaIdplanoaula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) diasemanaIddiasemana;
        hash += (int) estrategiaIdestrategia;
        hash += (int) planoaulaIdplanoaula;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiasemanaHasEstrategiaPK)) {
            return false;
        }
        DiasemanaHasEstrategiaPK other = (DiasemanaHasEstrategiaPK) object;
        if (this.diasemanaIddiasemana != other.diasemanaIddiasemana) {
            return false;
        }
        if (this.estrategiaIdestrategia != other.estrategiaIdestrategia) {
            return false;
        }
        if (this.planoaulaIdplanoaula != other.planoaulaIdplanoaula) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.DiasemanaHasEstrategiaPK[ diasemanaIddiasemana=" + diasemanaIddiasemana + ", estrategiaIdestrategia=" + estrategiaIdestrategia + ", planoaulaIdplanoaula=" + planoaulaIdplanoaula + " ]";
    }
    
}
