package pablo.tzeliks.service.contracts;

import pablo.tzeliks.exceptions.MovimentacaoEstoqueException;
import pablo.tzeliks.model.domain.Codigo;

public interface MovimentacaoEstoqueInterface {

    void AdicionarUnidadesEstoque(Codigo codigo, int quantidade) throws MovimentacaoEstoqueException;
    void RetirarUnidadesEstoque(Codigo codigo, int quantidade) throws MovimentacaoEstoqueException;

}