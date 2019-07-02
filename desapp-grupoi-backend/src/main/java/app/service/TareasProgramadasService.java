package app.service;

import app.model.account.Cuenta;
import app.model.account.Dinero;
import app.model.account.EnumTipos.*;
import app.model.web.NewMovimiento;
import app.service.account.CuentaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareasProgramadasService {

    private static final Logger logger = LogManager.getLogger(TareasProgramadasService.class);

    @Autowired
    private CuentaService cuentaService;

    /*
     * Se ejecuta cada un minuto para saber que las tareas programadas funcionan
     */
    @Scheduled(cron = "20 * * * * ?")
    public void loguearEnTerminalSaludo(){
        logger.info("TAREAS PROGRAMADAS CORRIENDO!");
    }

    /*
    * Se ejecuta los dias 5 de todos los meses a las 05:00:00 AM
    */
    @Scheduled(cron = "0 0 5 5 1-12 ?")
    public void cobrarCuotaCredito(){

        logger.info("COMIENZO - COBRANDO CUOTAS CREDITOS");

        List<Cuenta> cuentas = this.cuentaService.getAllCuentasConCreditosEnCurso();

        if(cuentas.size() == 0)
            logger.info("NINGUNA CUENTA TIENE CREDITOS EN CURSO");

        for (Cuenta c : cuentas) {
            int codigo = c.getTarjetaCredito().getCodigoSeguridad();
            Dinero monto = c.obtenerUltimoCredito().getMontoCuota();
            NewMovimiento movimiento = new NewMovimiento(codigo, monto);

            this.cuentaService.nuevoMovimiento(c.getId(),TipoMovimiento.PAGARCUOTA,movimiento);
        }

        logger.info("FIN - COBRANDO CUOTAS CREDITOS");
    }
}
