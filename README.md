# INSTRUÇÕES DE INCREMENTAÇÃO E ATIVIDADES DO CRUD


**Tecnologias utilizadas**

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="100px" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="100px" /><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg" width="100px" /><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="100px" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original-wordmark.svg" width="100px" /> 

**TIPOS DE ERROS A SEREM TRATADOS NO CRUD BY: PARDAL**
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

    OUTRO EXEMPLO (CHECK)*
    "error": "PASSWORD IS NULL",
    "message": "CAMPO SENHA VAZIO"

    OUTRO EXEMPLO (CHECK)*
    "error": "PASSWORD CONFIRMATION IS NULL",
    "message": "CAMPO CONFIRMAÇÃO DE SENHA VAZIO"

    OUTRO EXEMPLO (CHECK)*
    "error": "EMAIL IS NULL",
    "message": "CAMPO EMAIL VAZIO",

**TIPOS DE ERROS A SEREM TRATADOS NO CRUD BY: PARDAL**
DATA: 01/01/2022

* 1°Desafio: Corrigir "error" dos exemplos acima, que na msg devolva a mensagem de "error" acima;
* 2°Desafio: Diminuir @ExceptionHanlder apenas um metodo, mas que atenda diferentes tipos de error;
  * Rever Herança para resolver a diminuição de @ExceptionHandler;
  * Rever a parte de Exceptions;
* 3°Desafio: Adicionar campo cpf, conforme email, senha e confirmação, repetir busca, erros, lógica, com o novo campo cpf;



          
