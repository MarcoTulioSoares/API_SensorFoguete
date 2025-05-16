# 🔧 Projeto VLA – Aquisição e Armazenamento de Dados de Sensor

Este projeto consiste em uma **API desenvolvida com Spring Boot** responsável por coletar e armazenar dados de sensores via requisições HTTP, juntamente com um **servidor Python** que simula um dispositivo físico (como um ESP) enviando dados a cada 100ms em formato JSON.

---



## 🚀 Backend - Spring Boot

### Funcionalidade

- Coleta dados de sensores simulados a partir do endpoint `http://localhost:8080/json`
- Executa requisições agendadas a cada 100ms (ou configurável via `@Scheduled`)
- Armazena os dados em banco de dados utilizando Spring Data JPA

### Requisitos

- Java 17+
- Spring Boot 3.x
- Banco de dados (PostgreSQL, H2, etc.)
- Lombok
- Maven

### Inicialização

Executar o "DemoApplication" no Intellij

## 🤖 Simulador de Sensor - Python
Descrição
Servidor Python que simula um dispositivo embarcado, retornando dados aleatórios via JSON para cada requisição GET no endpoint /json.
O servidor estará disponível em http://localhost:8080/json.

Requisitos
Python 3.x

