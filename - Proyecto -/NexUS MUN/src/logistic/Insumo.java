// PAQUETE E IMPORTAR //
package logistic;
import java.util.Date;

import entities.Delegado;
import entities.MiembroOrganizacion;
import interfaces.IEntregable;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Insumo
// ══════════════════════════════════════════════════════════════
//  Elemento logístico que se le entrega a un delegado (kit, comida...). Guarda quién lo recibe, quién lo entregó y cuándo.
//  * HERENCIA *
//    - Kit y Comida son un Insumo, heredan el estado de entrega y solo agregan su contenido
//  * POLIMORFISMO *
//    - Cada subclase sobrescribe getDescripcion() y responde con su propia descripción
//  * ABSTRACCIÓN *
//    - Insumo no se instancia directamente, solo define lo común de los insumos
// ══════════════════════════════════════════════════════════════
public abstract class Insumo implements IEntregable {

    // ─────────────────── ATRIBUTOS ───────────────────
    private static int proximoId = 1;
    private int idInsumo;
    private Delegado delegado;
    private MiembroOrganizacion entregadoPor;
    private Date fechaEntrega;
    private boolean entregado;
    private String peticionEspecial;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Insumo() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public int getIdInsumo() { return 0; }
    public Delegado getDelegado() { return null; }
    public void setDelegado(Delegado delegado) { }
    public MiembroOrganizacion getEntregadoPor() { return null; }
    public Date getFechaEntrega() { return null; }
    public String getPeticionEspecial() { return null; }
    public void setPeticionEspecial(String peticionEspecial) { }

    // ══════════════════════════════════════════════════════════════
    //  GESTIÓN DE ENTREGA
    // ══════════════════════════════════════════════════════════════
    // entregar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public void entregar(MiembroOrganizacion responsable) {
    }
    // estaEntregado()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public boolean estaEntregado() {
        return false;
    }

    // ══════════════════════════════════════════════════════════════
    //  DESCRIPCIÓN
    // ══════════════════════════════════════════════════════════════
    // getDescripcion()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    public abstract String getDescripcion();

}
