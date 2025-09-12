package pablo.tzeliks.model;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;

public abstract class Equipamento {

    private int id;
    private Codigo codigo;
    private String nome;
    private int quantidade;
    private double preco;
    private TipoEquipamento tipoEquipamento;

    public Equipamento(int id, String nome, Codigo codigo, int quantidade, double preco, TipoEquipamento tipoEquipamento) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.preco = preco;
        this.tipoEquipamento = tipoEquipamento;
    }

    public void setId(int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public Codigo getCodigo() {
        return codigo;
    }

    public void setCodigo(Codigo codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public abstract String toString();
}