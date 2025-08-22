# CochitAPI - Sistema de Gestão e Controle de Serviços

Uma API REST desenvolvida em Java com Spring Boot para gestão e controle de serviços, funcionários e clientes com persistência em banco de dados.

## 📋 Sobre o Projeto

Este projeto faz parte da disciplina "Desenvolvimento Avançado com Spring e Microsserviços" da Pós-graduação MIT em Engenharia de Software. A aplicação implementa um sistema completo de CRUD (Create, Read, Update, Delete) para gestão de entidades de negócio, seguindo as melhores práticas de desenvolvimento com Spring Framework.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Web**
- **Spring Data JPA**
- **Spring Boot Validation**
- **H2 Database**
- **Maven**
- **RESTful API**

## 📁 Estrutura do Projeto

```
src/main/java/br/edu/infnet/cochitoapi/
├── controller/          # Camada de controle (REST Controllers)
├── model/
│   ├── domain/         # Entidades de domínio
│   │   └── exceptions/ # Exceções customizadas e handlers
│   ├── repository/     # Interfaces de repositório JPA
│   └── service/        # Camada de serviço (regras de negócio)
├── loader/             # Classes para carga inicial de dados
└── CochitoapiApplication.java
```

## 🏗️ Arquitetura

O projeto segue o padrão MVC (Model-View-Controller) com separação clara de responsabilidades:

- **Controller**: Responsável por receber requisições HTTP e retornar respostas
- **Service**: Contém a lógica de negócio e validações
- **Repository**: Camada de acesso a dados com Spring Data JPA
- **Model**: Define as entidades de domínio e suas relações

### Modelo de Domínio

```
Pessoa (Classe Abstrata - @MappedSuperclass)
├── Funcionario (@Entity)
└── Cliente (@Entity)

Endereco (@Entity - Classe de Associação)
Servico (Entidade Independente)
```

## 📊 Entidades

### Pessoa (Abstrata - @MappedSuperclass)
- `id`: Integer (PK, auto-increment)
- `nome`: String (validado: 3-50 caracteres)
- `email`: String (validado: formato email válido)
- `cpf`: String (validado: formato XXX.XXX.XXX-XX)
- `telefone`: String (validado: formato (XX) XXXXX-XXXX)

### Funcionario (@Entity extends Pessoa)
- `matricula`: int (obrigatório)
- `salario`: double (mínimo: 0)
- `ehAtivo`: boolean
- `endereco`: Endereco (@ManyToOne, cascade=ALL)

### Cliente (extends Pessoa)
- `fidelidade`: String

### Servico
- `id`: Integer
- `titulo`: String
- `preco`: double
- `descricao`: String

### Endereco (@Entity)
- `id`: Integer (PK, auto-increment)
- `cep`: String
- `logradouro`: String
- `complemento`: String
- `unidade`: String
- `bairro`: String
- `localidade`: String
- `uf`: String
- `estado`: String

## 🛠️ Funcionalidades Implementadas

### Feature 1: Configuração Essencial ✅
- ✅ Configuração inicial do projeto Spring Boot
- ✅ Modelagem de entidade principal (Funcionario)
- ✅ Implementação de operações CRUD básicas em memória
- ✅ API REST simples com carregamento inicial de dados
- ✅ Criação da primeira classe Controller
- ✅ Implementação do primeiro Loader
- ✅ Integração com Spring Boot e Maven

### Feature 2: Expansão do Modelo de Domínio ✅
- ✅ **Estrutura do modelo de domínio expandido**
  - ✅ Classe Mãe: Pessoa (abstrata) com 4+ atributos
  - ✅ Classe Filha 1: Funcionario (extends Pessoa) com atributos específicos
  - ✅ Classe Filha 2: Cliente (extends Pessoa) com atributos específicos  
  - ✅ Classe de Associação: Endereco (oneToOne com Funcionario)
