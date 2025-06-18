
# 📘 Documentação da API - Sistema de Convênios

Esta API permite o gerenciamento de convênios, aditivos e repasses (lançamentos), com autenticação baseada em JWT. A documentação abaixo apresenta os principais endpoints, exemplos de uso e estruturas de dados envolvidas.

---

## 🔐 Autenticação

### POST `/auth/login`

Realiza login e retorna o token de autenticação (JWT).

**Request:**
```json
{
  "username": "admin",
  "password": "123456"
}
```

**Response:**
```json
{
  "token": "jwt.token.aqui"
}
```

---

## 🧾 Convênios

### GET `/api/convenios/listar`
Lista todos os convênios cadastrados.

---

### POST `/api/convenios`
Cria um novo convênio.

**Body:**
```json
{
  "numeroConvenio": "001/2025",
  "objeto": "Construção de escola",
  "numeroProcesso": "1234",
  "proponente": "Município X",
  "convenente": "Estado",
  "responsaveis": "João da Silva",
  "dataInicio": "2025-01-01",
  "dataFim": "2025-12-31",
  "valorTotal": 500000.0,
  "tipoDeConvenio": "Obra",
  "situacaoDescricao": "Em andamento"
}
```

---

### GET `/api/convenios/{convenioId}`
Busca um convênio específico por ID.

---

### PUT `/api/convenios/{convenioId}`
Atualiza os dados de um convênio.

---

### DELETE `/api/convenios/{convenioId}`
Exclui um convênio pelo ID.

---

### POST `/api/convenios/filtrar`
Filtra convênios por campos como proponente, número, etc.

**Body:**
```json
{
  "proponente": "Município X",
  "objeto": "Construção"
}
```

---

### GET `/api/convenios/{convenioId}/detalhado`
Retorna os detalhes completos de um convênio, incluindo aditivos e lançamentos.

---

## 🧩 Aditivos

### POST `/api/convenios/{convenioId}/aditivos`
Adiciona um novo aditivo a um convênio.

---

### GET `/api/convenios/{convenioId}/aditivos/listar-aditivos`
Lista todos os aditivos de um convênio.

---

### PUT `/api/convenios/{convenioId}/aditivos/{aditivoId}`
Atualiza um aditivo específico.

---

### DELETE `/api/convenios/{convenioId}/aditivos/{aditivoId}`
Remove um aditivo.

---

## 💸 Lançamentos

### POST `/api/convenios/{convenioId}/lancamentos`
Adiciona um repasse (lançamento) a um convênio.

---

### GET `/api/convenios/{convenioId}/lancamentos/listar-lancamentos`
Lista os lançamentos de um convênio.

---

### POST `/api/convenios/{convenioId}/aditivos/{aditivoId}/lancamentos`
Adiciona um repasse vinculado a um aditivo.

---

### GET `/api/convenios/{convenioId}/aditivos/{aditivoId}/lancamentos/listar-lancamentos-do-aditivo`
Lista lançamentos relacionados a um aditivo.

---

## 👥 Usuários

### GET `/api/usuario`
Lista todos os usuários do sistema.

---

### POST `/api/usuario`
Cria um novo usuário.

**Body:**
```json
{
  "nome": "João da Silva",
  "login": "joaos",
  "email": "joao@email.com",
  "senha": "123456",
  "role": "ADMIN"
}
```

---

## 🧾 Esquemas de Dados (Modelos)

### 📌 Convenio
Campos: `numeroConvenio`, `objeto`, `numeroProcesso`, `dataInicio`, `dataFim`, `valorTotal`, etc.

### 📌 Aditivo
Campos: `numeroAditivo`, `responsaveis`, `dataInicio`, `dataFim`, `valorTotalAditivo`, etc.

### 📌 Lancamento
Campos: `dataRepasse`, `exercicio`, `valorPago`, `convenioId`, `aditivoId` (opcional).

### 📌 Usuario
Campos: `nome`, `login`, `email`, `senha`, `role`.

---

## 📗 Documentação Swagger

![Tela da documentação swagger](/src/main/resources/static/api-swagger.png)


---
## 👨‍💻 Autor

Desenvolvido por **Gabriel Jaune Ribera**.
