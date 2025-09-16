package pablo.tzeliks.view.helpers;

public class MenuHelper {

    public static void imprimirMenuPrincipal() {
        System.out.println("=======================================");
        System.out.println("  Sistema de Produção de Equipamentos  ");
        System.out.println("=======================================");
        System.out.println("1 - Cadastrar Equipamento");
        System.out.println("2 - Listar Equipamentos");
        System.out.println("3 - Pesquisar Equipamento por Código");
        System.out.println("4 - Remover Equipamento por Código");
        System.out.println("5 - Relatórios de Estoque");
        System.out.println("6 - Buscas Avançadas");
        System.out.println("7 - Criação de Pedido de Produção");
        System.out.println("8 - Iniciar Linha(s) de Produção");
        System.out.println("0 - Sair");
        System.out.println();
    }

    public static void imprimirMenuCadastro() {
        System.out.println("===============================");
        System.out.println("    CADASTRO DE EQUIPAMENTO    ");
        System.out.println("===============================");
        System.out.println();
    }

    public static void imprimirMenuListagem() {
        System.out.println("===============================");
        System.out.println("    LISTAGEM DE EQUIPAMENTO    ");
        System.out.println("===============================");
        System.out.println("1 - Listar todos os equipamentos");
        System.out.println("2 - Listar equipamentos por tipo");
        System.out.println("0 - Voltar");
        System.out.println();
    }

    public static void imprimirMenuPesquisar() {
        System.out.println("===============================");
        System.out.println("    PESQUISA DE EQUIPAMENTO    ");
        System.out.println("===============================");
        System.out.println();
    }

    public static void imprimirMenuRemocao() {
        System.out.println("===============================");
        System.out.println("     REMOÇÃO DE EQUIPAMENTO    ");
        System.out.println("===============================");
        System.out.println();
    }

    public static void imprimirConfirmacaoRemocao() {
        System.out.println("------ CONFIRMAR REMOÇÃO ------");
        System.out.println();
    }

    public static void imprimirMenuRelatorios() {
        System.out.println("===============================");
        System.out.println("     RELATÓRIOS DE ESTOQUE     ");
        System.out.println("===============================");
        System.out.println("1 - Quantidade total de equipamentos em estoque");
        System.out.println("2 - Equipamento com maior preço");
        System.out.println("3 - Equipamento com maior quantidade");
        System.out.println("4 - Equipamentos com estoque baixo (quantidade < 5)");
        System.out.println("0 - Voltar");
        System.out.println();
    }

    public static void imprimirMenuBuscaAvancada() {
        System.out.println("===============================");
        System.out.println("         BUSCA AVANÇADA        ");
        System.out.println("===============================");
        System.out.println();
    }

    public static void imprimirMenuCriacaoDePedidoDeProducao() {
        System.out.println("===============================");
        System.out.println("    CRIAR PEDIDO DE PRODUÇÃO   ");
        System.out.println("===============================");
        System.out.println();
    }

    public static void imprimirMenuInicioLinhaDeProducao() {
        System.out.println("===============================");
        System.out.println("  INICIAR LINHA(S) DE PRODUÇÃO ");
        System.out.println("===============================");
        System.out.println();
    }

    public static void imprimirBuscaNome() {
        System.out.println("---- BUSCA AVANÇADA: NOME ----");
        System.out.println();
    }

    public static void imprimirBuscaPreco() {
        System.out.println("---- BUSCA AVANÇADA: PREÇO ----");
        System.out.println();
    }

}