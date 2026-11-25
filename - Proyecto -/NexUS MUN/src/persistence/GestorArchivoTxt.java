// PAQUETE E IMPORTAR //
package persistence;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  GestorArchivoTxt
// ══════════════════════════════════════════════════════════════
//  Lee y escribe líneas de texto en un archivo de la carpeta src/files.
//  No sabe nada de modelos, solo de líneas.
//  * ENCAPSULAMIENTO *
//    - Nadie fuera de esta clase sabe cómo se arma la ruta ni cómo se abre el archivo.
//    - Este gestor maneja el archivo; convertir objetos a líneas le toca al repositorio.
// ══════════════════════════════════════════════════════════════
public class GestorArchivoTxt {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombreArchivo;
    // ───────────────── CONSTRUCTORES ─────────────────
    public GestorArchivoTxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // ─────────────── GETTERS & SETTERS ───────────────
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // ══════════════════════════════════════════════════════════════
    //  ARCHIVO
    // ══════════════════════════════════════════════════════════════
    // getFile()
    //  - COMO FUNCIONA: Crea y devuelve un objeto File con la ruta del archivo dentro de la carpeta src/files.
    //  - PARA QUÉ SIRVE: Permite acceder al archivo correspondiente sin tener que construir su ruta en cada método.
    //  * ENCAPSULAMIENTO *
    //    - Centraliza la construcción de la ruta y la mantiene oculta para las demás clases.
    private File getFile() {
        return new File("src/files/" + this.nombreArchivo);
    }

    // ══════════════════════════════════════════════════════════════
    //  LECTURA Y ESCRITURA
    // ══════════════════════════════════════════════════════════════
    // getDataOfFile()
    //  - COMO FUNCIONA: Verifica si el archivo existe y, si existe, lee sus líneas con BufferedReader y las devuelve en una lista; si no existe, retorna null.
    //  - PARA QUÉ SIRVE: Permite recuperar las líneas almacenadas para que el repositorio pueda convertirlas nuevamente en objetos.
    //  * ABSTRACCIÓN *
    //    - Ofrece la lectura del archivo mediante un método sin exponer directamente el proceso de abrirlo y leerlo línea por línea.
    public ArrayList<String> getDataOfFile() {
        ArrayList<String> lista = new ArrayList<>();
        File file = this.getFile();
        if (!file.exists()) {
            return lista;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lista.add(line);
            }
            reader.close();
            return lista;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    // writeFile()
    //  - COMO FUNCIONA: Recibe una línea y la agrega al final del archivo utilizando FileWriter en modo append.
    //  - PARA QUÉ SIRVE: Permite guardar un nuevo registro sin borrar los registros que ya existen en el archivo.
    //  * ABSTRACCIÓN *
    //    - Permite agregar información al archivo mediante una llamada sencilla sin manejar directamente el escritor.
    public boolean writeFile(String line) {
        File archivo = this.getFile();
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(line);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  ACTUALIZAR Y ELIMINAR
    // ══════════════════════════════════════════════════════════════
    // updateFile()
    //  - COMO FUNCIONA: Busca una línea específica, la reemplaza por otra y reescribe el archivo completo.
    //  - PARA QUÉ SIRVE: Permite actualizar un registro sin modificar los demás.
    //  * ABSTRACCIÓN *
    //    - Realiza la actualización del archivo mediante un metodo que oculta el proceso de lectura y reescritura.
    public boolean updateFile(String oldLine, String newLine) {
        ArrayList<String> lines = this.getDataOfFile();
        if (lines == null) {
            return false;
        }
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(oldLine)) {
                lines.set(i, newLine);
                return this.writeLinesToFile(lines);
            }
        }
        return false;
    }
    // deleteLineInFile()
    //  - COMO FUNCIONA: Busca una línea específica, la elimina de la lista y reescribe el archivo sin ella.
    //  - PARA QUÉ SIRVE: Permite eliminar un registro del archivo sin afectar los demás.
    //  * ABSTRACCIÓN *
    //    - Simplifica la eliminación de una línea sin exponer el proceso de reescritura.
    public boolean deleteLineInFile(String line) {
        ArrayList<String> lines = this.getDataOfFile();
        if (lines == null) {
            return false;
        }
        if (lines.remove(line)) {
            return this.writeLinesToFile(lines);
        }
        return false;
    }
    // writeLinesToFile()
    //  - COMO FUNCIONA: Recibe una lista de líneas, sobrescribe el archivo y escribe nuevamente todas las líneas recibidas.
    //  - PARA QUÉ SIRVE: Permite guardar los cambios realizados en una lista, como actualizaciones o eliminaciones.
    //  * ABSTRACCIÓN *
    //    - Centraliza la escritura completa del archivo para reutilizarla en los demás métodos.
    public boolean writeLinesToFile(List<String> lines) {
        File archivo = this.getFile();
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

}