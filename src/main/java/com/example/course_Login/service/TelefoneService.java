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

    public Telefone findByTelefone(String telefone) {
        Optional<Telefone> obj = telefoneRepository.findByTelefone(telefone);
        return obj.orElseThrow(() -> new NaoEncontradoCpfException(telefone));
    }

    public Telefone insertTelefone(Telefone telefone) {

        Optional<Usuario> buscarId = usuarioRepository.findById(telefone.getNovoId());
        Usuario usuario = buscarId.orElseThrow(() -> new NaoEncontradoIdException(telefone.getNovoId()));

        if (telefone.getTelefone().length() != 9) {
            throw new InvalidoTelefoneException(telefone);
        }
        
        Set<Telefone> telefoneList = usuario.getTelefoneSet();

        telefoneList.add(telefone);
        telefone.setUsuario(usuario);

        List<Telefone> listVivo = new ArrayList<>();
        for (Telefone telefoneLista : telefoneList) {
            if (listVivo.isEmpty()) {
                listVivo.add(telefoneLista);
            } else {
                for (Telefone vivo : listVivo) {
                    if (telefoneLista.getTelefone().equals(vivo.getTelefone())) {
                        throw new ExistenteTelefoneException(telefone);
                    }
                }
                listVivo.add(telefoneLista);
            }

        }

        return telefoneRepository.save(telefone);
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
        if (!obj.getTelefone().equals(9)) {
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
