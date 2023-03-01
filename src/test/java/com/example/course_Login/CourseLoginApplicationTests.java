package com.example.course_Login;

import com.example.course_Login.entities.Usuario;
import com.example.course_Login.service.UsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseLoginApplicationTests {

	@Autowired
	UsuarioService usuarioService;

	@Test
	void contextLoads() {
	}

	@Test
	public void teste_1(){
		UsuarioService usuarioService = new UsuarioService();
		List<Usuario> todosUsuarios = usuarioService.findAll();

		String expectativa =

		Assert.assertEquals(, todosUsuarios);


	}
}
