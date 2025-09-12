package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.mapper.EquipamentoMapper;
import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.dto.EquipamentoDTO;

public class EquipamentoFactory {

    private static final EquipamentoMapper mapper = Mappers.getMapper(EquipamentoMapper.class);

    public static Equipamento fromDTO(EquipamentoDTO dto) {

        try {
            switch(dto.tipoEquipamento()) {

                case MOTOR_ELETRICO : {
                    return mapper.toMotorEletrico(dto);
                }

            }

        } catch (Exception e) {
            System.out.println("Erro! Houve um erro ao Criar o Equipamento. Observe: " + e.getMessage());
        }

        return null;
    }
}