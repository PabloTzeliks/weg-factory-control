package pablo.tzeliks.model.domain;

import pablo.tzeliks.exceptions.CodigoProdutoException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Codigo {

    private final Pattern PADRAO = Pattern.compile("^[A-Za-z]{3}-\\d{4}$");

    private final String codigo;

    public Codigo(String codigo) {

        if (codigo == null) throw new CodigoProdutoException("Código não pode ser Nulo.");

        String trimmed = codigo.trim();

        if (!validate(trimmed)) {
            throw new CodigoProdutoException("Código inválido: '" + codigo + "'. Formato esperado: AAA-0000 (ex: WEG-1234).");
        }

        this.codigo = trimmed.toUpperCase();
    }

    public boolean validate(String codigo) {
        return isValid(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    private boolean isValid(String codigo) {
        return PADRAO.matcher(codigo.trim()).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Codigo)) return false;
        Codigo other = (Codigo) o;
        return codigo.equals(other.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo;
    }
}