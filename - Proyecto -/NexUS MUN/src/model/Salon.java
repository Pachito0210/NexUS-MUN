// PAQUETE MODEL //
package model;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Salon
// ══════════════════════════════════════════════════════════════
//  Representa un salón físico disponible para la organización del MUN.
//  * ENCAPSULAMIENTO *
//    - Protege sus atributos y administra su disponibilidad mediante métodos.
// ══════════════════════════════════════════════════════════════
public class Salon {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private int capacidad;
    private String nombreComiteAsignado;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Salon() { }

    // ══════════════════════════════════════════════════════════════
    //  GETTERS & SETTERS
    // ══════════════════════════════════════════════════════════════
    // getNombre()
    //  - COMO FUNCIONA: Devuelve el nombre o identificador del salón.
    //  - PARA QUÉ SIRVE: Consultar qué salón se está administrando.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al nombre sin acceder directamente al atributo.
    public String getNombre() {
        return nombre;
    }
    // setNombre()
    //  - COMO FUNCIONA: Asigna el nombre recibido al salón.
    //  - PARA QUÉ SIRVE: Establecer o modificar el identificador del salón.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo nombre.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // getCapacidad()
    //  - COMO FUNCIONA: Devuelve la cantidad máxima de personas que puede recibir el salón.
    //  - PARA QUÉ SIRVE: Consultar el límite de ocupación del espacio.
    //  * ENCAPSULAMIENTO *
    //    - Permite consultar la capacidad sin acceder directamente al atributo.
    public int getCapacidad() {
        return capacidad;
    }
    // setCapacidad()
    //  - COMO FUNCIONA: Asigna la capacidad recibida si es mayor que cero.
    //  - PARA QUÉ SIRVE: Establecer o modificar el límite de personas del salón.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo capacidad.
    public boolean setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            return false;
        }
        this.capacidad = capacidad;
        return true;
    }
    // getNombreComiteAsignado()
    //  - COMO FUNCIONA: Devuelve el nombre del comité asignado al salón.
    //  - PARA QUÉ SIRVE: Consultar qué comité ocupa actualmente el salón.
    //  * ENCAPSULAMIENTO *
    //    - Permite consultar la asignación sin acceder directamente al atributo.
    public String getNombreComiteAsignado() {
        return nombreComiteAsignado;
    }

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE DISPONIBILIDAD
    // ══════════════════════════════════════════════════════════════
    // estaDisponible()
    //  - COMO FUNCIONA: Verifica si el salón no tiene ningún comité asignado.
    //  - PARA QUÉ SIRVE: Saber si el salón puede asignarse a un comité.
    //  * ABSTRACCIÓN *
    //    - Permite consultar la disponibilidad sin revisar directamente el atributo.
    public boolean estaDisponible() {
        return nombreComiteAsignado == null;
    }
    // asignarComite()
    //  - COMO FUNCIONA: Asigna el nombre de un comité si el salón está disponible y el nombre recibido es válido.
    //  - PARA QUÉ SIRVE: Registrar qué comité ocupará el salón.
    //  * ABSTRACCIÓN *
    //    - Centraliza las condiciones necesarias para asignar un comité.
    public boolean asignarComite(String nombreComite) {
        if (nombreComite == null || nombreComite.trim().isEmpty() || !estaDisponible()) {
            return false;
        }
        nombreComiteAsignado = nombreComite;
        return true;
    }
    // liberar()
    //  - COMO FUNCIONA: Elimina el nombre del comité asignado estableciendo el atributo en null.
    //  - PARA QUÉ SIRVE: Dejar el salón disponible para otra asignación.
    //  * ABSTRACCIÓN *
    //    - Permite liberar el salón sin modificar directamente su atributo.
    public void liberar() {
        nombreComiteAsignado = null;
    }

    // ══════════════════════════════════════════════════════════════
    //  COMPARACIÓN
    // ══════════════════════════════════════════════════════════════
    // tieneElMismoNombreQue()
    //  - COMO FUNCIONA: Compara el nombre del salón con el nombre recibido ignorando mayúsculas y minúsculas.
    //  - PARA QUÉ SIRVE: Verificar si un salón ya existe o localizarlo por su identificador.
    //  * ABSTRACCIÓN *
    //    - Permite comparar salones por su nombre sin acceder directamente al atributo.
    public boolean tieneElMismoNombreQue(String nombre) {
        return this.nombre != null && this.nombre.equalsIgnoreCase(nombre);
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salon)) return false;
        Salon otro = (Salon) o;
        return nombre != null && nombre.equalsIgnoreCase(otro.nombre);
    }
    @Override public int hashCode() {
        return nombre == null ? 0 : nombre.toLowerCase().hashCode();
    }

}
