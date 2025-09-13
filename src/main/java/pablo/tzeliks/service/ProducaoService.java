package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.dto.PedidoProducaoDTO;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.mapper.EquipamentoMapper;
import pablo.tzeliks.mapper.PedidoProducaoMapper;
import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.PedidoProducao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProducaoService {

    List<PedidoProducao> listaPedidoProducao = new ArrayList<>();
    private int proximoId = 1;

    private PedidoProducaoMapper producaoMapper;
    private final EquipamentoFactory factory;

    public ProducaoService(PedidoProducaoMapper producaoMapper, EquipamentoFactory factory) {
        this.producaoMapper = Objects.requireNonNull(producaoMapper);
        this.factory = Objects.requireNonNull(factory);
    }

    public ProducaoService() {
        this.producaoMapper = Mappers.getMapper(PedidoProducaoMapper.class);
        this.factory = new EquipamentoFactory();
    }

    public void criarPedidoProducao(PedidoProducaoDTO dto) {

        if (dto == null) throw new ServiceException("DTO nulo.");

        Equipamento prototipo = EquipamentoFactory.fromDTO(dto.equipamento());



        PedidoProducao pedidoProducao = producaoMapper.toEntity(dto);


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