- ✅ **Tratamento de exceções customizadas**
  - ✅ RecursoInvalidoException para regras de negócio
  - ✅ RecursoNaoEncontradoException para recursos inexistentes
  - ✅ GlobalExceptionHandler para tratamento centralizado
- ✅ **Interface CrudService<T,ID> atualizada**
  - ✅ Contrato completo: incluir, alterar, buscarPorId, listarTodos, excluir
- ✅ **Gerenciamento de dados iniciais (Loaders)**
  - ✅ FuncionarioLoader: carrega funcionários e endereços
  - ✅ ClienteLoader: carrega clientes
  - ✅ ServicoLoader: carrega serviços
- ✅ **Camada de serviço completa**
  - ✅ FuncionarioService: CRUD + inativar()
  - ✅ ClienteService: CRUD + atualizarFidelidade()
  - ✅ ServicoService: CRUD básico
- ✅ **Camada de controle (API REST)**
  - ✅ FuncionarioController: GET, POST, PUT, PATCH, DELETE
  - ✅ ClienteController: GET, POST, PUT, PATCH, DELETE  
  - ✅ ServicoController: GET, POST, PUT básico
- ✅ **Testes com Postman**
  - ✅ Coleções preparadas para todos os endpoints
  - ✅ RequestBody e PathVariable implementados
  - ✅ Validação de todos os verbos HTTP

### Feature 3: Persistência com Banco de Dados 🚧 (~70% Implementada)
- ✅ **Dependências essenciais (pom.xml)**
  - ✅ Spring Boot Starter Data JPA
  - ✅ H2 Database
  - ✅ Spring Boot Validation
- ✅ **Configuração do banco de dados (application.properties)**
  - ✅ Configuração H2 completa (jdbc:h2:~/databaseCochito)
  - ✅ Console H2 habilitado (/h2-console)
  - ✅ Configuração JPA/Hibernate (ddl-auto=create, show-sql=true)
- 🚧 **Mapeamento das entidades com JPA (Parcial)**
  - ✅ @Entity em Funcionario e Endereco
  - ✅ @MappedSuperclass em Pessoa (estratégia de herança)
  - ✅ @Id e @GeneratedValue para chaves primárias
  - ✅ Relacionamento @ManyToOne entre Funcionario e Endereco
  - ✅ Cascade ALL para persistência automática de endereços
  - ❌ Cliente ainda é POJO (não tem @Entity)
  - ❌ Servico ainda é POJO (não tem @Entity)
- ✅ **Bean Validation implementado**
  - ✅ @NotNull, @NotBlank, @Email em Pessoa
  - ✅ @Size para validação de tamanho de strings
  - ✅ @Pattern para validação de CPF e telefone
  - ✅ @Min para validação de salário mínimo
  - ✅ @Valid para validação em cascata
- 🚧 **Criação de repositórios com Spring Data JPA (Parcial)**
  - ✅ FuncionarioRepository extends JpaRepository<Funcionario, Integer>
  - ❌ ClienteRepository não existe
  - ❌ ServicoRepository não existe
- 🚧 **Atualização da camada de serviço (Parcial)**
  - ✅ FuncionarioService migrado para JpaRepository
  - ✅ Remoção de Map e AtomicInteger no FuncionarioService
  - ❌ ClienteService ainda usa Map/ConcurrentHashMap
  - ❌ ServicoService ainda usa Map/ConcurrentHashMap
- ✅ **Refinamento da API REST com ResponseEntity**
  - ✅ FuncionarioController: Status HTTP apropriados (201, 200, 204, 400, 404)
  - ✅ ClienteController: ResponseEntity implementado
  - 🚧 ServicoController: ResponseEntity parcialmente implementado
- ✅ **Tratamento de exceções refinado**
  - ✅ GlobalExceptionHandler com ResponseEntity
  - ✅ Tratamento de MethodArgumentNotValidException
  - ✅ ErrorResponse e ValidationErrorResponse estruturados
  - ✅ Timestamps e URIs de erro incluídos

## 🗄️ Banco de Dados

