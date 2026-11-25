// PAQUETE E IMPORTAR //
package logistic;
import entities.Delegado;
import model.Comite;

import java.util.List;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  PreferenciaComite
// ══════════════════════════════════════════════════════════════
//  Comités que un delegado eligió como preferencia al inscribirse.
// ══════════════════════════════════════════════════════════════
public class PreferenciaComite {

    // ─────────────────── ATRIBUTOS ───────────────────
    private Delegado delegado;
    private List<Comite> comitesElegidos;
    // ───────────────── CONSTRUCTORES ─────────────────
    public PreferenciaComite() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public Delegado getDelegado() { return null; }
    public void setDelegado(Delegado delegado) { }
    public List<Comite> getComitesElegidos() { return null; }
    public void setComitesElegidos(List<Comite> comitesElegidos) { }

    // ══════════════════════════════════════════════════════════════
    //  CONSULTAS
    // ══════════════════════════════════════════════════════════════
    // incluye()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public boolean incluye(Comite comite) {
        return false;
    }

}
