package com.example.course_Login.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private String cpf;

    // Corrigir as anotações para melhorar a associação de varios telefonesmas para um usuário e verificar;
    // na classe Usuario as @, para verificar se esta associado corretamente com essa classe;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) //Cascade estudar mais sobre o uso disso
    private Set<Telefone> telefoneSet = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id, String email, String senha, String confirmacaoSenha, String cpf) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
        this.cpf = cpf;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Telefone> getTelefoneSet() {
        return telefoneSet;
    }

    public void setTelefoneSet(Set<Telefone> telefoneSet) {
        this.telefoneSet = telefoneSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
