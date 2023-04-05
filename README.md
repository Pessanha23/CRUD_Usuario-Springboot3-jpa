# POC
Essa é uma POC de uma aplicação utilizando Java 17 com [Spring Boot](https://spring.io/projects/spring-boot) e uma base
de dados [PostgreSQL](https://www.postgresql.org/). para rodas as
depêndencias externas foi utilizado o [Docker](https://www.docker.com/).

## Conceito
A idéia é construir uma API REST de cadastro de usuário, onde cada usuário deverá cadastrar alguns atributos como cpf, email, senha, telefone e midia social que , similar a um cadastro de login de um nov usuário. Utilizando boas práticas de código, testes e refatoração de código, padrões API REST para elaboração de uma URL.
API para cadastro de um usuário, onde cada usuário deve cadastrar:
- email
- senha
- confirmação de senha
- cpf
- lista de telefones
- lista de rede social

## Stack
- Java 17
- Spring boot 3
- PostgreSQL
- Postman
- Docker file
- API REST

## Estrutura do projeto
A estrutura do projeto foi criada baseada em alguns exemplos de DDD como
o [ddd-by-example](https://github.com/joolu/ddd-sample) e de Arquitetura Hexagonal como
o [Netflix](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749).
Basicamente temos 3 camadas:
* **application**: Camada responsável por expor os endpoints da API e receber as requisições.
* **domain**: Camada responsável por conter as regras de negócio e os domínios da aplicação.
* **infrastructure**: Camada responsável por conter as implementações de infraestrutura como banco de dados, mensageria,
  etc.

## Testes
![img.png](img.png)
### Unitários
Os testes unitários são os testes mais simples e rápidos de serem executados.
Devem mockar todas as dependências externas a classe que está sendo testada.  
Eles são utilizados em diversas camadas da aplicação, mas principalmente na camada de **domain**.

- **application**: Testes unitários dos *mappers*
- **domain**: Testes unitários dos *models* e *services*
- **infrastructure**: Testes unitários dos *mappers*

### Integração

Os testes de integração são utilizados para integrações com serviços externos, como banco de dados e APIs. Utilizando Mock Mvc para fazer a cobertura da integração entre os frameworks.

## POSTMAN
GET
- Retorna uma lista de todos os usuarios cadastrados. 
http://localhost:8080/usuarios
- Retorna o cadastro de um usuario procurado pelo endereço de email cadastrado. 
 http://localhost:8080/usuarios?email=matheus@gmail.com  
- Retorna o cadastro de um usuario procurado pelo cpf cadastrado. 
http://localhost:8080/usuarios?cpf=87275093030 
- Retorna uma lista de todos os usuarios cadastrados com cpf par. 
http://localhost:8080/usuarios?cpfpar=true 
- Retorna o cadastro de um usuario procurado pelo id gerado após cadastro. 
http://localhost:8080/usuarios/1
- Retorna uma lista de todos telefones cadastrados. 
http://localhost:8080/telefones
- Retorna o cadastrado apenas daquele telefone fornecido como parametro na URI. 
http://localhost:8080/telefones?telefone=666666666
- Retorna uma lista de todos telefones cadastrados.
  http://localhost:8080/redesocial
- Retorna o cadastrado apenas daquele telefone fornecido como parametro na URI.
  http://localhost:8080/redesocial?midia=Linkedin

POST

- Cadastro de usuário. 
http://localhost:8080/usuarios
- Cadastro de um telefone ou mais, para um usuário.
http://localhost:8080/usuarios/1/telefones
- Cadastro de uma rede social ou mais, para um usuário.
http://localhost:8080/usuarios/1/redesocial

PUT
- Atualização de usuário.
http://localhost:8080/usuarios/1
- Atualização de telefone de usuário.
http://localhost:8080/telefones/1
- Atualização de lista de rede social. 
http://localhost:8080/redesocial/1

DELETE
- Apagar usuario cadastrado atráves do id passado como parametro na URI. 
http://localhost:8080/usuarios/1

## Exemplos para testar
### 201 OK
Request
```
{
    "email": "gamiguel@gmail.com",
    "senha": "shazam",
    "confirmacaoSenha": "shazam",
    "cpf": "59358995076",
    "telefoneSet": [
        {
            "telefone": "22222222"
        }
    ],
    "redeSocialList":[
        {
             "midia": "Linkedin",
            "linkRede": "@Gamiguel"
        }
    ]
}
```

Response
```
{
    "id": 1,
    "email": "gamiguel@gmail.com",
    "senha": "shazam",
    "confirmacaoSenha": "shazam",
    "cpf": "59358995076",
    "telefoneSet": [
        {
            "id": 1,
            "telefone": "22222222",
            "novoId": null
        }
    ],
    "redeSocialList": [
        {
            "id": 1,
            "midia": "Linkedin",
            "linkRede": "@Gamiguel",
            "novoId": null
        }
    ]
}
```



