package pablo.tzeliks.view.menus;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;
import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.dto.EquipamentoDTO;

import java.util.Scanner;

public class CadastroView {

    public static void executar(Scanner scanner, EstoqueService service) {
        MenuHelper.imprimirMenuCadastro();

        String nome = InputHelper.lerString(scanner, "Nome do equipamento: ");

        Codigo codigo = null;
        while (codigo == null) {
            String codigoRaw = InputHelper.lerString(scanner, "Código do equipamento ( ex: AAA-0001 ): ");
            try {
                codigo = new Codigo(codigoRaw);
            } catch (Exception e) {
                MessageHelper.erro("Código inválido: " + e.getMessage() + ". Tente novamente.");
            }
        }

        int quantidade;
        while (true) {
            quantidade = InputHelper.lerInteiro(scanner, "Quantidade: ");
            if (quantidade < 0) {
                MessageHelper.erro("Quantidade não pode ser negativa.");
            } else {
                break;
            }
        }

        double preco;
        while (true) {
            preco = InputHelper.lerDouble(scanner, "Preço: ");
            if (preco <= 0) {
                MessageHelper.erro("Preço deve ser maior que zero.");
            } else {
                break;
            }
        }

        TipoEquipamento tipo = InputHelper.escolherEnum(scanner, "Escolha o tipo do equipamento:");
        if (tipo == null) {
            MessageHelper.info("O tipo do equipamento deve ser informado.");
            return;
        }

        // Campos específicos por Subclasse
        Double potencia = null;
        Integer tensao = null;

        if (tipo == TipoEquipamento.MOTOR_ELETRICO) {
            potencia = InputHelper.lerDouble(scanner, "Potência (kW): ");
        }

        // Enviar Dados como DTO
        EquipamentoDTO dto;
        try {

            if (tipo == TipoEquipamento.MOTOR_ELETRICO && potencia == null) {
                MessageHelper.erro("Potência é obrigatória para motor. Cadastro cancelado.");
                return;
            }

            // QUICKFIX: converter wrappers para primitivos antes de chamar o construtor que espera primitivos
            double potenciaVal = (potencia != null) ? potencia.doubleValue() : 0.0;

            dto = new EquipamentoDTO(
                    0, // Para o sistema definir o ID
                    nome,
                    codigo,
                    quantidade,
                    preco,
                    tipo,
                    potenciaVal
            );
        } catch (Exception e) {
            MessageHelper.erro("Erro ao construir EquipamentoDTO. Verifique a assinatura do construtor/record. Detalhe: " + e.getMessage());
            return;
        }

        // Chamar o Service
        try {
            service.cadastrarEquipamento(dto);
            MessageHelper.sucesso("Equipamento cadastrado com sucesso!");
        } catch (Exception e) {
            MessageHelper.erro("Falha ao cadastrar equipamento: " + e.getMessage());
        }
    }
}