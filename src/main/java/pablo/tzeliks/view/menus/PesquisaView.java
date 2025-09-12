package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.view.helpers.EquipamentoPrinter;

import java.util.Scanner;

public class PesquisaView {

    public static void executar(Scanner scanner, EstoqueService service) {

        MenuHelper.imprimirMenuPesquisar();

        String codigoRaw = InputHelper.lerString(scanner, "Informe o código do equipamento (ex: AAA-0001): ");

        Codigo codigo;
        try {
            codigo = new Codigo(codigoRaw);
        } catch (Exception e) {
            MessageHelper.erro("Código inválido: " + e.getMessage());
            return;
        }

        try {
            EquipamentoDTO dto = service.acharPorCodigo(codigo);
            if (dto == null) {
                MessageHelper.info("Equipamento não encontrado.");
            } else {
                EquipamentoPrinter.imprimirEquipamento(dto);
            }
        } catch (Exception e) {
            // Service pode lançar uma exceção com mensagem clara (p.ex. "Equipamento não encontrado").
            MessageHelper.erro("Falha ao pesquisar equipamento: " + e.getMessage());
        }
    }
}