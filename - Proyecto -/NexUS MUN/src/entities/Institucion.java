// PAQUETE MODELS //
package entities;

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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // ══════════════════════════════════════════════════════════════
    //  COMPARACIÓN
    // ══════════════════════════════════════════════════════════════
    // tieneElMismoNombreQue()
    //  - COMO FUNCIONA: Compara el nombre de la institución con el nombre recibido y devuelve true si son iguales.
    //  - PARA QUÉ SIRVE: Verificar si una institución ya está registrada evitando duplicados.
    //  * ENCAPSULAMIENTO *
    //    - Permite comparar las instituciones utilizando su nombre sin acceder directamente a sus atributos.
    public boolean tieneElMismoNombreQue(String nombre) {
        return this.nombre != null && this.nombre.equalsIgnoreCase(nombre);
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Institucion)) return false;
        Institucion otra = (Institucion) o;
        return nombre != null && nombre.equalsIgnoreCase(otra.nombre);
    }
    @Override public int hashCode() {
        return nombre == null ? 0 : nombre.toLowerCase().hashCode();
    }

}
