package com.example.course_Login.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;


import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;

    //o @Transient faz a função de ignorar para o banco de dados, não criar a coluna novoId, apenas para usar informação;
    // utilizar o transient min. para nao se criar na tabela a coluna que faz apenas associcao dos id(novoid);
    @Transient
    private transient Long novoId;

    // Corrigir as anotações para melhorar a associação de varios telefonesmas para um usuário e verificar;
    // na classe Usuario as @, para verificar se esta associado corretamente com essa classe;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    public Telefone() {
    }

    public Telefone(Long id, String telefone, Long novoId) {
        super();
        this.id = id;
        this.telefone = telefone;
        this.novoId = novoId;
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
