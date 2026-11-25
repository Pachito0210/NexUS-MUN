// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Sponsor
// ══════════════════════════════════════════════════════════════
//  Patrocinador del evento. Pertenece a una institución y su ID empieza por "SP" (SP001, SP002...).
//  * HERENCIA *
//    - Sponsor ES UN Participante, hereda nombre, correo e ID y solo agrega su institución
//  * POLIMORFISMO *
//    - Sponsor sobrescribe getRol() de Participante y responde "Sponsor"
// ══════════════════════════════════════════════════════════════
public class Sponsor extends Participante {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static String prefijoID = "SP";
    private static int ultimoNumeroAsignado = 0;
    private Institucion institucion;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Sponsor() { }

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
