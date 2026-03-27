# 🔵 BFF Agendador de Tarefas

Backend for Frontend responsável por orquestrar os microserviços do sistema de agendamento de tarefas.

---

## 🧱 Arquitetura

Este projeto faz parte de uma arquitetura de microserviços composta por:

- 🟣 **cadastrausuario** → gerenciamento de usuários (PostgreSQL)
- 🟢 **agendador-tarefas** → gerenciamento de tarefas (MongoDB)
- 🟡 **notificacao** → envio de notificações
- 🔵 **bff-agendador** → gateway de comunicação (este projeto)

---

## 🔗 Comunicação

O BFF se comunica com os serviços via REST utilizando **OpenFeign**.

---

## ⚙️ Tecnologias

- Java 21
- Spring Boot
- Spring Cloud OpenFeign
- Maven
- Docker
- Swagger (OpenAPI)

---

## 🚀 Como executar

### 🔥 Subir todos os serviços

```bash
docker-compose up --build