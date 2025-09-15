package pablo.tzeliks.view.helpers;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.model.enums.TipoEquipamento;

import java.util.Scanner;

public class InputHelper {

    public static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                MessageHelper.erro("Valor inválido, tente novamente.");
            }
        }
    }

    public static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input.trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                MessageHelper.erro("Valor inválido, tente novamente.");
            }
        }
    }

    public static String lerString(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static TipoEquipamento escolherEnum(Scanner scanner,  String mensagem) {
        while (true) {

            System.out.println(mensagem);
            System.out.println();

            System.out.println("Tipos de Equipamento:\n" +
                    "\n1- Motor Elétrico \n");

            String input = scanner.nextLine();

            try {
                if (input.equals("1")) {
                    TipoEquipamento tipo = TipoEquipamento.MOTOR_ELETRICO;

                    return tipo;
                } else {
                    return null;
                }
            } catch (NumberFormatException e) {
                MessageHelper.erro("Valor inválido, tente novamente.");
            }
        }
    }

    public static boolean confirmarExclusao(Scanner scanner, EquipamentoDTO dto) {

        MenuHelper.imprimirConfirmacaoRemocao();

        EquipamentoPrinter.imprimirEquipamento(dto);

        System.out.println("\n1- Confirmar Remoção" +
                "\n0- Cancelar");

        String input = scanner.nextLine();

        try {
            if (input.equals("1")) {
                return true;
            } else if (input.equals("0")) {
                return false;
            }
        } catch (NumberFormatException e) {
            MessageHelper.erro("Valor inválido, tente novamente.");
        }
        return false;
    }

    public static int escolhaMovimentacaoEstoque(Scanner scanner) {

        System.out.println("Escolha uma opção:");
        System.out.println("1- Adicionar Unidades");
        System.out.println("2- Retirar Unidades");
        System.out.println("0- Voltar");

        String input = scanner.nextLine();

        try {
            if (input.equals("1")) {
                return 1;
            } else if (input.equals("2")) {
                return 2;
            } else if (input.equals("0")) {
                return 0; // Voltar
            }
        } catch (NumberFormatException e) {
            MessageHelper.erro("Valor inválido, tente novamente.");
        }
        return 0;
    }

}