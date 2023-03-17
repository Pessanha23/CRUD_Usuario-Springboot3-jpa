package com.example.course_Login;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.RedeSocialService;
import com.example.course_Login.service.TelefoneService;
import com.example.course_Login.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioResourceTest {

    @Autowired
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;

    @MockBean
    private TelefoneService telefoneService;

    @MockBean
    private RedeSocialService redeSocialService;

    @Autowired
    private MockMvc mockMvc;

    private static final String PATH = "/usuarios";

    @Test
    public void deve_retornar_uma_lista_de_usuarios() throws Exception {
        Usuario primeiroUsuario = new Usuario(1L, "joao.pessanga@gmail.com",
                "maquim123", "maquim123", "42362444899");

        Usuario segundoUsuario = new Usuario(2L, "diega@gmail.com",
                "tl", "tl", "43917097087");

        Mockito.when(repository.findAll(Mockito.any(Sort.class))).thenReturn(List.of(primeiroUsuario, segundoUsuario));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("joao.pessanga@gmail.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].email").value("diega@gmail.com"))
                .andReturn()
                .getResponse();

    }

    @Test
    public void deve_retornar_usario_quando_passado_tiver_email_existente_no_banco() throws Exception {
        String email = "diega@gmail.com";
        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diega@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Usuario segundoUsuario = new Usuario(2L, "diega@gmail.com",
                "tl", "tl", "43917097087");

        Mockito.when(repository.findByEmail(Mockito.eq("diega@gmail.com"))).thenReturn(optionalUsuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios?email=diega@gmail.com", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("diega@gmail.com"));

        System.out.println("");
    }

    @Test
    public void deve_retornar_usuario_quando_passado_tiver_cpf_existente_no_banco() throws Exception {
        String cpf = "07314877025";
        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diega@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Mockito.when(repository.findByCpf(Mockito.eq("07314877025"))).thenReturn(optionalUsuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios?cpf=07314877025", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cpf").value("07314877025"));
    }

    @Test
    public void deve_retornar_lista_usuario_quando_passado_tiver_cpfpar_existente_no_banco() throws Exception {
        Boolean cpfpar = true;
        Usuario primeiroUsuario = new Usuario(1L, "joao.pessanga@gmail.com",
                "xupita", "xupita", "07314877025");

        Usuario segundoUsuario = new Usuario(2L, "diega@gmail.com",
                "tl", "tl", "44113302022");

        Usuario terceiroUsuario = new Usuario(3L, "gamiguela@gmail.com",
                "cs", "cs", "26130457014");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuario, segundoUsuario, terceiroUsuario));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios?cpfpar=true", cpfpar)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[0].cpf").value("44113302022"))
                .andExpect(jsonPath("$[1].cpf").value("26130457014"));

    }

    @Test
    public void deve_retornar_usuario_quando_passado_tiver_id_existente_no_banco() throws Exception {
        Long id = 1L;
        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diega@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(optionalUsuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
//                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cpf").value("07314877025"));
    }
}


