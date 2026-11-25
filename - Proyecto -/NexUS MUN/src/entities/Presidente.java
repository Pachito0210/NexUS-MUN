package entities;

/*
 * =====================================================================
 *  CLASE: Presidente extends Participante   (ROL 2 — paquete models)
 * =====================================================================
 *  - COMO FUNCIONA: Hereda ID, nombre y correo de Participante y añade
 *    la Institucion a la que pertenece. Su ID se genera solo con el
 *    prefijo "PR" y un contador estático (PR001, PR002, ...).
 *  - PARA QUÉ SIRVE: Representar a los presidentes de comité del Modelo de
 *    Naciones Unidas.
 *  * CONCEPTO DE POO *: Herencia, Polimorfismo (getRol) y atributo de
 *    clase (static) compartido por todos los objetos Presidente.
 * =====================================================================
 */
public class Presidente extends Participante {

    public static final String PREFIJO = "PR";

    /** Último consecutivo usado. Es static: lo comparten todos los Presidentes. */
    private static int ultimoNumeroAsignado = 0;

    private Institucion institucion;

    /*
     * ---------------------------------------------------------------
     *  CONSTRUCTOR: Presidente()
     *  - COMO FUNCIONA: Crea el objeto sin datos y SIN gastar un ID.
     *  - PARA QUÉ SIRVE: Lo usa RepositorioJson al cargar: el ID real
     *    viene del archivo.
     *  * CONCEPTO DE POO *: Sobrecarga de constructores.
     * ---------------------------------------------------------------
     */
    public Presidente() {}

    /*
     * ---------------------------------------------------------------
     *  CONSTRUCTOR: Presidente(String nombre, String correo, Institucion inst)
     *  - COMO FUNCIONA: Llama a super(generarId(), nombre, correo), que
     *    valida el correo, y luego asigna la institución.
     *  - PARA QUÉ SIRVE: Crear un presidente nuevo con ID automático.
     *  * CONCEPTO DE POO *: Herencia (uso de super) y Encapsulamiento.
     * ---------------------------------------------------------------
     */
    public Presidente(String nombre, String correoElectronico, Institucion institucion) {
        super(generarId(), nombre, correoElectronico);
        this.institucion = institucion;
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODOS: getInstitucion() / setInstitucion(Institucion)
     *  - COMO FUNCIONA: Leen o cambian la institución asociada.
     *  - PARA QUÉ SIRVE: Saber de qué colegio/universidad viene.
     *  * CONCEPTO DE POO *: Asociación entre clases y Encapsulamiento.
     * ---------------------------------------------------------------
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODO: getRol()
     *  - COMO FUNCIONA: Retorna el texto "Presidente".
     *  - PARA QUÉ SIRVE: Identificar el tipo de participante.
     *  * CONCEPTO DE POO *: Polimorfismo (implementa el método abstracto
     *    de Participante con @Override).
     * ---------------------------------------------------------------
     */
    @Override
    public String getRol() {
        return "Presidente";
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODO: generarId()
     *  - COMO FUNCIONA: Suma 1 al contador estático y arma el ID con
     *    Participante.armarId(PREFIJO, numero) -> "PR001".
     *  - PARA QUÉ SIRVE: Que cada presidente nuevo tenga un ID único.
     *  * CONCEPTO DE POO *: Encapsulamiento (private: nadie fuera de la
     *    clase puede gastar IDs) y método de clase (static).
     * ---------------------------------------------------------------
     */
    private static String generarId() {
        ultimoNumeroAsignado++;
        return armarId(PREFIJO, ultimoNumeroAsignado);
    }

    /*
     * ---------------------------------------------------------------
     *  MÉTODO: sincronizarContador(int mayorNumero)
     *  - COMO FUNCIONA: Si mayorNumero > ultimoNumeroAsignado, actualiza
     *    el contador; si no, no hace nada (nunca retrocede).
     *  - PARA QUÉ SIRVE: Tras cargar el JSON, que el siguiente presidente
     *    no repita un ID que ya existe en el archivo.
     *  * CONCEPTO DE POO *: Método de clase (static) que modifica un
     *    atributo de clase.
     * ---------------------------------------------------------------
     */
    public static void sincronizarContador(int mayorNumero) {
        if (mayorNumero > ultimoNumeroAsignado) {
            ultimoNumeroAsignado = mayorNumero;
        }
    }
}
