// PAQUETE E IMPORTAR //
package repositories;
import interfaces.IRepositorio;
import persistence.GestorArchivoTxt;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  BaseRepositorioTxt<T>
// ══════════════════════════════════════════════════════════════
//  Repositorio genérico para archivos de texto. Implementa el CRUD; cada subclase solo define cómo convertir su objeto a una línea y viceversa.
//  * ABSTRACCIÓN *
//    - Es abstracta porque funciona como plantilla para repositorios específicos y no se instancia directamente.
//  * HERENCIA *
//    - Los repositorios concretos heredan el CRUD completo y solo definen toLinea, fromLinea y getClave.
//  * INTERFAZ *
//    - Implementa IRepositorio<T> y debe cumplir las operaciones que establece su contrato.
//  * COMPOSICIÓN *
//    - Contiene un GestorArchivoTxt que utiliza para delegar la lectura y escritura de archivos.
// ══════════════════════════════════════════════════════════════
public abstract class BaseRepositorioTxt<T> implements IRepositorio<T> {

    // ─────────────────── ATRIBUTOS ───────────────────
    protected GestorArchivoTxt gestor;
    protected String delimitador;
    // ───────────────── CONSTRUCTORES ─────────────────
    public BaseRepositorioTxt(String nombreArchivo, String delimitador) {
        this.gestor = new GestorArchivoTxt(nombreArchivo);
        this.delimitador = delimitador;
    }

    // ══════════════════════════════════════════════════════════════
    //  MÉTODOS QUE DEFINE CADA SUBCLASE
    // ══════════════════════════════════════════════════════════════
    // getClave()
    //  - COMO FUNCIONA: Recibe un objeto T y devuelve la clave única que permite identificarlo dentro del repositorio.
    //  - PARA QUÉ SIRVE: Permite buscar, actualizar y eliminar objetos utilizando un identificador común.
    //  * ABSTRACCIÓN *
    //    - Define cómo obtener la clave de un objeto sin establecer qué atributo específico utilizará cada repositorio.
    protected abstract String getClave(T entity);
    // toLinea()
    //  - COMO FUNCIONA: Recibe un objeto T y convierte sus atributos en una sola línea de texto separada por el delimitador definido.
    //  - PARA QUÉ SIRVE: Permite transformar los objetos del sistema en texto para almacenarlos en un archivo.
    //  * ABSTRACCIÓN *
    //    - Define la conversión a texto sin establecer cómo se organiza la información de cada modelo específico.
    protected abstract String toLinea(T entity);
    // fromLinea()
    //  - COMO FUNCIONA: Recibe una línea de texto, separa sus datos y construye un objeto T con la información recuperada.
    //  - PARA QUÉ SIRVE: Permite recuperar los objetos del sistema a partir de la información guardada en los archivos.
    //  * ABSTRACCIÓN *
    //    - Define la conversión de texto a objeto sin especificar cómo se reconstruye cada modelo concreto.
    protected abstract T fromLinea(String linea);

    // ══════════════════════════════════════════════════════════════
    //  LECTURA
    // ══════════════════════════════════════════════════════════════
    // getDataList()
    //  - COMO FUNCIONA: Lee todas las líneas del archivo, convierte cada una en un objeto T y agrega a la lista las entidades que se hayan reconstruido correctamente.
    //  - PARA QUÉ SIRVE: Permite recuperar todos los objetos almacenados para utilizarlos dentro del sistema.
    //  * GENÉRICOS *
    //    - Trabaja con una lista de objetos T sin depender de una clase específica.
    @Override public ArrayList<T> getDataList() {
        ArrayList<T> lista = new ArrayList<>();
        ArrayList<String> lineas = gestor.getDataOfFile();
        if (lineas == null) {
            return lista; // archivo inexistente: lista vacía
        }
        for (String linea : lineas) {
            T entidad = fromLinea(linea);
            if (entidad != null) {
                lista.add(entidad);
            }
        }
        return lista;
    }
    // buscarPorClave()
    //  - COMO FUNCIONA: Recorre los objetos recuperados y compara sus claves con la recibida, devolviendo el objeto coincidente o null si no existe.
    //  - PARA QUÉ SIRVE: Permite encontrar un registro específico utilizando su identificador.
    //  * ABSTRACCIÓN *
    //    - Ofrece una búsqueda por clave sin que otras clases deban recorrer manualmente los registros.
    @Override public T buscarPorClave(String clave) {
        for (T entidad : getDataList()) {
            if (getClave(entidad).equalsIgnoreCase(clave)) {
                return entidad;
            }
        }
        return null;
    }

