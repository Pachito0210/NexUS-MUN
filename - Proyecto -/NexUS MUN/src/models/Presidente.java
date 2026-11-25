// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Presidente
// ══════════════════════════════════════════════════════════════
//  Persona que preside un comité. Pertenece a una institución y su ID empieza por "PR" (PR001, PR002...).
//  * HERENCIA *
//    - Presidente ES UN Participante, hereda nombre, correo e ID y solo agrega su institución
//  * POLIMORFISMO *
//    - Presidente sobrescribe getRol() de Participante y responde "Presidente"
// ══════════════════════════════════════════════════════════════
public class Presidente extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "PR";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Presidente() { }

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
