# Projeto API de Parcelamento	

Este é o repositório do projeto de API de Parcelamento


## Resumo
Este projeto é uma aplicação Java que recebe uma entrada JSON contendo dados de um produto e uma forma de pagamento em parcelamento, e retorna uma lista contendo as informações da entrada (parcela 0) e as subsequentes parcelas.

## Modelo de Entrada

    {
      "produto": {
        "codigo": 123,
        "nome": "Nome do Produto",
        "valor": "10000.00"
    },
    "condicaoPagamento": {
        "valorEntrada": "2000.00",
        "qtdeParcelas": 10
    }
    }

## Instalação

Clone o projeto, abra-o com sua IDE de preferência (utilizei o Eclipse) e configure o maven para rodar os comandos abaixo (se já não estiverem configurados):

    clean verify install
Em seguida, execute o projeto. Em caso de utilização do maven por linha de comando, execute o comando acima com o mvn. 
## Gerando o Pacote e Executando
Caso esteja usando linha de comando, gere o pacote com 

    mvn package
E em seguida execute o jar gerado na pasta target. Caso esteja utilizando sua IDE, rode o projeto como uma aplicação Spring Boot.

## Chamando a API
A chamada foi testada com o aplicativo [Postman](https://www.postman.com/), porém qualquer outro aplicativo ou linha de comando servem para utilização
### POST /installments
O método de POST é o que envia a URL a ser convertida, e retorna uma URL encurtada. Basta enviar um objeto JSON no formato descrito anteriormente.
