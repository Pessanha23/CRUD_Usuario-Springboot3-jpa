package com.example.course_Login.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(name = "tb_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;

    // Corrigir as anotações para melhorar a associação de varios telefonesmas para um usuário e verificar;
    // na classe Usuario as @, para verificar se esta associado corretamente com essa classe;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    public Telefone() {
    }

    public Telefone(Long id, String telefone) {
        super();
        this.id = id;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone1 = (Telefone) o;
        return Objects.equals(id, telefone1.id) && Objects.equals(telefone, telefone1.telefone) && Objects.equals(usuario, telefone1.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telefone, usuario);
    }
}
