package pablo.tzeliks.model.domain;

import pablo.tzeliks.exceptions.CodigoProdutoException;

public class Codigo {

    private String codigo;

    public Codigo(String codigo) {

        if (isValid(codigo)) this.codigo = codigo;
    }

    private boolean isValid(String codigo) {

        boolean validation = codigo.matches("\\A-Za-z{3}-\\d{4}");

        if (!validation) {
            throw new CodigoProdutoException("Erro! Código de Equipamento inserido não segue as diretrizes corretas.");
        }

        return validation;

    }

    public String getCodigo() {

        return codigo;

    }

}
