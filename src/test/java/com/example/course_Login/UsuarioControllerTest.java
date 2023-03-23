package com.example.course_Login;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UsuarioControllerTest {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private static final String PATH = "/usuarios";

    @Test
    public void deve_retornar_uma_lista_de_usuarios() throws Exception {

        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "58814312001");
        Usuario segundoUsuario = new Usuario(null, "gamiguel@gmail.com",
                "shazam", "shazam", "59358995076");

        Usuario usuarioId = repository.save(primeiroUsuario);
        Usuario usuarioId2 = repository.save(segundoUsuario);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(usuarioId.getId()))
                .andExpect(jsonPath("$[1].id").value(usuarioId2.getId()))
                .andExpect(jsonPath("$[0].email").value("diega@gmail.com"))
                .andExpect(jsonPath("$[1].email").value("gamiguel@gmail.com"))
                .andExpect(jsonPath("$[0].cpf").value("58814312001"))
                .andExpect(jsonPath("$[1].cpf").value("59358995076"))
                .andReturn()
                .getResponse();

    }

    @Test
    public void deve_retornar_usario_quando_passado_tiver_email_existente_no_banco() throws Exception {

        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "58814312001");
        Usuario usuarioId = repository.save(primeiroUsuario);
        String email = usuarioId.getEmail();

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios?email=diega@gmail.com", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(usuarioId.getId()))
                .andExpect(jsonPath("$[0].email").value("diega@gmail.com"));

    }

    @Test
    public void deve_retornar_usuario_quando_passado_tiver_cpf_existente_no_banco() throws Exception {

        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "58814312001");
        Usuario usuarioId = repository.save(primeiroUsuario);
        String cpf = usuarioId.getCpf();

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios?cpf=58814312001", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(usuarioId.getId()))
                .andExpect(jsonPath("$[0].cpf").value("58814312001"))
                .andExpect(jsonPath("$[0].email").value("diega@gmail.com"));
    }

    @Test
    public void deve_retornar_lista_usuario_quando_passado_tiver_cpfpar_existente_no_banco() throws Exception {
        Boolean cpfpar = true;
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Usuario usuarioId = repository.save(primeiroUsuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios?cpfpar=true", cpfpar)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(usuarioId.getId()))
                .andExpect(jsonPath("$[0].cpf").value("23549649002"));

    }

    @Test
    public void deve_retornar_usuario_quando_passado_tiver_id_existente_no_banco() throws Exception {

        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Usuario usuarioId = repository.save(primeiroUsuario);
        Long id = usuarioId.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(usuarioId.getId()))
                .andExpect(jsonPath("$.cpf").value("23549649002"));

    }

    @Test
    public void deve_retornar_usuario_criado_quando_preenchido_corretamente() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Telefone primeiroTelefoneUsuario = new Telefone(null, "32994545", null);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(primeiroUsuario);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isCreated());

        List<Usuario> listaBanco = repository.findAll();
        Set<Telefone> telefoneSet1 = listaBanco.get(0).getTelefoneSet();
        String primeiroTelefone = telefoneSet1.stream().findFirst().get().getTelefone();

        Assertions.assertEquals(1,listaBanco.size());
        Assertions.assertEquals("diega@gmail.com", listaBanco.get(0).getEmail());
        Assertions.assertEquals("32994545", primeiroTelefone);



    }

    @Test
    public void deve_retornar_erro_NotAcceptable_quando_body_conter_parametros_similares_ao_usuario_banco() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Telefone primeiroTelefoneUsuario = new Telefone(null, "32994545", null);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);
        Usuario usuarioId = repository.save(primeiroUsuario);

        Usuario segundoUsuario = new Usuario(null, "gamiguel@gmail.com",
                "shazam", "shazam", "23549649002");
        Telefone segundoTelefoneUsuario = new Telefone(null, "32084484", null);
        Set<Telefone> telefoneSet2 = new HashSet<>();
        telefoneSet2.add(segundoTelefoneUsuario);
        segundoUsuario.setTelefoneSet(telefoneSet2);

        List<Usuario> all = repository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(segundoUsuario);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isNotAcceptable());

    }

    @Test
    public void deve_retornar_telefone_criado_quando_preenchido_corretamente() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Telefone primeiroTelefoneUsuario = new Telefone(null, "32994545", null);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);
        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long idTelefoneSalvo = usuarioSave.getTelefoneSet().stream().findFirst().get().getId();
        Long usuarioId = usuarioSave.getId();

        Telefone segundoTelefone = new Telefone(null, "32084484", null);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(segundoTelefone);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH +"/{id}/telefones", usuarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isCreated());

        List<Usuario> listaBanco = repository.findAll();
        Set<Telefone> telefoneSet1 = listaBanco.get(0).getTelefoneSet();
        String telefoneNovo = telefoneSet1
                .stream()
                .filter(x-> !x.getId().equals(idTelefoneSalvo))
                .map(Telefone::getTelefone)
                .findFirst()
                .get();

        Assertions.assertEquals(1,listaBanco.size());
        Assertions.assertEquals("diega@gmail.com", listaBanco.get(0).getEmail());
        Assertions.assertEquals("32084484", telefoneNovo);

    }

    @Test
    public void deve_retornar_BadRequest_quando_telefone_body_existir_base_de_dados_do_usuario() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Telefone primeiroTelefoneUsuario = new Telefone(null, "32994545", null);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);
        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long usuarioSaveId = usuarioSave.getId();

        Telefone segundoTelefone = new Telefone(null, "32994545", null);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(segundoTelefone);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH +"/{id}/telefones", usuarioSaveId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deve_retornar_IdNotFound_quando_id_url_nao_existir_base_de_dados_telefone() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        Telefone primeiroTelefoneUsuario = new Telefone(null, "32994545", null);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);
        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long id = 2L;

        Telefone segundoTelefone = new Telefone(null, "32084484", null);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(segundoTelefone);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH +"/{id}/telefones", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void deve_retornar_redeSocial_criado_quando_preenchido_corretamente() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        RedeSocial redeSocial = new RedeSocial(null, "Enjoei","enjoas",null);
        Set<RedeSocial> redeSocialSet = new HashSet<>();
        redeSocialSet.add(redeSocial);
        primeiroUsuario.setRedeSocialList(redeSocialSet);
        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long usuarioId = usuarioSave.getId();
        Long redeSocialId = usuarioSave.getRedeSocialList().stream().findFirst().get().getId();

        RedeSocial segundaRedeSocial = new RedeSocial(null, "Linkedin", "linkedolas", null);
        List<RedeSocial> redeSocialList = new ArrayList<>();
        redeSocialList.add(segundaRedeSocial);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(redeSocialList);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/{id}/redesocial", usuarioId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isCreated());

        List<Usuario> listaBanco = repository.findAll();
        Set<RedeSocial> redeSocialSet1 = listaBanco.get(0).getRedeSocialList();
        String redeSocialNew = redeSocialSet1
                .stream()
                .filter(x -> !x.getId().equals(redeSocialId))
                .map(RedeSocial::getMidia)
                .findFirst()
                .get();

        Assertions.assertEquals(1,listaBanco.size());
        Assertions.assertEquals("diega@gmail.com", listaBanco.get(0).getEmail());
        Assertions.assertEquals("Linkedin", redeSocialNew);

    }

    @Test
    public void deve_retornar_NotAcceptable_quando_redeSocial_body_obter_midia_duplicada() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        RedeSocial primeiraRedeSocial = new RedeSocial(null, "Enjoei","enjoas",null);
        Set<RedeSocial> redeSocialSet = new HashSet<>();
        redeSocialSet.add(primeiraRedeSocial);
        primeiroUsuario.setRedeSocialList(redeSocialSet);
        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long usuarioId = usuarioSave.getId();

        RedeSocial segundaRedeSocial = new RedeSocial(null, "Linkedin", "linedolas", null);
        RedeSocial terceiraRedeSocial = new RedeSocial(null, "Linkedin", "linkedolas", null);
        List<RedeSocial> redeSocialList = new ArrayList<>();
        redeSocialList.add(segundaRedeSocial);
        redeSocialList.add(terceiraRedeSocial);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(redeSocialList);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH +"/{id}/redesocial", usuarioId )
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void deve_retornar_IdNotFound_quando_id_url_nao_existir_base_de_dados_rede_social() throws Exception {
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");
        RedeSocial primeiraRedeSocial = new RedeSocial(null, "Enjoei","enjoas",null);
        Set<RedeSocial> redeSocialSet = new HashSet<>();
        redeSocialSet.add(primeiraRedeSocial);
        primeiroUsuario.setRedeSocialList(redeSocialSet);
        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long id = 2L;

        RedeSocial segundaRedeSocial = new RedeSocial(null, "Linkedin", "linedolas", null);
        List<RedeSocial> redeSocialList = new ArrayList<>();
        redeSocialList.add(segundaRedeSocial);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(redeSocialList);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH +"/{id}/redesocial", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void deve_retornar_sucesso_ao_atualizar_usuario() throws Exception{
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");

        Usuario usuarioSave = repository.save(primeiroUsuario);

        Usuario segundoUsuario = new Usuario(null, "gamiguel@gmail.com",
                "shazam", "shazam", "59358995076");

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(segundoUsuario);

        Long usuarioId = usuarioSave.getId();

        mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/{id}", usuarioId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deve_retornar_sucesso_ao_deletar_usuario() throws Exception{
        Usuario primeiroUsuario = new Usuario(null, "diega@gmail.com",
                "popsmoke", "popsmoke", "23549649002");

        Usuario usuarioSave = repository.save(primeiroUsuario);
        Long usuarioId = usuarioSave.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{id}", usuarioId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }


}
