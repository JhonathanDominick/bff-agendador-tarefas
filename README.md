# 🚀 Sistema de Agendamento de Tarefas (Microserviços)

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring](https://img.shields.io/badge/Spring-Boot-green)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Architecture](https://img.shields.io/badge/Architecture-Microservices-orange)

---

## 📌 Sobre o Projeto

Sistema de agendamento de tarefas desenvolvido com **arquitetura de microserviços**, utilizando **Java 21 + Spring Boot**.

O projeto foi estruturado com foco em:

* Separação de responsabilidades
* Escalabilidade
* Comunicação entre serviços
* Autenticação distribuída

A aplicação permite:

* Cadastro e autenticação de usuários
* Criação e gerenciamento de tarefas
* Envio de notificações por e-mail

---

## 🏗️ Arquitetura

A aplicação segue o padrão **BFF (Backend for Frontend)**, centralizando o acesso do cliente:
Cliente
│
▼
BFF-AGENDADOR (8086)
│
├── CADASTRAUSUARIO (8083 - PostgreSQL)
├── AGENDADOR-TAREFAS (8081 - MongoDB)
└── NOTIFICACAO (8085)

text

### 🔗 Comunicação

* HTTP (REST)
* OpenFeign

### 🔐 Autenticação

* JWT (stateless)
* Token propagado entre microserviços

### 🐳 Infraestrutura

* Docker
* Docker Compose

---

## 📦 Microserviços

### 🔵 bff-agendador

**Backend for Frontend**

Responsável por:

* Centralizar requisições do cliente
* Orquestrar chamadas entre microserviços
* Propagar o token JWT
* Expor endpoints consolidados

* Porta: `8086`
* Build: Maven

---

### 🟢 cadastrausuario

Responsável por:

* Cadastro de usuários
* Autenticação (login)
* Geração e validação de JWT
* Integração com API externa de CEP

* Porta: `8083`
* Banco: PostgreSQL
* Build: Gradle

---

### 🟡 agendador-tarefas

Responsável por:

* Criar tarefas
* Atualizar tarefas
* Listar tarefas
* Remover tarefas

* Porta: `8081`
* Banco: MongoDB
* Build: Gradle

---

### 🟣 notificacao

Responsável por:

* Envio de e-mails
* Templates HTML com Thymeleaf

* Porta: `8085`
* Build: Gradle

---

## 📄 Documentação da API (Swagger)

A aplicação possui documentação interativa utilizando **OpenAPI (Swagger)**, permitindo visualizar e testar todos os endpoints diretamente pelo navegador.

### ▶️ Acessar Swagger

Após subir o projeto:

| Serviço | Swagger UI |
|---------|------------|
| BFF (principal) | http://localhost:8086/swagger-ui/index.html |


---

### 🔍 O que é possível fazer no Swagger

* Visualizar todos os endpoints disponíveis
* Testar requisições diretamente
* Ver estrutura de request/response (DTOs)
* Validar autenticação via JWT

---

### 🔐 Autenticação no Swagger

Para acessar endpoints protegidos:

1. Realize o login via endpoint `/usuario/login`
2. Copie o token JWT retornado
3. Clique em **"Authorize"** no Swagger
4. Informe:
   Bearer {seu_token}

text

---

### 📌 Endpoints disponíveis (exemplos)

#### Usuário

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/usuario` | Criar usuário |
| POST | `/usuario/login` | Autenticação |
| GET | `/usuario` | Buscar usuário |
| PUT | `/usuario` | Atualizar dados |
| DELETE | `/usuario/{email}` | Remover usuário |

#### Tarefas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/tarefas` | Criar tarefa |
| GET | `/tarefas` | Listar tarefas |
| PUT | `/tarefas` | Atualizar tarefa |
| PATCH | `/tarefas` | Alterar status |
| DELETE | `/tarefas` | Remover tarefa |

> 💡 **Toda a estrutura de dados pode ser visualizada na seção Schemas dentro do Swagger.**

---

## 🔐 Fluxo de Autenticação

1. Usuário realiza cadastro
2. Usuário realiza login
3. API retorna um token JWT
4. O token deve ser enviado nas requisições:

```http
Authorization: Bearer {token}
Todos os serviços validam o token de forma independente (arquitetura stateless).

🔄 Fluxo Básico de Uso
Exemplo de uso do sistema:

Criar usuário

Realizar login

Receber token JWT

Criar tarefas autenticadas

Sistema pode acionar envio de notificação

🧪 Execução com Docker
▶️ Subir todo o ambiente
bash
cd bff-agendador
docker compose up --build
📦 Serviços iniciados
PostgreSQL

MongoDB

cadastrausuario

agendador-tarefas

notificacao

bff-agendador

💻 Execução Local (Sem Docker)
BFF
bash
cd bff-agendador
./mvnw spring-boot:run
Microserviços (Gradle)
bash
./gradlew bootRun
⚠️ Necessário:

PostgreSQL rodando

MongoDB rodando

📂 Estrutura do Projeto
text
/
├── README.md
├── bff-agendador/
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── pom.xml
│
├── cadastrausuario/
│   ├── Dockerfile
│   └── build.gradle
│
├── agendador-tarefas/
│   ├── Dockerfile
│   └── build.gradle
│
└── notificacao/
    ├── Dockerfile
    └── build.gradle
🧠 Decisões Arquiteturais
Banco de dados isolado por microserviço

Comunicação síncrona via OpenFeign

Autenticação distribuída com JWT

Containerização individual por serviço

Orquestração com Docker Compose

Uso de BFF para desacoplamento do frontend

📈 Evoluções Futuras
API Gateway dedicado

Service Discovery

Observabilidade e métricas

Pipeline CI/CD

Testes automatizados

👨‍💻 Autor
Jhonatan Dominick