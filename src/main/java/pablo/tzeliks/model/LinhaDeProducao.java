package pablo.tzeliks.model;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.domain.CodigoProducao;
import pablo.tzeliks.model.enums.StatusProducao;
import pablo.tzeliks.service.util.CodigoProducaoFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class LinhaDeProducao implements Runnable {

    private final PedidoProducao pedido;
    private static final AtomicInteger contadorSerial = new AtomicInteger(1);

    public LinhaDeProducao(PedidoProducao pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        System.out.println("Linha de Produção de ID " + contadorSerial + " | Acabou de Iniciar");

        for (int i = 0; i < pedido.getQuantidadeAProduzir(); i++) {
            StatusProducao status = StatusProducao.getRandomStatus();

            CodigoProducao codigoProducao = CodigoProducaoFactory.gerarProximo();

            pedido.registrarItemProduzido(codigoProducao, status);

            System.out.println("\uD83D\uDCE6 " + " - Linha de Produção acabou de produzir " + codigoProducao + " | Status: " + status);

            try {
                Thread.sleep((long) (Math.random() * 200 + 50)); // Simula tempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("🛑 Linha de ID " + contadorSerial + " foi interrompida.");
                break;
            }
        }
        System.out.println("✅ Linha de Produção de ID " + contadorSerial + " finalizou.");
    }

//    private Equipamento criarInstancia(Equipamento prototipo) {
//        int novoId = contadorSerial.getAndIncrement();
//        Codigo novoCodigo = new Codigo("WEG-" + String.format("%04d", novoId));
//
//        if (prototipo instanceof MotorEletrico) {
//            MotorEletrico prototipoMotor = (MotorEletrico) prototipo;
//            return new MotorEletrico(
//                    novoId,
//                    prototipoMotor.getNome(),
//                    novoCodigo,
//                    1,
//                    prototipoMotor.getPreco(),
//                    prototipoMotor.getTipoEquipamento(),
//                    prototipoMotor.getPotencia()
//            );
//        }
//        throw new IllegalArgumentException("Tipo de protótipo não suportado: " + prototipo.getClass());
//    }
}