// PAQUETE MODELS //
package entities;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  JuntaDirectiva
// ══════════════════════════════════════════════════════════════
//  Integrante de la Junta Directiva. Tiene un cargo y su ID empieza por "JD" (JD001, JD002...).
//  * HERENCIA *
//    - JuntaDirectiva ES UN Participante, hereda nombre, correo e ID y agrega su cargo.
//  * POLIMORFISMO POR SOBRECARGA *
//    - JuntaDirectiva sobrescribe getRol() de Participante y responde "JuntaDirectiva".
// ══════════════════════════════════════════════════════════════
public class JuntaDirectiva extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "JD";
    private static int ultimoNumeroAsignado = 0;
    private String cargo;
    // ───────────────── CONSTRUCTORES ─────────────────
    public JuntaDirectiva() { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Devuelve el nombre del rol correspondiente a esta clase.
    //  - PARA QUÉ SIRVE: Permite identificar a este participante como integrante de la Junta Directiva.
    //  * POLIMORFISMO POR SOBRECARGA *
    //    - Implementa el metodo abstracto getRol() heredado de Participante.
    @Override public String getRol() {
        return "JuntaDirectiva";
    }

    // ══════════════════════════════════════════════════════════════
    //  CARGO
    // ══════════════════════════════════════════════════════════════
    // setCargo()
    //  - COMO FUNCIONA: Asigna el cargo recibido al atributo cargo.
    //  - PARA QUÉ SIRVE: Permite establecer la función que desempeña el integrante dentro de la Junta Directiva.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado cargo.
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    // getCargo()
    //  - COMO FUNCIONA: Devuelve el cargo almacenado en el atributo cargo.
    //  - PARA QUÉ SIRVE: Permite consultar la función que desempeña el integrante de la Junta Directiva.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado cargo desde otras clases.
    public String getCargo() {
        return cargo;
    }

}