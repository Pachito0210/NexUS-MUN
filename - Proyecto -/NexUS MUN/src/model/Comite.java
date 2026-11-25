// PAQUETE E IMPORTAR //
package model;
import java.util.ArrayList;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Comite
// ══════════════════════════════════════════════════════════════
//  Representa un comité dentro de una edición del MUN.
//  * ENCAPSULAMIENTO *
//    - Protege sus atributos y permite administrarlos mediante métodos.
//  * COMPOSICIÓN *
//    - Administra las delegaciones que participan en el comité.
// ══════════════════════════════════════════════════════════════
public class Comite {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private String fecha;
    private String tema;
    private int cupoMaximo;
    private Salon salon;
    private ArrayList<Delegacion> delegaciones;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Comite() {
        delegaciones = new ArrayList<>();
    }
    // ══════════════════════════════════════════════════════════════
    //  GETTERS & SETTERS
    // ══════════════════════════════════════════════════════════════
    // getNombre()
    //  - COMO FUNCIONA: Devuelve el nombre del comité.
    //  - PARA QUÉ SIRVE: Consultar el nombre con el que se identifica el comité.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al nombre sin acceder directamente al atributo.
    public String getNombre() {
        return nombre;
    }
    // setNombre()
    //  - COMO FUNCIONA: Asigna el nombre recibido al comité.
    //  - PARA QUÉ SIRVE: Establecer o modificar el nombre del comité.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo nombre.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // getFecha()
    //  - COMO FUNCIONA: Devuelve la fecha asociada al comité.
    //  - PARA QUÉ SIRVE: Consultar cuándo se realiza el comité.
    //  * ENCAPSULAMIENTO *
    //    - Permite consultar la fecha sin acceder directamente al atributo.
    public String getFecha() {
        return fecha;
    }
    // setFecha()
    //  - COMO FUNCIONA: Asigna la fecha recibida al comité.
    //  - PARA QUÉ SIRVE: Establecer o modificar la fecha del comité.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo fecha.
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    // getTema()
    //  - COMO FUNCIONA: Devuelve el tema establecido para el comité.
    //  - PARA QUÉ SIRVE: Consultar el asunto que se debatirá.
    //  * ENCAPSULAMIENTO *
    //    - Permite consultar el tema sin acceder directamente al atributo.
    public String getTema() {
        return tema;
    }
    // setTema()
    //  - COMO FUNCIONA: Asigna el tema recibido al comité.
    //  - PARA QUÉ SIRVE: Establecer o modificar el tema de debate.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo tema.
    public void setTema(String tema) {
        this.tema = tema;
    }
    // getCupoMaximo()
    //  - COMO FUNCIONA: Devuelve el número máximo de delegaciones permitidas.
    //  - PARA QUÉ SIRVE: Consultar la capacidad establecida para el comité.
    //  * ENCAPSULAMIENTO *
    //    - Permite consultar el cupo sin acceder directamente al atributo.
    public int getCupoMaximo() {
        return cupoMaximo;
    }
    // setCupoMaximo()
    //  - COMO FUNCIONA: Asigna el cupo máximo recibido si no es negativo y no es inferior a las delegaciones actuales.
    //  - PARA QUÉ SIRVE: Establecer o modificar la capacidad del comité sin dejarla por debajo de su ocupación.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del cupo máximo mediante una condición.
    public boolean setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo < delegaciones.size() || cupoMaximo <= 0) {
            return false;
        }
        this.cupoMaximo = cupoMaximo;
        return true;
    }
    // getDelegaciones()
    //  - COMO FUNCIONA: Devuelve una lista con las delegaciones asignadas al comité.
    //  - PARA QUÉ SIRVE: Consultar las delegaciones que participan en el comité.
    //  * ENCAPSULAMIENTO *
    //    - Permite consultar las delegaciones administradas por el comité.
    public ArrayList<Delegacion> getDelegaciones() {
        return delegaciones;
    }
    // getSalon()
    //  - COMO FUNCIONA: Devuelve el salón asignado al comité.
    //  - PARA QUÉ SIRVE: Consultar en qué salón se realizará el comité.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al salón asignado sin acceder directamente al atributo.
    public Salon getSalon() {
        return salon;
    }
    // setSalon()
    //  - COMO FUNCIONA: Asigna el salón recibido al comité.
    //  - PARA QUÉ SIRVE: Establecer o modificar el salón donde se realizará el comité.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo salon.
    public boolean setSalon(Salon salon) {
        if (salon == null) {
            if (this.salon != null) {
                this.salon.liberar();
            }
            this.salon = null;
            return true;
        }
        boolean yaEsDeEsteComite = nombre != null && nombre.equalsIgnoreCase(salon.getNombreComiteAsignado());
        if (!yaEsDeEsteComite && !salon.asignarComite(nombre)) {
            return false;
        }
        if (this.salon != null && this.salon != salon) {
            this.salon.liberar();
        }
        this.salon = salon;
        return true;
    }


    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE CUPO
    // ══════════════════════════════════════════════════════════════
    // hayCupo()
    //  - COMO FUNCIONA: Compara la cantidad actual de delegaciones con el cupo máximo.
    //  - PARA QUÉ SIRVE: Verificar si el comité puede recibir otra delegación.
    //  * ABSTRACCIÓN *
    //    - Permite consultar la disponibilidad sin conocer cómo se calcula internamente.
    public boolean hayCupo() {
        return delegaciones.size() < cupoMaximo;
    }
    // getDelegacionesDisponibles()
    //  - COMO FUNCIONA: Resta la cantidad de delegaciones asignadas al cupo máximo.
    //  - PARA QUÉ SIRVE: Conocer cuántas delegaciones más puede recibir el comité.
    //  * ABSTRACCIÓN *
    //    - Resume la disponibilidad del comité en un valor numérico.
    public int getDelegacionesDisponibles() {
        return cupoMaximo - delegaciones.size();
    }
    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE DELEGACIONES
    // ══════════════════════════════════════════════════════════════
    // asignarDelegacion()
    //  - COMO FUNCIONA: Agrega una delegación si hay cupo y no se encuentra ya en la lista.
    //  - PARA QUÉ SIRVE: Registrar una delegación como participante del comité.
    //  * ABSTRACCIÓN *
    //    - Centraliza las condiciones necesarias para agregar una delegación al comité.
    public boolean asignarDelegacion(Delegacion delegacion) {
        if (delegacion == null || delegacion.getNombre() == null || !hayCupo()) {
            return false;
        }
        for (Delegacion existente : delegaciones) {
            if (existente.tieneElMismoNombreQue(delegacion.getNombre())) {
                return false;
            }
        }
        delegaciones.add(delegacion);
        return true;
    }
    // retirarDelegacion()
    //  - COMO FUNCIONA: Busca y elimina la delegación recibida de la lista.
    //  - PARA QUÉ SIRVE: Retirar una delegación que ya no participa en el comité.
    //  * ABSTRACCIÓN *
    //    - Permite retirar delegaciones sin manipular directamente la lista.
    public boolean retirarDelegacion(Delegacion delegacion) {
        return delegaciones.remove(delegacion);
    }

    // ══════════════════════════════════════════════════════════════
    //  COMPARACIÓN
    // ══════════════════════════════════════════════════════════════
    // tieneElMismoNombreQue()
    //  - COMO FUNCIONA: Compara el nombre del comité con el nombre recibido ignorando mayúsculas y minúsculas.
    //  - PARA QUÉ SIRVE: Verificar si un comité ya existe o localizarlo por su nombre.
    //  * ABSTRACCIÓN *
    //    - Permite comparar comités por su nombre sin acceder directamente al atributo.
    public boolean tieneElMismoNombreQue(String nombre) {
        return this.nombre != null && this.nombre.equalsIgnoreCase(nombre);
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comite)) return false;
        Comite otro = (Comite) o;
        return nombre != null && nombre.equalsIgnoreCase(otro.nombre);
    }
    @Override public int hashCode() {
        return nombre == null ? 0 : nombre.toLowerCase().hashCode();
    }

}
