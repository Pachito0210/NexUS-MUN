// PAQUETE MODELS //
package entities;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Presidente
// ══════════════════════════════════════════════════════════════
//  Presidente de un comité organizador. Pertenece a una institución y su ID empieza por "PR" (PR001, PR002...).
//  * HERENCIA *
//    - Presidente ES UN Participante, hereda nombre, correo e ID y agrega su institución.
//  * POLIMORFISMO POR SOBRECARGA *
//    - Presidente sobrescribe getRol() de Participante y responde "Presidente".
// ══════════════════════════════════════════════════════════════
public class Presidente extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "PR";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Presidente() { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Devuelve el nombre del rol correspondiente a esta clase.
    //  - PARA QUÉ SIRVE: Permite identificar a este participante como Presidente.
    //  * POLIMORFISMO POR SOBRESCRITURA *
    //    - Implementa el metodo abstracto getRol() heredado de Participante.
    @Override public String getRol() {
        return "Presidente";
    }

    // ══════════════════════════════════════════════════════════════
    //  INSTITUCIÓN
    // ══════════════════════════════════════════════════════════════
    // setInstitucion()
    //  - COMO FUNCIONA: Asigna la institución recibida al atributo institucion.
    //  - PARA QUÉ SIRVE: Permite establecer a qué institución pertenece el Presidente.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado institucion.
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
    // getInstitucion()
    //  - COMO FUNCIONA: Devuelve la institución almacenada en el atributo institucion.
    //  - PARA QUÉ SIRVE: Permite consultar a qué institución pertenece el Presidente.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado institucion desde otras clases.
    public Institucion getInstitucion() {
        return institucion;
    }

}