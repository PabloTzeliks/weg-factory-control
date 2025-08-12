package pablo.tzeliks.model;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;

public class PainelControle extends Equipamento {

    private double tensao;

    public PainelControle(int id, String nome, Codigo codigo, int quantidade, double preco, TipoEquipamento tipoEquipamento, double tensao) { // Uma Tensão como Tipo String, eu acredito não fazer sentido.

        super(id, nome, codigo, quantidade, preco, tipoEquipamento);
        this.tensao = tensao;

    }

    public double getTensao() {
        return tensao;
    }

    public void setTensao(double tensao) {
        this.tensao = tensao;
    }

    @Override
    public String toString() {

        return String.format("ID: " + getId()
                + "\nNome: " + getNome()
                + "\nCódigo: " + getCodigo()
                + "\nQuantidade: " + getQuantidade()
                + "\nPreço: " + getPreco()
                + "\nTipo do Equipamento: " + getTipoEquipamento()
                + "\nTensão: " + getTensao());
    }
}
