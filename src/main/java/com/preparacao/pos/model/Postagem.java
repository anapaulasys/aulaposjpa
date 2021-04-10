/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.model;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @NotNull(message = "O campo 'conteúdo' deve ser informado")
    private String conteudo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postadoEm;
    @NotNull(message = "O campo 'usuario' deve ser informado")
    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getPostadoEm() {
        return postadoEm;
    }

    public void setPostadoEm(Date postadoEm) {
        this.postadoEm = postadoEm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
