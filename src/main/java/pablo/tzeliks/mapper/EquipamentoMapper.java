package pablo.tzeliks.mapper;

import org.mapstruct.Mapper;
import pablo.tzeliks.model.MotorEletrico;
import pablo.tzeliks.dto.EquipamentoDTO;

@Mapper
public interface EquipamentoMapper {

    MotorEletrico toMotorEletrico(EquipamentoDTO dto);
    EquipamentoDTO toDTO(MotorEletrico entity);
}