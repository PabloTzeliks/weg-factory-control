package pablo.tzeliks.model.enums;

import java.util.Random;

public enum StatusProducao {
    PRODUZIDO("Produzido"),
    EM_TESTE("Em teste"),
    REPROVADO("Reprovado");

    private final String descricao;

    StatusProducao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusProducao getRandomStatus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}