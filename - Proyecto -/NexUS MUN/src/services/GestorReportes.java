// PAQUETE E IMPORTAR //
package services;
import java.util.List;
import interfaces.IConsultaReportes;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  GestorReportes
// ══════════════════════════════════════════════════════════════
//  Genera los reportes de comités e insumos a partir de los gestores.
//  * INTERFAZ *
//    - Implementa IConsultaReportes, que define qué reportes se pueden consultar
// ══════════════════════════════════════════════════════════════
public class GestorReportes implements IConsultaReportes {

    // ─────────────────── ATRIBUTOS ───────────────────
    private GestorComites gestorComites;
    private GestorLogistica gestorLogistica;
    // ───────────────── CONSTRUCTORES ─────────────────
    public GestorReportes() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public GestorComites getGestorComites() { return null; }
    public void setGestorComites(GestorComites gestorComites) { }
    public GestorLogistica getGestorLogistica() { return null; }
    public void setGestorLogistica(GestorLogistica gestorLogistica) { }

    // ══════════════════════════════════════════════════════════════
    //  REPORTES
    // ══════════════════════════════════════════════════════════════
    // consultarReporteComites()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public List<String> consultarReporteComites() {
        return null;
    }
    // consultarReporteInsumos()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public List<String> consultarReporteInsumos() {
        return null;
    }

}
