package pablo.tzeliks.dto;

import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.domain.CodigoProducao;
import pablo.tzeliks.model.enums.StatusProducao;

import java.util.Map;

public record PedidoProducaoDTO(int id, Equipamento equipamento, int quantidadeProduzir, Map<CodigoProducao, StatusProducao> itensProducao) {
}
