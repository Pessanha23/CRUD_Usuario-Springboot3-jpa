package com.example.course_Login.resource;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.service.RedeSocialService;
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

    @GetMapping //localhost:8080/redesocial?midia=Linkedin
    public ResponseEntity<List<RedeSocial>> findAll(@RequestParam(value = "midia", required = false) String midia) {
        List<RedeSocial> list;
        if (midia != null){
            list = redeSocialService.findBySocial(midia);
            return ResponseEntity.ok().body(list);
        } else {
            list = redeSocialService.findAll();
        }

        return ResponseEntity.ok().body(list);
    }

//    @PostMapping(value = "/multiplosRedeSocial")
//    public ResponseEntity <List<RedeSocial>> insertRedeSocialMultiples(@RequestBody List<RedeSocial> socialList,
//            @RequestParam Long usuarioId ) {
//        List<RedeSocial> redeSocialsController = redeSocialService.insertRedeSocialMultiples(socialList, usuarioId);
//        return ResponseEntity.ok().body(redeSocialsController);
//    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <List<RedeSocial>> updateRedeSocial(@PathVariable Long id, @RequestBody List<RedeSocial> redeSocial){
        List<RedeSocial> redeSocialsController = redeSocialService.update(id,redeSocial);
        return ResponseEntity.ok().body(redeSocialsController);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        redeSocialService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
