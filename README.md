# API de Produtos — Gelados

API REST para gerir os produtos de uma aplicação de gelados (gelados, iogurtes e gelados de mucua), feita com Spring Boot.

## Tecnologias

- Java 25
- Spring Boot 4.1.2 (Web MVC, Data JPA, Validation)
- Base de dados H2 (ficheiro local); PostgreSQL configurável
- Maven

## Como executar

Pré-requisito: JDK instalado (versão definida no `pom.xml`).

```bash
git clone https://github.com/<SEU_USUARIO>/<REPOSITORIO>.git
cd <REPOSITORIO>
./mvnw spring-boot:run
```

No Windows: `mvnw.cmd spring-boot:run`

A API fica disponível em `http://localhost:8080`.

## Modelo de dados

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | Long | Gerado automaticamente |
| `name` | String | Nome do produto (a fruta). Obrigatório |
| `type` | String | `gelado`, `iogurte` ou `gelado-mucua`. Obrigatório |
| `price` | Double | Preço, maior que zero. Obrigatório |
| `description` | String | Descrição livre (máx. 500 caracteres) |

## Endpoints

Base: `/api/produtos`

| Método | Rota | Descrição | Resposta |
|---|---|---|---|
| POST | `/api/produtos` | Cria um produto | 201 |
| GET | `/api/produtos` | Lista todos (filtro opcional `?type=iogurte`) | 200 |
| GET | `/api/produtos/{id}` | Busca um produto | 200 / 404 |
| PUT | `/api/produtos/{id}` | Substitui o produto (todos os campos) | 200 / 404 |
| PATCH | `/api/produtos/{id}` | Atualiza só os campos enviados | 200 / 404 |
| DELETE | `/api/produtos/{id}` | Apaga o produto | 204 / 404 |

### Exemplo: criar produto

```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{"name":"Manga","type":"gelado","price":500.00,"description":"Gelado de manga"}'
```

Resposta (`201 Created`):

```json
{
  "id": 1,
  "name": "Manga",
  "type": "gelado",
  "price": 500.0,
  "description": "Gelado de manga"
}
```

### Exemplo: atualizar só o preço

```bash
curl -X PATCH http://localhost:8080/api/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{"price":700.00}'
```

### Erros de validação (`400`)

```json
{ "type": "type deve ser: gelado, iogurte ou gelado-mucua" }
```

## Estrutura do projeto

```
src/main/java/<pacote>/
├── controller/   # endpoints HTTP
├── service/      # regras de negócio
├── repository/   # acesso à base de dados (JPA)
├── model/        # entidade Produto
├── dto/          # objetos de entrada (POST/PUT/PATCH)
└── exception/    # tratamento de erros
```

## Configuração da base de dados

Por padrão usa H2 em ficheiro (`./data/produtosdb`). Para PostgreSQL, altera o `application.properties` e define a senha por variável de ambiente (`DB_PASSWORD`).

## Autor

<JOCARSE 12> — <https://github.com/JCa12s>
