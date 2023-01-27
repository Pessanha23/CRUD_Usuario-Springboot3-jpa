package com.example.course_Login.service;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.RedeSocialRepository;
import com.example.course_Login.repositories.TelefoneRepository;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.exceptions.CampoEmailVazioException;
import com.example.course_Login.service.exceptions.ExistenteEmailException;
import com.example.course_Login.service.exceptions.NaoEncontradoCpfException;
import com.example.course_Login.service.exceptions.NaoEncontradoIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RedeSocialService {
    @Autowired
    private RedeSocialRepository redeSocialRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<RedeSocial> findAll() {
        return redeSocialRepository.findAll();
    }

    public RedeSocial findById(Long id) {
        Optional<RedeSocial> obj = redeSocialRepository.findById(id);
        return obj.orElseThrow(() -> new NaoEncontradoIdException(id));
    }

    public RedeSocial findByRedeSocial(String midia) {
        Optional<RedeSocial> obj = redeSocialRepository.findyByRedeSocial(midia);
        return obj.orElseThrow(() -> new NaoEncontradoCpfException(midia));
    }

    public Telefone insertTelefone(Telefone bodyTelefone) {

        Long listaTelefoneBody = bodyTelefone.getNovoId();
        Optional<Usuario> buscarId = usuarioRepository.findById(listaTelefoneBody);
        Usuario usuario = buscarId.orElseThrow(() -> new NaoEncontradoIdException(bodyTelefone.getNovoId()));

        Set<Telefone> listaBanco = usuario.getTelefoneSet();
        Telefone recebaTelefone = new Telefone();
        Telefone retornoVazio = new Telefone();

        boolean encontrado = false;

        for (Telefone telefone1 : listaBanco) {
            if (telefone1.getTelefone().equals(bodyTelefone.getTelefone()) || bodyTelefone.getTelefone().length() != 9) {
                encontrado = true;
                return retornoVazio;
            }
        }
        recebaTelefone = bodyTelefone;
        listaBanco.add(bodyTelefone);
        bodyTelefone.setUsuario(usuario);

        return telefoneRepository.save(recebaTelefone);
    }

    public List<Telefone> insertTelefoneMultiples(List<Telefone> bodyTelefone) {

        Telefone primeiroTelefone = bodyTelefone.get(0);
        Long novoId = primeiroTelefone.getNovoId();

        for (Telefone telefone : bodyTelefone) {
            if (telefone.getNovoId().equals(novoId)) {
                novoId = telefone.getNovoId();
            } else {
                throw new NaoEncontradoIdException(telefone.getNovoId());
            }
        }

        Optional<Usuario> usuarioId = usuarioRepository.findById(novoId);
        Long finalNovoId = novoId;
        Usuario usuarioSet = usuarioId.orElseThrow(() -> new NaoEncontradoIdException(finalNovoId));
        usuarioSet = usuarioId.get();

        List<Telefone> listaString = new ArrayList<>();
        Usuario telefoneNaLista = usuarioId.get();
        Set<Telefone> listaTelBanco = telefoneNaLista.getTelefoneSet();
        boolean encontrado = false;

        for (Telefone body : bodyTelefone) {
            for (Telefone bancoTel : listaTelBanco) {
                if (bancoTel.getTelefone().equals(body.getTelefone()) || body.getTelefone().length() != 9) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                listaString.add(body);
                listaTelBanco.add(body);
                body.setUsuario(usuarioSet);
            }
            encontrado = false;
        }

        /*Metodo, nao eficaz mas para casos de transformar um tipo de String, orientação a objeto isso!!!!!
        List<Telefone> lastDanceList = new ArrayList<>();
        for (String s : listaString) {
            Telefone teste = new Telefone();
            teste.setTelefone(s);
            teste.setNovoId(bodyTelefone.get(0).getNovoId());
            teste.setUsuario(novoUsuario);
            lastDanceList.add(teste);
        }*/

        return telefoneRepository.saveAll(listaString);
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
