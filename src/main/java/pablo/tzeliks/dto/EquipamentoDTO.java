package pablo.tzeliks.dto;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;

public record EquipamentoDTO(int id, String nome, Codigo codigo, int quantidade, double preco, TipoEquipamento tipoEquipamento, double potencia) {
}
