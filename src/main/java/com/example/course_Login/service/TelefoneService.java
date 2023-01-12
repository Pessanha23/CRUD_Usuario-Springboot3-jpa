package com.example.course_Login.service;

import com.example.course_Login.entities.Telefone;
import com.example.course_Login.repositories.TelefoneRepository;
import com.example.course_Login.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {
    @Autowired
    private TelefoneRepository telefoneRepository;


    public List<Telefone> findAll() {
        return telefoneRepository.findAll();
    }

    public Telefone findById(Long id) {
        Optional<Telefone> obj = telefoneRepository.findById(id);
        return obj.orElseThrow(() -> new NaoEncontradoIdException(id));
    }

    public Telefone findByTelefone(String telefone){
        Optional<Telefone> obj = telefoneRepository.findByTelefone(telefone);
        return obj.orElseThrow(()-> new NaoEncontradoCpfException(telefone));
    }

    public Telefone insertTelefone(Telefone obj) {
        List<Telefone> todosUsuarios = telefoneRepository.findAll();

        return telefoneRepository.save(obj);
    }

    public void delete(Long id) {
        telefoneRepository.deleteById(id);
    }

    public Telefone update(Long id, Telefone obj) {
        List<Telefone> todosUsuarios = telefoneRepository.findAll();
        Optional<Telefone> optionalContatoUsuario = telefoneRepository.findById(id);

        if (optionalContatoUsuario.isEmpty()){
            throw new NaoEncontradoIdException(id);
        }
        if (obj.getTelefone().isBlank()){
            throw new CampoEmailVazioException(obj);
        }
        if (!obj.getTelefone().equals(9)){
            throw new CampoEmailVazioException(obj);
        }
        for (Telefone todosUsuario : todosUsuarios) {
            if (!todosUsuario.getId().equals(id)) {
                if ((obj.getTelefone().equals(todosUsuario.getTelefone()))){
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
