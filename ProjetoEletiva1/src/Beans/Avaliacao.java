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
 * @author Terminal
 */
@Entity
@Table(name = "avaliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a"),
    @NamedQuery(name = "Avaliacao.findByIdavaliacao", query = "SELECT a FROM Avaliacao a WHERE a.idavaliacao = :idavaliacao"),
    @NamedQuery(name = "Avaliacao.findByTipo", query = "SELECT a FROM Avaliacao a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Avaliacao.findByArquivo", query = "SELECT a FROM Avaliacao a WHERE a.arquivo = :arquivo"),
    @NamedQuery(name = "Avaliacao.findByStatus", query = "SELECT a FROM Avaliacao a WHERE a.status = :status")})
public class Avaliacao implements Serializable {

    @Column(name = "observacao")
    private String observacao;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idavaliacao")
    private Integer idavaliacao;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "arquivo")
    private String arquivo;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "areaconhecimento_idareaconhecimento", referencedColumnName = "idconhecimento")
    @ManyToOne
    private Areaconhecimento areaconhecimentoIdareaconhecimento;
    @JoinColumn(name = "usuario_login", referencedColumnName = "login")
    @ManyToOne
    private Usuario usuarioLogin;

    public Avaliacao() {
    }

    public Avaliacao(Integer idavaliacao) {
        this.idavaliacao = idavaliacao;
    }

    public Integer getIdavaliacao() {
        return idavaliacao;
    }

    public void setIdavaliacao(Integer idavaliacao) {
        this.idavaliacao = idavaliacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Areaconhecimento getAreaconhecimentoIdareaconhecimento() {
        return areaconhecimentoIdareaconhecimento;
    }

    public void setAreaconhecimentoIdareaconhecimento(Areaconhecimento areaconhecimentoIdareaconhecimento) {
        this.areaconhecimentoIdareaconhecimento = areaconhecimentoIdareaconhecimento;
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
        hash += (idavaliacao != null ? idavaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.idavaliacao == null && other.idavaliacao != null) || (this.idavaliacao != null && !this.idavaliacao.equals(other.idavaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Avaliacao[ idavaliacao=" + idavaliacao + " ]";
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}
