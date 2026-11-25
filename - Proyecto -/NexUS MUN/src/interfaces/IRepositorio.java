// PAQUETE E IMPORTAR //
package interfaces;
import java.util.ArrayList;

// ══════════════════════════════════════════════════════════════
//  INTERFAZ  ·  IRepositorio<T>
// ══════════════════════════════════════════════════════════════
//  Contrato CRUD: cualquier clase del proyecto se puede guardar, buscar, editar y borrar con estas mismas operaciones.
//  * POLIMORFISMO PARAMÉTRICO *
//    - El parámetro <T> permite reutilizar el contrato para Institucion, MiembroOrganizacion, Comite, etc.
//  * INTERFAZ *
//    - Define QUÉ se puede hacer, no CÓMO; cada formato (TXT, JSON) lo implementa a su manera
// ══════════════════════════════════════════════════════════════
public interface IRepositorio<T> {

    // ══════════════════════════════════════════════════════════════
    //  LECTURA
    // ══════════════════════════════════════════════════════════════
    // getDataList()
    //  - COMO FUNCIONA: Devuelve una lista con todos los objetos almacenados del tipo T en el repositorio.
    //  - PARA QUÉ SIRVE: Permite consultar todos los registros disponibles, como instituciones, comités o participantes.
    //  * POLIMORFISMO PARAMÉTRICO *
    //    - La lista contiene objetos del tipo definido por T, permitiendo reutilizar el metodo con diferentes clases.
    ArrayList<T> getDataList();
    // buscarPorClave()
    //  - COMO FUNCIONA: Recibe una clave identificadora, busca el objeto que coincida y lo devuelve, o retorna null si no lo encuentra.
    //  - PARA QUÉ SIRVE: Permite localizar un registro específico sin tener que recorrer y gestionar manualmente toda la lista desde otras clases.
    //  * ABSTRACCIÓN *
    //    - Define una operación de búsqueda sin exponer cómo se realiza internamente.
    T buscarPorClave(String clave);

    // ══════════════════════════════════════════════════════════════
    //  ESCRITURA
    // ══════════════════════════════════════════════════════════════
    // insertData()
    //  - COMO FUNCIONA: Recibe un objeto de tipo T y lo incorpora al repositorio si cumple las condiciones de registro, devolviendo true si tuvo éxito.
    //  - PARA QUÉ SIRVE: Permite registrar nuevos objetos en el sistema y conservarlos mediante el mecanismo de persistencia utilizado.
    boolean insertData(T entity);
    // updateData()
    //  - COMO FUNCIONA: Recibe el objeto anterior y el actualizado, busca el anterior y lo reemplaza por el nuevo, devolviendo true si tuvo éxito.
    //  - PARA QUÉ SIRVE: Permite modificar la información de un registro existente sin tener que eliminarlo y registrarlo nuevamente.
    boolean updateData(T oldEntity, T newEntity);
    // deleteData()
    //  - COMO FUNCIONA: Recibe un objeto de tipo T, lo localiza en el repositorio y lo elimina si existe, devolviendo true si tuvo éxito.
    //  - PARA QUÉ SIRVE: Permite borrar registros que ya no deben formar parte de los datos almacenados.
    //  * ABSTRACCIÓN *
    //    - Define la operación de eliminación sin especificar cómo se ejecuta internamente en el repositorio.
    boolean deleteData(T entity);

}
