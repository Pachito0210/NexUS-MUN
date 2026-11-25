// PAQUETE E IMPORTAR //
package interfaces;
import java.util.List;
import model.Comite;
import logistic.PreferenciaComite;
import logistic.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IImportable
// ══════════════════════════════════════════════════════════════
//  Contrato para importar inscripciones desde un archivo.
// ══════════════════════════════════════════════════════════════
public interface IImportable {
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
