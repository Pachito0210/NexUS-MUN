// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  MiembroOrganizacion
// ══════════════════════════════════════════════════════════════
//  Integrante del equipo organizador. Tiene un cargo y su ID empieza por "US" (US001, US002...).
//  * HERENCIA *
//    - MiembroOrganizacion ES UN Participante, hereda nombre, correo e ID y solo agrega su cargo
//  * POLIMORFISMO *
//    - MiembroOrganizacion sobrescribe getRol() de Participante y responde "MiembroOrganizacion"
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
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public String getRol() {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  CARGO
    // ══════════════════════════════════════════════════════════════
    // setCargo()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public void setCargo(String cargo) {
    }
    // getCargo()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public String getCargo() {
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
    public static String generarId() {
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
