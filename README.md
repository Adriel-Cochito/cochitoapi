# CochitoApi - Sistema de Gestão de Serviços

## 🆕 Feature 02 - Integração com Cálculo de Distância (Novidade!)

### **🚀 Implementação Recente: Consumo de APIs Externas**

O projeto **cochitoApi** foi **atualizado** para consumir o serviço de cálculo de distância do projeto **cochitoServicoApi** da disciplina atual. Esta implementação demonstra **comunicação entre microserviços** e **enriquecimento automático de dados**.

#### **✅ O que foi Implementado na Feature 02:**

##### **1. Cliente Feign para Comunicação Externa**
```java
@FeignClient(name = "distanciaClient", url = "${cochitoservicoapi.url}")
public interface DistanciaFeignClient {
    @GetMapping("/api/servicos/distancia")
    DistanciaQueryResult calcularDistancia(
        @RequestParam("cepOrigem") String cepOrigem,
        @RequestParam("cepDestino") String cepDestino
    );
}
```

##### **2. Serviço de Integração**
```java
@Service
public class DistanciaService {
    public DistanciaQueryResult consultarDistancia(String cepOrigem, String cepCliente) {
        return distanciaFeignClient.calcularDistancia(cepOrigem, cepCliente);
    }
}
```

##### **3. Controller para Exposição**
```java
@RestController
@RequestMapping("/api/distancia")
public class DistanciaController {
    @GetMapping("/consulta/{cepOrigem}/{cepCliente}")
    public ResponseEntity<DistanciaQueryResult> consultarDistancia(
        @PathVariable String cepOrigem, @PathVariable String cepCliente) {
        // Implementação que consome cochitoServicoApi
    }
}
```

##### **4. Enriquecimento Automático de Ordem de Serviço**
- **OrdemServicoService** foi modificado para incluir automaticamente a distância entre funcionário e cliente
- Campo `@Transient` adicionado em **OrdemServico** para não persistir no banco
- **Consulta automática** sempre que uma ordem é recuperada por ID

#### **🎯 Funcionalidades da Feature 02:**

- ✅ **Endpoint direto**: `GET /api/distancia/consulta/{origem}/{destino}`
- ✅ **Enriquecimento automático**: Ordens de serviço incluem distância funcionário-cliente
- ✅ **Integração robusta**: Tratamento de erros e fallback
- ✅ **Configuração externa**: URL do cochitoServicoApi via properties

#### **📊 Exemplo de Resposta Enriquecida:**
```json
{
    "id": 1,
    "cliente": { "nome": "Maria Santos", "endereco": { "cep": "38020-433" } },
    "funcionario": { "nome": "João Silva", "endereco": { "cep": "38065-065" } },
    "servico": { "titulo": "Desenvolvimento Web" },
    "status": "EM_ANDAMENTO",
    "distancia": {
        "cepOrigem": "38065065",
        "cepDestino": "38020433",
        "distanciaKm": 2.66,
        "tempoMinutos": 3.39,
        "enderecoOrigem": "Rua Governador Valadares",
        "enderecoDestino": "Avenida Santa Beatriz da Silva"
    }
}
```

#### **⚙️ Configuração Necessária:**
```properties
# application.properties
cochitoservicoapi.url=http://localhost:8081
server.port=8080
```

---

## 📋 Sobre o Projeto

Uma API REST desenvolvida em Java com Spring Boot para gestão e controle de serviços, funcionários e clientes com persistência em banco de dados.

