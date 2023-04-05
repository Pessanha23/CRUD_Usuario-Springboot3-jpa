package com.example.course_Login.resource;

import com.example.course_Login.entities.Telefone;
import com.example.course_Login.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneResource {

    @Autowired
    public TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<Telefone>> findAll(@RequestParam(value = "telefone", required = false) String telefone) {
        List<Telefone> list;
        if (telefone != null) { //http://localhost:8080/telefones?telefone=666666666
                list = telefoneService.findByTelefone(telefone);
                return ResponseEntity.ok().body(list);

        } else {
            list = telefoneService.findAll();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Telefone> buscarId(@PathVariable(value = "id") Long id) {
        Telefone obj = telefoneService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody Telefone obj) {
        telefoneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Telefone> updateTelefone(@PathVariable Long id, @RequestBody Telefone obj) {
        obj = telefoneService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
