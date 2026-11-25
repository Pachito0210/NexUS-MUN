// PAQUETE MODELS //
package entities;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Delegado
// ══════════════════════════════════════════════════════════════
//  Representante de una institución participante. Pertenece a una institución y su ID empieza por "DE" (DE001, DE002...).
//  * HERENCIA *
//    - Delegado ES UN Participante, hereda nombre, correo e ID y agrega su institución.
//  * POLIMORFISMO POR SOBRECARGA *
//    - Delegado sobrescribe getRol() de Participante y responde "Delegado".
// ══════════════════════════════════════════════════════════════
public class Delegado extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "DE";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Delegado() { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Devuelve el nombre del rol correspondiente a esta clase.
    //  - PARA QUÉ SIRVE: Permite identificar a este participante como Delegado.
    //  * POLIMORFISMO POR SOBRECARGA *
    //    - Implementa el metodo abstracto getRol() heredado de Participante.
    @Override public String getRol() {
        return "Delegado";
    }

    // ══════════════════════════════════════════════════════════════
    //  INSTITUCIÓN
    // ══════════════════════════════════════════════════════════════
    // setInstitucion()
    //  - COMO FUNCIONA: Asigna la institución recibida al atributo institucion.
    //  - PARA QUÉ SIRVE: Permite establecer a qué institución pertenece el Delegado.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado institucion.
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
    // getInstitucion()
    //  - COMO FUNCIONA: Devuelve la institución almacenada en el atributo institucion.
    //  - PARA QUÉ SIRVE: Permite consultar a qué institución pertenece el Delegado.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado institucion desde otras clases.
    public Institucion getInstitucion() {
        return institucion;
    }

}