package pablo.tzeliks;

import pablo.tzeliks.service.EstoqueService;
import pablo.tzeliks.service.ProducaoService;
import pablo.tzeliks.view.ConsoleController;

/*

    * Projeto: Controle de Estoque de Equipamentos
    * Autor: Pablo Ruan Tzeliks
    * Data: 17/08/2025
    * Descrição: Aplicação de console para gerenciar o estoque de equipamentos, incluindo cadastro, listagem, pesquisa, remoção, movimentação de estoque e relatórios.

    * Requisitos para rodar aplicação:
    * - Java 21
    * - Maven 4.0.0
    * - IDE de sua preferência (IntelliJ, Eclipse, etc.)

    * Instruções para rodar:
    * 1. Clone o repositório ou baixe os arquivos.
    * 2. Abra o projeto na sua IDE.
    * 3. Certifique-se de que o Maven esteja configurado corretamente.
    * 4. Execute o Maven, junto a um clean install, e compile o projeto.
    * 5. Execute a classe Main para iniciar a aplicação.
    * 6. Siga as instruções no console para interagir com o sistema.

    * Faça bom uso!

 */

public class Main {
    public static void main(String[] args) {

        EstoqueService serviceEstoque = new EstoqueService();
        ProducaoService serviceProducao = new ProducaoService();

        ConsoleController inicio = new ConsoleController(serviceEstoque, serviceProducao);

        inicio.iniciar();
    }
}