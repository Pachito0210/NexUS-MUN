package entities;

/*
 * =====================================================================
 *  CLASE: Institucion                 (ROL 2 — paquete models)
 * =====================================================================
 *  - COMO FUNCIONA: Guarda el nombre y el tipo de una institución
 *    (ej. "Colegio San Carlos", "Colegio").
 *  - PARA QUÉ SIRVE: Agrupar a los Delegados, Sponsors y Presidentes
 *    según el colegio o universidad del que vienen.
 *  * CONCEPTO DE POO *: Asociación: un Delegado "tiene una"
 *    Institucion; Encapsulamiento de sus datos.
 * =====================================================================
 */
public class Institucion {

    private String nombre;
    private String tipo;

    /*
     * ---------------------------------------------------------------
     *  CONSTRUCTOR: Institucion()
     *  - COMO FUNCIONA: Crea la institución sin datos.
     *  - PARA QUÉ SIRVE: Lo necesita la carga desde JSON.
     *  * CONCEPTO DE POO *: Sobrecarga de constructores.
     * ---------------------------------------------------------------
     */
    public Institucion() {}

    /*
     * ---------------------------------------------------------------
     *  CONSTRUCTOR: Institucion(String nombre, String tipo)
     *  - COMO FUNCIONA: Asigna nombre y tipo quitando espacios extra.
     *  - PARA QUÉ SIRVE: Crear la institución en una sola línea.
     *  * CONCEPTO DE POO *: Sobrecarga de constructores.
     * ---------------------------------------------------------------
     */
    public Institucion(String nombre, String tipo) {
        this.nombre = nombre == null ? null : nombre.trim();
        this.tipo = tipo == null ? null : tipo.trim();
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODOS: getNombre() / setNombre(String) / getTipo() / setTipo(String)
     *  - COMO FUNCIONA: Leen o cambian los atributos privados.
     *  - PARA QUÉ SIRVE: Consultar y corregir los datos de la institución.
     *  * CONCEPTO DE POO *: Encapsulamiento.
     * ---------------------------------------------------------------
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo == null ? null : tipo.trim();
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODO: tieneElMismoNombreQue(String nombre)
     *  - COMO FUNCIONA: Compara los nombres con trim() y
     *    equalsIgnoreCase(); retorna false si alguno es null.
     *  - PARA QUÉ SIRVE: Evitar instituciones duplicadas como
     *    "Colegio X" y " colegio x ".
     *  * CONCEPTO DE POO *: Comportamiento propio del objeto: la
     *    institución sabe compararse a sí misma.
     * ---------------------------------------------------------------
     */
    public boolean tieneElMismoNombreQue(String nombre) {
        if (this.nombre == null || nombre == null) {
            return false;
        }
        return this.nombre.trim().equalsIgnoreCase(nombre.trim());
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODO: toString()
     *  - COMO FUNCIONA: Devuelve "Nombre (Tipo)".
     *  - PARA QUÉ SIRVE: Mostrar la institución en listas y consola.
     *  * CONCEPTO DE POO *: Polimorfismo (sobrescritura).
     * ---------------------------------------------------------------
     */
    @Override
    public String toString() {
        return nombre + (tipo == null || tipo.isEmpty() ? "" : " (" + tipo + ")");
    }
}
