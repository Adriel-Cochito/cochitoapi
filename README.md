# CochitAPI - Sistema de Gestão e Controle de Serviços

Uma API REST desenvolvida em Java com Spring Boot para gestão e controle de serviços, funcionários e clientes.

## 📋 Sobre o Projeto

Este projeto faz parte da disciplina "Desenvolvimento Avançado com Spring e Microsserviços" da Pós-graduação MIT em Engenharia de Software. A aplicação implementa um sistema completo de CRUD (Create, Read, Update, Delete) para gestão de entidades de negócio, seguindo as melhores práticas de desenvolvimento com Spring Framework.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Maven**
- **RESTful API**

## 📁 Estrutura do Projeto

```
src/main/java/br/edu/infnet/cochitoapi/
├── controller/          # Camada de controle (REST Controllers)
├── model/
│   ├── domain/         # Entidades de domínio
│   └── service/        # Camada de serviço (regras de negócio)
├── *Loader.java        # Classes para carga inicial de dados
└── CochitoapiApplication.java
```

## 🏗️ Arquitetura

O projeto segue o padrão MVC (Model-View-Controller) com separação clara de responsabilidades:

- **Controller**: Responsável por receber requisições HTTP e retornar respostas
- **Service**: Contém a lógica de negócio e validações
- **Model**: Define as entidades de domínio e suas relações

### Modelo de Domínio

```
Pessoa (Classe Abstrata)
├── Funcionario
└── Cliente

Endereco (Classe de Associação)
Servico (Entidade Independente)
```

## 📊 Entidades

### Pessoa (Abstrata)
- `id`: Integer
- `nome`: String
- `email`: String
- `cpf`: String
- `telefone`: String

### Funcionario (extends Pessoa)
- `matricula`: int
- `salario`: double
- `ehAtivo`: boolean
- `endereco`: Endereco

### Cliente (extends Pessoa)
- `fidelidade`: String

### Servico
- `id`: Integer
- `titulo`: String
- `preco`: double
- `descricao`: String

### Endereco
- `cep`: String
- `localidade`: String

## 🛠️ Funcionalidades Implementadas

### Feature 1: Configuração Essencial ✅
- Configuração inicial do projeto Spring Boot
- Modelagem de entidade principal (Funcionario)
- Implementação de operações CRUD básicas em memória
- API REST simples com carregamento inicial de dados

### Feature 2: Expansão do Modelo de Domínio ✅
- **Modelagem completa**: Herança (Pessoa → Funcionario/Cliente) e Associação (Endereco)
- **CRUD Completo**: Funcionario e Cliente com todos os verbos HTTP
- **Operações Específicas**: `inativar` funcionário e `atualizarFidelidade` cliente
- **Tratamento Robusto**: Exceções customizadas e GlobalExceptionHandler
- **Loaders**: Carregamento automático de dados via arquivos texto

## 🔌 Endpoints da API

### Funcionários
- `GET /api/funcionarios` - Lista todos os funcionários
- `GET /api/funcionarios/{id}` - Busca funcionário por ID
- `POST /api/funcionarios` - Cria novo funcionário
- `PUT /api/funcionarios/{id}` - Altera funcionário completo
- `PATCH /api/funcionarios/{id}/inativar` - Inativa funcionário
- `DELETE /api/funcionarios/{id}` - Remove funcionário

### Clientes
- `GET /api/clientes` - Lista todos os clientes
- `GET /api/clientes/{id}` - Busca cliente por ID
- `POST /api/clientes` - Cria novo cliente
- `PUT /api/clientes/{id}` - Altera cliente completo
- `PATCH /api/clientes/{id}/fidelidade` - Atualiza nível de fidelidade
- `DELETE /api/clientes/{id}` - Remove cliente

### Serviços
- `GET /api/servicos` - Lista todos os serviços
- `GET /api/servicos/{id}` - Busca serviço por ID

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

### Exemplo de teste POST (Funcionário):
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
    "ehAtivo": true,
    "endereco": {
        "cep": "01234-567",
        "localidade": "São Paulo"
    }
}
```

### Exemplo de teste POST (Cliente):
```http
POST http://localhost:8080/api/clientes
Content-Type: application/json

{
    "nome": "Maria Santos",
    "cpf": "987.654.321-00",
    "email": "maria@email.com",
    "telefone": "(11) 88888-8888",
    "fidelidade": "GOLD"
}
```

## 🎯 Próximas Features

### Feature 3: Persistência com Banco de Dados (Em Desenvolvimento)
- [ ] Integração com banco H2
- [ ] Configuração do Spring Data JPA
- [ ] Mapeamento JPA das entidades
- [ ] Repositórios com JpaRepository
- [ ] ResponseEntity e códigos de status HTTP apropriados

## 🏛️ Padrões e Boas Práticas

- **Injeção de Dependência**: Uso de injeção por construtor
- **Tratamento de Exceções**: Exceções customizadas para regras de negócio
- **Separação de Responsabilidades**: Camadas bem definidas
- **Interface Genérica**: `CrudService<T,ID>` para padronização
- **Thread Safety**: Uso de `ConcurrentHashMap` para armazenamento em memória

## 📝 Conceitos Aplicados

- **Orientação a Objetos**: Herança, Polimorfismo, Encapsulamento
- **Padrões de Design**: MVC, Dependency Injection
- **Spring Framework**: Annotations, Inversão de Controle
- **API REST**: Verbos HTTP, Status Codes, JSON

## 👨‍💻 Desenvolvimento

Este projeto está sendo desenvolvido seguindo metodologia ágil com entregas incrementais por features, permitindo validação contínua e aplicação progressiva dos conceitos aprendidos.

## 📧 Contato

Projeto desenvolvido como parte do curso de Pós-graduação MIT em Engenharia de Software - Instituto INFNET.

---

**Status do Projeto**: 🚀 Feature 2 Concluída - CRUD Completo Implementado