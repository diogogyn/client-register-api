# API Rest - Cadastro de usuarios

## Versão 1.1
- Inclusão da biblioteca de Spring Boot Admin para monitoramento da API
- Implementação de Ecache em memoria (ainda sem utilziar um serviço externo)
- Implementação da funcionalidade do Spring Actuator
- Implementação de perfis para deploy da aplicação
##### Funcionalidades

Esta é uma API Rest implementada em Java 17 com o framework Spring Boot, para fazer cadastro, listagem, edição e exclusão. Por se tratar de uma primeira versão, esta ainda conta com recursos limitados.

## Versão 1.0
##### Funcionalidades

- Cadastro, atualização e exclusão de usuários;
- Cadastro, atualização e exclusão de pacientes;
- Cadastro de usuários (sem perfis de acesso);
- Agendamento e cancelamento de consultas;
- API Docs para auxilio de desenvolvedores;

## Tech
Para implementar esta aplicação foram utilizados os seguntes recursos:
- [SpringBoot] - Java 17 + Spring Boot
- [SpringDoc] - Spring Doc Open Api
- [lombok] - Lombok
- [Maven] - Apache Maven
- [Spring actuator] Spring Actuator

## Deployment

Padrão
```bash
  java -jar client-register-api-0.0.1-SNAPSHOT.jar 
```
Em prod:

```bash
  java -Dspring.profiles.active=prod -jar client-register-api-0.0.1-SNAPSHOT.jar 
```

[maven]: <https://maven.apache.org/>
[lombok]: <https://projectlombok.org/>
[SpringDoc]: <https://springdoc.org/>
[SpringBoot]: <https://spring.io/projects/spring-boot>
[SpringBoot actuator]: <https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html>

[PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
[PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
[PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
[PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
[PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
[PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
