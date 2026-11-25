// PAQUETE MODELS //
package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Comida
// ══════════════════════════════════════════════════════════════
//  Insumo de alimentación para un delegado, según el tipo de comida y la jornada.
//  * HERENCIA *
//    - Comida ES UN Insumo, hereda el estado de entrega y solo agrega su tipo y jornada
//  * POLIMORFISMO *
//    - Comida sobrescribe getDescripcion() de Insumo y describe la comida y su jornada
// ══════════════════════════════════════════════════════════════
public class Comida extends Insumo {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String tipoComida;
    private String jornada;
    // ───────────────── CONSTRUCTORES ─────────────────
    public Comida() { }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getTipoComida() { return null; }
    public void setTipoComida(String tipoComida) { }
    public String getJornada() { return null; }
    public void setJornada(String jornada) { }

    // ══════════════════════════════════════════════════════════════
    //  DESCRIPCIÓN
    // ══════════════════════════════════════════════════════════════
    // getDescripcion()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public String getDescripcion() {
        return null;
    }

}
