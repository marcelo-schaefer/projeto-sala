# Getting Started

mongodb://localhost:27020/prova
POST localhost:8080/pessoas -> endpoint para cadastro de pessoas
request {"nome": "marcelo", "sobrenome": "schaefer"}
response {id: 123, "nome": "marcelo", "sobrenome": "schaefer"}
GET localhost:8080/pessoas/id -> endpoint para buscar os dados das pessoas
request {id: 123}
response {id: 123, "nome": "marcelo", "sobrenome": "schaefer"}
GET localhost: 8080/pessoas -> endpoint para buscar dados de todas pessoas
response {id: 123, "nome": "marcelo", "sobrenome": "schaefer"}

POST localhost:8080/sala -> endpoint para criar uma sala 
request {"nome": "sala 1", "lotação": 10, "tipo": evento}
response {id: 1, "nome": "sala 1", "lotação": 10, "tipo": evento}
GET localhost:8080/salas/id -> endpoint para buscar os dados das pessoas
request {id: 1}
response {id: 1, "nome": "sala 1", "lotação": 10, "tipo": evento}
GET localhost:8080/salas/tipo -> endpoint para buscar os dados das pessoas
request {tipo: evento}
response {id: 1, "nome": "sala 1", "lotação": 10, "tipo": evento}

POST localhost:8080/organizador -> endpoint para organizar as pessoas nas salas
response {sala: "sala1", pessoa: "marcelo"}

