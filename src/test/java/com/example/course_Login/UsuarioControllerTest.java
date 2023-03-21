package com.example.course_Login;

import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    @MockBean
    private UsuarioService usuarioService;

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
        Usuario usuarioId = repository.save(primeiroUsuario);
        Telefone primeiroTelefoneUsuario = new Telefone(usuarioId.getId(), "32994545", null);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());


    }
}
