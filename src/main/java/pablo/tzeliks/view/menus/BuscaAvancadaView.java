package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.view.helpers.EquipamentoPrinter;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class BuscaAvancadaView {

    public static void executar(Scanner scanner, EstoqueService service) {
        boolean sair = false;
        while (!sair) {

            MenuHelper.imprimirMenuBuscaAvancada();

            int opcao = InputHelper.lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 0:
                    sair = true;
                    break;

                case 1:
                    buscarPorNome(scanner, service);
                    break;

                case 2:
                    buscarPorPreco(scanner, service);
                    break;

                default:
                    MessageHelper.erro("Opção inválida. Tente novamente.");
            }

            if (!sair) {
                InputHelper.lerString(scanner, "\nPressione ENTER para continuar...");
            }
        }
    }

    private static void buscarPorNome(Scanner scanner, EstoqueService service) {

        MenuHelper.imprimirBuscaNome();

        String termo = InputHelper.lerString(scanner, "Digite parte do nome para buscar (ENTER para cancelar): ");
        if (termo == null || termo.isBlank()) {
            MessageHelper.info("Busca cancelada.");
            return;
        }

        try {
            List<EquipamentoDTO> resultados = service.buscarPorNomeParcial(termo);
            if (resultados == null || resultados.isEmpty()) {
                MessageHelper.info("Nenhum equipamento encontrado contendo: \"" + termo + "\".");
                return;
            }

            MessageHelper.info("Resultados da busca por: \"" + termo + "\"");
            EquipamentoPrinter.imprimirLista(resultados);

            permitirVerDetalhes(scanner, service, resultados);

        } catch (Exception e) {
            MessageHelper.erro("Erro durante a busca por nome: " + e.getMessage());
        }
    }

    private static void buscarPorPreco(Scanner scanner, EstoqueService service) {

        MenuHelper.imprimirBuscaPreco();

        double minimo;
        while (true) {
            minimo = InputHelper.lerDouble(scanner, "Informe o preço mínimo (valor >= 0): R$");
            if (minimo < 0) {
                MessageHelper.erro("Valor inválido. Informe um número maior ou igual a 0.");
            } else {
                break;
            }
        }

        try {
            List<EquipamentoDTO> resultados = service.buscarPorPrecoMaiorQue(minimo);
            if (resultados == null || resultados.isEmpty()) {
                MessageHelper.info("Nenhum equipamento encontrado com preço maior que R$" + String.format("%.2f", minimo) + ".");
                return;
            }

            MessageHelper.info("Equipamentos com preço maior que R$" + String.format("%.2f", minimo) + ":");
            EquipamentoPrinter.imprimirLista(resultados);

            permitirVerDetalhes(scanner, service, resultados);

        } catch (Exception e) {
            MessageHelper.erro("Erro durante a busca por preço: " + e.getMessage());
        }
    }

    // Permite ao usuário ver detalhes de um equipamento específico a partir dos resultados da busca *Poderia ter aplicado um POO mais avançado, mas para manter a simplicidade, mantive o método estático, e internamnete nesta View*
    private static void permitirVerDetalhes(Scanner scanner, EstoqueService service, List<EquipamentoDTO> resultados) {
        while (true) {
            int id = InputHelper.lerInteiro(scanner, "\nDigite o ID para ver detalhes (0 para voltar): ");
            if (id == 0) break;

            EquipamentoDTO dto = service.acharPorId(id);
            if (dto == null) {
                MessageHelper.erro("Nenhum equipamento encontrado com o ID: " + id);
            } else {
                MessageHelper.info("Detalhes do equipamento (ID " + id + "):");
                EquipamentoPrinter.imprimirEquipamento(dto);
                InputHelper.lerString(scanner, "\nPressione ENTER para voltar à lista de resultados...");
                // reimprime a lista para referência
                EquipamentoPrinter.imprimirLista(resultados);
            }
        }
    }
}