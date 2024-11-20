# Em Construção

---
#### Objetivo do Auth Service:
O principal objetivo do Auth Service é gerenciar o processo de autenticação (verificar a identidade do usuário) e autorização (determinar o que o usuário pode ou não fazer). Ele vai fornecer tokens JWT (JSON Web Tokens) que os usuários utilizarão para autenticar suas requisições aos outros microserviços.

#### Funcionalidades Principais do Auth Service:
**Registro de Usuário (Cadastro):**

 -   O usuário vai se registrar no sistema fornecendo informações como nome, e-mail, senha, e outros dados necessários.
    As informações são validadas (ex: e-mail único) e a senha é criptografada (geralmente com BCrypt).
    Após o cadastro, o sistema pode retornar uma mensagem de sucesso ou erro.
    Login de Usuário (Autenticação):

-    O usuário vai fornecer suas credenciais (usuário e senha).
    O Auth Service vai verificar se o e-mail existe e se a senha está correta (comparando a senha fornecida com a senha criptografada no banco de dados).
    Se a autenticação for bem-sucedida, o Auth Service vai gerar um token JWT e enviá-lo de volta para o usuário.
    O JWT será usado como um “cartão de identificação” para autorizar o usuário em requisições subsequentes.
    Validação de Token (Autorização):

 -   Quando o usuário faz uma requisição a outro microserviço, ele envia o token JWT no cabeçalho da requisição (geralmente no campo Authorization).
    O microserviço vai verificar se o token é válido chamando o Auth Service ou validando localmente (com a chave secreta usada na geração do JWT).
    Se o token for válido, o microserviço pode liberar o acesso ao recurso solicitado, caso contrário, retorna um erro de autorização (ex: HTTP 401).
    Renovação de Token (Refresh Token):

 -   O JWT tem um tempo de expiração (ex: 15 minutos).
    Para evitar que o usuário precise fazer login novamente, você pode implementar um mecanismo de refresh token.
    O refresh token tem um tempo de vida maior (ex: 30 dias) e é usado para gerar novos tokens JWT sem que o usuário precise inserir as credenciais novamente.
    Fluxo de Funcionamento do Auth Service:
    Registro (Sign Up):

-    O usuário envia uma requisição POST para a rota /auth/register com as informações necessárias (nome, e-mail, senha).
    O Auth Service valida as informações e cria o usuário no banco de dados, armazenando a senha criptografada.
    Responde com uma mensagem de sucesso ou erro.
    Login (Sign In):

-   O usuário envia uma requisição POST para a rota /auth/login com as credenciais (e-mail e senha).
    O Auth Service valida as credenciais (se o e-mail e a senha estão corretos).
    Se autenticado, o sistema gera um JWT e retorna ao usuário.
    Se a autenticação falhar, o sistema responde com um erro de 401 Unauthorized.
    Verificação de Token (Authorization):
    
-   O microserviço que deseja verificar a autorização do usuário envia uma requisição ao Auth Service para validar o token JWT.
    O Auth Service valida o token, verificando a assinatura e se ele ainda não expirou.
    Se válido, o Auth Service retorna as permissões do usuário (ou o ID do usuário) para o microserviço.
    Se inválido ou expirado, o Auth Service retorna um erro.
    Refresh Token:
    
-   O usuário envia uma requisição para /auth/refresh com o refresh token.
    O Auth Service valida o refresh token e, se válido, gera um novo JWT.
    O novo JWT é enviado para o usuário.
    Estrutura do Banco de Dados para o Auth Service:
    Você pode utilizar um banco de dados como o PostgreSQL para armazenar as informações de usuário e tokens. A tabela mais básica seria:

#### Tabela de Usuários (Users):
````
id (Primary Key, Integer)
name (String)
email (String, único)
password (String, criptografada)
role (String, ex: "ADMIN", "USER", etc.)
created_at (Timestamp)
updated_at (Timestamp)
````

#### Tabela de Refresh Tokens:
````
id (Primary Key, Integer)
user_id (Foreign Key para Users)
token (String, o refresh token)
expires_at (Timestamp)

````
#### Tecnologias a Serem Usadas:
**Spring Boot:** Framework para criar APIs RESTful.  
**Spring Security:** Para configurar a autenticação e autorização.  
**JWT:** Para gerar e validar os tokens.  
**BCrypt:** Para criptografar as senhas antes de armazená-las no banco de dados.  
**PostgreSQL:** Para armazenar os dados dos usuários e refresh tokens.  
**Swagger/OpenAPI:** Para documentar as APIs de forma fácil e acessível.  
#### Exemplo de Fluxo de Autenticação:  
1. O usuário acessa a aplicação e se registra fornecendo nome, e-mail e senha.

2. O Auth Service registra o usuário no banco, criptografa a senha com BCrypt, e armazena no banco de dados.
3. O usuário faz login fornecendo e-mail e senha.

4. O Auth Service valida as credenciais.
- Se tudo estiver correto, o serviço gera um JWT com um tempo de expiração de 15 minutos e um Refresh Token com validade maior (30 dias).
Quando o usuário envia uma requisição para qualquer outro microserviço, ele inclui o JWT no cabeçalho da requisição (Authorization: Bearer <token>).

- O microserviço valida o token com o Auth Service para garantir que a requisição seja de um usuário autenticado.
Se o token expirar, o usuário pode enviar o Refresh Token para o Auth Service, que vai gerar um novo JWT.

