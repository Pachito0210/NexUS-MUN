// PAQUETE E IMPORTAR //
package models;
import java.util.List;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Kit
// ══════════════════════════════════════════════════════════════
//  Insumo que contiene un conjunto de materiales para un delegado.
//  * HERENCIA *
//    - Kit ES UN Insumo, hereda el estado de entrega y solo agrega su contenido
//  * POLIMORFISMO *
//    - Kit sobrescribe getDescripcion() de Insumo y describe sus materiales
// ══════════════════════════════════════════════════════════════
public class Kit extends Insumo {

    // ─────────────────── ATRIBUTOS ───────────────────
    private List<Material> contenido;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Kit() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public List<Material> getContenido() { return null; }
    public void setContenido(List<Material> contenido) { }

    // ══════════════════════════════════════════════════════════════
    //  DESCRIPCIÓN
    // ══════════════════════════════════════════════════════════════
    // getDescripcion()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public String getDescripcion() {
        return null;
    }

}
