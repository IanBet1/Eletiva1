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
public class ClasseHasAlunoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "classe_idclasse")
    private String classeIdclasse;
    @Basic(optional = false)
    @Column(name = "aluno_matricula")
    private String alunoMatricula;

    public ClasseHasAlunoPK() {
    }

    public ClasseHasAlunoPK(String classeIdclasse, String alunoMatricula) {
        this.classeIdclasse = classeIdclasse;
        this.alunoMatricula = alunoMatricula;
    }

    public String getClasseIdclasse() {
        return classeIdclasse;
    }

    public void setClasseIdclasse(String classeIdclasse) {
        this.classeIdclasse = classeIdclasse;
    }

    public String getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(String alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classeIdclasse != null ? classeIdclasse.hashCode() : 0);
        hash += (alunoMatricula != null ? alunoMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasseHasAlunoPK)) {
            return false;
        }
        ClasseHasAlunoPK other = (ClasseHasAlunoPK) object;
        if ((this.classeIdclasse == null && other.classeIdclasse != null) || (this.classeIdclasse != null && !this.classeIdclasse.equals(other.classeIdclasse))) {
            return false;
        }
        if ((this.alunoMatricula == null && other.alunoMatricula != null) || (this.alunoMatricula != null && !this.alunoMatricula.equals(other.alunoMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.ClasseHasAlunoPK[ classeIdclasse=" + classeIdclasse + ", alunoMatricula=" + alunoMatricula + " ]";
    }
    
}