**Link do repositório**: [https://github.com/Adriel-Cochito/cochitoapi](https://github.com/Adriel-Cochito/cochitoapi)

**Collection Postman**: [Acessar Collection](https://api.postman.com/collections/33558167-802ea8c2-aeea-42c5-bcd2-81c4329b7c7b?access_key=PMAT-01K3Q35BC53066TFP2V3VFJRXT)

- 📋 **Aluno**: Adriel Henrique Borges Cochito
- 📋 **Disciplina**: Desenvolvimento de aplicações Java com Spring Boot [25E3_2]
- 📋 **Curso**: MIT Engenharia de Software (JAVA)

Este projeto faz parte da disciplina "Desenvolvimento Avançado com Spring e Microsserviços" da Pós-graduação MIT em Engenharia de Software. A aplicação implementa um sistema completo de CRUD (Create, Read, Update, Delete) para gestão de entidades de negócio, seguindo as melhores práticas de desenvolvimento com Spring Framework.

**Status do Projeto**: ✅ **CONCLUÍDO** - Todas as 4 Features implementadas com sucesso!

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Web**
- **Spring Data JPA**
- **Spring Boot Validation**
- **Spring Cloud OpenFeign** *(Novidade Feature 02)*
- **H2 Database**
- **Maven**
- **RESTful API**
- **Bean Validation**
- **Global Exception Handling**

## 📁 Estrutura do Projeto

```
src/main/java/br/edu/infnet/cochitoapi/
├── controller/          # Camada de controle (REST Controllers)
├── model/
│   ├── clients/        # 🆕 Feign Clients (Feature 02)
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
- **🆕 Clients**: Comunicação com serviços externos via Feign *(Feature 02)*

### Modelo de Domínio

```
Pessoa (Classe Abstrata - @MappedSuperclass)
├── Funcionario (@Entity)
└── Cliente (@Entity)

Endereco (@Entity - Classe de Associação)
Servico (@Entity - Entidade Independente)
OrdemServico (@Entity - Núcleo do sistema) + distancia (@Transient) 🆕
```

## 📊 Entidades

### Pessoa (Abstrata - @MappedSuperclass)
- `id`: Integer (PK, auto-increment)
- `nome`: String (validado: 3-50 caracteres)
- `email`: String (validado: formato email válido)
- `cpf`: String (validado: formato XXX.XXX.XXX-XX)
- `telefone`: String (validado: formato (XX) XXXXX-XXXX)

### Funcionario (@Entity extends Pessoa)
- `matricula`: int (obrigatório, mínimo: 1)
- `salario`: double (mínimo: 0)
- `ativo`: boolean
- `endereco`: Endereco (@ManyToOne, cascade=ALL)

### Cliente (@Entity extends Pessoa)
- `fidelidade`: String (validado: 3-20 caracteres)

### Servico (@Entity)
- `id`: Integer (PK, auto-increment)
- `titulo`: String (validado: 3-100 caracteres)
- `preco`: double (mínimo: 0)
- `descricao`: String (validado: 10-500 caracteres)

### OrdemServico (@Entity) - ⭐ Atualizada na Feature 02
- `id`: Integer (PK, auto-increment)
- `cliente`: Cliente (@ManyToOne)
- `funcionario`: Funcionario (@ManyToOne)
- `servico`: Servico (@ManyToOne)
- `dataCriacao`: LocalDateTime
- `dataExecucao`: LocalDateTime
- `status`: String (validado: enum valores)
- `distancia`: DistanciaQueryResult (**@Transient** - 🆕 Feature 02)

### 🆕 DistanciaQueryResult (Feature 02)
- `cepOrigem`: String
- `cepDestino`: String
- `enderecoOrigem`: String
- `bairroOrigem`: String
- `ufOrigem`: String
- `enderecoDestino`: String
- `bairroDestino`: String
- `ufDestino`: String
- `distanciaKm`: double
- `tempoMinutos`: double

### Endereco (@Entity)
- `id`: Integer (PK, auto-increment)
- `cep`: String (validado: formato XXXXX-XXX)
- `logradouro`: String (validado: 5-100 caracteres)
- `complemento`: String (opcional, max: 50 caracteres)
- `unidade`: String (opcional, max: 10 caracteres)
- `bairro`: String (validado: 2-50 caracteres)
- `localidade`: String (validado: 2-50 caracteres)
- `uf`: String (validado: exatamente 2 caracteres)
- `estado`: String (validado: 4-20 caracteres)

## 🛠️ Endpoints Principais

### 🆕 **Feature 02 - Cálculo de Distância**
```http
GET /api/distancia/consulta/{cepOrigem}/{cepDestino}
```

### **Funcionários**
```http
GET    /api/funcionarios           # Listar todos
GET    /api/funcionarios/{id}      # Buscar por ID
POST   /api/funcionarios           # Criar novo
PUT    /api/funcionarios/{id}      # Atualizar
DELETE /api/funcionarios/{id}      # Excluir
```

### **Clientes**
```http
GET    /api/clientes               # Listar todos
GET    /api/clientes/{id}          # Buscar por ID
POST   /api/clientes               # Criar novo
PUT    /api/clientes/{id}          # Atualizar
DELETE /api/clientes/{id}          # Excluir
```

### **Serviços**
```http
GET    /api/servicos               # Listar todos
GET    /api/servicos/{id}          # Buscar por ID
POST   /api/servicos               # Criar novo
PUT    /api/servicos/{id}          # Atualizar
DELETE /api/servicos/{id}          # Excluir
```

### **Ordens de Serviço** - ⭐ Atualizada na Feature 02
```http
GET    /api/ordens-servico         # Listar todas
GET    /api/ordens-servico/{id}    # Buscar por ID (🆕 com distância automática)
POST   /api/ordens-servico         # Criar nova
PUT    /api/ordens-servico/{id}    # Atualizar
DELETE /api/ordens-servico/{id}    # Excluir

# Query Methods (Feature 4)
GET    /api/ordens-servico/status/{status}
GET    /api/ordens-servico/cliente/{clienteId}/status/{status}
GET    /api/ordens-servico/funcionario/{funcionarioId}/status/{status}
GET    /api/ordens-servico/periodo?inicio={data}&fim={data}
GET    /api/ordens-servico/servico-titulo/{titulo}
GET    /api/ordens-servico/cliente-nome/{nome}
GET    /api/ordens-servico/cliente-cpf/{cpf}
GET    /api/ordens-servico/count/status/{status}
GET    /api/ordens-servico/pendentes/periodo?inicio={data}&fim={data}
```

## 📝 Exemplos de Uso

### 🆕 **Testar Feature 02 - Cálculo de Distância**

```bash
# 1. Consulta direta de distância
curl -X GET "http://localhost:8080/api/distancia/consulta/38067290/38065065"

# 2. Buscar ordem com distância automática (funcionário + cliente)
curl -X GET "http://localhost:8080/api/ordens-servico/1"
```

### **Criar Funcionário**
```bash
curl -X POST "http://localhost:8080/api/funcionarios" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "cpf": "123.456.789-01",
    "telefone": "(11) 99999-1111",
    "matricula": 123,
    "salario": 5000.0,
    "ativo": true,
    "endereco": {
      "cep": "38065-065",
      "logradouro": "Rua Governador Valadares",
      "bairro": "Fabrício",
      "localidade": "Uberaba",
      "uf": "MG",
      "estado": "Minas Gerais"
    }
  }'
```

### **Criar Ordem de Serviço**
```bash
curl -X POST "http://localhost:8080/api/ordens-servico" \
  -H "Content-Type: application/json" \
  -d '{
    "cliente": {"id": 1},
    "funcionario": {"id": 1},
    "servico": {"id": 1},
    "dataExecucao": "2024-12-01T14:00:00",
    "status": "PENDENTE"
  }'
