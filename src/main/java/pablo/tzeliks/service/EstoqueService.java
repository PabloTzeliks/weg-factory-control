package pablo.tzeliks.service;

import pablo.tzeliks.exceptions.MovimentacaoEstoqueException;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;
import pablo.tzeliks.service.contracts.CrudEstoqueInterface;
import pablo.tzeliks.service.contracts.MovimentacaoEstoqueInterface;
import pablo.tzeliks.view.EquipamentoDTO;

import java.util.ArrayList;
import java.util.List;

public class EstoqueService implements CrudEstoqueInterface<Equipamento>, MovimentacaoEstoqueInterface {

    private List<Equipamento> estoque = new ArrayList<>();

    int proximoId = 1;

    // Tentei criar com DTOs porém me deparei com problemas

//    @Override
//    public void cadastrarEquipamento(EquipamentoDTO dto) {
//        validarDTO(dto);
//
//        // Gera ID automático se não fornecido
//        if (dto.id() <= 0) {
//            dto = new EquipamentoDTO(
//                    proximoId++,
//                    dto.nome(),
//                    dto.codigo(),
//                    dto.quantidade(),
//                    dto.preco(),
//                    dto.tipoEquipamento(),
//                    dto.potencia(),
//                    dto.tensao()
//            );
//        }
//
//        Equipamento equipamento = EquipamentoFactory.InstanceOf(dto);
//
//        if (equipamento == null) {
//            throw new ServiceException("Erro! Não foi possível criar o produto");
//        }
//
//        // Verifica se já existe um produto com o mesmo ID
//        if (buscarPorId(dto.id()) != null) {
//            throw new ServiceException("Já existe um produto com o ID " + dto.id());
//        }
//
//        // Verifica se já existe um produto com o mesmo Código
//        if (buscarPorCodigo(dto.codigo()) != null) {
//            throw new ServiceException("Já existe um produto com o Código " + dto.codigo());
//        }
//
//        estoque.add(equipamento);
//    }

    @Override
    public void cadastrarEquipamento(Equipamento equipamento) {



    }

    @Override
    public List<Equipamento> listarTodos() {

        if (estoque.isEmpty()) {
            throw new ServiceException("Erro! Lista de Produtos está vazia.");
        }

        return new ArrayList<>(estoque);
    }

    @Override
    public List<Equipamento> listarPorTipo(TipoEquipamento tipoEquipamento) {

        if (estoque.isEmpty()) {
            throw new ServiceException("Erro! Lista de Produtos está vazia.");
        }

        return estoque.stream()
                .filter(equipamento -> equipamento.getTipoEquipamento() == tipoEquipamento)
                .toList();
    }

    @Override
    public Equipamento acharPorCodigo(Codigo codigo) {
        return estoque.stream()
                .filter(equipamento -> equipamento.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Equipamento acharPorId(int id) {
        return estoque.stream()
                .filter(equipamento -> equipamento.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void removerPorCodigo(Codigo codigo) {

        Equipamento equipamento = acharPorCodigo(codigo);

        if (equipamento == null) {
            throw new ServiceException("Erro! Equipamento não encontrado de Código: " + codigo);
        }

        estoque.remove(equipamento);
    }

    @Override
    public void removerPorId(int id) {

        Equipamento equipamento = acharPorId(id);

        if (equipamento == null) {
            throw new ServiceException("Erro! Equipamento não encontrado de ID: " + id);
        }

        estoque.remove(equipamento);
    }

    @Override
    public void AdicionarUnidadesEstoque(Codigo codigo, int quantidade) throws MovimentacaoEstoqueException {

        if (quantidade <= 0) {
            throw new MovimentacaoEstoqueException("Erro! Quantidade inserida incorreta.");
        }

        Equipamento equipamento = acharPorCodigo(codigo);

        int quantidadeAtual = equipamento.getQuantidade();

        int quantidadeNova = quantidadeAtual + quantidade;

        equipamento.setQuantidade(quantidadeNova);
    }

    @Override
    public void RetirarUnidadesEstoque(Codigo codigo, int quantidade) throws MovimentacaoEstoqueException {

        if (quantidade <= 0) {
            throw new MovimentacaoEstoqueException("Erro! Quantidade inserida incorreta.");
        }

        Equipamento equipamento = acharPorCodigo(codigo);

        int quantidadeAtual = equipamento.getQuantidade();

        int quantidadeNova = quantidadeAtual - quantidade;

        if (quantidadeNova < 0) {
            throw new MovimentacaoEstoqueException("Erro! Quantidade inserida incorreta.");
        }

        equipamento.setQuantidade(quantidadeNova);
    }

    private void validarEquipamento(Equipamento equipamento) {

        if (equipamento == null) {
            throw new ServiceException("Erro! DTO inválido por ser nulo.");
        }
        if (equipamento.getNome() == null) {
            throw new ServiceException("Erro! Nome inválido por ser nulo.");
        }
        if (equipamento.getCodigo() == null) {
            throw new ServiceException("Erro! Código inválido por ser nulo.");
        }
        if (equipamento.getQuantidade() < 0) {
            throw new ServiceException("Erro! Quantidade inválida por ser menor que 0.");
        }
        if (equipamento.getPreco() <= 0) {
            throw new ServiceException("Erro! Preço inválido, não pode ser 0 ou negativo.");
        }
        if (equipamento.getTipoEquipamento() == null) {
            throw new ServiceException("Erro! Tipo do equipamento inválido por ser nulo.");
        }

    }

}