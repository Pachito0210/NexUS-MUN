// PAQUETE E IMPORTAR //
package interfaces;
import java.util.List;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IConsultaReportes
// ══════════════════════════════════════════════════════════════
//  Contrato para consultar los reportes del evento.
// ══════════════════════════════════════════════════════════════
public interface IConsultaReportes {
    // ══════════════════════════════════════════════════════════════
    //  REPORTES
    // ══════════════════════════════════════════════════════════════
    // consultarReporteComites()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    List<String> consultarReporteComites();
    // consultarReporteInsumos()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    List<String> consultarReporteInsumos();

}
