package pablo.tzeliks.view.menus;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.ProducaoService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class CriarPedidoDeProducao {

    public static void executar(Scanner scanner, ProducaoService service) {

        MenuHelper.imprimirMenuCriacaoDePedidoDeProducao();

        String codigoRaw = InputHelper.lerString(scanner, "Informe o código do equipamento (ex: AAA-0001): ");

        Codigo codigo;
        try {
            codigo = new Codigo(codigoRaw);
        } catch (Exception e) {
            MessageHelper.erro("Código inválido: " + e.getMessage());
            return;
        }

//        EquipamentoDTO equipamentoDTO;

//        try {
//            equipamentoDTO = estoqueService.acharPorCodigo(codigo);
//            if (equipamentoDTO == null) {
//                MessageHelper.erro("Equipamento com código " + codigo + " não encontrado no estoque.");
//                return;
//            } else {
//                EquipamentoPrinter.imprimirEquipamento(equipamentoDTO);
//            }
//        } catch (Exception e) {
//            MessageHelper.erro("Falha ao buscar equipamento no estoque: " + e.getMessage());
//            return;
//        }

        int quantidade = 0;

        try {

            quantidade = InputHelper.lerInteiro(scanner, "Informe a quantidade a produzir: ");

        } catch (Exception e) {
            MessageHelper.erro("Falha ao inserir quantidade do Pedido de Produção, observe: " + e.getMessage());
            return;
        }

        try {
            service.criarPedidoProducao(0, codigo, quantidade);
            MessageHelper.sucesso("Pedido de Produção criado com sucesso.");
        } catch (Exception e) {
            MessageHelper.erro("Falha ao criar Pedido de Produção: " + e.getMessage());
        }
    }
}