    // ══════════════════════════════════════════════════════════════
    //  ESCRITURA
    // ══════════════════════════════════════════════════════════════
    // insertData()
    //  - COMO FUNCIONA: Verifica que el objeto no sea null ni tenga una clave repetida, lo agrega a la lista y guarda nuevamente todos los registros.
    //  - PARA QUÉ SIRVE: Permite registrar nuevos objetos evitando duplicados según la clave definida.
    //  * GENÉRICOS *
    //    - Permite insertar entidades de cualquier tipo T utilizando la misma lógica de registro.
    @Override public boolean insertData(T entity) {
        if (entity == null || !esValido(entity) || buscarPorClave(getClave(entity)) != null) {
            return false;
        }
        ArrayList<T> lista = getDataList();
        lista.add(entity);
        return guardarTodo(lista);
    }
    // updateData()
    //  - COMO FUNCIONA: Busca el objeto anterior por su clave, verifica que la nueva clave no esté repetida en otro registro, lo reemplaza y guarda la lista.
    //  - PARA QUÉ SIRVE: Permite actualizar un registro existente sin crear duplicados ni eliminar manualmente todo el archivo.
    //  * GENÉRICOS *
    //    - Utiliza objetos T tanto para identificar el registro original como para reemplazarlo por la versión actualizada.
    @Override public boolean updateData(T oldEntity, T newEntity) {
        if (oldEntity == null || newEntity == null || !esValido(newEntity)) {
            return false;
        }
        ArrayList<T> lista = getDataList();
        int posicion = posicionDe(lista, getClave(oldEntity));
        if (posicion == -1) {
            return false;
        }
        int repetida = posicionDe(lista, getClave(newEntity));
        if (repetida != -1 && repetida != posicion) {
            return false;
        }
        lista.set(posicion, newEntity);
        return guardarTodo(lista);
    }
    // deleteData()
    //  - COMO FUNCIONA: Busca el objeto por su clave, lo elimina de la lista y guarda nuevamente los registros restantes.
    //  - PARA QUÉ SIRVE: Permite eliminar un registro del repositorio evitando modificar manualmente el archivo.
    //  * GENÉRICOS *
    //    - Permite eliminar objetos T utilizando la misma operación sin importar el modelo concreto.
    @Override public boolean deleteData(T entity) {
        if (entity == null) {
            return false;
        }
        ArrayList<T> lista = getDataList();
        int posicion = posicionDe(lista, getClave(entity));
        if (posicion == -1) {
            return false;
        }
        lista.remove(posicion);
        return guardarTodo(lista);
    }

    // ══════════════════════════════════════════════════════════════
    //  APOYO INTERNO
    // ══════════════════════════════════════════════════════════════
    // dividir()
    //  - COMO FUNCIONA: Separa una línea de texto en partes utilizando el delimitador definido, tratándolo como texto literal y conservando campos vacíos.
    //  - PARA QUÉ SIRVE: Permite recuperar individualmente los datos que fueron almacenados en una misma línea.
    //  * ENCAPSULAMIENTO *
    //    - Centraliza la forma de separar las líneas para que las subclases reutilicen el mismo método.
    protected String[] dividir(String linea) {
        return linea.split(Pattern.quote(delimitador), -1);
    }
    // posicionDe()
    //  - COMO FUNCIONA: Recorre una lista y compara la clave de cada objeto con la recibida, devolviendo su posición o -1 si no encuentra coincidencias.
    //  - PARA QUÉ SIRVE: Permite identificar la ubicación de un registro dentro de la lista para poder actualizarlo o eliminarlo.
    //  * ENCAPSULAMIENTO *
    //    - Mantiene privada la lógica de búsqueda de posiciones que utilizan internamente las operaciones CRUD.
    private int posicionDe(List<T> lista, String clave) {
        for (int i = 0; i < lista.size(); i++) {
            if (getClave(lista.get(i)).equalsIgnoreCase(clave)) {
                return i;
            }
        }
        return -1;
    }
    // guardarTodo()
    //  - COMO FUNCIONA: Convierte todos los objetos de la lista en líneas de texto y las entrega al gestor para sobrescribir el archivo.
    //  - PARA QUÉ SIRVE: Permite guardar los cambios de inserción, actualización o eliminación realizados sobre la lista.
    //  * ABSTRACCIÓN *
    //    - Centraliza el proceso de convertir y guardar todos los registros sin que las operaciones CRUD deban manejar directamente el archivo.
    private boolean guardarTodo(List<T> lista) {
        List<String> lineas = new ArrayList<>();
        for (T entidad : lista) {
            lineas.add(toLinea(entidad));
        }
        return gestor.writeLinesToFile(lineas);
    }
    protected boolean esTextoSeguro(String texto) {
        return texto == null
                || (!texto.contains(delimitador)
                && !texto.contains("\n")
                && !texto.contains("\r"));
    }
    protected boolean esValido(T entity) {
        return true; // cada repositorio lo sobrescribe
    }

}