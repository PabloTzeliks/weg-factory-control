package pablo.tzeliks.dto;

import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.domain.CodigoProducao;
import pablo.tzeliks.model.enums.StatusProducao;

import java.util.Map;

public record PedidoProducaoDTO(int id, Equipamento prototipo, int quantidadeProduzir) {
}
