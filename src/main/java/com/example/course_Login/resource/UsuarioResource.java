package com.example.course_Login.resource;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.service.UsuarioService;
import com.example.course_Login.service.exceptions.NaoEncontradoCpfException;
import com.example.course_Login.service.exceptions.NaoEncontradoEmailException;
import com.example.course_Login.service.exceptions.NaoEncontradoIdException;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    public UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(@RequestParam(value = "email", required = false) String email,
                                                 @RequestParam(value = "cpf", required = false) String cpf,
                                                 @RequestParam(value = "cpfpar", required = false) Boolean cpfpar) {
        List<Usuario> list;
        if (cpfpar == null)
            cpfpar = false;

        if (email != null) {
            try {  //http://localhost:8080/usuarios?email=matheus@gmail.com
                list = Collections.singletonList(service.findByEmail(email));
                return ResponseEntity.ok().body(list);
            } catch (NaoEncontradoEmailException e) {
                throw new NaoEncontradoEmailException(e.getMessage());
            }
        } else if (cpf != null) { //http://localhost:8080/usuarios?cpf=87275093030
            try {
                list = Collections.singletonList(service.findByCpf(cpf));
                return ResponseEntity.ok().body(list);
            } catch (NaoEncontradoCpfException e) {
                throw new NaoEncontradoCpfException(e.getMessage());
            }
        } else if (cpfpar) { //http://localhost:8080/usuarios?cpfpar=true
            try {
                list = service.findAllCpfPar();
                return ResponseEntity.ok().body(list);
            } catch (NaoEncontradoCpfException e) {
                throw new NaoEncontradoCpfException(e.getMessage());
            }
        } else {
            list = service.findAll();
        }

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> buscarId(@PathVariable(value = "id") Long id) {
        try {
            Usuario obj = service.findById(id);
            return ResponseEntity.ok().body(obj);
        } catch (NaoEncontradoIdException e) {
            throw new NaoEncontradoIdException(e.getMessage());
        }
    }

    @GetMapping(value = "/usuarioTelefone")
    public ResponseEntity<List<Usuario>> findAllTelefone() {
        List<Usuario> list = service.findAllTelefone();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody @Validated Usuario obj) {
        Usuario objeto;
        objeto = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody Usuario obj) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
