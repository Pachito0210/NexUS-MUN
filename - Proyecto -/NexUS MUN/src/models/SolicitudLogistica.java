// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  SolicitudLogistica
// ══════════════════════════════════════════════════════════════
//  Petición de material o apoyo logístico hecha desde un salón, con su nivel de urgencia.
// ══════════════════════════════════════════════════════════════
public class SolicitudLogistica {

    // ─────────────────── ATRIBUTOS ───────────────────
    private int idSolicitud;
    private String salon;
    private String tipo;
    private int urgencia;
    private boolean atentidad = false;
    // ───────────────── CONSTRUCTORES ─────────────────
    public SolicitudLogistica() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public int getIdSolicitud() { return 0; }
    public String getSalon() { return null; }
    public void setSalon(String salon) { }
    public String getTipo() { return null; }
    public void setTipo(String tipo) { }
    public int getUrgencia() { return 0; }
    public void setUrgencia(int urgencia) { }
    public boolean isAtentidad() { return false; }

    // ══════════════════════════════════════════════════════════════
    //  ATENCIÓN
    // ══════════════════════════════════════════════════════════════
    // atender()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public void atender() {
    }
    // getNombreUrgencia()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public String getNombreUrgencia() {
        return null;
    }

}
