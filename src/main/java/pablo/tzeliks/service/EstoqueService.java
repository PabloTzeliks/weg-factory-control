package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.mapper.EquipamentoMapper;
import pablo.tzeliks.model.Equipamento;
import pablo.tzeliks.model.MotorEletrico;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;
import pablo.tzeliks.service.contracts.*;
import pablo.tzeliks.dto.EquipamentoDTO;

import java.util.*;
import java.util.stream.Collectors;

public class EstoqueService implements CrudEstoqueInterface<EquipamentoDTO>, RelatorioEstoqueInterface<EquipamentoDTO>, BuscaEstoqueInterface<EquipamentoDTO> {

    private final List<Equipamento> estoque = new ArrayList<>();
    private int proximoId = 1;

    private final EquipamentoMapper mapper;
    private final EquipamentoFactory factory;

    public EstoqueService() {
        this.mapper = Mappers.getMapper(EquipamentoMapper.class);
        this.factory = new EquipamentoFactory();
    }

    public EstoqueService(EquipamentoMapper mapper, EquipamentoFactory factory) {
        this.mapper = Objects.requireNonNull(mapper);
        this.factory = Objects.requireNonNull(factory);
    }

    // ---------- CRUD baseado em DTOs ----------

    @Override
    public synchronized void cadastrarEquipamento(EquipamentoDTO dto) {
        if (dto == null) throw new ServiceException("DTO nulo.");

        // Factory cria a entidade concreta a partir do DTO
        Equipamento equipamento = EquipamentoFactory.fromDTO(dto);
        if (equipamento == null) {
            throw new ServiceException("Erro ao criar equipamento a partir do DTO.");
        }

        validarEquipamento(equipamento);

        // Criação de ID quando necessário para os Equipamentos
        if (equipamento.getId() <= 0) {
            equipamento.setId(proximoId++);
        } else if (equipamento.getId() >= proximoId) {
            proximoId = equipamento.getId() + 1;
        }

        // Validações adicionais
        if (acharPorCodigoEntidade(equipamento.getCodigo()) != null) {
            throw new ServiceException("Já existe um produto com o mesmo código: " + equipamento.getCodigo());
        }
        if (acharPorIdEntidade(equipamento.getId()) != null) {
            throw new ServiceException("Já existe um produto com o mesmo id: " + equipamento.getId());
        }

        estoque.add(equipamento);
    }

    @Override
    public List<EquipamentoDTO> listarTodos() {
        if (estoque.isEmpty()) throw new ServiceException("Lista vazia.");
        return estoque.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EquipamentoDTO> listarPorTipo(TipoEquipamento tipoEquipamento) {
        if (estoque.isEmpty()) throw new ServiceException("Lista vazia.");
        return estoque.stream()
                .filter(e -> e.getTipoEquipamento() == tipoEquipamento)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EquipamentoDTO acharPorCodigo(Codigo codigo) {
        Equipamento e = acharPorCodigoEntidade(codigo);
        return e == null ? null : toDto(e);
    }

    @Override
    public EquipamentoDTO acharPorId(int id) {
        Equipamento e = acharPorIdEntidade(id);
        return e == null ? null : toDto(e);
    }

    @Override
    public synchronized void removerPorCodigo(Codigo codigo) {
        Equipamento e = acharPorCodigoEntidade(codigo);
        if (e == null) throw new ServiceException("Equipamento não encontrado para código: " + codigo);
        estoque.remove(e);
    }

    @Override
    public synchronized void removerPorId(int id) {
        Equipamento e = acharPorIdEntidade(id);
        if (e == null) throw new ServiceException("Equipamento não encontrado para id: " + id);
        estoque.remove(e);
    }

    // ---------- Relatórios ----------

    @Override
    public int getQuantidadeTotalEstoque() {
        return estoque.stream().mapToInt(Equipamento::getQuantidade).sum();
    }

    @Override
    public EquipamentoDTO getEquipamentoMaiorPreco() {
        return estoque.stream()
                .max(Comparator.comparingDouble(Equipamento::getPreco))
                .map(this::toDto)
                .orElseThrow(() -> new ServiceException("Estoque vazio."));
    }

    @Override
    public EquipamentoDTO getEquipamentoMaiorQuantidade() {
        return estoque.stream()
                .max(Comparator.comparingInt(Equipamento::getQuantidade))
                .map(this::toDto)
                .orElseThrow(() -> new ServiceException("Estoque vazio."));
    }

    @Override
    public List<EquipamentoDTO> listarEstoqueBaixo(int limite) {
        return estoque.stream()
                .filter(e -> e.getQuantidade() < limite)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ---------- Buscas avançadas ----------

    @Override
    public List<EquipamentoDTO> buscarPorNomeParcial(String textoParcial) {
        if (textoParcial == null || textoParcial.isBlank()) throw new ServiceException("Texto inválido.");
        String termo = textoParcial.toLowerCase();
        return estoque.stream()
                .filter(e -> e.getNome() != null && e.getNome().toLowerCase().contains(termo))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipamentoDTO> buscarPorPrecoMaiorQue(double precoMinimo) {
        if (precoMinimo < 0) throw new ServiceException("Preço inválido.");
        return estoque.stream()
                .filter(e -> e.getPreco() > precoMinimo)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ---------- Helpers internos (entidades) ----------

    // Deixar público fere em partes o Encapsulamento e a Segurança, porém como versão inicial será feito assim.
    public Equipamento acharPorCodigoEntidade(Codigo codigo) {
        if (codigo == null) return null;
        return estoque.stream().filter(e -> codigo.equals(e.getCodigo())).findFirst().orElse(null);
    }

    public Equipamento acharPorIdEntidade(int id) {
        return estoque.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    // Conversão DTO para entidade de tipo Subclasse de Equipamento
    private EquipamentoDTO toDto(Equipamento equipamento) {
        if (equipamento == null) return null;

        if (equipamento instanceof MotorEletrico) {
            return mapper.toDTO((MotorEletrico) equipamento);
        } else {
            throw new ServiceException("Tipo de equipamento não suportado para mapeamento: " + equipamento.getClass().getName());
        }
    }

    // Validação de entidade (reaproveita lógica)
    private void validarEquipamento(Equipamento equipamento) {
        if (equipamento == null) throw new ServiceException("Equipamento nulo.");
        if (equipamento.getNome() == null || equipamento.getNome().isBlank()) throw new ServiceException("Nome inválido.");
        if (equipamento.getCodigo() == null) throw new ServiceException("Código inválido.");
        if (equipamento.getQuantidade() < 0) throw new ServiceException("Quantidade inválida.");
        if (equipamento.getPreco() <= 0) throw new ServiceException("Preço inválido.");
        if (equipamento.getTipoEquipamento() == null) throw new ServiceException("Tipo inválido.");
    }
}