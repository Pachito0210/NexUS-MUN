// PAQUETE E IMPORTAR //
package persistence;
import java.util.List;
import interfaces.IImportable;
import model.Comite;
import logistic.PreferenciaComite;
import logistic.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  ImportadorExcel
// ══════════════════════════════════════════════════════════════
//  Lee las inscripciones de los delegados desde un archivo de Excel.
//  * INTERFAZ *
//    - Implementa IImportable, que define cómo se validan y leen las inscripciones
// ══════════════════════════════════════════════════════════════
public class ImportadorExcel implements IImportable {

    // ───────────────── CONSTRUCTORES ─────────────────
    public ImportadorExcel() { }

    // ══════════════════════════════════════════════════════════════
    //  IMPORTACIÓN
    // ══════════════════════════════════════════════════════════════
    // validarArchivo()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public ResultadoOperacion validarArchivo(String ruta) {
        return null;
    }
    // leerInscripciones()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public List<PreferenciaComite> leerInscripciones(String ruta, List<Comite> comites) {
        return null;
    }

}
