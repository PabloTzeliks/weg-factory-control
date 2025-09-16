package pablo.tzeliks.view.menus;

import pablo.tzeliks.service.ProducaoService;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

public class IniciarLinhaDeProducao {

    public static void executar(ProducaoService service) {

        MenuHelper.imprimirMenuInicioLinhaDeProducao();

        try {

            service.iniciarLinhasDeProducao();

        } catch (Exception e) {
            MessageHelper.erro("Falha ao iniciar Linha(s) de Produção, observe: " + e.getMessage());
        }
    }
}
