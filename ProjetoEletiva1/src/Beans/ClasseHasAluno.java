/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "classe_has_aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasseHasAluno.findAll", query = "SELECT c FROM ClasseHasAluno c")
    , @NamedQuery(name = "ClasseHasAluno.findByClasseIdclasse", query = "SELECT c FROM ClasseHasAluno c WHERE c.classeHasAlunoPK.classeIdclasse = :classeIdclasse")
    , @NamedQuery(name = "ClasseHasAluno.findByAlunoMatricula", query = "SELECT c FROM ClasseHasAluno c WHERE c.classeHasAlunoPK.alunoMatricula = :alunoMatricula")
    , @NamedQuery(name = "ClasseHasAluno.findByStatus", query = "SELECT c FROM ClasseHasAluno c WHERE c.status = :status")})
public class ClasseHasAluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClasseHasAlunoPK classeHasAlunoPK;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "classe_idclasse", referencedColumnName = "idclasse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classe classe;
    @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Aluno aluno;

    public ClasseHasAluno() {
    }

    public ClasseHasAluno(ClasseHasAlunoPK classeHasAlunoPK) {
        this.classeHasAlunoPK = classeHasAlunoPK;
    }

    public ClasseHasAluno(ClasseHasAlunoPK classeHasAlunoPK, boolean status) {
        this.classeHasAlunoPK = classeHasAlunoPK;
        this.status = status;
    }

    public ClasseHasAluno(String classeIdclasse, String alunoMatricula) {
        this.classeHasAlunoPK = new ClasseHasAlunoPK(classeIdclasse, alunoMatricula);
    }

    public ClasseHasAlunoPK getClasseHasAlunoPK() {
        return classeHasAlunoPK;
    }

    public void setClasseHasAlunoPK(ClasseHasAlunoPK classeHasAlunoPK) {
        this.classeHasAlunoPK = classeHasAlunoPK;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classeHasAlunoPK != null ? classeHasAlunoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasseHasAluno)) {
            return false;
        }
        ClasseHasAluno other = (ClasseHasAluno) object;
        if ((this.classeHasAlunoPK == null && other.classeHasAlunoPK != null) || (this.classeHasAlunoPK != null && !this.classeHasAlunoPK.equals(other.classeHasAlunoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.ClasseHasAluno[ classeHasAlunoPK=" + classeHasAlunoPK + " ]";
    }
    
}
