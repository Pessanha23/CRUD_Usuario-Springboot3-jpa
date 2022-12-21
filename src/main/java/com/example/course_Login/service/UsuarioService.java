package com.example.course_Login.service;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.exceptions.EmailIgualException;
import com.example.course_Login.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Usuario insert(Usuario obj) {
        List<Usuario> recebaEmail = repository.findAll();
        for (Usuario usuario : recebaEmail) {
            if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new EmailIgualException(obj);
            }
        }
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Usuario update(Long id, Usuario obj) {
        List<Usuario> recebaEmail = repository.findAll();
        for (Usuario usuario : recebaEmail) {
            if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new EmailIgualException(obj);
            }
        }
        Usuario entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);

    }

    public void updateData(Usuario entity, Usuario obj) {
        entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
    }


}
