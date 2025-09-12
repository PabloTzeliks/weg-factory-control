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

public class ListarView {

    public static void executar(Scanner scanner, EstoqueService service) {
        while (true) {

            MenuHelper.imprimirMenuListagem();

            int opcao = InputHelper.lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 0:
                    return;
                case 1:
                    listarTodos(service);
                    break;
                case 2:
                    listarPorTipo(scanner, service);
                    break;
                default:
                    MessageHelper.erro("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void listarTodos(EstoqueService service) {
        try {
            List<EquipamentoDTO> lista = service.listarTodos();
            EquipamentoPrinter.imprimirLista(lista);
        } catch (Exception e) {
            MessageHelper.erro("Não foi possível listar equipamentos: " + e.getMessage());
        }
    }

    public static void listarPorTipo(Scanner scanner, EstoqueService service) {
        TipoEquipamento tipo = InputHelper.escolherEnum(scanner, "Escolha o tipo do equipamento:");
        if (tipo == null) {
            MessageHelper.info("O tipo do equipamento deve ser informado.");
            return;
        }

        try {
            List<EquipamentoDTO> lista = service.listarPorTipo(tipo);
            EquipamentoPrinter.imprimirLista(lista);
        } catch (Exception e) {
            MessageHelper.erro("Não foi possível listar equipamentos por tipo: " + e.getMessage());
        }
    }
}