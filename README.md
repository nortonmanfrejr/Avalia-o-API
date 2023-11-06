# API Cadastro de Usúario com Documento
<hr>

## Descrição do Projeto

API para leitura, criação, atualização e remoção de beneficiários e seus documentos.

## Tecnologias

O projeto foi desenvolvido utilizando as seguintes tecnologias:

* Java 17
* Spring Boot (v3.1.5)
* Spring Data JPA
* Spring Test
* Lombok
* H2 (banco de dados)
* IntelliJ (IDE)

## Build

Para realizar o build do projeto, é necessário ter o Java 17 instalado, assim como o maven.

Na pasta do projeto, executar o seguinte comando:

    mvn package

Será gerado o jar **BeneficiaryRegister-0.0.1-SNAPSHOT.jar** na pasta _target_.

Para executar a aplicação, basta acessar a pasta _target_ no terminal e executar o comando

    java -jar BeneficiaryRegister-0.0.1-SNAPSHOT.jar

## Beneficiario JSON Object

### Endpoints

* Lista todos os Beneficiarios. GET: /beneficiario
* Retorna um Beneficiario através do ID. GET: /beneficiario/{id}
* Retorna todos os documentos através do ID do beneficiario. GET: /beneficiario/docs/{id}
* Passar um Objeto JSON de Beneficiario para API realizar operação. POST: /beneficiario
* Deleta um beneficiário através do ID, Obs: ao excluir um automáticamente seus documentos tambem são excluidos. DELETE: /beneficiario/{id}
* Altera alguns campos do beneficiario, Obs: Os campos que podem ser alterados através do End-Point são dataNascimento,nome e telefone. PUT: /beneficiario/{id}

```JSON
{
    "nome": "NOME BENEFICIARIO",
    "telefone": "NUMERO DE TELEFONE",
    "dataNascimento": "yyyy-mm-dd" 
}
```
## Documento JSON Object

### Endpoints

* Lista todos os documentos. GET: /documento
* Retorna um documento através do ID. GET: /documento/{id}
* Retorna todos os documentos através do ID do beneficiario. GET: /documento/beneficiario={id}
* Adiciona um documento para um benefeciario passando o seguinte end-point e um objeto JSON de documento. POST: /documento/beneficiario={id}
* Deleta um beneficiário através do ID, Obs: ao excluir um automáticamente seus documentos tambem são excluidos. DELETE: /documento/{id}

```JSON
{
    "tipoDocumento": "TIPO DOCUMENTO",
    "descricao": "DESCRICAO DO DOCUMENTO e/ou NUMERAÇÃO"
}
```

## Beneficiario + Documento JSON Object

* Adicionar um Beneficiario + Documentos juntos, passar o seguinte objeto JSON. POST: /beneficiario/docs
```JSON
{
    "beneficiario": {
        "nome": "NOME BENEFICIARIO",
        "telefone": "NUMERO DE TELEFONE",
        "dataNascimento": "yyyy-mm-dd"
    },
    "docs": [
        {
            "tipoDocumento": "TIPO DOCUMENTO",
            "descricao": "DESCRICAO DO DOCUMENTO e/ou NUMERAÇÃO"
        },
        {
            "tipoDocumento": "TIPO DOCUMENTO",
            "descricao": "DESCRICAO DO DOCUMENTO e/ou NUMERAÇÃO"
        },
    ...
    ]
}
```