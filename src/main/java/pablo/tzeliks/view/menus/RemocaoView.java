package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class RemocaoView {

    public static void executar(Scanner scanner, EstoqueService service) {

        MenuHelper.imprimirMenuRemocao();

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
                boolean confirmacaoExclusao = InputHelper.confirmarExclusao(scanner, dto);

                if (confirmacaoExclusao) {
                    service.removerPorCodigo(codigo);
                }
            }
        } catch (Exception e) {
            // Service pode lançar uma exceção com mensagem clara (p.ex. "Equipamento não encontrado").
            MessageHelper.erro("Falha ao pesquisar equipamento: " + e.getMessage());
        }
    }
}