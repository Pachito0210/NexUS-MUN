// PAQUETE E IMPORTAR //
package interfaces;
import java.util.List;
import logistic.ResultadoOperacion;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IExportable
// ══════════════════════════════════════════════════════════════
//  Contrato para exportar datos a un archivo.
// ══════════════════════════════════════════════════════════════
public interface IExportable {
    // ══════════════════════════════════════════════════════════════
    //  EXPORTACIÓN
    // ══════════════════════════════════════════════════════════════
    // exportar()
    //  - COMO FUNCIONA: ...
    //  - PARA QUÉ SIRVE: ...
    //  * CONCEPTO DE POO *
    //    - ...
    ResultadoOperacion exportar(String ruta, List<String> encabezados, List<List<String>> filas);

}
