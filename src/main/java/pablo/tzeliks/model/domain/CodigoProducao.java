package pablo.tzeliks.model.domain;

import pablo.tzeliks.exceptions.CodigoProdutoException;

import java.util.Objects;

public final class CodigoProducao {

    private final String valor;

    public CodigoProducao(String valor) {

        if (!validate(valor)) {
            throw new CodigoProdutoException("Código inválido: '" + valor + "'. Formato esperado: PROD-0000 (ex: PROD-1234).");
        }

        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public boolean validate(String codigo) {
        return isValid(codigo);
    }

    private boolean isValid(String valor) {

        if (valor == null || valor.isBlank()) {

            throw new CodigoProdutoException("Código não existente (nulo ou vazio).");

        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoProducao that = (CodigoProducao) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}