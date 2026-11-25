// PAQUETE E IMPORTAR //
package interfaces;
import java.util.List;
import models.Comite;
import models.PreferenciaComite;
import models.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IImporble
// ══════════════════════════════════════════════════════════════
//  Contrato para importar inscripciones desde un archivo.
// ══════════════════════════════════════════════════════════════
public interface IImporble {
    // ══════════════════════════════════════════════════════════════
    //  IMPORTACIÓN
    // ══════════════════════════════════════════════════════════════
    // validarArchivo()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    ResultadoOperacion validarArchivo(String ruta);
    // leerInscripciones()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    List<PreferenciaComite> leerInscripciones(String ruta, List<Comite> comites);

}
