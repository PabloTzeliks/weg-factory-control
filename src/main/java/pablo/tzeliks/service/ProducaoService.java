package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.dto.PedidoProducaoDTO;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.mapper.PedidoProducaoMapper;
import pablo.tzeliks.model.PedidoProducao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProducaoService {

    List<PedidoProducao> listaPedidoProducao = new ArrayList<>();
    private int proximoId = 1;

    private PedidoProducaoMapper mapper = new PedidoProducaoMapper;

    public ProducaoService(PedidoProducaoMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper);
    }

    public ProducaoService() {
        this.mapper = Mappers.getMapper(PedidoProducaoMapper.class);
    }

    public PedidoProducao criarPedidoProducao(PedidoProducaoDTO dto) {

        if (dto == null) throw new ServiceException("DTO nulo.");

        PedidoProducao pedidoProducao = mapper.toEntity(dto);
        if (pedidoProducao == null) {
            throw new ServiceException("Erro ao criar o Pedido de Produção a partir do DTO.");
        }

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
