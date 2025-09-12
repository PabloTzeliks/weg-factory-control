package pablo.tzeliks.service.util;

import pablo.tzeliks.model.domain.CodigoProducao;
import java.util.concurrent.atomic.AtomicInteger;

public class CodigoProducaoFactory {

    // Inicia o contador em 1000 para garantir que sempre tenha 4 dígitos.
    private static final AtomicInteger contador = new AtomicInteger(1000);
    private static final String PREFIXO = "PROD-";

    private CodigoProducaoFactory() {}

    public static CodigoProducao gerarProximo() {

        int proximoNumero = contador.getAndIncrement();

        String numeroFormatado = String.format("%04d", proximoNumero);

        String valorFinal = PREFIXO + numeroFormatado;

        return new CodigoProducao(valorFinal);
    }
}