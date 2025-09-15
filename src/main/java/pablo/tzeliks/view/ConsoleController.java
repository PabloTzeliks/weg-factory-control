package pablo.tzeliks.view;

import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.service.ProducaoService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.view.menus.*;

import java.util.Scanner;

public class ConsoleController {

    private final EstoqueService serviceEstoque;
    private final ProducaoService serviceProducao;
    private final Scanner scanner;

    public ConsoleController(EstoqueService serviceEstoque, ProducaoService serviceProducao) {
        this.serviceEstoque = serviceEstoque;
        this.serviceProducao = serviceProducao;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            MenuHelper.imprimirMenuPrincipal();
            opcao = InputHelper.lerInteiro(scanner, "Digite a opção desejada: ");
            switch (opcao) {
                case 1:
                    CadastroView.executar(scanner, serviceEstoque);
                    break;
                case 2:
                    ListarView.executar(scanner, serviceEstoque);
                    break;
                case 3:
                    PesquisaView.executar(scanner, serviceEstoque);
                    break;
                case 4:
                    RemocaoView.executar(scanner, serviceEstoque);
                    break;
                case 5:
                    RelatoriosView.executar(scanner, serviceEstoque);
                    break;
                case 6:
                    BuscaAvancadaView.executar(scanner, serviceEstoque);
                    break;
                case 7:
                    CriarPedidoDeProducao.executar(scanner, serviceProducao);
                    break;
//                case 8:
//                    IniciarLinhaDeProducao.executar(scanner, serviceProducao);
//                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    MessageHelper.erro("Opção inválida!");
            }
        } while (opcao != 0);
    }
}