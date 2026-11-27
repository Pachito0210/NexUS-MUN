// PAQUETE MODELS //
package entities;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Sponsor
// ══════════════════════════════════════════════════════════════
//  Representante de una institución y sus delegados en el evento. Pertenece a una institución y su ID empieza por "SP" (SP001, SP002...).
//  * HERENCIA *
//    - Sponsor ES UN Participante, hereda nombre, correo e ID y agrega su institución.
//  * POLIMORFISMO POR SOBRECARGA *
//    - Sponsor sobrescribe getRol() de Participante y responde "Sponsor".
// ══════════════════════════════════════════════════════════════
public class Sponsor extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "SP";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Sponsor() { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Devuelve el nombre del rol correspondiente a esta clase.
    //  - PARA QUÉ SIRVE: Permite identificar a este participante como Sponsor.
    //  * POLIMORFISMO POR SOBRESCRITURA *
    //    - Implementa el metodo abstracto getRol() heredado de Participante.
    @Override public String getRol() {
        return "Sponsor";
    }

    // ══════════════════════════════════════════════════════════════
    //  INSTITUCIÓN
    // ══════════════════════════════════════════════════════════════
    // setInstitucion()
    //  - COMO FUNCIONA: Asigna la institución recibida al atributo institucion.
    //  - PARA QUÉ SIRVE: Permite establecer a qué institución pertenece el Sponsor.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado institucion.
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
    // getInstitucion()
    //  - COMO FUNCIONA: Devuelve la institución almacenada en el atributo institucion.
    //  - PARA QUÉ SIRVE: Permite consultar a qué institución pertenece el Sponsor.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado institucion desde otras clases.
    public Institucion getInstitucion() {
        return institucion;
    }

}