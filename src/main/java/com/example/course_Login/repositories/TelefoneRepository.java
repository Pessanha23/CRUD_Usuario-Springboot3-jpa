package com.example.course_Login.repositories;

import com.example.course_Login.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    Optional<Telefone> findByTelefone(String telefone);
}