```

## ⚙️ Configuração e Execução

### **Pré-requisitos**
- Java 17+
- Maven 3.6+
- **cochitoServicoApi** rodando na porta 8081 *(para Feature 02)*

### **Configuração application.properties**
```properties
# Configuração do Servidor
spring.application.name=cochitoapi
server.port=8080

# 🆕 Feature 02 - URL do Serviço Externo
cochitoservicoapi.url=http://localhost:8081

# Banco H2 (Desenvolvimento)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Validação
spring.jpa.properties.hibernate.validator.apply_to_ddl=false
```

### **Executar o Projeto**
```bash
# 1. Compilar
mvn clean compile

# 2. Executar testes
mvn test

# 3. Rodar aplicação
mvn spring-boot:run

# 4. Acessar aplicação
# API: http://localhost:8080
# H2 Console: http://localhost:8080/h2-console
# Swagger UI: http://localhost:8080/swagger-ui.html
```

## 🔄 **Integração com cochitoServicoApi (Feature 02)**

### **Fluxo de Comunicação:**
```
cochitoApi (porta 8080) → cochitoServicoApi (porta 8081) → APIs Externas
       ↓                           ↓                           ↓
OrdemServicoService      DistanciaService           AwesomeAPI + OpenRoute
       ↓                           ↓                           ↓
Enriquece resposta    Calcula distância real      Coordenadas + Rota
```

### **Dependências Adicionadas:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### **Habilitação do Feign:**
```java
@SpringBootApplication
@EnableFeignClients
public class CochitoapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CochitoapiApplication.class, args);
    }
}
```

## 📈 **Funcionalidades por Feature**

### ✅ **Feature 1**: Sistema Base (Completa)
- CRUD completo para todas as entidades
- Validações robustas com Bean Validation
- Relacionamentos JPA configurados
- Exception handling global

### ✅ **Feature 2**: Integração Externa (🆕 Implementada)
- Cliente Feign para cochitoServicoApi
- Enriquecimento automático de OrdemServico
- Endpoint dedicado para consulta de distância
- Tratamento de erros e fallback

### ✅ **Feature 3**: Busca por CEP via IBGE (Completa)
- Integração com API pública do IBGE
- Busca de localidades por CEP
- Preenchimento automático de endereços

### ✅ **Feature 4**: Query Methods (Completa)
- Métodos de consulta customizados
- Filtros por status, cliente, funcionário
- Consultas por período e contadores
- Busca textual em campos específicos

## 🎯 **Benefícios da Feature 02**

### **Para o Negócio:**
- **Cálculo automático** de distância funcionário-cliente
- **Otimização de deslocamentos** e custos operacionais  
- **Informações precisas** para planejamento de rotas
- **Integração transparente** sem impacto na UX

### **Para Desenvolvimento:**
- **Comunicação entre microserviços** via Feign
- **Enriquecimento não-intrusivo** de dados
- **Arquitetura distribuída** preparada para escala
- **Padrões de integração** bem estabelecidos

### **Para Qualidade:**
- **Separação de responsabilidades** entre projetos
- **Fallback e tratamento de erros** robusto
- **Configuração externa** para ambientes
- **Baixo acoplamento** entre sistemas

---

## 📄 Licença

Este projeto é desenvolvido para fins acadêmicos como parte do curso de Engenharia de Software com foco em metodologias ágeis e boas práticas de desenvolvimento.

---

**🎯 Projeto atualizado com integração completa entre microserviços, demonstrando comunicação eficiente via OpenFeign e enriquecimento automático de dados.**