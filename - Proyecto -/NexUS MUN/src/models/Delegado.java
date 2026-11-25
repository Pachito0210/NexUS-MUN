// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Delegado
// ══════════════════════════════════════════════════════════════
//  Persona que representa a un país u organización en un comité. Pertenece a una institución y su ID empieza por "DE" (DE001, DE002...).
//  * HERENCIA *
//    - Delegado ES UN Participante, hereda nombre, correo e ID y solo agrega su institución
//  * POLIMORFISMO *
//    - Delegado sobrescribe getRol() de Participante y responde "Delegado"
// ══════════════════════════════════════════════════════════════
public class Delegado extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "DE";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Delegado() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public Institucion getInstitucion() { return null; }
    public void setInstitucion(Institucion institucion) { }

    // ══════════════════════════════════════════════════════════════
    //  ROL
    // ══════════════════════════════════════════════════════════════
    // getRol()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public String getRol() {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE ID
    // ══════════════════════════════════════════════════════════════
    // generarId()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    private static String generarId() {
        return null;
    }
    // sincronizarContador()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public static void sincronizarContador(int mayorNumero) {
    }

}
