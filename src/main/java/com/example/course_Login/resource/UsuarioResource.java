package com.example.course_Login.resource;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    public UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> buscarId(@PathVariable(value = "id") Long id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    /*
    http://localhost:8080/usuarios/3
    http://localhost:8080/usuarios/negao.pessanga@gmail.com
    find?email=negao.pessanga@gmail.com
     */
    @GetMapping(value = "/find")
    public ResponseEntity<Usuario> buscarEmail(@RequestParam(required = false) String email) {
        Usuario obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario obj) {
        Usuario objeto;
        objeto= service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody Usuario obj){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj){
        obj = service.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }
}
