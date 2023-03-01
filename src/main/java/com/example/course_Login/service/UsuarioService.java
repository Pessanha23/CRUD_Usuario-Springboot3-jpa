package com.example.course_Login.service;

import com.example.course_Login.entities.RedeSocial;
import com.example.course_Login.entities.Telefone;
import com.example.course_Login.entities.Usuario;
import com.example.course_Login.repositories.RedeSocialRepository;
import com.example.course_Login.repositories.TelefoneRepository;
import com.example.course_Login.repositories.UsuarioRepository;
import com.example.course_Login.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private RedeSocialRepository redeSocialRepository;

    public List<Usuario> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Usuario> findAllCpfPar() {
        List<Usuario> usuarioList = repository.findAll();
        List<Usuario> usuarioCopiaLista = new ArrayList<>();

        if (usuarioList.isEmpty()) {
            throw new NaoEncontradoCpfException(usuarioList);
        }

        for (Usuario usuario : usuarioList) {
            String cpfCount = usuario.getCpf().substring(8);
            int cpfCount2 = Integer.parseInt(cpfCount);
            if (cpfCount2 % 2 == 0) {
                usuarioCopiaLista.add(usuario);
            }
        }

        return usuarioCopiaLista;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NaoEncontradoIdException(id));
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new NaoEncontradoEmailException(email));
    }

    public Usuario findByCpf(String cpf) {
        Optional<Usuario> obj = repository.findByCpf(cpf);
        return obj.orElseThrow(() -> new NaoEncontradoCpfException(cpf));
    }

    public Usuario insert(Usuario obj) {
        List<Usuario> todosUsuarios = repository.findAll();

        if (obj.getEmail().isBlank()) {
            throw new CampoEmailVazioException(obj);
        }
        if (obj.getSenha().isBlank()) {
            throw new CampoSenhaVazioException(obj);
        }
        if (!obj.getSenha().equals(obj.getConfirmacaoSenha())) {
            throw new InvalidoSenhaException(obj);
        }
        if (obj.getCpf().isBlank()) {
            throw new CampoCpfVazioException(obj);
        }
        if (!validacaoCpf(obj.getCpf())) {
            throw new InvalidoCpfException(obj);
        }

        for (Usuario usuario : todosUsuarios) {
            if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new ExistenteEmailException(obj);
            }
            if (obj.getSenha().equalsIgnoreCase(usuario.getSenha())) {
                throw new ExistenteSenhaException(obj);
            }
            if (obj.getCpf().equals(usuario.getCpf())) {
                throw new ExistenteCpfException(obj);
            }

        }

        Set<Telefone> listaTelefonica = obj.getTelefoneSet();
        List<Telefone> listaVivo = new ArrayList<>();

        for (Telefone telefone : listaTelefonica) {
            telefone.setUsuario(obj);
            if (telefone.getTelefone().length() != 9) {
                throw new InvalidoTelefoneException(obj);
            }
            if (listaVivo.isEmpty()) {
                listaVivo.add(telefone);
            } else {
                for (Telefone vivo : listaVivo) {
                    if (telefone.getTelefone().equals(vivo.getTelefone())) {
                        throw new ExistenteTelefoneException(obj);
                    }
                }
                listaVivo.add(telefone);
            }
        }

        Set<RedeSocial> redeSocialList = obj.getRedeSocialList();
        List<RedeSocial> redeSocialsNewList = new ArrayList<>();

        for (RedeSocial social : redeSocialList) {
            social.setUsuario(obj);
            if (redeSocialsNewList.isEmpty()){
                redeSocialsNewList.add(social);
            }else {
                for (RedeSocial redeSocial : redeSocialsNewList) {
                    if (social.getMidia().equals(redeSocial.getMidia())){
                        throw new ExistenteRedeSocialException(obj); // TODO: 30/01/2023 Adicionar Excpetion
                    }
                }
                redeSocialsNewList.add(social);
            }

        }


        /*-Metodo igual ao de cima, com lambda
        listaTelefonica.forEach(telefone -> {telefone.setUsuario(obj);});

        -Metodo utilizado para testar, com Orientacao Objeto e testando
        Telefone t2 = new Telefone(null, "997575755");
        Telefone t1 = new Telefone(null, "999999999");
        //em casos de preenchimento de duas tabelas, é obrigatório existir o set
        //ajustar lógica de como ajustar direto do request e associando aqui
        t1.setUsuario(obj);
        t2.setUsuario(obj);
        Set<Telefone> teste3 = Set.of(t2, t1);
        obj.setTelefoneSet(teste3);
        */
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Usuario update(Long id, Usuario obj) {
        List<Usuario> todosUsuarios = repository.findAll();
        Optional<Usuario> optionalUsuario = repository.findById(id);

        if (optionalUsuario.isEmpty()) {
            throw new NaoEncontradoIdException(id);
        }
        if (obj.getEmail().isBlank()) {
            throw new CampoEmailVazioException(obj);
        }
        if (obj.getSenha().isBlank()) {
            throw new CampoSenhaVazioException(obj);
        }
        if (!obj.getSenha().equals(obj.getConfirmacaoSenha())) {
            throw new InvalidoSenhaException(obj);
        }
        if (obj.getCpf().isBlank()) {
            throw new CampoCpfVazioException(obj);
        }
        if (!validacaoCpf(obj.getCpf())) {
            throw new InvalidoCpfException(obj);
        }

        for (Usuario usuario : todosUsuarios) {
            if (!usuario.getId().equals(id)) {
                if (obj.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                    throw new ExistenteEmailException(obj);
                }
                if (obj.getSenha().equalsIgnoreCase(usuario.getSenha())) {
                    throw new ExistenteSenhaException(obj);
                }
                if (obj.getCpf().equals(usuario.getCpf())) {
                    throw new ExistenteCpfException(obj);
                }
            }
        }
        updateData(optionalUsuario.get(), obj);
        return repository.save(optionalUsuario.get());
    }

    public void updateData(Usuario entity, Usuario obj) {
        entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
        entity.setConfirmacaoSenha(obj.getConfirmacaoSenha());
        entity.setCpf(obj.getCpf());
    }

    public static boolean validacaoCpf(String cpf) {

        String cpfTeste = String.valueOf(cpf);
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.toString().length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (cpf.toString().charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.toString().charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.toString().charAt(9)) && (dig11 == cpf.toString().charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

}
