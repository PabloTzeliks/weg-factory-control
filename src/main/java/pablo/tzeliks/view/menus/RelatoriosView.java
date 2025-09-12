package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.model.enums.TipoEquipamento;
import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.view.helpers.EquipamentoPrinter;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.List;
import java.util.Scanner;

public class RelatoriosView {

    public static void executar(Scanner scanner, EstoqueService service) {
        boolean sair = false;
        while (!sair) {
            MenuHelper.imprimirMenuRelatorios();
            int opcao = InputHelper.lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 0:
                    sair = true;
                    break;

                case 1:
                    try {
                        int total = service.getQuantidadeTotalEstoque();
                        MessageHelper.info("Quantidade total de equipamentos em estoque: " + total);
                    } catch (Exception e) {
                        MessageHelper.erro("Falha ao obter quantidade total: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        EquipamentoDTO maisCaro = service.getEquipamentoMaiorPreco();
                        MessageHelper.info("Equipamento com maior preço:");
                        EquipamentoPrinter.imprimirEquipamento(maisCaro);
                    } catch (Exception e) {
                        MessageHelper.erro("Falha ao obter equipamento de maior preço: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        EquipamentoDTO maisQuantidade = service.getEquipamentoMaiorQuantidade();
                        MessageHelper.info("Equipamento com maior quantidade:");
                        EquipamentoPrinter.imprimirEquipamento(maisQuantidade);
                    } catch (Exception e) {
                        MessageHelper.erro("Falha ao obter equipamento com maior quantidade: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        final int LIMITE_BAIXO = 5; // Requisito: quantidade < 5
                        List<EquipamentoDTO> criticos = service.listarEstoqueBaixo(LIMITE_BAIXO);
                        if (criticos == null || criticos.isEmpty()) {
                            MessageHelper.info("Nenhum equipamento com estoque abaixo de " + LIMITE_BAIXO + ".");
                        } else {
                            MessageHelper.info("Equipamentos com estoque baixo (quantidade < " + LIMITE_BAIXO + "):");
                            EquipamentoPrinter.imprimirLista(criticos);
                        }
                    } catch (Exception e) {
                        MessageHelper.erro("Falha ao listar estoque baixo: " + e.getMessage());
                    }
                    break;

                default:
                    MessageHelper.erro("Opção inválida. Tente novamente.");
            }

            if (!sair) {
                InputHelper.lerString(scanner, "\nPressione ENTER para continuar...");
            }
        }
    }
}
