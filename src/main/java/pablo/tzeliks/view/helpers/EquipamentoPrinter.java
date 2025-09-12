package pablo.tzeliks.view.helpers;

import pablo.tzeliks.dto.EquipamentoDTO;
import pablo.tzeliks.model.enums.TipoEquipamento;

import java.util.List;
import java.util.Locale;

public final class EquipamentoPrinter {

    private EquipamentoPrinter() {}

    public static void imprimirLista(List<EquipamentoDTO> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum equipamento encontrado.");
            return;
        }
        System.out.println();
        System.out.println("---- Equipamentos ----");
        for (EquipamentoDTO dto : lista) {
            imprimirEquipamento(dto);
            System.out.println("----------------------");
        }
    }

    public static void imprimirEquipamento(EquipamentoDTO dto) {
        if (dto == null) {
            System.out.println("Equipamento nulo.");
            return;
        }

        String preco = String.format(Locale.ENGLISH, "%.2f", dto.preco());

        System.out.println("ID: " + dto.id());
        System.out.println("Nome: " + safeString(dto.nome()));
        System.out.println("Código: " + safeString(dto.codigo()));
        System.out.println("Tipo: " + (dto.tipoEquipamento() != null ? dto.tipoEquipamento().name() : "N/A"));
        System.out.println("Quantidade: " + dto.quantidade());
        System.out.println("Preço: R$ " + preco);

        TipoEquipamento tipo = dto.tipoEquipamento();
        if (tipo == TipoEquipamento.MOTOR_ELETRICO) {
            System.out.println("Potência (kW): " + String.format(Locale.ENGLISH, "%.2f", dto.potencia()));
        }

        // Isso poderia virar uma Abstração para a Design Pattern de Simple Factory Design Pattern

    }

    private static void imprimirListaResumo(List<EquipamentoDTO> lista) {
        System.out.printf("%-4s %-30s %-12s %-10s %-10s %-15s%n", "ID", "NOME", "CÓDIGO", "QTD", "PREÇO", "TIPO");
        System.out.println("----------------------------------------------------------------------------------------");
        for (EquipamentoDTO dto : lista) {
            String codigoStr = dto.codigo() != null ? dto.codigo().toString() : "-";
            System.out.printf("%-4d %-30s %-12s %-10d %-10.2f %-15s%n",
                    dto.id(),
                    abreviar(dto.nome(), 30),
                    codigoStr,
                    dto.quantidade(),
                    dto.preco(),
                    dto.tipoEquipamento() != null ? dto.tipoEquipamento().name() : "-"
            );
        }
    }

    private static String abreviar(String s, int tamanho) {
        if (s == null) return "-";
        if (s.length() <= tamanho) return s;
        return s.substring(0, tamanho - 3) + "...";
    }

    private static String safeString(Object o) {
        return o == null ? "-" : o.toString();
    }
}