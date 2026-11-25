// PAQUETE E IMPORTAR //
package models;
import java.util.List;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  CentroCrisis
// ══════════════════════════════════════════════════════════════
//  Equipo de miembros de la organización que atiende uno o varios comités en situaciones de crisis.
// ══════════════════════════════════════════════════════════════
public class CentroCrisis {

    // ─────────────────── ATRIBUTOS ───────────────────
    private List<MiembroOrganizacion> miembros;
    private List<Comite> comites;
    // ───────────────── CONSTRUCTORES ─────────────────
    public CentroCrisis() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public List<MiembroOrganizacion> getMiembros() { return null; }
    public void setMiembros(List<MiembroOrganizacion> miembros) { }
    public List<Comite> getComites() { return null; }
    public void setComites(List<Comite> comites) { }

    // ══════════════════════════════════════════════════════════════
    //  ATENCIÓN
    // ══════════════════════════════════════════════════════════════
    // atiende()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public boolean atiende(Comite comite) {
        return false;
    }

}
