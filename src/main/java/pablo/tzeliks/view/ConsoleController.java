package pablo.tzeliks.view;

import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.view.menus.*;

import java.util.Scanner;

public class ConsoleController {

    private final EstoqueService service;
    private final Scanner scanner;

    public ConsoleController(EstoqueService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            MenuHelper.imprimirMenuPrincipal();
            opcao = InputHelper.lerInteiro(scanner, "Digite a opção desejada: ");
            switch (opcao) {
                case 1:
                    CadastroView.executar(scanner, service);
                    break;
                case 2:
                    ListarView.executar(scanner, service);
                    break;
                case 3:
                    PesquisaView.executar(scanner, service);
                    break;
                case 4:
                    RemocaoView.executar(scanner, service);
                    break;
                case 5:
                    RelatoriosView.executar(scanner, service);
                    break;
                case 6:
                    BuscaAvancadaView.executar(scanner, service);
                    break;
                case 7:
                    CriarPedidoDeProducao.executar(scanner, service);
                    break;
                case 8:
                    IniciarLinhaDeProducao.executar(scanner, service);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    MessageHelper.erro("Opção inválida!");
            }
        } while (opcao != 0);
    }
}