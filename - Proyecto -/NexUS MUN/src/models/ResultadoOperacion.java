// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  ResultadoOperacion
// ══════════════════════════════════════════════════════════════
//  Resultado de una operación: indica si fue exitosa y trae un mensaje.
// ══════════════════════════════════════════════════════════════
public class ResultadoOperacion {

    // ─────────────────── ATRIBUTOS ───────────────────
    private boolean exitoso;
    private String mensaje;
    // ───────────────── CONSTRUCTORES ─────────────────
    public ResultadoOperacion() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public boolean isExitoso() { return false; }
    public String getMensaje() { return null; }
    public void setMensaje(String mensaje) { }

}
