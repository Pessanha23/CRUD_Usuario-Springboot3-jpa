# INSTRUÇÕES DE INCREMENTAÇÃO E ATIVIDADES DO CRUD


**Tecnologias utilizadas**

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="100px" /> 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="100px" />
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg" width="100px" />
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="80px" /> 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original-wordmark.svg" width="100px" /> 

**TIPOS DE ERROS A SEREM TRATADOS NO CRUD BY: PARDAL**

* 1°Desafio: Ajustar campo senha na atualizacao p/ que possa repetir a mesma senha anterior do usuario, mesmo nao podendo colocar a senha similar de novos usuarios;
* 2°Desafio: Buscar pelo endereço do localhost o campo email; (CHECK)
* 3°Desafio: Quando atualizar com a mesma senha, email, ou confirmacao de email, devera retornar 200(Sucessfull) o Json;
* 4°Desafio: Se algum dos campos estiver vazio devera retornar um erro;
* 5°Desafio: Reduzir, o Handler, ou seja, diminuir a quantidade de metodos de tratativas de erro para apenas um ou dois, criar uma logica que atenda a maioria dos erros e retorne Sucessfull;
* 6°Desafio: Ajustar os erros dos exemplos abaixo;

    OUTRO EXEMPLO
    "error": "EXISTING EMAIL",
    "message": "EMAIL EXISTENTE" 

    OUTRO EXEMPLO
    "error": "EXISTING PASSWORD",
    "message": "SENHA EXISTENTE"

    OUTRO EXEMPLO
    "error": "PASSWORD IS NULL",
    "message": "CAMPO SENHA VAZIO"

    OUTRO EXEMPLO
    "error": "PASSWORD CONFIRMATION IS NULL",
    "message": "CAMPO CONFIRMAÇÃO DE SENHA VAZIO"

    OUTRO EXEMPLO
    "error": "EMAIL IS NULL",
    "message": "CAMPO EMAIL VAZIO",

    OUTRO EXEMPLO
    "error": "ALL FIELDS IS NULL",
    "message": "TODOS OS CAMPOS SE ENCONTRAM VAZIO",
    
    OUTRO EXEMPLO
    "error": "EMAIL AND PASSWORD IS NULL",
    "message": "CAMPOS EMAIL E SENHA SE ENCONTRAM VAZIO",
    
    OUTRO EXEMPLO
    "error": "EMAIL AND PASSWORD CONFIRMATION IS NULL",
    "message": "CAMPOS EMAIL E CONFIRMACAO DE SENHA SE ENCONTRAM VAZIO",
    
    OUTRO EXEMPLO
    "error": "PASSWORD AND PASSWORD CONFIRMATION IS NULL",
    "message": "CAMPOS SENHA E CONFIRMAÇÃO DE SENHA SE ENCONTRAM VAZIO",
          
