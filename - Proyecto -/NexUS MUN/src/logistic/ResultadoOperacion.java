// PAQUETE MODELS //
package logistic;

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
    public ResultadoOperacion() {
        this.exitoso = false;
        this.mensaje = "";
    }
    public ResultadoOperacion(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    // ─────────────── GETTERS & SETTERS ───────────────
    public boolean isExitoso() { return exitoso; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

}
