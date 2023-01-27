package com.example.course_Login.repositories;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedeSocialRepository extends JpaRepository<RedeSocial, Long> {
    Optional<RedeSocial> findyByRedeSocial(String linkRede);
}
