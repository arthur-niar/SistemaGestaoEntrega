# SistemaGestaoEntrega

## 📘 Descrição

Este projeto foi desenvolvido para a disciplina Proj. Arq. de Software, ministrada pelo professor Francisco Estevão. O objetivo é criar um sistema de gestão de entregas utilizando uma arquitetura baseada em microserviços.

## 🛠️ Tecnologias Utilizadas

### 1. **Java com Spring Boot**

O projeto é desenvolvido em Java, utilizando o framework Spring Boot para facilitar a criação de microserviços independentes e escaláveis. O SpringBoot proporciona uma configuração mínima, permitindo que a equipe se concentrem na lógica de negócios.

### 2. **Arquitetura de Microserviços**

A aplicação é estruturada em microserviços, cada um responsável por uma funcionalidade específica do sistema. Essa abordagem permite maior flexibilidade e escalabilidade, além de facilitar a manutenção e evolução do sistema.

### 3. **Eureka (Service Discovery)**

O Eureka é utilizado como servidor de descoberta de serviços. Ele permite que os microserviços se registrem e descubram uns aos outros de forma dinâmica, facilitando a comunicação entre eles sem a necessidade de configurações estáticas de rede.

### 4. **MongoDB**

O MongoDB é um banco de dados NoSQL utilizado para armazenar os dados do sistema. Sua estrutura flexível permite armazenar informações de forma eficiente, adaptando-se às necessidades do sistema de gestão de entregas.

## 🚀 Como Executar o Projeto

1. **Clonar o Repositório**

   ```
   bash
   git clone https://github.com/arthur-niar/SistemaGestaoEntrega.git
   cd SistemaGestaoEntrega
   ```

2. **Iniciar o Eureka Server**

   Navegue até o diretório "eureka-server" e execute:

   ```
   bash
   ./mvnw spring-boot:run
   ```

   O Eureka Server estará disponível no seu localhost na porta 8761 por padrão.  
   (pode ser alterado acessando eureka-server/src/main/resources/application.properties)

4. **Iniciar os Microserviços**

   Para cada microserviço (por exemplo, "usuario-service", "pedido-service", etc.), navegue até o diretório correspondente e execute:

   ```
   bash
   ./mvnw spring-boot:run
   ```

   Cada microserviço será registrado automaticamente no Eureka Server.

5. **Iniciar o Gateway**

   O Gateway atua como ponto de entrada para os microserviços. Navegue até o diretório "gateway-server" e execute:

   ```
   bash
   ./mvnw spring-boot:run
   ```

   O Gateway estará disponível no seu localhost, na porta 8080 por padrão.  
   (pode ser alterado acessando gateway-server/src/main/resources/application.properties)

7. **Acessar a Aplicação**

   Com todos os serviços em execução, você pode acessar a aplicação através do Gateway. As requisições serão roteadas para os microserviços correspondentes.

## 📂 Estrutura do Repositório

- "eureka-server/": Contém o servidor Eureka.
- "gateway-server/": Contém o API Gateway.
- "usuario-service/": Microserviço responsável pela gestão de usuários.
- "pedido-service/": Microserviço responsável pela gestão de pedidos.
- "entregador-service/": Microserviço responsável pela gestão de entregadores.
- "cliente-service/": Microserviço responsável pela gestão de clientes.

## ☕ Equipe

- Arthur Wagner de Carvalho Gondim Lemos
- Carlos Dirceu Rios Rodrigues Neto
- João Lucas Lobo Pinto Barboza
- Renê Medeiros Montenegro
- Rhyan da Rocha Ferreira

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
