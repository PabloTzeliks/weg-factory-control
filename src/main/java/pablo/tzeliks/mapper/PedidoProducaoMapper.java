package pablo.tzeliks.mapper;

import org.mapstruct.Mapper;
import pablo.tzeliks.dto.PedidoProducaoDTO;
import pablo.tzeliks.model.PedidoProducao;

@Mapper
public interface PedidoProducaoMapper {

    PedidoProducaoDTO toDTO(PedidoProducao entity);
    PedidoProducao toEntity(PedidoProducaoDTO dto);
}