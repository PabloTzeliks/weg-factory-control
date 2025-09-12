package pablo.tzeliks.service.contracts;

import java.util.List;

public interface BuscaEstoqueInterface<T> {

    List<T> buscarPorNomeParcial(String textoParcial);
    List<T> buscarPorPrecoMaiorQue(double precoMinimo);
}