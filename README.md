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
* 2° Desafio: Retornar apenas usuarios que contém telefone, no novo endpoint get;(CHECK)
  * Retornar erro ao não ter nenhum usuario com telefone;(CHECK)
* 3° Desafio: Retornar apenas usuarios com o cpf, com o final par;(CHECK)
* 4° Desafio: Retornar um erro caso o telefone cadastrado seja igual ao numero de telefone cadastro na base de dados (em todo lugar
  * que manipula o telefone deverá haver essa regra); (CHECK)
  * Adicionar campo de limitação de telefone para 10 numeros(fazer a logica);(CHECK)
  * Aplicar erro se o numero de telefone cadastrado for igual, algum outro já cadastrado pelo post/telefone;(CHECK)
* 5° Desafio: Atualizar telefone, metodo update; (ADIADO)

**TASK BY: PARDAL**
DATA: 24/01/2022
* 1°Desafio: Fazer o endpoint do telefones, receber mais de um cadastro de telefone, pro **mesmo usuário;** (CHECK)
  * PS: Endpoint deverá continuar funcionando, e você deverá criar um novo endpoint;(CHECK)
* 2°Desafio: Continuação do desafio acima. Não pode dar erro cadastrar um telefone repetido.
  * Exemplo: Se um telefone foi cadastrado no inicio e ao cadastrar novamente novos telefones, um deles deverá ser cadastrado 
  * e o número repetido no endpoint telefones, deverá apenas não ser cadastrado, não retornado um novo erro;(CHECK)
* 3°Desafio: O sistema de cadastro de telefone, no geral não pdoe retornar erro com número cadastrado repetido;(CHECK)
* 4° Desafio: No caso do único endpoint de telefone, que apenas cadastra um telefone, ele deve cadastrar mas apenas substituir o 
  * duplicado, ele não pode adicionar outro numero duplicado, no getfinal não pode aparecer telefones duplicados;(CHECK)
* Pergunta do pardal: TelefoneResource;

**TASK BY: PARDAL**
DATA: 27/01/2022
* 1°Desafio: Rede Social
* REGRAS GERAIS:
* Regra: Criar um endpoint da classe rede social, service, controller, e repository
* Usuario pode ter apenas uma rede social de cada;
* Terá que ter uma edição para a rede social cadastrada, e apagar uma rede que o cliente queira apagar

* endpoint: (get)buscar rede social de todos usuarios com rede (CHECK)
* endpoint: (get)buscar rede social por cada rede social, retornando todas as redes sociais com link e rede social daquele endpoint (CHECK)

* enpoints: (post usuario) cadastro da usuario service (CHECK)
* * Regra: Não pode ter mais de um link por rede social, mas pode cadastrar quantas redes quiser;(CHECK)

* endpoint: (post multiplas) redes sociais,apenas cadastra, não edita ou apaga(CHECK)
* * Regra: Deverá editar por multiplus; (CHECK)
* Regra: O usuario pode passar quantas redes sociais e links que quiser, mas apenas um link por rede;(CHECK)
* * Regra: Não pode ter mais de um link por rede social;(CHECK)

* endpoint: editar redes sociais no caso ele pode apenas editar o link
* * Regra: Deverá editar por multiplus;
* * Regra: Apenas editar o link, não pode editar o nome da Rede Social;
* * Regra: Apenas editar o link,não pode adicionar ou apagar;

* endpoint: apagar rede social
* * Regra: Deverá editar por multiplus;
* * Regra: apenas um enpoint, para apagar multiplos, verifique qual a melhor maneira;
* * Regra: Ele pode apagar quantas redes sociais ele quiser, em apenas 

* PLUS: Rede Social, GitHub deverá retornar a quantidade de repositorios, no caso inserido manual, e caso diminua a quantidade ou aumente deverá
editar atualizar a quantidade de repositorios;
* PLUS: Lidar com arquivos posicionais, 
* PLUS: Transações ACID;



* 
