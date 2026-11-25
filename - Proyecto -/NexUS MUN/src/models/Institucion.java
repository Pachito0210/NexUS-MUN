// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Institucion
// ══════════════════════════════════════════════════════════════
//  Colegio, universidad u organización a la que pertenecen los participantes.
// ══════════════════════════════════════════════════════════════
public class Institucion {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private String tipo;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Institucion() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getNombre() { return null; }
    public void setNombre(String nombre) { }
    public String getTipo() { return null; }
    public void setTipo(String tipo) { }

    // ══════════════════════════════════════════════════════════════
    //  COMPARACIÓN
    // ══════════════════════════════════════════════════════════════
    // tieneElMismoNombreQue()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public boolean tieneElMismoNombreQue(String nombre) {
        return false;
    }

}