### Configuração H2 
```properties
# application.properties
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:~/databaseCochito
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Console H2 disponível em**: http://localhost:8080/h2-console

### Estrutura das Tabelas

**PESSOA (Superclasse - @MappedSuperclass)**
- Atributos herdados pelas tabelas filhas

**FUNCIONARIO**
```sql
CREATE TABLE funcionario (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    matricula INTEGER NOT NULL,
    salario DOUBLE,
    ativo BOOLEAN,
    endereco_id INTEGER,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);
```

**ENDERECO**
```sql
CREATE TABLE endereco (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(9),
    logradouro VARCHAR(255),
    complemento VARCHAR(255),
    unidade VARCHAR(50),
    bairro VARCHAR(100),
    localidade VARCHAR(100),
    uf VARCHAR(2),
    estado VARCHAR(50)
);
```

## 🔌 Endpoints da API

### Funcionários
- `GET /api/funcionarios` - Lista todos os funcionários
- `GET /api/funcionarios/{id}` - Busca funcionário por ID
- `POST /api/funcionarios` - Cria novo funcionário (201 CREATED)
- `PUT /api/funcionarios/{id}` - Altera funcionário completo (200 OK)
- `PATCH /api/funcionarios/{id}/inativar` - Inativa funcionário (200 OK)
- `DELETE /api/funcionarios/{id}` - Remove funcionário (204 NO CONTENT)

### Clientes
- `GET /api/clientes` - Lista todos os clientes
- `GET /api/clientes/{id}` - Busca cliente por ID
- `POST /api/clientes` - Cria novo cliente (201 CREATED)
- `PUT /api/clientes/{id}` - Altera cliente completo (200 OK)
- `PATCH /api/clientes/{id}/fidelidade` - Atualiza nível de fidelidade (200 OK)
- `DELETE /api/clientes/{id}` - Remove cliente (204 NO CONTENT)

### Serviços
- `GET /api/servicos` - Lista todos os serviços
- `GET /api/servicos/{id}` - Busca serviço por ID
- `POST /api/servicos` - Cria novo serviço (201 CREATED)
- `PUT /api/servicos/{id}` - Altera serviço completo (200 OK)

## 🚦 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+

### Passos para execução

1. **Clone o repositório**
```bash
git clone [URL_DO_REPOSITORIO]
cd cochitoapi
```

2. **Compile o projeto**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a API**
```
http://localhost:8080/api
```

5. **Acesse o Console H2** (Para visualizar o banco)
```
http://localhost:8080/h2-console
```

## 📂 Arquivos de Dados

O projeto utiliza arquivos texto para carga inicial dos dados:

- `funcionario.txt` - Dados dos funcionários e endereços
- `cliente.txt` - Dados dos clientes
- `servico.txt` - Dados dos serviços

### Formato dos arquivos:

**funcionario.txt:**
```
Nome;Matricula;Salario;EhAtivo;CPF;Email;Telefone;CEP;Localidade
```

**cliente.txt:**
```
Nome;CPF;Email;Telefone;Fidelidade
```

**servico.txt:**
```
Titulo;Preco;Descricao
```

## 🧪 Testando a API

Recomenda-se o uso do **Postman** para testar os endpoints da API. Importe a coleção de requisições ou crie manualmente as seguintes requisições:

### Exemplo de teste POST (Funcionário com Validação):
```http
POST http://localhost:8080/api/funcionarios
Content-Type: application/json

