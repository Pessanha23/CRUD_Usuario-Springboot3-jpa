package com.example.course_Login.resource;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.service.RedeSocialService;
import com.example.course_Login.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/redesocial")
public class RedeSocialResource {

    @Autowired
    public RedeSocialService redeSocialService;

    @GetMapping
    public ResponseEntity<List<RedeSocial>> findAll() {
        List<RedeSocial> list = redeSocialService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/findredesocial")
    public ResponseEntity<RedeSocial> buscarRedeSocial(@RequestParam String midia){
        RedeSocial obj = redeSocialService.findByTelefone(telefone);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Telefone> insertRedeSocial(@RequestBody RedeSocial obj) {
        RedeSocial objeto;
        objeto= telefoneService.insertTelefone(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    @PostMapping(value = "/multiplosRedeSocial")
    public ResponseEntity <List<Telefone>> insertRedeSocial(@RequestBody List<RedeSocial> obj) {
        List<Telefone> objeto;
        objeto= telefoneService.insertTelefoneMultiples(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody Telefone obj){
        telefoneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Telefone> updateTelefone(@PathVariable Long id, @RequestBody Telefone obj){
        obj = telefoneService.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }
}
