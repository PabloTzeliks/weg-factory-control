package pablo.tzeliks.model;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoEquipamento;
import pablo.tzeliks.view.EquipamentoDTO;

public class MotorEletrico extends Equipamento {

    private double potencia;

    public MotorEletrico(int id, String nome, Codigo codigo, int quantidade, double preco, TipoEquipamento tipoEquipamento, double potencia) {

        super(id, nome, codigo, quantidade, preco, tipoEquipamento);
        this.potencia = potencia;

    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {

        return String.format("ID: " + getId()
                + "\nNome: " + getNome()
                + "\nCódigo: " + getCodigo()
                + "\nQuantidade: " + getQuantidade()
                + "\nPreço: " + getPreco()
                + "\nTipo do Equipamento: " + getTipoEquipamento()
                + "\nPotência: " + getPotencia());
    }
}
