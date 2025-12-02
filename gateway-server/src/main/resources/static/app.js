document.addEventListener("DOMContentLoaded", () => {
  function $(id) {
    return document.getElementById(id);
  }

  const output = $("output");

  function show(msg) {
    output.textContent =
      typeof msg === "string" ? msg : JSON.stringify(msg, null, 2);
  }

  function getBase() {
    const url = $("gatewayUrl").value.trim();
    if (url) {
      return url.replace(/\/$/, "");
    }
    return "http://localhost:8080";
  }

  function openModal(id) {
    $(id).style.display = "block";
  }

  function closeModal(el) {
    el.style.display = "none";
  }

  document.addEventListener("click", (e) => {
    if (e.target.classList.contains("close")) {
      const m = e.target.closest(".modal");
      if (m) closeModal(m);
    }
  });

  // AQUI OS LISTENERS AGORA FUNCIONAM
  $("openUsuario").addEventListener("click", () => openModal("modalUsuario"));
  $("openCliente").addEventListener("click", () => openModal("modalCliente"));
  $("openEntregador").addEventListener("click", () =>
    openModal("modalEntregador")
  );
  $("openPedido").addEventListener("click", () => openModal("modalPedido"));
  $("openPedidoTipo").addEventListener("click", () =>
    openModal("modalPedidoTipo")
  );

  // ESTES DOIS NÃO FUNCIONAVAM — AGORA FUNCIONAM
  $("openShipment").addEventListener("click", () => openModal("modalShipment"));
  $("openConsultarShipment").addEventListener("click", () =>
    openModal("modalConsultarShipment")
  );

  async function postJson(url, body) {
    try {
      const res = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: body ? JSON.stringify(body) : undefined,
      });
      const text = await res.text();
      let parsed;
      try {
        parsed = JSON.parse(text);
      } catch (e) {
        parsed = text;
      }
      show({ status: res.status, body: parsed });
      return { status: res.status, body: parsed };
    } catch (err) {
      show("Erro: " + err);
      throw err;
    }
  }

  $("submitUsuario").addEventListener("click", async () => {
    const usuario = {
      id: $("usuario_id").value || undefined,
      nome: $("usuario_nome").value || undefined,
      email: $("usuario_email").value || undefined,
      senha: $("usuario_senha").value || undefined,
      tipo: $("usuario_tipo").value || undefined,
    };
    const url = getBase() + "/api/usuarios/cadastrar";
    await postJson(url, usuario);
    closeModal($("modalUsuario"));
  });

  $("submitCliente").addEventListener("click", async () => {
    const cliente = {
      id: $("cliente_id").value || undefined,
      usuarioId: $("cliente_usuarioId").value || undefined,
      nome: $("cliente_nome").value || undefined,
      email: $("cliente_email").value || undefined,
      telefone: $("cliente_telefone").value || undefined,
      endereco: $("cliente_endereco").value || undefined,
    };
    if (!cliente.usuarioId) {
      show("usuarioId é obrigatório para cliente");
      return;
    }
    const url = getBase() + "/api/clientes/cadastrar";
    await postJson(url, cliente);
    closeModal($("modalCliente"));
  });

  $("submitEntregador").addEventListener("click", async () => {
    const entregador = {
      id: $("entregador_id").value || undefined,
      usuarioId: $("entregador_usuarioId").value || undefined,
      pedidoId: undefined,
      nome: $("entregador_nome").value || undefined,
      email: $("entregador_email").value || undefined,
      telefone: $("entregador_telefone").value || undefined,
      CNH: $("entregador_CNH").value || undefined,
      veiculo: $("entregador_veiculo").value || undefined,
    };
    if (!entregador.usuarioId) {
      show("usuarioId é obrigatório para entregador");
      return;
    }
    const url = getBase() + "/api/entregadores/cadastrar";
    await postJson(url, entregador);
    closeModal($("modalEntregador"));
  });

  $("submitPedido").addEventListener("click", async () => {
    const pedido = {
      id: $("pedido_id").value || undefined,
      descricao: $("pedido_descricao").value || undefined,
      taxaEntrega: parseFloat($("pedido_taxa").value || 0),
    };
    const url = getBase() + "/api/pedidos/adicionar";
    await postJson(url, pedido);
    closeModal($("modalPedido"));
  });

  $("submitPedidoTipo").addEventListener("click", async () => {
    const tipo = $("pedido_tipo").value;
    const url =
      getBase() + "/api/pedidos/adicionar/" + encodeURIComponent(tipo);
    await postJson(url, null);
    closeModal($("modalPedidoTipo"));
  });

  $("submitShipment").addEventListener("click", async () => {
    const pedidoId = $("shipment_pedidoId").value;
    if (!pedidoId) {
      show("Pedido ID é obrigatório");
      return;
    }
    const url = getBase() + "/tms/enviar/" + encodeURIComponent(pedidoId);
    await postJson(url, null);
    closeModal($("modalShipment"));
  });

  $("submitConsultarShipment").addEventListener("click", async () => {
    const shipmentId = $("consultar_shipmentId").value;
    if (!shipmentId) {
      show("Shipment ID é obrigatório");
      return;
    }
    try {
      const res = await fetch(
        getBase() + "/shipments/" + encodeURIComponent(shipmentId)
      );
      const text = await res.text();
      let parsed;
      try {
        parsed = JSON.parse(text);
      } catch (e) {
        parsed = text;
      }
      show({ status: res.status, body: parsed });
    } catch (err) {
      show("Erro: " + err);
    }
    closeModal($("modalConsultarShipment"));
  });

  document.querySelectorAll(".modal").forEach((m) => {
    m.addEventListener("click", (e) => {
      if (e.target === m) closeModal(m);
    });
  });
});
