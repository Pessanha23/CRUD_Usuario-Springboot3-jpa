package com.example.course_Login.resource;

import com.example.course_Login.entities.Telefone;
import com.example.course_Login.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneResource {

    @Autowired
    public TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<Telefone>> findAll() {
        List<Telefone> list = telefoneService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Telefone> buscarId(@PathVariable(value = "id") Long id) {
        Telefone obj = telefoneService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    /*
    http://localhost:8080/telefones/3
    find?telefone=negao.pessanga@gmail.com
     */
    @GetMapping(value = "/findTelefone")
    public ResponseEntity<Telefone> buscarTelefone(@RequestParam String telefone){
        Telefone obj = telefoneService.findByTelefone(telefone);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Telefone> insertTelefone(@RequestBody Telefone obj) {
        Telefone objeto;
        objeto= telefoneService.insertTelefone(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
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
