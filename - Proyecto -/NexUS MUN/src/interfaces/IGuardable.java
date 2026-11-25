// PAQUETE E IMPORTAR //
package interfaces;
import models.EdicionMUN;
import models.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IGuardable
// ══════════════════════════════════════════════════════════════
//  Contrato para guardar y cargar una edición del evento.
// ══════════════════════════════════════════════════════════════
public interface IGuardable {
    // ══════════════════════════════════════════════════════════════
    //  PERSISTENCIA
    // ══════════════════════════════════════════════════════════════
    // guardar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    ResultadoOperacion guardar(EdicionMUN edicion);
    // cargar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    EdicionMUN cargar();

}
