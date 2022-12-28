package com.example.course_Login.service;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.exceptions.*;
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
        return obj.orElseThrow(() -> new IdNaoEncontradoException(id));
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new EmailNaoEncontradoException(email));
    }

    public Usuario insert(Usuario obj) {
        List<Usuario> recebaEmail = repository.findAll();

        if (!obj.getSenha().equals(obj.getConfirmacaoSenha())) {
            throw new SenhaDiferenteException(obj);
        }

        for (Usuario usuario : recebaEmail) {
            if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new EmailIgualException(obj);
            }
            if (obj.getSenha().equalsIgnoreCase(usuario.getSenha())) {
                throw new SenhaExistenteException(obj);
            }
        }
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Usuario update(Long id, Usuario obj) {
        List<Usuario> recebaEmail = repository.findAll();
        Usuario entity = repository.getReferenceById(id);

        if (!id.equals(repository.getReferenceById(id).getId())){
            throw new IdNaoEncontradoException(id);
        }

        if (!obj.getSenha().equals(obj.getConfirmacaoSenha())) {
            throw new SenhaDiferenteException(obj);
        }

        for (Usuario usuario : recebaEmail) {
            if (obj.getEmail().equalsIgnoreCase(usuario.getEmail()) && !obj.getEmail().equals(repository.getReferenceById(id).getEmail())) {
                throw new EmailIgualException(obj);
            }
            if (obj.getSenha().equalsIgnoreCase(usuario.getSenha()) && !obj.getSenha().equals(repository.getReferenceById(id).getSenha())) {
                throw new SenhaExistenteException(obj);
            }
        }
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(Usuario entity, Usuario obj) {
        entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
        entity.setConfirmacaoSenha(obj.getConfirmacaoSenha());
    }


}
