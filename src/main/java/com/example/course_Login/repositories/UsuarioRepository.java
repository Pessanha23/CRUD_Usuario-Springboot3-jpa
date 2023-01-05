package com.example.course_Login.repositories;

import com.example.course_Login.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCpf(Long cpf);
}
