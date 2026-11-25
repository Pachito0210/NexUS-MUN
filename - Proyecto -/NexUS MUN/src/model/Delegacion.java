// PAQUETE MODEL //
package model;
import entities.Delegado;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Delegacion
// ══════════════════════════════════════════════════════════════
//  Representa un país, entidad u organización que participa en un comité.
//  * COMPOSICIÓN *
//    - Contiene la información de la delegación y el delegado que la representa.
// ══════════════════════════════════════════════════════════════
public class Delegacion {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private Delegado delegado;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Delegacion() { }

    // ══════════════════════════════════════════════════════════════
    //  GETTERS & SETTERS
    // ══════════════════════════════════════════════════════════════
    // getNombre()
    //  - COMO FUNCIONA: Devuelve el nombre del país, entidad u organización representada.
    //  - PARA QUÉ SIRVE: Consultar qué representa la delegación.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al nombre sin acceder directamente al atributo.
    public String getNombre() {
        return nombre;
    }
    // setNombre()
    //  - COMO FUNCIONA: Asigna el nombre recibido a la delegación.
    //  - PARA QUÉ SIRVE: Establecer o modificar el país, entidad u organización representada.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo nombre.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // getDelegado()
    //  - COMO FUNCIONA: Devuelve el delegado asignado a la delegación.
    //  - PARA QUÉ SIRVE: Consultar qué participante representa al país o entidad.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al delegado asignado sin acceder directamente al atributo.
    public Delegado getDelegado() {
        return delegado;
    }
    // setDelegado()
    //  - COMO FUNCIONA: Asigna el delegado recibido a la delegación.
    //  - PARA QUÉ SIRVE: Establecer quién representa al país o entidad.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del delegado asignado.
    public void setDelegado(Delegado delegado) {
        this.delegado = delegado;
    }

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE ASIGNACIÓN
    // ══════════════════════════════════════════════════════════════
    // estaLibre()
    //  - COMO FUNCIONA: Verifica si el atributo delegado se encuentra en null.
    //  - PARA QUÉ SIRVE: Saber si la delegación puede recibir un delegado.
    //  * ABSTRACCIÓN *
    //    - Permite conocer el estado de asignación sin revisar directamente el atributo.
    public boolean estaLibre() {
        return delegado == null;
    }
    // asignarDelegado()
    //  - COMO FUNCIONA: Asigna un delegado si la delegación está libre y el delegado recibido no es null.
    //  - PARA QUÉ SIRVE: Vincular un participante con el país o entidad que representará.
    //  * ABSTRACCIÓN *
    //    - Centraliza las condiciones necesarias para asignar un delegado.
    public boolean asignarDelegado(Delegado delegado) {
        if (delegado == null || !estaLibre()) {
            return false;
        }
        this.delegado = delegado;
        return true;
    }
    // liberar()
    //  - COMO FUNCIONA: Elimina el delegado asignado estableciendo el atributo delegado en null.
    //  - PARA QUÉ SIRVE: Dejar la delegación disponible para una nueva asignación.
    //  * ABSTRACCIÓN *
    //    - Permite liberar la delegación sin modificar directamente su atributo.
    public void liberar() {
        delegado = null;
    }

    // ══════════════════════════════════════════════════════════════
    //  COMPARACIÓN
    // ══════════════════════════════════════════════════════════════
    // tieneElMismoNombreQue()
    //  - COMO FUNCIONA: Compara el nombre de la delegación con el nombre recibido ignorando mayúsculas y minúsculas.
    //  - PARA QUÉ SIRVE: Verificar si una delegación ya existe o localizarla por su nombre.
    //  * ABSTRACCIÓN *
    //    - Permite comparar delegaciones por su nombre sin acceder directamente al atributo.
    public boolean tieneElMismoNombreQue(String nombre) {
        return this.nombre != null && this.nombre.equalsIgnoreCase(nombre);
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delegacion)) return false;
        Delegacion otra = (Delegacion) o;
        return nombre != null && nombre.equalsIgnoreCase(otra.nombre);
    }
    @Override public int hashCode() {
        return nombre == null ? 0 : nombre.toLowerCase().hashCode();
    }

}
