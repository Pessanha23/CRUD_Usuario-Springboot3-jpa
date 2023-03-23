package com.example.course_Login.service;

import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.TelefoneRepository;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TelefoneService {
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Telefone> findAll() {
        return telefoneRepository.findAll();
    }

    public Telefone findById(Long id) {
        Optional<Telefone> obj = telefoneRepository.findById(id);
        return obj.orElseThrow(() -> new NaoEncontradoIdException(id));
    }

    public List<Telefone> findByTelefone(String telefone) {
        boolean encontrado = false;
        List<Telefone> byTelefone = telefoneRepository.findByTelefone(telefone);
        if (byTelefone.size() == 0){
            throw new NaoEncontradoTelefoneException(telefone);
        }
        return byTelefone;
    }

    public Telefone insertTelefone(Long id, Telefone bodyTelefone) {

        Optional<Usuario> buscarId = usuarioRepository.findById(id);
        Usuario usuario = buscarId.orElseThrow(() -> new NaoEncontradoIdException(bodyTelefone.getNovoId()));

        Set<Telefone> listaBanco = usuario.getTelefoneSet();
        Telefone recebaTelefone;

        for (Telefone telefone1 : listaBanco) {
            if (telefone1.getTelefone().equals(bodyTelefone.getTelefone()) || bodyTelefone.getTelefone().length() != 8) {
                throw new InvalidoTelefoneException(bodyTelefone);
            }
        }

            recebaTelefone = bodyTelefone;
            listaBanco.add(bodyTelefone);
            bodyTelefone.setUsuario(usuario);

        return telefoneRepository.save(recebaTelefone);
    }

    public void delete(Long id) {
        telefoneRepository.deleteById(id);
    }

    public Telefone update(Long id, Telefone obj) {
        List<Telefone> todosUsuarios = telefoneRepository.findAll();
        Optional<Telefone> optionalContatoUsuario = telefoneRepository.findById(id);

        if (optionalContatoUsuario.isEmpty()) {
            throw new NaoEncontradoIdException(id);
        }
        if (obj.getTelefone().isBlank()) {
            throw new CampoEmailVazioException(obj);
        }
        if (!obj.getTelefone().equals(8)) {
            throw new CampoEmailVazioException(obj);
        }
        for (Telefone todosUsuario : todosUsuarios) {
            if (!todosUsuario.getId().equals(id)) {
                if ((obj.getTelefone().equals(todosUsuario.getTelefone()))) {
                    throw new ExistenteEmailException(obj);
                }
            }
        }

        updateData(optionalContatoUsuario.get(), obj);
        return telefoneRepository.save(optionalContatoUsuario.get());
    }

    public void updateData(Telefone entity, Telefone obj) {
        entity.setTelefone(obj.getTelefone());
    }
}
