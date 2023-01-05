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
        return obj.orElseThrow(() -> new NaoEncontradoIdException(id));
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new NaoEncontradoEmailException(email));
    }

    public Usuario findByCpf(Long cpf){
        Optional<Usuario> obj = repository.findByCpf(cpf);
        return obj.orElseThrow(()-> new NaoEncontradoCpfException(cpf));
    }

    public Usuario insert(Usuario obj) {
        List<Usuario> todosUsuarios = repository.findAll();

        if (obj.getEmail().isBlank()){
            throw new CampoEmailVazioException(obj);
        }
        if (obj.getSenha().isBlank()){
            throw new CampoSenhaVazioException(obj);
        }

        if (!obj.getSenha().equals(obj.getConfirmacaoSenha())) {
            throw new DiferenteSenhaException(obj);
        }

        if (obj.getCpf() == null ){
            throw new CampoCpfVazioException(obj);
        }

        if (obj.getCpf().toString().length() != 9){
            throw new DiferenteCpfException(obj);
        }

        for (Usuario usuario : todosUsuarios) {
            if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new ExistenteEmailException(obj);
            }
            if (obj.getSenha().equalsIgnoreCase(usuario.getSenha())) {
                throw new ExistenteSenhaException(obj);
            }
            if (obj.getCpf().equals(usuario.getCpf())){
                throw new ExistenteCpfException(obj);
            }
        }

        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Usuario update(Long id, Usuario obj) {
        List<Usuario> todosUsuarios = repository.findAll();
        Optional<Usuario> optionalUsuario = repository.findById(id);

        if (optionalUsuario.isEmpty()){
            throw new NaoEncontradoIdException(id);
        }

        if (obj.getEmail().isBlank()){
            throw new CampoEmailVazioException(obj);
        }
        if (obj.getSenha().isBlank()){
            throw new CampoSenhaVazioException(obj);
        }

        if (!obj.getSenha().equals(obj.getConfirmacaoSenha())) {
            throw new DiferenteSenhaException(obj);
        }

        if (obj.getCpf() == null){
            throw new CampoCpfVazioException(obj);
        }

        if (obj.getCpf().toString().length() != 9){
            throw new DiferenteCpfException(obj);
        }

        for (Usuario usuario : todosUsuarios) {

            if (!usuario.getId().equals(id)){

                if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                    throw new ExistenteEmailException(obj);
                }
                if (obj.getSenha().equalsIgnoreCase(usuario.getSenha())) {
                    throw new ExistenteSenhaException(obj);
                }
                if (obj.getCpf().equals(usuario.getCpf())){
                    throw new ExistenteCpfException(obj);
                }
            }

        }
        updateData(optionalUsuario.get(), obj);
        return repository.save(optionalUsuario.get());
    }

    public void updateData(Usuario entity, Usuario obj) {
        entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
        entity.setConfirmacaoSenha(obj.getConfirmacaoSenha());
    }


}
