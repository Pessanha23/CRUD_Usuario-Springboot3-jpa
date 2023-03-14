package com.example.course_Login;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.RedeSocialService;
import com.example.course_Login.service.TelefoneService;
import com.example.course_Login.service.UsuarioService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioResourceTest {
    @MockBean
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

        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(primeiroUsuario);

        Mockito.when(repository.findAll()).thenReturn(usuarioList);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String expected = "[]";

        Assert.assertEquals(expected, response.getContentAsString());

    }

}
