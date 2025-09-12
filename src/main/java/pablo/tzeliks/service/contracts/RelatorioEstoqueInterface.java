package pablo.tzeliks.service.contracts;

import java.util.List;

public interface RelatorioEstoqueInterface<T> {

    int getQuantidadeTotalEstoque();
    T getEquipamentoMaiorPreco();
    T getEquipamentoMaiorQuantidade();
    List<T> listarEstoqueBaixo(int limite); // limite configurável (ex: 5)
}