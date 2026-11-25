// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Participante
// ══════════════════════════════════════════════════════════════
//  Persona inscrita en el evento. Define lo común (ID, nombre y correo) para todos los roles.
//  * ABSTRACCIÓN *
//    - Participante no se instancia directamente, solo define lo común de cada rol
//  * HERENCIA *
//    - Delegado, Sponsor, Presidente y MiembroOrganizacion son un Participante y heredan sus datos
//  * POLIMORFISMO *
//    - Cada subclase sobrescribe getRol() y responde con su propio rol
// ══════════════════════════════════════════════════════════════
public abstract class Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String idParticipante;
    private String nombre;
    private String correoElectronico;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Participante() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getIdParticipante() { return null; }
    public String getNombre() { return null; }
    public void setNombre(String nombre) { }
    public String getCorreoElectronico() { return null; }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public abstract String getRol();

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE CORREO
    // ══════════════════════════════════════════════════════════════
    // setCorreoElectronico()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public void setCorreoElectronico(String correoElectronico) {
    }
    // esCorreoValido()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public static boolean esCorreoValido(String correo) {
        return false;
    }

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE ID
    // ══════════════════════════════════════════════════════════════
    // armarId()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public static String armarId(String prefijo, int numero) {
        return null;
    }
    // extraerNumeroDelId()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public static int extraerNumeroDelId(String id) {
        return 0;
    }

}
