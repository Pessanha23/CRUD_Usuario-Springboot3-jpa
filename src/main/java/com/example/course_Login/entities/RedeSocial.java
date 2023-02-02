package com.example.course_Login.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "tb_redesocial")
public class RedeSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String midia;
    private String linkRede;
    @Transient
    private transient Long novoId;
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    public RedeSocial() {
    }

    public RedeSocial(Long id, String midia, String linkRede, Long novoId) {
        this.id = id;
        this.midia = midia;
        this.linkRede = linkRede;
        this.novoId = novoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    public String getLinkRede() {
        return linkRede;
    }

    public void setLinkRede(String linkRede) {
        this.linkRede = linkRede;
    }

    public Long getNovoId() {
        return novoId;
    }

    public void setNovoId(Long novoId) {
        this.novoId = novoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
