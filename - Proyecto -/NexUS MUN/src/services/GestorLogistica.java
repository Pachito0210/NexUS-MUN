// PAQUETE SERVICES //
package services;
import java.util.List;
import interfaces.IGestionSolicitudes;
import models.Comida;
import models.Comite;
import models.Delegado;
import models.Insumo;
import models.Kit;
import models.Material;
import models.MiembroOrganizacion;
import models.SolicitudLogistica;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  GestorLogistica
// ══════════════════════════════════════════════════════════════
//  Administra insumos, materiales y solicitudes logísticas: genera kits y comidas y controla las entregas.
//  * COMPOSICIÓN *
//    - GestorLogistica contiene sus Insumos, Materiales y Solicitudes
//  * INTERFAZ *
//    - Implementa IGestionSolicitudes, que define cómo se registran y listan las solicitudes
// ══════════════════════════════════════════════════════════════
public class GestorLogistica implements IGestionSolicitudes {

    // ─────────────────── ATRIBUTOS ───────────────────
    private List<Insumo> insumos;
    private List<Material> materiales;
    private List<SolicitudLogistica> solicitudes;
    // ───────────────── CONSTRUCTORES ─────────────────
    public GestorLogistica() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public List<Insumo> getInsumos() { return null; }
    public void setInsumos(List<Insumo> insumos) { }
    public List<Material> getMateriales() { return null; }
    public void setMateriales(List<Material> materiales) { }
    public List<SolicitudLogistica> getSolicitudes() { return null; }
    public void setSolicitudes(List<SolicitudLogistica> solicitudes) { }

    // ══════════════════════════════════════════════════════════════
    //  GENERACIÓN DE INSUMOS
    // ══════════════════════════════════════════════════════════════
    // generarKits()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public List<Kit> generarKits(List<Delegado> delegados) {
        return null;
    }
    // generarComidas()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public List<Comida> generarComidas(List<Delegado> delegados, String tipoComida, String jornada) {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  ENTREGA
    // ══════════════════════════════════════════════════════════════
    // entregarInsumo()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public boolean entregarInsumo(Insumo insumo, MiembroOrganizacion responsable) {
        return false;
    }
    // listarPendientes()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public List<Insumo> listarPendientes(Comite comite) {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  SOLICITUDES
    // ══════════════════════════════════════════════════════════════
    // registrarSolicitud()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public void registrarSolicitud(SolicitudLogistica solicitud) {
    }
    // listarSolicitudesPendientes()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public List<SolicitudLogistica> listarSolicitudesPendientes() {
        return null;
    }

}
