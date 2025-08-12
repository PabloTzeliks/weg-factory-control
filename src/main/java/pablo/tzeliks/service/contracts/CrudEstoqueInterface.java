package pablo.tzeliks.service.contracts;

import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;

import java.util.List;

public interface CrudEstoqueInterface<Equipamento> {

    void cadastrarEquipamento(Equipamento equipamento);
    List<Equipamento> listarTodos();
    List<Equipamento> listarPorTipo(TipoEquipamento tipoEquipamento);
    Equipamento acharPorCodigo(Codigo codigo);
    Equipamento acharPorId(int id);
    void removerPorCodigo(Codigo codigo);
    void removerPorId(int id);

}

