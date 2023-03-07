package com.example.course_Login;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.RedeSocialRepository;
import com.example.course_Login.repositories.TelefoneRepository;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.resource.UsuarioResource;
import com.example.course_Login.service.UsuarioService;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import jakarta.websocket.SendHandler;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.yaml.snakeyaml.events.Event;


import java.text.ParseException;
import java.util.*;


class CourseLoginApplicationTests {

	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepository repository;
	@Mock
	private TelefoneRepository telefoneRepository;
	@Mock
	private RedeSocialRepository redeSocialRepository;

	@BeforeEach
	public void setup(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Deve retornar todos os Usuários da list")
	public void retornarSucesso_todosUsuarios() {
	//		given();
		Usuario primeiroUsuario = new  Usuario(1L, "joao.pessanga@gmail.com", "maquim123", "maquim123", "42362444899");

		//when
		Mockito.when(repository.findAll(Mockito.any(Sort.class))).thenReturn(List.of(primeiroUsuario));
		List<Usuario> actual = usuarioService.findAll();

		// then
		Assert.assertNotNull(actual);
		Assert.assertEquals(1, actual.size());
		Assert.assertEquals(Usuario.class, actual.get(0).getClass());
		Assert.assertEquals(primeiroUsuario.getId(), actual.get(0).getId());
		Assert.assertEquals(primeiroUsuario.getEmail(), actual.get(0).getEmail());
		Assert.assertEquals(primeiroUsuario.getSenha(), actual.get(0).getSenha());
		Assert.assertEquals(primeiroUsuario.getConfirmacaoSenha(), actual.get(0).getConfirmacaoSenha());
		Assert.assertEquals(primeiroUsuario.getCpf(), actual.get(0).getCpf());
	}
}
