// PAQUETE E IMPORTAR //
package models;
import java.util.Date;
import java.util.List;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Comite
// ══════════════════════════════════════════════════════════════
//  Comité del evento con su salón, fecha, cupo máximo, delegaciones y presidentes.
//  * COMPOSICIÓN *
//    - Comite contiene sus Delegaciones; si el comité desaparece, sus delegaciones también
// ══════════════════════════════════════════════════════════════
public class Comite {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private Date fecha;
    private String salon;
    private int cupoMaximo;
    private List<Delegacion> representaciones;
    private List<Presidente> presidentes;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Comite() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getNombre() { return null; }
    public void setNombre(String nombre) { }
    public Date getFecha() { return null; }
    public void setFecha(Date fecha) { }
    public String getSalon() { return null; }
    public void setSalon(String salon) { }
    public int getCupoMaximo() { return 0; }
    public void setCupoMaximo(int cupoMaximo) { }
    public List<Delegacion> getRepresentaciones() { return null; }
    public void setRepresentaciones(List<Delegacion> representaciones) { }
    public List<Presidente> getPresidentes() { return null; }
    public void setPresidentes(List<Presidente> presidentes) { }

    // ══════════════════════════════════════════════════════════════
    //  DELEGACIONES
    // ══════════════════════════════════════════════════════════════
    // asignarDelegacion()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public ResultadoOperacion asignarDelegacion(String nombre) {
        return null;
    }
    // getDelegados()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public List<Delegado> getDelegados() {
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  CUPOS
    // ══════════════════════════════════════════════════════════════
    // cuposDisponibles()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public int cuposDisponibles() {
        return 0;
    }
    // estaLleno()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public boolean estaLleno() {
        return false;
    }
    // getMotivoNoDisponible()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public String getMotivoNoDisponible() {
        return null;
    }

}
