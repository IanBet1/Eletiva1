/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author ianbe
 */
@Entity
@Table(name = "aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")
    , @NamedQuery(name = "Aluno.findByMatricula", query = "SELECT a FROM Aluno a WHERE a.matricula = :matricula")
    , @NamedQuery(name = "Aluno.findByNomealuno", query = "SELECT a FROM Aluno a WHERE a.nomealuno = :nomealuno")
    , @NamedQuery(name = "Aluno.findByNascimento", query = "SELECT a FROM Aluno a WHERE a.nascimento = :nascimento")
    , @NamedQuery(name = "Aluno.findByMae", query = "SELECT a FROM Aluno a WHERE a.mae = :mae")
    , @NamedQuery(name = "Aluno.findByPai", query = "SELECT a FROM Aluno a WHERE a.pai = :pai")
    , @NamedQuery(name = "Aluno.findByEndereco", query = "SELECT a FROM Aluno a WHERE a.endereco = :endereco")
    , @NamedQuery(name = "Aluno.findByNumero", query = "SELECT a FROM Aluno a WHERE a.numero = :numero")
    , @NamedQuery(name = "Aluno.findByBairro", query = "SELECT a FROM Aluno a WHERE a.bairro = :bairro")
    , @NamedQuery(name = "Aluno.findByCidade", query = "SELECT a FROM Aluno a WHERE a.cidade = :cidade")
    , @NamedQuery(name = "Aluno.findByUf", query = "SELECT a FROM Aluno a WHERE a.uf = :uf")
    , @NamedQuery(name = "Aluno.findByComplemento", query = "SELECT a FROM Aluno a WHERE a.complemento = :complemento")
    , @NamedQuery(name = "Aluno.findByTelefone1", query = "SELECT a FROM Aluno a WHERE a.telefone1 = :telefone1")
    , @NamedQuery(name = "Aluno.findByTelefone2", query = "SELECT a FROM Aluno a WHERE a.telefone2 = :telefone2")
    , @NamedQuery(name = "Aluno.findByTelefone3", query = "SELECT a FROM Aluno a WHERE a.telefone3 = :telefone3")
    , @NamedQuery(name = "Aluno.findByStatus", query = "SELECT a FROM Aluno a WHERE a.status = :status")})
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "nomealuno")
    private String nomealuno;
    @Basic(optional = false)
    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Basic(optional = false)
    @Column(name = "mae")
    private String mae;
    @Basic(optional = false)
    @Column(name = "pai")
    private String pai;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "uf")
    private String uf;
    @Basic(optional = false)
    @Column(name = "complemento")
    private String complemento;
    @Basic(optional = false)
    @Column(name = "telefone1")
    private String telefone1;
    @Column(name = "telefone2")
    private String telefone2;
    @Column(name = "telefone3")
    private String telefone3;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "classe_idclasse", referencedColumnName = "idclasse")
    @ManyToOne(optional = false)
    private Classe classeIdclasse;

    public Aluno() {
    }

    public Aluno(String matricula) {
        this.matricula = matricula;
    }

    public Aluno(String matricula, String nomealuno, Date nascimento, String mae, String pai, String endereco, String numero, String bairro, String cidade, String uf, String complemento, String telefone1, boolean status) {
        this.matricula = matricula;
        this.nomealuno = nomealuno;
        this.nascimento = nascimento;
        this.mae = mae;
        this.pai = pai;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.telefone1 = telefone1;
        this.status = status;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomealuno() {
        return nomealuno;
    }

    public void setNomealuno(String nomealuno) {
        this.nomealuno = nomealuno;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Classe getClasseIdclasse() {
        return classeIdclasse;
    }

    public void setClasseIdclasse(Classe classeIdclasse) {
        this.classeIdclasse = classeIdclasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Aluno[ matricula=" + matricula + " ]";
    }
    
}
