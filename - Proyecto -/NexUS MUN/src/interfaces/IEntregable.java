// PAQUETE E IMPORTAR //
package interfaces;
import entities.MiembroOrganizacion;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IEntregable
// ══════════════════════════════════════════════════════════════
//  Contrato para todo lo que se pueda entregar a un delegado.
// ══════════════════════════════════════════════════════════════
public interface IEntregable {
    // ══════════════════════════════════════════════════════════════
    //  ENTREGA
    // ══════════════════════════════════════════════════════════════
    // entregar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    void entregar(MiembroOrganizacion responsable);
    // estaEntregado()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    boolean estaEntregado();

}
