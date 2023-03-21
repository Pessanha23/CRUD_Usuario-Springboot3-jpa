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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


class UsuarioServiceTest {

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

    @Test
    public void deve_lancar_exception_quando_NaoEncontradoIdException() {
        Optional<Usuario> optionalUsuario = Optional.empty();

        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(optionalUsuario);
        Assertions.assertThrows(NaoEncontradoIdException.class,() -> {
            usuarioService.findById(1L);
        });
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
    public void deve_retornar_error_quando_NaoEncontradoCpfException() {

        Mockito.when(repository.findAll()).thenReturn(List.of());
        Assertions.assertThrows(NaoEncontradoCpfException.class,() -> {
            usuarioService.findAllCpfPar();
        });
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
    public void deve_lancar_exception_quando_NaoEncontradoEmailException() {
        Optional<Usuario> optionalUsuario = Optional.empty();
        Mockito.when(repository.findByEmail(Mockito.eq("xispin@gmail.com"))).thenReturn(optionalUsuario);
        Assertions.assertThrows(NaoEncontradoEmailException.class,() -> {
            usuarioService.findByEmail("xispin@gmail.com");
        });
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
        Assertions.assertThrows(NaoEncontradoCpfException.class,() -> {
            usuarioService.findByCpf("222");
        });
    }

    @Test
    public void deve_retornar_sucesso_usuario_quando_corretamente_preenchido() {
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

        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);

        Usuario atual = usuarioService.insert(primeiroUsuario);

        Assert.assertNotNull(atual);

    }

    @Test
    public void deve_retornar_error_quando_email_estiver_vazio() {
        Usuario primeiroUsuario = new Usuario(1L, "",
                "popsmoke", "popsmoke", "58814312001");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);
        Assertions.assertThrows(CampoEmailVazioException.class,() -> {
            usuarioService.insert(primeiroUsuario);
        });
    }

