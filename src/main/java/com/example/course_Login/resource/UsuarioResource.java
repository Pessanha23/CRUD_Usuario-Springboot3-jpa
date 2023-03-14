package com.example.course_Login.resource;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.service.RedeSocialService;
import com.example.course_Login.service.TelefoneService;
import com.example.course_Login.service.UsuarioService;
import com.example.course_Login.service.exceptions.NaoEncontradoIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    public UsuarioService service;

    @Autowired
    public TelefoneService telefoneService;
    @Autowired
    public RedeSocialService redeSocialService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(@RequestParam(value = "email", required = false) String email,
                                                 @RequestParam(value = "cpf", required = false) String cpf,
                                                 @RequestParam(value = "cpfpar", required = false) Boolean cpfpar) {
        List<Usuario> list;
        if (cpfpar == null)
            cpfpar = false;

        if (email != null) { //http://localhost:8080/usuarios?email=matheus@gmail.com

            list = Collections.singletonList(service.findByEmail(email));
            return ResponseEntity.ok().body(list);

        } else if (cpf != null) { //http://localhost:8080/usuarios?cpf=87275093030
            list = Collections.singletonList(service.findByCpf(cpf));
            return ResponseEntity.ok().body(list);

        } else if (cpfpar) { //http://localhost:8080/usuarios?cpfpar=true
            list = service.findAllCpfPar();
            return ResponseEntity.ok().body(list);

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

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody @Validated Usuario obj) {
        Usuario objeto;
        objeto = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    @PostMapping(path = "/{id}/telefones") //localhost:8080/usuarios/59/telefones
    public ResponseEntity<Telefone> insertTelefone(@PathVariable(value = "id") Long id, @RequestBody Telefone telefone) {
        Telefone objeto;
        objeto = telefoneService.insertTelefone(id, telefone);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    @PostMapping(path = "/{id}/redesocial") //localhost:8080/usuarios/59/redesocial
    public ResponseEntity<List<RedeSocial>> insertRedeSocialMultiples(@PathVariable(value = "id") Long id,
                                                                      @RequestBody List<RedeSocial> bodyRedeSocial) {
        List<RedeSocial> social;
        social = redeSocialService.insertRedeSocialMultiples(bodyRedeSocial, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(social).toUri();
        return ResponseEntity.created(uri).body(social);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
