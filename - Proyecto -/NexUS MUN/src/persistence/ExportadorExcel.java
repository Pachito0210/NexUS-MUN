// PAQUETE E IMPORTAR //
package persistence;
import java.util.List;
import interfaces.IExportable;
import logistic.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  ExportadorExcel
// ══════════════════════════════════════════════════════════════
//  Exporta tablas de datos a un archivo de Excel.
//  * INTERFAZ *
//    - Implementa IExportable, que define cómo se exportan los datos
// ══════════════════════════════════════════════════════════════
public class ExportadorExcel implements IExportable {

    // ───────────────── CONSTRUCTORES ─────────────────
    public ExportadorExcel() { }

    // ══════════════════════════════════════════════════════════════
    //  EXPORTACIÓN
    // ══════════════════════════════════════════════════════════════
    // exportar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    @Override public ResultadoOperacion exportar(String ruta, List<String> encabezados, List<List<String>> filas) {
        return null;
    }

}
