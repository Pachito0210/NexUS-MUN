// PAQUETE E IMPORTAR //
package interfaces;
import java.util.List;
import logistic.SolicitudLogistica;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IGestionSolicitudes
// ══════════════════════════════════════════════════════════════
//  Contrato para registrar y listar solicitudes logísticas.
// ══════════════════════════════════════════════════════════════
public interface IGestionSolicitudes {
    // ══════════════════════════════════════════════════════════════
    //  SOLICITUDES
    // ══════════════════════════════════════════════════════════════
    // registrarSolicitud()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    void registrarSolicitud(SolicitudLogistica solicitud);
    // listarSolicitudesPendientes()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    List<SolicitudLogistica> listarSolicitudesPendientes();

}
