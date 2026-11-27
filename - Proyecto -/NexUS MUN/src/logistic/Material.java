// PAQUETE MODELS //
package logistic;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Material
// ══════════════════════════════════════════════════════════════
//  Material del evento con la cantidad requerida y la disponible.
// ══════════════════════════════════════════════════════════════
public class Material {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private int cantidadRequerida;
    private int cantidadDisponible;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Material() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getNombre() { return null; }
    public void setNombre(String nombre) { }
    public int getCantidadRequerida() { return 0; }
    public void setCantidadRequerida(int cantidadRequerida) { }
    public int getCantidadDisponible() { return 0; }
    public void setCantidadDisponible(int cantidadDisponible) { }

    // ══════════════════════════════════════════════════════════════
    //  INVENTARIO
    // ══════════════════════════════════════════════════════════════
    // faltante()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public int faltante() {
        return 0;
    }
    // estaCompleto()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public boolean estaCompleto() {
        return false;
    }

}