{
    "nome": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao@email.com",
    "telefone": "(11) 99999-9999",
    "matricula": 12345,
    "salario": 5000.00,
    "ativo": true,
    "endereco": {
        "cep": "01234-567",
        "localidade": "São Paulo"
    }
}
```

### Exemplo de resposta de erro de validação:
```json
{
    "success": false,
    "status": 400,
    "code": "VALIDATION_ERROR",
    "message": "Dados inválidos fornecidos",
    "path": "/api/funcionarios",
    "timestamp": "2025-01-XX...",
    "validationErrors": [
        {
            "field": "cpf",
            "rejectedValue": "123456789",
            "message": "CPF deve estar no formato XXX.XXX.XXX-XX"
        }
    ]
}
```

## 🎯 Próximas Features

### Feature 4: A Definir (Aguardando Especificação)
- 🔮 **Especificação pendente**
  - [ ] *Aguardando definição dos requisitos*
  - [ ] *Conceitos e tecnologias a serem aplicados*
  - [ ] *Entregáveis e objetivos da feature*

### 🎯 Roadmap Completo
1. ✅ **Feature 1**: Fundação e CRUD básico 
2. ✅ **Feature 2**: Expansão do modelo e robustez
3. ✅ **Feature 3**: Persistência JPA e refinamento da API
4. 🔮 **Feature 4**: *Especificação em desenvolvimento*

## 🏛️ Padrões e Boas Práticas

- **Injeção de Dependência**: Uso de injeção por construtor
- **Tratamento de Exceções**: Exceções customizadas para regras de negócio
- **Separação de Responsabilidades**: Camadas bem definidas
- **Interface Genérica**: `CrudService<T,ID>` para padronização
- **Thread Safety**: Uso de `ConcurrentHashMap` para armazenamento em memória
- **Bean Validation**: Validações declarativas com annotations
- **JPA/Hibernate**: Mapeamento objeto-relacional automático
- **Response Entity**: Controle granular de respostas HTTP
- **Global Exception Handling**: Tratamento centralizado de exceções

## 📊 Status de Entrega por Feature

| Feature | Status | Entregáveis | Progresso |
|---------|---------|-------------|-----------|
| **Feature 1** | ✅ **Concluída** | Configuração base + CRUD simples | 100% |
| **Feature 2** | ✅ **Concluída** | Modelo expandido + CRUD completo | 100% |
| **Feature 3** | 🚧 **70% Implementada** | Persistência JPA + API refinada | 70% |
| **Feature 4** | 🔮 **A definir** | *Aguardando especificação* | 0% |

### Implementações da Feature 3 🚧 (70% Concluída)

#### ✅ Totalmente Implementado
- **Dependências JPA e H2**: Completas no pom.xml
- **Configuração do banco**: application.properties com H2 completo
- **Mapeamento JPA do Funcionario**: @Entity, relacionamentos, validações
- **Repository do Funcionario**: Spring Data JPA implementado
- **Bean Validation**: Completo com @Valid, @NotNull, @Email, @Pattern, etc.
- **Global Exception Handler**: Tratamento robusto de erros
- **ResponseEntity**: Implementado na maioria dos controllers

#### ❌ Ainda Pendente (Para atingir 100%)
- **Cliente/Servico como @Entity**: Ainda são POJOs simples
- **Repositories faltando**: ClienteRepository e ServicoRepository
- **Services não migrados**: Cliente e Servico ainda usam Map em memória
- **API incompleta**: ServicoController sem DELETE endpoint

### Status Atual: Arquitetura Híbrida
- **Funcionario**: 100% JPA (persistência real no H2)
- **Cliente/Servico**: Ainda em memória (Map + AtomicInteger)
- **Banco H2**: Funcional com console disponível
- **Validações**: Bean Validation ativo
- **API REST**: ResponseEntity implementado

---

## 👨‍💻 Desenvolvimento

Este projeto está sendo desenvolvido seguindo metodologia ágil com entregas incrementais por features, permitindo validação contínua e aplicação progressiva dos conceitos aprendidos.

**Conceitos aplicados na Feature 3:**
- Spring Data JPA e Hibernate
- Bean Validation
- Estratégias de herança JPA
- Relacionamentos JPA (@ManyToOne, Cascade)
- ResponseEntity e Status HTTP
- Global Exception Handling
- H2 Database em memória

## 📧 Contato

Projeto desenvolvido como parte do curso de Pós-graduação MIT em Engenharia de Software - Instituto INFNET.

---

**Status do Projeto**: 🚧 Feature 3 - 70% Implementada | 🔮 Feature 4 A Definir