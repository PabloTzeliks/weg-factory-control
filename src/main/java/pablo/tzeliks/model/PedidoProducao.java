package pablo.tzeliks.model;

import pablo.tzeliks.exceptions.ProducaoException;
import pablo.tzeliks.model.domain.CodigoProducao;
import pablo.tzeliks.model.enums.StatusProducao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PedidoProducao {

    private int id;
    private Equipamento prototipo;
    private final int quantidadeProduzir;
    private final Map<CodigoProducao, StatusProducao> itensProducao;

    public PedidoProducao(int id, Equipamento prototipo, int quantidadeProduzir) {

        if (prototipo == null) { throw new ProducaoException("Equipamento inserido não existente."); }

        this.id = id;
        this.prototipo = prototipo;
        this.quantidadeProduzir = quantidadeProduzir;
        this.itensProducao = new ConcurrentHashMap<>();
    }

    // Getters
    public int getIdPedido() { return id; }
    public void setIdPedido(int id) { this.id = id }
    public Equipamento getPrototipo() { return prototipo; }
    public int getQuantidadeAProduzir() { return quantidadeProduzir; }
    public Map<CodigoProducao, StatusProducao> getItensProduzidos() { return itensProducao; }

    public void registrarItemProduzido(CodigoProducao codigoEquipamento, StatusProducao status) {
        this.itensProducao.put(codigoEquipamento, status);
    }
}