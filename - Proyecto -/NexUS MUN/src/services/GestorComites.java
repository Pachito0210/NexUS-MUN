// PAQUETE E IMPORTAR //
package services;
import java.util.List;
import models.Comite;
import models.Delegacion;
import models.Delegado;
import models.PreferenciaComite;
import models.ResultadoOperacion;
import models.Sugerencia;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  GestorComites
// ══════════════════════════════════════════════════════════════
//  Administra los comités: registra preferencias, asigna delegados a delegaciones y sugiere reubicaciones.
//  * COMPOSICIÓN *
//    - GestorComites contiene los Comites, las Preferencias y los Centros de Crisis
// ══════════════════════════════════════════════════════════════
public class GestorComites {

    // ─────────────────── ATRIBUTOS ───────────────────
    private List<Comite> comites;
    private List<PreferenciaComite> preferencias;
    // ───────────────── CONSTRUCTORES ─────────────────
    public GestorComites() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public List<Comite> getComites() { return null; }
    public void setComites(List<Comite> comites) { }
    public List<PreferenciaComite> getPreferencias() { return null; }
    public void setPreferencias(List<PreferenciaComite> preferencias) { }

    // ══════════════════════════════════════════════════════════════
    //  PREFERENCIAS
    // ══════════════════════════════════════════════════════════════
    // registrarPreferencia()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public ResultadoOperacion registrarPreferencia(Delegado delegado, Comite comite) {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  ASIGNACIÓN
    // ══════════════════════════════════════════════════════════════
    // asignarDelegado()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public ResultadoOperacion asignarDelegado(Delegado delegado, Comite comite, Delegacion delegacion, boolean esExcepcion) {
        return null;
    }
    // liberarDelegacion()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public void liberarDelegacion(Delegacion delegacion) {
    }

    // ══════════════════════════════════════════════════════════════
    //  ANÁLISIS
    // ══════════════════════════════════════════════════════════════
    // contarSolicitudes()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public int contarSolicitudes(Comite comite) {
        return 0;
    }
    // sugerirReubicaciones()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public List<Sugerencia> sugerirReubicaciones() {
        return null;
    }

}
