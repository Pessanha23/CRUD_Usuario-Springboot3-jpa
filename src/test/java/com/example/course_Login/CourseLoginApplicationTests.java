package com.example.course_Login;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.RedeSocialRepository;
import com.example.course_Login.repositories.TelefoneRepository;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.UsuarioService;
import com.example.course_Login.service.exceptions.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

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
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deve_retornar_sucesso_todos_usuarios() {
        //		given();
        Usuario primeiroUsuario = new Usuario(1L, "joao.pessanga@gmail.com",
                "maquim123", "maquim123", "42362444899");

        //when
        Mockito.when(repository.findAll(Mockito.any(Sort.class))).thenReturn(List.of(primeiroUsuario));
        List<Usuario> atual = usuarioService.findAll();

        // then
        Assert.assertNotNull(atual);
        Assert.assertEquals(1, atual.size());
        Assert.assertEquals(Usuario.class, atual.get(0).getClass());
        Assert.assertEquals(primeiroUsuario.getId(), atual.get(0).getId());
        Assert.assertEquals(primeiroUsuario.getEmail(), atual.get(0).getEmail());
        Assert.assertEquals(primeiroUsuario.getSenha(), atual.get(0).getSenha());
        Assert.assertEquals(primeiroUsuario.getConfirmacaoSenha(), atual.get(0).getConfirmacaoSenha());
        Assert.assertEquals(primeiroUsuario.getCpf(), atual.get(0).getCpf());
    }

    @Test
    public void deve_retonar_lista_vazia_quando_nao_houver_usuarios() {

        Mockito.when(repository.findAll(Mockito.any(Sort.class))).thenReturn(List.of());

        List<Usuario> atual = usuarioService.findAll();

        Assert.assertNotNull(atual);
        Assert.assertEquals(0, atual.size());
    }

    @Test
    public void deve_retornar_sucesso_apenas_um_usuario() {
        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diego@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(optionalUsuario);

        Usuario atual = usuarioService.findById(1L);
        // voce sempre compara os atributos, ou uma lista vc compra tambem, nunca a classe, muito raro;
        Assert.assertNotNull(atual);
        Assert.assertEquals(optionalUsuario.get().getEmail(), atual.getEmail());
    }

    // retornar para realizar o test do NaoEncontradoIdExcpetion
    @Test
    public void deve_retornar_sucesso_quando_lista_conter_cpfpar() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmail.com",
                "popsmoke", "popsmoke", "44113302022");
        Usuario segundoUsuario = new Usuario(2L, "john@gmail.com",
                "john123", "john123", "41905644004");
        Usuario terceiroUsuario = new Usuario(3L, "math@gmail.com",
                "math123", "math123", "58814312001");
        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuario, segundoUsuario, terceiroUsuario));
        List<Usuario> atual = usuarioService.findAllCpfPar();

        Assert.assertNotNull(atual);
        Assert.assertEquals(2, atual.size());
        Assert.assertEquals(primeiroUsuario.getCpf(), atual.get(0).getCpf());
        Assert.assertEquals(segundoUsuario.getCpf(), atual.get(1).getCpf());
    }

    @Test
    public void deve_retornar_vazio_quando_lista_nao_conter_cpfpar() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmail.com",
                "popsmoke", "popsmoke", "58814312001");
        Usuario segundoUsuario = new Usuario(2L, "john@gmail.com",
                "john123", "john123", "10765268027");
        Usuario terceiroUsuario = new Usuario(3L, "math@gmail.com",
                "math123", "math123", "61664293043");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuario, segundoUsuario, terceiroUsuario));
        List<Usuario> atual = usuarioService.findAllCpfPar();

        Assert.assertNotNull(atual);
        Assert.assertEquals(0, atual.size());
    }

    @Test
    public void deve_retornar_sucesso_quando_Email_localizado() {
        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diego@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Mockito.when(repository.findByEmail(Mockito.eq("diega@gmail.com"))).thenReturn(optionalUsuario);

        Usuario atual = usuarioService.findByEmail("diega@gmail.com");

        Assert.assertNotNull(atual);
        Assert.assertEquals(optionalUsuario.get().getEmail(), atual.getEmail());

    }

    @Test
	public void deve_lancar_exception_quando_NaoEncontradoEmailException(){
		Optional<Usuario> optionalUsuario = Optional.empty();
		Mockito.when(repository.findByEmail(Mockito.eq("xispin@gmail.com"))).thenReturn(optionalUsuario);

		try{
			usuarioService.findByEmail("xispin@gmail.com");
            Assert.fail();
		} catch (Exception e) {
            Assert.assertEquals("EMAIL NÃO ENCONTRADO: xispin@gmail.com", e.getMessage());
		}
	}
    @Test
    public void deve_retornar_sucesso_ao_localizar_cpf() {
        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diego@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Mockito.when(repository.findByCpf(Mockito.eq("07314877025"))).thenReturn(optionalUsuario);

        Usuario atual = usuarioService.findByCpf("07314877025");

        Assert.assertNotNull(atual);
        Assert.assertEquals(optionalUsuario.get().getCpf(), atual.getCpf());
    }

    @Test
    public void deve_lancar_exception_quando_NaoEncontradoCpfException() {
        Optional<Usuario> optionalUsuario = Optional.empty();

        Mockito.when(repository.findByCpf(Mockito.eq("222"))).thenReturn(optionalUsuario);
        try{
            usuarioService.findByCpf("222");
            Assert.fail();
        } catch (NaoEncontradoCpfException e) {
            Assert.assertEquals("CPF NÃO ENCONTRADO: 222", e.getMessage());
        }

    }

    @Test
    public void deve_retornar_usuario_quando_esta_corretamente_preenchido() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmail.com",
                "popsmoke", "popsmoke", "58814312001");

        Telefone primeiroTelefoneUsuario = new Telefone(1L, "32994545", 1L);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneUsuario);
        primeiroUsuario.setTelefoneSet(telefoneSet);
        RedeSocial primeiroRedeSocialUsuario = new RedeSocial(1L, "Linkedin", "www.linkrede.com.br", 1L);
        Set<RedeSocial> redeSocialSet = new HashSet<>();
        redeSocialSet.add(primeiroRedeSocialUsuario);
        primeiroUsuario.setRedeSocialList(redeSocialSet);

        Mockito.when(repository.save(Mockito.any())).thenReturn((primeiroUsuario));

        Usuario atual = usuarioService.insert(primeiroUsuario);

        Assert.assertNotNull(atual);

    }

    @Test
    public void deve_retornar_error_quando_email_estiver_vazio(){
        Usuario primeiroUsuario = new Usuario(1L, "",
                "popsmoke", "popsmoke", "58814312001");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);

        try{
            usuarioService.insert(primeiroUsuario);
            Assert.fail();
        } catch (CampoEmailVazioException e){
            Assert.assertEquals("CAMPO EMAIL VAZIO",e.getMessage());
        }

    }

    @Test
    public void deve_retornar_error_quando_senha_estiver_vazio(){
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "", "popsmoke", "58814312001");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);

        try{
            usuarioService.insert(primeiroUsuario);
            Assert.fail();
        } catch (CampoSenhaVazioException e){
            Assert.assertEquals("CAMPO SENHA VAZIO",e.getMessage());
        }

    }
    @Test
    public void deve_retornar_error_quando_senha_for_diferente_confirmacaoSenha(){
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "popsmoki", "popsmoke", "58814312001");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);

        try{
            usuarioService.insert(primeiroUsuario);
            Assert.fail();
        }catch(InvalidoSenhaException e){
            Assert.assertEquals("SENHA INVÁLIDA", e.getMessage());
        }
    }

    @Test
    public void deve_retornar_error_quando_cpf_vazio(){
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "popsmoke", "popsmoke", "");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);

        try{
            usuarioService.insert(primeiroUsuario);
            Assert.fail();
        }catch(CampoCpfVazioException e){
            Assert.assertEquals("CAMPO CPF VAZIO", e.getMessage());
        }
    }

    @Test
    public void deve_retornar_error_quando_senha_for_invalido(){
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "popsmoke", "popsmoke", "222222222");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);

        try{
            usuarioService.insert(primeiroUsuario);
            Assert.fail();
        }catch(InvalidoCpfException e){
            Assert.assertEquals("CPF INVÁLIDO", e.getMessage());
        }
    }

    @Test
    public void deve_retornar_error_quando_email_for_igual_ao_banco(){
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "gamiguela@gmaiul.com",
                "tenis", "tenis", "45437694067");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuarioBanco));

        try{
            usuarioService.insert(segundoUsuarioBody);
            Assert.fail();
        }catch(ExistenteEmailException e){
            Assert.assertEquals("EMAIL EXISTENTE!!", e.getMessage());
        }
    }

    @Test
    public void deve_retornar_error_quando_senha_for_igual_ao_banco(){
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "diega@gmaiul.com",
                "popsmoke", "popsmoke", "45437694067");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuarioBanco));

        try{
            usuarioService.insert(segundoUsuarioBody);
            Assert.fail();
        }catch(ExistenteSenhaException e){
            Assert.assertEquals("SENHA EXISTENTE!! MODIFIQUE SUA SENHA", e.getMessage());
        }
    }

    @Test
    public void deve_retornar_error_quando_cpf_for_igual_ao_banco(){
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "diega@gmaiul.com",
                "tenis", "tenis", "14756175007");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuarioBanco));

        try{
            usuarioService.insert(segundoUsuarioBody);
            Assert.fail();
        }catch(ExistenteCpfException e){
            Assert.assertEquals("CPF REGISTRADO!! INFORME OUTRO CPF", e.getMessage());
        }
    }
}
