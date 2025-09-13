package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.service.ProducaoService;
import pablo.tzeliks.view.helpers.EquipamentoPrinter;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class CriarPedidoDeProducao {

    public static void execute(Scanner scanner, ProducaoService service, EstoqueService estoqueService) {

        MenuHelper.imprimirMenuCriacaoDePedidoDeProducao();

        String codigoRaw = InputHelper.lerString(scanner, "Informe o código do equipamento (ex: AAA-0001): ");

        Codigo codigo;
        try {
            codigo = new Codigo(codigoRaw);
        } catch (Exception e) {
            MessageHelper.erro("Código inválido: " + e.getMessage());
            return;
        }

        try {
            EquipamentoDTO dto = estoqueService.acharPorCodigo(codigo);
            if (dto == null) {
                MessageHelper.info("Equipamento não encontrado.");
            } else {
                EquipamentoPrinter.imprimirEquipamento(dto);


            }

        } catch (Exception e) {
            MessageHelper.erro("Falha ao buscar equipamento: " + e.getMessage());
        }





    }

}
