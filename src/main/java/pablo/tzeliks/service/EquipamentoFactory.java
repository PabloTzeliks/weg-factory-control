package pablo.tzeliks.service;

import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.MotorEletrico;
import pablo.tzeliks.model.PainelControle;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.view.EquipamentoDTO;

public class EquipamentoFactory {

    public static Equipamento InstanceOf(EquipamentoDTO equipamentoDTO) {

        try {

            switch(equipamentoDTO.tipoEquipamento()) {

                case MOTOR_ELETRICO : {
                    return new MotorEletrico(equipamentoDTO.id(), equipamentoDTO.nome(), equipamentoDTO.codigo(), equipamentoDTO.quantidade(), equipamentoDTO.preco(), equipamentoDTO.tipoEquipamento(), equipamentoDTO.potencia());
                }

                case PAINEL_CONTROLE : {
                    return new PainelControle(equipamentoDTO.id(), equipamentoDTO.nome(), equipamentoDTO.codigo(), equipamentoDTO.quantidade(), equipamentoDTO.preco(), equipamentoDTO.tipoEquipamento(), equipamentoDTO.tensao());
                }

            }

        } catch (Exception e) {
            System.out.println("Erro! Houve um erro ao Criar o Equipamento. Observe: " + e.getMessage());
        }

        return null;
    }
}
