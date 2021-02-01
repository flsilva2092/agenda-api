# agenda-api

Uma API em Java e Spring Framework para gerenciamento de uma agenda de contatos.

## Como a API deve funcionar?

Nossa API deve criar, atualizar, deletar e listar contatos. Além disso, deve calcular estatísticas sobre as idades. A API terá os seguintes endpoints:

`POST/agenda-api/contato`: cria um contato. 

**Body:**

<code>
{
  "id": 1,
  "nome": "José Roberto",
  "tipo_telefone": "RESIDENCIAL",
  "telefone": "(11)2122-2324",
  "nascimento": "1970-09-11",
  "endereco": "Rua São Francisco, 501",
  "cidade": "Campinas",
  "estado": "SP",
  "CEP": "01000-100"
}
</code>

**Where:**

`id`: número único do contato;
`nome`: nome do contato no sistema.
`tipo_telefone`: se o telefone é RESIDENCIAL, do TRABALHO ou AMBOS;
`telefone`: telefone do contato;
`nascimento`: data de início da viagem no formato ISO 8601 YYYY-MM-DDThh:mm:ss.sssZ no timezone local.
`endereco`: endereço para correspondência.
`cidade`: cidade onde mora.
`estado`: estado onde mora.
`CEP`: CEP onde mora.

Deve retornar com body vazio com um dos códigos a seguir:

* 201: caso a viagem seja criada com sucesso.
* 400: caso o JSON seja inválido.
* 422: se qualquer um dos campos não for parseável ou se a data de nascimento for maior que a data atual.
* 500: erro no servidor (são raros)

`PUT/agenda-api/contato/{id}`: atualiza um contato.

**Body:**

<code>
{
  "nome": "José Roberto",
  "tipo_telefone": "RESIDENCIAL",
  "telefone": "(11)2133-2324",
  "nascimento": "1970-09-11"
}
</code>

Deve ser enviado o objeto que será modificado. O retorno deve ser o próprio objeto modificado.

<code>
{
  "id": 1,
  "nome": "José Roberto",
  "tipo_telefone": "RESIDENCIAL",
  "telefone": "(11)2133-2324",
  "nascimento": "1970-09-11",
  "endereco": "Rua São Francisco, 501",
  "cidade": "Campinas",
  "estado": "SP",
  "CEP": "01000-100"
}
</code>

A resposta deve conter os códigos a seguir:

* 200: em caso de sucesso.
* 400: caso o JSON seja inválido.
* 404: caso tentem atualizar um registro que não existe.
* 422: se qualquer um dos campos não for parseável (JSON mal formatado).

`GET/agenda-api/contato`: retorna todos os contatos criados.

Deve retornar uma lista de contatos.

<code>
{
   "id": 1,
  "nome": "José Roberto",
  "tipo_telefone": "RESIDENCIAL",
  "telefone": "(11)2133-2324",
  "nascimento": "1970-09-11",
  "endereco": "Rua São Francisco, 501",
  "cidade": "Campinas",
  "estado": "SP",
  "CEP": "01000-100"
},
{   
   "id": 2,
  "nome": "Roberto José",
  "tipo_telefone": "TRABALHO",
  "telefone": "(11)2324-2133",
  "nascimento": "1980-09-11",
  "endereco": "Rua São Januário, 501",
  "cidade": "Ribeirão Preto",
  "estado": "SP",
  "CEP": "01000-100"
}
</code>

A resposta deve conter os códigos a seguir:

* 200: caso exista contatos cadastrados
* 404: caso não exista contatos criados.

`DELETE/agenda-api/contato`: remove todos os contatos.

Deve aceitar uma requisição com body vazio e retornar 204.

`GET/agenda-api/estatistica`: retorna estatísticas básicas sobre as idades dos contatos.

<code>
{   
   "sum": "72.88",
   "avg": "31.44",
   "max": "350.0",
   "min": "22.88",
   "count": "2"
}
</code>

Em que:
`sum`: um BigDecimal especificando a soma total das idades dos contatos.
`avg`: um BigDecimal especificando a média das idades dos contatos criadas.
`max`: um BigDecimal especificando o maior valor dentre as idades dos contatos criadas.
`min`: um BigDecimal especificando o menor valor dentre as idades dos contatos criadas.
`count`: um long especificando o número total de contatos.

Todos os campos que são BigDecimal devem ter apenas duas casas decimais, por exemplo: 15.385 deve ser retornado como 15.39. 

### Testes

* Para executar o teste unitário, o comando executado deve ser:

```
mvn test
```

* Para executar todos os testes (incluindo o de integração), o comando executado deve ser:

```
mvn integration-test
```

### Execução

Para rodar a API via .jar:

```
java -jar agenda-api-1.0.1.jar --spring.profiles.active=dev
```
    
ou

```
mvn spring-boot:run -Dspring.profiles.active=dev
```

Por default, a API está disponível no endereço [http://localhost:8080/](http://localhost:8080/)
