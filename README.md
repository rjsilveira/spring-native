Spring Native
===

Projeto base utilizado na apresentação do fórum técnico no PagSeguro.

---

Dependências:
* Docker
* Docker-compose
* SDKMAN! (Não esqueça de mudar o `auto-env` para true no arquivo `sdkman_auto_env`)
  * Para rodar na JVM padrão, tenha instalado a versão `11.0.10-open`
  * Para rodar na Imagem Nativa, tenha instalado a versão `21.3.0.r11-grl`
  * Para imagem nativa, tbm será necessário realizar a seguinte instalação: `gu install native-image` (rode o comando quando estive usando a versão do Java para imagem nativa)
  * Tenha certeza que todos os prequisitos [aqui](https://www.graalvm.org/reference-manual/native-image/#prerequisites) listado estão sendo atendidos:

---

Stack:

* Kotlin
* Spring Boot 
* Spring Data JPA
* MySQL

---

O projeto possui dois módulos:
* jvm
* native

Os dois possuem o mesmo comportamento, ambos sendo uma API de controle de usuários, sendo permitido:

Criar um usuário:
```shell
curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Nome aqui",
    "age": 18
}'
```

Deletar um usuário:
```shell
curl --location --request DELETE 'http://localhost:8080/users/{id}'
```

Recuperar um usuário pelo id:
```shell
curl --location --request GET 'http://localhost:8080/users/{id}'
```

**Atenção! A porta muda dependendo do módulo**:
* JVM: `8080`
* Imagem Nativa: `8081`

---

## Rodando a aplicação:

O primeiro passo é subir as bases usadas pelos dois módulos:
```shell
docker-compose up -d
```

### Rodando a versão na JVM 

Para gerar o arquivo .jar da aplicação, você deve:

Ir para o diretório do projeto:
```shell
cd jvm
```

Buildar o projeto:
```shell
./gradlew clean build
```

Rodar o arquivo .jar para iniciar a aplicação:
```shell
java -jar build/libs/jvm-0.0.1-SNAPSHOT.jar
```

Para rodar a aplicação de forma dockerizada, rodar o seguinte comando:
```shell
docker build -t jvm .
docker run --rm --network host jvm:latest
```

### Rodando a versão usando Imagem Nativa

Para gerar o binário da aplicação, voce deve rodar:
```shell
./gradlew nativeCompile
```

e para rodar a aplicação:
```shell
./build/native/nativeCompile/native
```

Para rodar a aplicação de forma dockerizada, rodar o seguinte comando:
```shell
./gradlew bootBuildImage
docker run --rm --network host native:0.0.1
```
