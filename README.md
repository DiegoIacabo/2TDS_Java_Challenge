# Challenge Plusoft 2TDSPJ

## Integrantes
- Alejandro Rabelo RM93606 (Responsável pelo vídeo *pitch*)
- Cristian Alvaro Condori Paucara RM550509 (Responsável pela criação dos protótipos de telas mobile)
- Diego Seiti Ogita Iacabo RM551289 (Responsável pelo desenvolvimento do código Java e modelagem do Banco de Dados)
- João Lucas Marques Costa RM98376 (Responsável por desenvolver a declaração de visão e escopo do projeto em formato *pitch*)
- Luiz Felipe Azevedo de Oliveira RM550348 (Responsável pelo escopo do projeto na ótica de DevOps e virtualização)

## Como Rodar a Aplicação
    1. Clonar o repositório: `git clone https://github.com/DiegoIacabo/challenge-2tdspj.git`;
    
    2. Navegar até o diretório do projeto: `cd challenge-2tdspj`;

    3. Verificar se todas as dependências estão instaladas no arquivo pom.xml;

    4. Verificar o acesso ao banco de dados na pasta resources. No profile 'desenvolvimento' conexão com MariaDB
    e no profile 'homologacao' conexão com Oracle;

    5. Rodar a aplicação no arquivo 'ChallengeApplication';

    6. Testar as requisições dentro da pasta 'documentacao' geradas no Postman.

## Diagramas
### Diagrama de Classes
![diagrama_classes_challenge.png](documentacao%2Fimagens%2Fdiagrama_classes_challenge.png)

### Diagrama de Entidade e Relacionamento
![modelo_relacional_challenge.png](documentacao%2Fimagens%2Fmodelo_relacional_challenge.png)

## Vídeo Pitch
- Link: https://youtu.be/pt8mEREtx3k

## Listagem de Endpoints
- BancoResource:
  - "localhost/banco" - GET by example
  - "localhost/banco" - POST
  - "localhost/banco/{id}" - GET
- AgenciaResource:
  - "localhost/agencia" - GET by example
  - "localhost/agencia" - POST
  - "localhost/agencia/{id}" - GET
- ClienteResource:
  - "localhost/cliente" - GET by example
  - "localhost/cliente" - POST
  - "localhost/cliente/{id}" - GET
- ContaResource:
  - "localhost/conta" - GET by example
  - "localhost/conta" - POST
  - "localhost/conta/{id}" - GET
  - "localhost/conta/{id}/clientes" - POST
- CartaoDeCreditoResource:
  - "localhost/cartaoDeCredito" - GET by example
  - "localhost/cartaoDeCredito" - POST
  - "localhost/cartaoDeCredito/{id}" - GET