    @Test
    public void deve_retornar_error_quando_senha_estiver_vazio() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "", "popsmoke", "58814312001");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);
        Assertions.assertThrows(CampoSenhaVazioException.class,() -> {
            usuarioService.insert(primeiroUsuario);
        });
    }

    @Test
    public void deve_retornar_error_quando_senha_for_diferente_confirmacaoSenha() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "popsmoki", "popsmoke", "58814312001");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);
        Assertions.assertThrows(InvalidoSenhaException.class,() -> {
            usuarioService.insert(primeiroUsuario);
        });
    }

    @Test
    public void deve_retornar_error_quando_cpf_vazio() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "popsmoke", "popsmoke", "");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);
        Assertions.assertThrows(CampoCpfVazioException.class,() -> {
            usuarioService.insert(primeiroUsuario);
        });
    }

    @Test
    public void deve_retornar_error_quando_senha_for_invalido() {
        Usuario primeiroUsuario = new Usuario(1L, "diega@gmaiul.com",
                "popsmoke", "popsmoke", "222222222");
        Mockito.when(repository.save(Mockito.any())).thenReturn(primeiroUsuario);
        Assertions.assertThrows(InvalidoCpfException.class,() -> {
            usuarioService.insert(primeiroUsuario);
        });
    }

    @Test
    public void deve_retornar_error_quando_email_for_igual_ao_banco() {
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "gamiguela@gmaiul.com",
                "tenis", "tenis", "45437694067");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuarioBanco));
        Assertions.assertThrows(ExistenteEmailException.class,() -> {
            usuarioService.insert(segundoUsuarioBody);
        });
    }

    @Test
    public void deve_retornar_error_quando_senha_for_igual_ao_banco() {
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "diega@gmaiul.com",
                "popsmoke", "popsmoke", "45437694067");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuarioBanco));
        Assertions.assertThrows(ExistenteSenhaException.class,() -> {
            usuarioService.insert(segundoUsuarioBody);
        });
    }

    @Test
    public void deve_retornar_error_quando_cpf_for_igual_ao_banco() {
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "diega@gmaiul.com",
                "tenis", "tenis", "14756175007");

        Mockito.when(repository.findAll()).thenReturn(List.of(primeiroUsuarioBanco));
        Assertions.assertThrows(ExistenteCpfException.class,() -> {
            usuarioService.insert(segundoUsuarioBody);
        });
    }

    @Test
    public void deve_retornar_error_quando_telefone_numero_for_invalido() {
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Usuario segundoUsuarioBody = new Usuario(1L, "diega@gmaiul.com",
                "tenis", "tenis", "14756175007");
        Telefone primeiroTelefoneBanco = new Telefone(1L, "3299454", 1L);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneBanco);
        primeiroUsuarioBanco.setTelefoneSet(telefoneSet);

        Mockito.when(repository.save(Mockito.any())).thenReturn(List.of(primeiroUsuarioBanco));
        Assertions.assertThrows(InvalidoTelefoneException.class,() -> {
            usuarioService.insert(primeiroUsuarioBanco);
        });
    }

    @Test
    public void deve_retornar_error_quando_numero_telefone_estiver_cadastrada_no_mesmo_usuario() {
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        Telefone primeiroTelefoneBanco = new Telefone(1L, "32994545", 1L);
        Telefone firstTelefoneBanco = new Telefone(1L, "32994545", 1L);
        Set<Telefone> telefoneSet = new HashSet<>();
        telefoneSet.add(primeiroTelefoneBanco);
        telefoneSet.add(firstTelefoneBanco);
        primeiroUsuarioBanco.setTelefoneSet(telefoneSet);

        Mockito.when(repository.findAll()).thenReturn(List.of());
        Assertions.assertThrows(ExistenteTelefoneException.class,() -> {
            usuarioService.insert(primeiroUsuarioBanco);
        });
    }

    @Test
    public void deve_retornar_error_quando_numero_midia_social_estiver_cadastrada_no_mesmo_usuario() {
        Usuario primeiroUsuarioBanco = new Usuario(1L, "gamiguela@gmaiul.com",
                "popsmoke", "popsmoke", "14756175007");
        RedeSocial firstRedeSocial = new RedeSocial(1L, "Linkedin", "gamiguel@linkedin", 1L);
        RedeSocial secondRedeSocial = new RedeSocial(1L, "Linkedin", "gamiguel@linkedin", 1L);
        Set<RedeSocial> redeSocialSet = new HashSet<>();
        redeSocialSet.add(firstRedeSocial);
        redeSocialSet.add(secondRedeSocial);
        primeiroUsuarioBanco.setRedeSocialList(redeSocialSet);

        Mockito.when(repository.findAll()).thenReturn(List.of());
        Assertions.assertThrows(ExistenteRedeSocialException.class,() -> {
            usuarioService.insert(primeiroUsuarioBanco);
        });
    }

    @Test
    public void deve_retornar_error_quando_NaoEncontradoIdException() {
        Optional<Usuario> optionalUsuario = Optional.empty();
        Usuario usuarioBody = new Usuario(1L, "gamiguela@gmail.com",
                "tenis", "tenis", "30082246017");
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(optionalUsuario);
        Assertions.assertThrows(NaoEncontradoIdException.class,() -> {
            usuarioService.update(1l, usuarioBody);
        });

    }

    @Test
    public void deve_retornar_error_quando_campo_email_vazio() {
        Usuario usuarioBody = new Usuario(1L, "",
                "tenis", "tenis", "30082246017");
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(usuarioBody));
        Assertions.assertThrows(CampoEmailVazioException.class,() -> {
            usuarioService.update(1l, usuarioBody);
        });

    }

    @Test
    public void deve_retornar_error_quando_campo_venha_vazioException() {
        Usuario usuarioBody = new Usuario(1L, "gamiguel@gmail.com",
                "", "tenis", "30082246017");
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(usuarioBody));

        Assertions.assertThrows(CampoSenhaVazioException.class,() -> {
            usuarioService.update(1l, usuarioBody);
        });

    }

    @Test
    public void deve_retornar_error_quando_InvalidoSenhaException() {
        Usuario usuarioBody = new Usuario(1L, "gamiguel@gmail.com",
                "teniss", "tenis", "30082246017");
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(usuarioBody));
        Assertions.assertThrows(InvalidoSenhaException.class, () -> {
            usuarioService.update(1l, usuarioBody);
        });


    }
    @Test
    public void deve_retornar_error_quando_CampoCpfVazioException() {
        Usuario usuarioBody = new Usuario(1L, "gamiguel@gmail.com",
                "tenis", "tenis", "");
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(usuarioBody));
        Assertions.assertThrows(CampoCpfVazioException.class,() -> {
            usuarioService.update(1l, usuarioBody);
        });
    }

    @Test
    public void deve_retornar_error_quando_InvalidoCpfException() {
        Usuario usuarioBody = new Usuario(1L, "gamiguel@gmail.com",
                "tenis", "tenis", "22222222222");
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(usuarioBody));
        Assertions.assertThrows(InvalidoCpfException.class, () -> {
            usuarioService.update(1l, usuarioBody);
        });
    }

    @Test
    public void deve_retornar_error_quando_email_existente_no_banco_for_igual_ao_body() {
        Usuario usuarioBody= new Usuario(1L, "diega@gmail.com",
                "tenis", "tenis", "21338974025");
        Usuario usuarioBanco = new Usuario(1L, "gamiguel@gmail.com",
                "bota", "bota", "72670386032");
        Usuario segundoBanco = new Usuario(2L, "diega@gmail.com",
                "louça", "louça", "45470718026");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(usuarioBanco));
        Mockito.when(repository.findAll()).thenReturn(List.of(usuarioBanco,segundoBanco));

        Assertions.assertThrows(ExistenteEmailException.class, () -> {
            usuarioService.update(1l, usuarioBody);
        });
    }

    @Test
    public void deve_retornar_error_quando_senha_existente_no_banco_for_igual_ao_body() {
        Usuario usuarioBody= new Usuario(1L, "gamiguel@gmail.com",
                "tenis", "tenis", "21338974025");
        Usuario usuarioBanco = new Usuario(1L, "gamiguel@gmail.com",
                "bota", "bota", "72670386032");
        Usuario segundoBanco = new Usuario(2L, "diega@gmail.com",
                "tenis", "tenis", "45470718026");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(usuarioBanco));
        Mockito.when(repository.findAll()).thenReturn(List.of(usuarioBanco,segundoBanco));

        Assertions.assertThrows(ExistenteSenhaException.class, () -> {
            usuarioService.update(1l, usuarioBody);
        });

    }

    @Test
    public void deve_retornar_error_quando_cpf_existente_no_banco_for_igual_ao_body() {
        Usuario usuarioBody= new Usuario(1L, "gamiguel@gmail.com",
                "tenis", "tenis", "45470718026");
        Usuario usuarioBanco = new Usuario(1L, "gamiguel@gmail.com",
                "bota", "bota", "72670386032");
        Usuario segundoBanco = new Usuario(2L, "diega@gmail.com",
                "lil", "lil", "45470718026");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(usuarioBanco));
        Mockito.when(repository.findAll()).thenReturn(List.of(usuarioBanco,segundoBanco));

        Assertions.assertThrows(ExistenteCpfException.class, () -> {
            usuarioService.update(1l, usuarioBody);
        });

    }

    @Test
    public void deve_retornar_sucesso_ao_deletar_usuario() {

        Optional<Usuario> optionalUsuario;
        optionalUsuario = Optional.of(new Usuario(1L, "diego@gmail.com",
                "popsmoke", "popsmoke", "07314877025"));

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUsuario);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyLong());

        usuarioService.delete(1L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test()
    public void deve_retornar_error_ao_deletar_um_Objeto_com_id_nao_encontrado() {
        //Quando é void, vc tem que utilizar o Throw, então se atentar no que o metodo retorna, como nesse caso é void
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(Mockito.anyLong());
        Assertions.assertThrows(NaoEncontradoIdException.class, () -> {
            usuarioService.delete(1L);
        });
    }

    @Test()
    public void deve_retornar_sucesso_quando_for_passado_cpf_valido(){
       Boolean validacaoCpf = usuarioService.validacaoCpf("27133755092");
        Assert.assertTrue(validacaoCpf);
    }
    @Test()
    public void deve_retornar_sucesso_quando_for_passado_cpf_invalido(){
        Boolean validacaoCpf = usuarioService.validacaoCpf("27133755099");
        Assert.assertFalse(validacaoCpf);
    }
}
