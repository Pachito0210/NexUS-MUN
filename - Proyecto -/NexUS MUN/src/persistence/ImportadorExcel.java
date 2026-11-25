// PAQUETE E IMPORTAR //
package persistence;
import java.util.List;
import interfaces.IImporble;
import models.Comite;
import models.PreferenciaComite;
import models.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  ImportadorExcel
// ══════════════════════════════════════════════════════════════
//  Lee las inscripciones de los delegados desde un archivo de Excel.
//  * INTERFAZ *
//    - Implementa IImporble, que define cómo se validan y leen las inscripciones
// ══════════════════════════════════════════════════════════════
public class ImportadorExcel implements IImporble {

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
