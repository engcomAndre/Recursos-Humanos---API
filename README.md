# Desafio
## Objetivo

>O RH da empresa solicitou a equipe de Sistemas uma aplicação para gerenciar informações
>dos colaboradores da empresa. Seu objetivo é desenvolver uma API Rest para que a equipe
>de front-end realize as consultas nos endpoints e retorne os Recursos solicitados pelo RH.

##### Fase 01 - Projeto:
 -  Criar o projeto Spring Boot.
 - Configurar o banco de dados: H2
 -  O projeto deve conter duas entidades pessoas e setor.
 -  O context-path da aplicação de ser /api
##### Fase 03 - Recursos
 -  Cadastrar Pessoas / Cadastrar Setor
 -  Atualizar Pessoa / Atualizar Setor
 -  Deletar Pessoas / Deletar Setor
 -  Listar Pessoas / Listar Setor
 -  Listar Pessoas por Setor
##### Fase 05 - Testes
 -  Implemente classes de teste para no minimo 5 rotas;
##### Fase 06 - Segurança
 -  Configure uma Autenticação para a api
##### Fase 07 - Documentação
 -  Documente a API usando Swagger
##### Fase 08 - Deploy
 -  Gere uma imagem docker e envie para o https://hub.docker.com/
##### Fase 09 - Bonus
  - Consumir a api MockApi
 https://5e61af346f5c7900149bc5b3.mockapi.io/desafio03/employer
 e atualizar as 
informaçōes do banco de dados

## Tecnologias
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* [Maven](https://maven.apache.org/guides/index.html)
* [Docker](https://docs.docker.com/)

### Rodando a aplicação com docker em Mint
Esta aplicação esta armazenada em [Dockerhub](https://hub.docker.com/)
##### Faça o build da aplicação com o comando:
```
sudo clean compile package
```
##### Faça o build com o docker com o seguinte comando:
```
sudo docker build -t rh:0.0.1 .
```
##### Rode a aplicação com o comando:
```
sudo docker run -d -p 8080:8080 -t rh:0.0.1
```
##### Verifique se a aplicação esta rodando:
```
sudo docker ps
```
##### Veja a página da Documentação da API:
http://localhost:8080/api/swagger-ui.html

### Docker Hub
Subindo a aplicação publicada no DockerHub

##### Faça login com o comando :
```
sudo docker login -u={USUARIO_DOCKER_HUB} -p={SENHA}
```
##### Rodando a imagem no Dockerhub :
```
sudo docker run -d -p 8080:8080 -t sgavsnake/rh:0.0.1
```
