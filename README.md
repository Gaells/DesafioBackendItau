# 💼 Desafio Backend - Itaú Unibanco

Este projeto consiste em uma API REST desenvolvida com **Kotlin + Spring Boot**, criada para o **Desafio de Programação do Itaú Unibanco**. A API permite o recebimento de transações e o cálculo de estatísticas em tempo real.

---
## 👨‍💻 Autor
Desenvolvido por Gabriel Chioquetta para o desafio técnico do Itaú.
---
## Repo do Desafio https://github.com/feltex/desafio-itau-backend
---

## 🚀 Tecnologias Utilizadas

- Kotlin 1.9.25
- Spring Boot 3.2.5
- Spring Web
- Spring Validation
- Springdoc OpenAPI (Swagger)
- JUnit 5 + Mockito Kotlin

---

## 📦 Como executar

### Pré-requisitos

- JDK 17+
- Gradle 8+

### Rodando o projeto

```bash
./gradlew bootRun
```
1. A aplicação estará disponível em: http://localhost:8080

---

## 📦 Endpoints da API

| Método | Rota         | Descrição                                 |
|--------|--------------|-------------------------------------------|
| POST   | `/transacao` | Recebe uma nova transação válida          |
| DELETE | `/transacao` | Remove todas as transações registradas    |
| GET    | `/estatistica` | Retorna estatísticas dos últimos 60s    |

---

## 📥 Exemplo de Requisição

### `POST /transacao`

```json
{
  "value": 100.50,
  "dateTime": "2025-04-30T11:00:00.000-03:00"
}
```
### GET /estatistica - Resposta esperada

```
{
  "count": 1,
  "sum": 100.5,
  "avg": 100.5,
  "min": 100.5,
  "max": 100.5
}
```
---

## 📊 Documentação Swagger

1.Inicie o projeto.
2.Acesse: http://localhost:8080/swagger-ui.html ou http://localhost:8080/v3/api-docs

---

## ✅ Regras de Negócio
1.O campo value não pode ser negativo.

2.O campo dateTime deve ser no passado (transações no futuro são rejeitadas).

3.Apenas transações dos últimos 60 segundos são consideradas para estatísticas.

4.Os dados são armazenados em memória (sem banco de dados).

---

## 🧪 Executar os Testes

```bash
./gradlew test
```
Os testes cobrem:

1.Validação de transações

2.Estatísticas

3.Respostas HTTP

4.Controller REST com MockMvc