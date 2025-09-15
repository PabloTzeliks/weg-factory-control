package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.dto.PedidoProducaoDTO;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.mapper.EquipamentoMapper;
import pablo.tzeliks.mapper.PedidoProducaoMapper;
import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.PedidoProducao;
import pablo.tzeliks.model.domain.Codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProducaoService {

    List<PedidoProducao> listaPedidoProducao = new ArrayList<>();
    private int proximoId = 1;

    private PedidoProducaoMapper producaoMapper;
    private EquipamentoMapper equipamentoMapper;
    private final EstoqueService estoqueService;

    public ProducaoService(PedidoProducaoMapper producaoMapper, EquipamentoMapper equipamentoMapper, EstoqueService estoqueService) {
        this.producaoMapper = Objects.requireNonNull(producaoMapper);
        this.equipamentoMapper = Objects.requireNonNull(equipamentoMapper);
        this.estoqueService = Objects.requireNonNull(estoqueService);
    }

    public void criarPedidoProducao(int id, Codigo codigo, int quantidadeProduzir) {

        if (codigo == null) throw new ServiceException("Código nulo.");

        Equipamento prototipo = estoqueService.acharPorCodigoEntidade(codigo);

        if (prototipo == null) {
            throw new ServiceException("Prototipo com código " + codigo + " não encontrado no estoque.");
        }

        PedidoProducao pedidoProducao = new PedidoProducao(id, prototipo, quantidadeProduzir);

        validarPedidoProducao(pedidoProducao);

        // Criação de ID quando necessário para os Equipamentos
        if (pedidoProducao.getIdPedido() <= 0) {
            pedidoProducao.setIdPedido(proximoId++);
        } else if (pedidoProducao.getIdPedido() >= proximoId) {
            proximoId = pedidoProducao.getIdPedido() + 1;
        }

        // Validações adicionais
        if (acharPorIdEntidade(pedidoProducao.getIdPedido()) != null) {
            throw new ServiceException("Já existe um Pedido de Produção com o mesmo id: " + pedidoProducao.getIdPedido());
        }

        listaPedidoProducao.add(pedidoProducao);

    }

    private PedidoProducao acharPorIdEntidade(int id) {
        return listaPedidoProducao.stream().filter(e -> e.getIdPedido() == id).findFirst().orElse(null);
    }

    private void validarPedidoProducao(PedidoProducao pedidoProducao) {
        if (pedidoProducao == null) throw new ServiceException("Pedido de Produção nulo.");
        if (pedidoProducao.getPrototipo() == null) throw new ServiceException("Sem Prototipo anexado.");
        if (pedidoProducao.getQuantidadeAProduzir() <= 0) { throw new ServiceException("Sem quantidade de a Produzir anexada."); }
    }

}
