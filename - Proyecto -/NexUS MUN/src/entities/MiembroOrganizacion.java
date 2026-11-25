// PAQUETE MODELS //
package entities;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  MiembroOrganizacion
// ══════════════════════════════════════════════════════════════
//  Integrante del equipo organizador. Tiene un cargo y su ID empieza por "US" (US001, US002...).
//  * HERENCIA *
//    - MiembroOrganizacion ES UN Participante, hereda nombre, correo e ID y solo agrega su cargo.
//  * POLIMORFISMO POR SOBRESCRITURA *
//    - MiembroOrganizacion sobrescribe getRol() de Participante y responde "MiembroOrganizacion".
// ══════════════════════════════════════════════════════════════
public class MiembroOrganizacion extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "US";
    private static int ultimoNumeroAsignado = 0;
    private String cargo;
    // ───────────────── CONSTRUCTORES ─────────────────
    public MiembroOrganizacion() { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: Devuelve el nombre del rol correspondiente a esta clase.
    //  - PARA QUÉ SIRVE: Permite identificar a este participante como miembro de la organización.
    //  * POLIMORFISMO POR SOBRECARGA *
    //    - Implementa el metodo abstracto getRol() heredado de Participante.
    @Override public String getRol() {
        return "MiembroOrganizacion";
    }

    // ══════════════════════════════════════════════════════════════
    //  CARGO
    // ══════════════════════════════════════════════════════════════
    // setCargo()
    //  - COMO FUNCIONA: Asigna el cargo recibido al atributo cargo.
    //  - PARA QUÉ SIRVE: Permite establecer la función que desempeña el miembro dentro de la organización.
    //  * ENCAPSULAMIENTO *
    //    - Controla la modificación del atributo privado cargo.
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    // getCargo()
    //  - COMO FUNCIONA: Devuelve el cargo almacenado en el atributo cargo.
    //  - PARA QUÉ SIRVE: Permite consultar la función que desempeña el miembro de la organización.
    //  * ENCAPSULAMIENTO *
    //    - Permite acceder al atributo privado cargo desde otras clases.
    public String getCargo() {
        return cargo;
    }

}