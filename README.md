# INSTRUÇÕES DE INCREMENTAÇÃO E ATIVIDADES DO CRUD


**Tecnologias utilizadas**

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="100px" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="100px" /><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg" width="100px" /><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="100px" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original-wordmark.svg" width="100px" /> 

**TASK BY: PARDAL**
DATA: 26/12/2022
* 1°Desafio: Ajustar campo senha na atualizacao p/ que possa repetir a mesma senha anterior do usuario, mesmo nao podendo colocar a senha similar de novos usuarios; (CHECK)
* 2°Desafio: Buscar pelo endereço do localhost o campo email; (CHECK)
* 3°Desafio: Quando atualizar com a mesma senha, email, ou confirmacao de email, devera retornar 200(Sucessfull) o Json; (CHECK)
* 4°Desafio: Se algum dos campos estiver vazio devera retornar um erro; (CHECK)
* 5°Desafio: Reduzir, o Handler, ou seja, diminuir a quantidade de metodos de tratativas de erro para apenas um ou dois, criar uma logica que atenda a maioria dos erros e retorne Sucessfull;
* 6°Desafio: Ajustar os erros dos exemplos abaixo;(CHECK)

    OUTRO EXEMPLO (CHECK)
    "error": "EXISTING EMAIL",
    "message": "EMAIL EXISTENTE" 

    OUTRO EXEMPLO (CHECK)
    "error": "EXISTING PASSWORD",
    "message": "SENHA EXISTENTE"

    OUTRO EXEMPLO (CHECK)
    "error": "PASSWORD IS NULL",
    "message": "CAMPO SENHA VAZIO"

    OUTRO EXEMPLO (CHECK)
    "error": "EMAIL IS NULL",
    "message": "CAMPO EMAIL VAZIO",

**TASK BY: PARDAL**
DATA: 01/01/2022

* 1°Desafio: Corrigir "error" dos exemplos acima, que na msg devolva a mensagem de "error" acima;(CHECK)
* 2°Desafio: Diminuir @ExceptionHanlder apenas um metodo, mas que atenda diferentes tipos de error;
  * Rever Herança para resolver a diminuição de @ExceptionHandler;(TENTAR)
  * Rever a parte de Exceptions;(TENTAR)
* 3°Desafio: Adicionar campo cpf, conforme email, senha e confirmação, repetir busca, erros, lógica, com o novo campo cpf;(CHECK)

**TASK BY: PARDAL**
DATA: 06/01/2022

* 1°Desafio: Melhorar a lógica de geração do CPF, para ver se é válido (procurar a regra);(CHECK)
* 2°Desafio: Cadastrar diversos telefone para cada usuario (na hora do cadastro, no post, posso cadastrar todos de uma só vez), mas 
  * adicionar endpoint para atualizar, um endpoint a parte só para atualizar os telefones;(CHECK)
* 3°Desafio: Criar um endpoint para apenas ler os telefones cadastrados do usuario e um endpoint a parte com relação a deletar;(CHECK)

**TASK BY: PARDAL**
DATA: 12/01/2022

* 1°Desafio: Retornar telefone cadastrado pelo TelefoneService(insert), mas não pode passar apenas o telefone e cadastrar, tem que 
  * cadastrar e linkar com o id do usuario;(CHECK / MAS REVER ISSO)
* 2° Desafio: Retornar apenas usuarios que contém telefone, no novo endpoint get;
  * Retornar erro ao não ter nenhum usuario com telefone;
* 3° Desafio: Retornar apenas usuarios com o cpf, com o final par;
* 4° Desafio: Retornar um erro caso o telefone cadastrado seja igual ao numero de telefone cadastro na base de dados (em todo lugar
  * que manipula o telefone deverá haver essa regra);
* 5° Desafio: Atualizar telefone, metodo update; (ADIADO)