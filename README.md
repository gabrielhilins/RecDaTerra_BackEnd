# RecDaterra - Back-end

## Descrição
O **RecDaterra** é uma plataforma digital inovadora que conecta pequenos empreendedores locais do Recife a consumidores conscientes, promovendo a produção sustentável e a economia local. Este repositório contém o código do back-end da plataforma, desenvolvido com **Java** e **Spring Boot**, fornecendo uma API REST para o gerenciamento de produtos, pedidos e usuários.

## Tecnologias Utilizadas
- **Java**: Linguagem principal do back-end.
- **Spring Boot**: Framework para criar microserviços robustos.
  - **Spring MVC**: Segue o padrão Model-View-Controller.
  - **Spring Data JPA**: Gerenciamento de persistência de dados.
  - **Spring Security**: Implementação de autenticação e autorização.
- **MariaDB**: Banco de dados utilizado para armazenamento, hospedado localmente.
- **JWT (JSON Web Token)**: Para autenticação de usuários.
- **Maven**: Gerenciamento de dependências.
- **Swagger**: Documentação interativa da API.
- **Postman**: Testes e documentação dos endpoints (veja [aqui](https://documenter.getpostman.com/view/34995005/2sA3Qy79Ud)).

## Arquitetura
O projeto segue o padrão **MVC (Model-View-Controller)** e inclui:

- **Model**: Representa as entidades e suas interações com o banco de dados.
  
- **Controller**: Responsável por manipular as requisições HTTP, como a criação, atualização e remoção de produtos, pedidos e usuários.

- **Service**: Implementa a lógica de negócios. O Service Layer separa as regras da aplicação da camada de controle.

- **Config**: Implementação do **CORS (Cross-Origin Resource Sharing)** para permitir comunicação segura entre front-end e back-end. Exemplo:
  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
              .allowedOrigins("http://localhost:3000")
              .allowedMethods("GET", "POST", "PUT", "DELETE")
              .allowedHeaders("*");
      }
  }

## Desenvolvedores
- Arthur Vinícius
- Cecília Sitcovsky
- Gabriel Henrique
- Matheus Vinícius
- Yuri Catunda
