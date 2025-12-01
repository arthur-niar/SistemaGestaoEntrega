# Frontend mínimo

Arquivos estáticos mínimos para interagir com os serviços via Gateway (somente POSTs simples).

Como usar

1. Certifique-se de que o `eureka-server`, o `gateway-server` e os microservices (usuario, cliente, entregador, pedido) estejam em execução e registrados no Eureka. Por padrão o gateway está em http://localhost:8080.
2. Abra `frontend/index.html` no navegador (por exemplo arraste o arquivo para o navegador) ou sirva os arquivos estáticos com um servidor simples.
3. Ajuste o campo "Gateway base URL" se o gateway estiver em outra porta ou host.
4. Use os botões para abrir modais e enviar requisições POST:

- `Criar Usuário` -> POST /api/usuarios/cadastrar
- `Criar Cliente` -> POST /api/clientes/cadastrar (campo usuarioId obrigatório)
- `Criar Entregador` -> POST /api/entregadores/cadastrar (campo usuarioId obrigatório)
- `Criar Pedido (manual)` -> POST /api/pedidos/adicionar (enviar descricao e taxaEntrega)
- `Criar Pedido (factory/strategy)` -> POST /api/pedidos/adicionar/{tipo} com tipo `rapida` ou `agendada` (não envia body)

Notas

- Este frontend é propositalmente simples e não trata autenticação.
- Os campos `id` são opcionais: se você quiser criar um cliente/entregador atrelado a um usuário específico, crie primeiro o usuário e então use o id retornado no campo `usuarioId`.
