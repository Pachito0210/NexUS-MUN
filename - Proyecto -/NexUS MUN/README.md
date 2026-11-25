# Sistema de Gestión de Biblioteca

Proyecto académico para el curso de **Programación Orientada a Objetos (POO)** — Universidad de La Sabana, 2025.

El objetivo principal es aprender a construir sistemas usando principios POO y comprender la diferencia entre almacenamiento **dinámico en memoria** (ArrayList) y almacenamiento **permanente en archivos de texto** (.txt).

---

## Tabla de contenido

1. [Dominio del problema](#dominio-del-problema)
2. [Conceptos POO demostrados](#conceptos-poo-demostrados)
3. [Arquitectura del sistema](#arquitectura-del-sistema)
4. [Estructura del proyecto](#estructura-del-proyecto)
5. [Jerarquía de clases](#jerarquía-de-clases)
6. [Formato de archivos de datos](#formato-de-archivos-de-datos)
7. [Cómo ejecutar el proyecto](#cómo-ejecutar-el-proyecto)
8. [ArrayList vs Archivo de texto](#arraylist-vs-archivo-de-texto)
9. [Reglas de negocio](#reglas-de-negocio)

---

## Dominio del problema

El sistema modela la gestión básica de una **biblioteca universitaria**. Los principales actores y entidades son:

| Entidad | Descripción |
|---|---|
| `Estudiante` | Usuario con programa y semestre. Puede tomar hasta **3 libros** por **7 días**. |
| `Profesor` | Usuario con departamento y especialidad. Puede tomar hasta **5 libros** por **15 días**. |
| `Libro` | Recurso físico con ISBN, autor, editorial, año, género y cantidad de ejemplares. |
| `Autor` | Entidad independiente que se asocia a un Libro (composición). |
| `Prestamo` | Registro de la relación entre un usuario y un libro, con fechas y estado. |

---

## Conceptos POO demostrados

Cada concepto se puede rastrear a una o más clases concretas del proyecto:

### Herencia (IS-A)
```
Usuario (abstracto)
├── Estudiante
└── Profesor

BaseRepository (abstracto)
├── EstudianteRepository
├── ProfesorRepository
├── LibroRepository
└── PrestamoRepository
```
`Estudiante` **es un** `Usuario`. Hereda los atributos comunes (`id`, `nombre`, `telefono`, `email`) y agrega los propios (`programa`, `semestre`).

### Abstracción
- `Usuario` es una clase **abstracta**: no se puede instanciar. Define el contrato que deben cumplir sus subclases mediante métodos abstractos:
  - `getMaxLibros()` — cada tipo retorna su propio límite
  - `getDiasMaximoPrestamo()` — cada tipo retorna su propio plazo
  - `getTipoUsuario()` — identifica el tipo para la serialización en archivos

### Polimorfismo
El mismo código funciona diferente según el tipo real del objeto en tiempo de ejecución:

```java
Usuario u1 = new Estudiante(...);
Usuario u2 = new Profesor(...);

u1.getMaxLibros();          // → 3
u2.getMaxLibros();          // → 5

u1.getDiasMaximoPrestamo(); // → 7
u2.getDiasMaximoPrestamo(); // → 15
```

`PrestamoServicio.validarNuevoPrestamo(Usuario, Libro)` funciona para cualquier tipo de usuario sin necesitar saber si es Estudiante o Profesor.

### Encapsulación
Todos los atributos son `private` o `protected`. El acceso externo se hace exclusivamente a través de **getters** y **setters**:

```java
// MAL — acceso directo al atributo (no es posible con private)
estudiante.nombre = "Juan";

// BIEN — a través del setter encapsulado
estudiante.setNombre("Juan");
```

### Interfaces
El proyecto tiene tres interfaces:

| Interfaz | Propósito |
|---|---|
| `IRepository<T>` | Contrato CRUD (Create, Read, Update, Delete) para todos los repositorios |
| `IServicio<T>` | Contrato de búsqueda y consulta para todos los servicios |
| `IImprimible` | Contrato de presentación en consola (`imprimir()`, `aLinea()`) |

### Interfaces múltiples
Una clase puede implementar varias interfaces simultáneamente (Java no permite herencia múltiple de clases, pero sí de interfaces):

```java
// Estudiante hereda de Usuario E implementa IImprimible
public class Estudiante extends Usuario implements IImprimible { ... }

// Un objeto Estudiante puede usarse como cualquiera de los tres tipos
Estudiante e = new Estudiante(...);
Usuario u = e;          // polimorfismo por herencia
IImprimible imp = e;    // polimorfismo por interfaz
```

### Composición (HAS-A)
La **composición** modela relaciones donde un objeto *contiene* a otro:

```
Libro       HAS-A  Autor
Prestamo    HAS-A  Usuario
Prestamo    HAS-A  Libro
MenuPrincipal HAS-A EstudianteServicio, ProfesorServicio, ...
```

```java
// Libro TIENE UN Autor — el Autor es un atributo de tipo objeto
public class Libro {
    private Autor autor;  // COMPOSICIÓN
    ...
}

// Al llamar toString() de Libro, se accede al toString() de Autor
// a través de la composición:
libro.toString(); // → "[1] Clean Code | Autor: Robert C. Martin (USA) | 2008"
```

**Diferencia con herencia:**
- Herencia (IS-A): `Estudiante ES UN Usuario` → usar cuando existe una relación de especialización
- Composición (HAS-A): `Libro TIENE UN Autor` → usar cuando un objeto forma parte de otro

### Sobreescritura de métodos (`@Override`)
Los métodos heredados de `Object` se sobreescriben para dar comportamiento útil:

```java
// Sin sobreescribir toString():
System.out.println(libro); // → "modelos.Libro@1b6d3586" (dirección en memoria)

// Con toString() sobreescrito:
System.out.println(libro); // → "[1] Cien Años de Soledad | García Márquez | 1967 | Novela"
```

`equals()` permite comparar por **contenido** en lugar de por **referencia**:

```java
Autor a1 = new Autor("García Márquez", "Colombiana");
Autor a2 = new Autor("García Márquez", "Colombiana");

a1 == a2          // false — referencias distintas en memoria
a1.equals(a2)     // true  — mismo nombre (comparación de contenido)
```

### Sobrecarga de métodos (Overloading)
Mismo nombre de método, diferente firma (parámetros):

```java
// En LibroServicio — tres versiones de buscar():
Libro            buscar(String isbn)   // busca por ISBN exacto
ArrayList<Libro> buscar(Genero genero) // busca por género (enum)
ArrayList<Libro> buscar(int anio)      // busca por año de publicación

// En EstudianteServicio — dos versiones de buscarPorNombre():
ArrayList<Estudiante> buscarPorNombre(String nombre)
ArrayList<Estudiante> buscarPorNombre(String nombre, boolean parcial)
```

### Sobrecarga de constructores
```java
// Tres constructores de Libro — mismo nombre, diferente cantidad de parámetros
new Libro(id, isbn, titulo, autor, editorial, anio, ejemplares, genero) // completo
new Libro(isbn, titulo, autor, editorial, anio, ejemplares, genero)     // sin ID
new Libro(isbn, titulo)                                                  // mínimo

// Dos constructores de EstudianteServicio — determinan el modo de almacenamiento
new EstudianteServicio()                        // MODO ARCHIVO
new EstudianteServicio(ArrayList<Estudiante>)   // MODO ARRAYLIST
```

### Métodos y atributos estáticos (`static`)
Pertenecen a la **clase**, no a una instancia específica:

```java
// Método estático — se llama sin crear un objeto
Autor autorGenerico = Autor.desconocido();

// Contador estático — compartido entre TODAS las instancias de Prestamo
Prestamo.generarId(); // → 1, 2, 3, ... (incrementa con cada llamada)

// Constante estática — valor fijo compartido por todos los Libros
Libro.MAX_EJEMPLARES_POR_LIBRO // → 20
```

### Enum
```java
// Sin enum (propenso a errores):
String genero = "NOVELA"; // ¿Y si alguien escribe "Novela" o "novela"?

// Con enum (seguro en tiempo de compilación):
Genero genero = Genero.NOVELA; // el compilador valida que sea un valor válido

// Iteración del enum — útil para mostrar opciones al usuario:
for (Genero g : Genero.values()) {
    System.out.println(g.getDescripcion()); // → Novela, Ciencia y Tecnología, ...
}
```

### Genericidad (`<T>`)
Permite escribir código que funciona con cualquier tipo de dato:

```java
// Una sola interfaz sirve para todos los repositorios
IRepository<Estudiante> repoEst = new EstudianteRepository();
IRepository<Profesor>   repoProf = new ProfesorRepository();
IRepository<Libro>      repoLibro = new LibroRepository();

// Todos tienen los mismos métodos: getDataList(), insertData(), updateData(), deleteData()
```

---

## Arquitectura del sistema

El proyecto sigue una **arquitectura de 4 capas**. Cada capa tiene una responsabilidad única y solo se comunica con la capa inmediatamente adyacente:

```
┌─────────────────────────────────────────────────────────────┐
│                     CAPA UI (ui/)                           │
│  MenuPrincipal, MenuEstudiantes, MenuProfesores,            │
│  MenuLibros, MenuPrestamos, DemoAlmacenamiento              │
│  Responsabilidad: interacción con el usuario (entrada/salida│
│  por consola). No contiene lógica de negocio.              │
├─────────────────────────────────────────────────────────────┤
│                  CAPA SERVICIOS (servicios/)                 │
│  EstudianteServicio, ProfesorServicio,                      │
│  LibroServicio, PrestamoServicio                            │
│  Responsabilidad: lógica de negocio, validaciones,          │
│  coordinar operaciones entre repositorios.                  │
├─────────────────────────────────────────────────────────────┤
│                CAPA REPOSITORIOS (repositorios/)             │
│  EstudianteRepository, ProfesorRepository,                  │
│  LibroRepository, PrestamoRepository                        │
│  Responsabilidad: operaciones CRUD sobre archivos de texto. │
│  Convierte objetos Java ↔ líneas de texto.                  │
├─────────────────────────────────────────────────────────────┤
│                  CAPA MODELOS (modelos/)                     │
│  Usuario, Estudiante, Profesor, Libro, Autor, Prestamo      │
│  + enums/Genero  + interfaces/IRepository, IServicio,       │
│    IImprimible   + persistencia/FileManage                  │
│  Responsabilidad: representar las entidades del dominio.    │
│  No contiene lógica de acceso a datos.                      │
└─────────────────────────────────────────────────────────────┘
```

**Flujo de una operación** (ejemplo: registrar un estudiante):

```
Usuario escribe datos en consola
    ↓
MenuEstudiantes.registrarEstudiante()   [capa UI]
    ↓
EstudianteServicio.insertData(est)      [capa Servicios]
    ↓
EstudianteRepository.insertData(est)    [capa Repositorios]
    ↓
FileManage.writeFile("1|Juan|...")      [capa Modelos/Persistencia]
    ↓
estudiantes.txt en disco                [archivo de texto]
```

---

## Estructura del proyecto

```
SistemaGestionBiblioteca/
└── src/
    ├── Main.java                              Punto de entrada — lanza MenuPrincipal
    │
    ├── enums/
    │   └── Genero.java                        Enum de géneros literarios
    │
    ├── interfaces/
    │   ├── IRepository.java                   Contrato CRUD genérico <T>
    │   ├── IServicio.java                     Contrato de consulta genérico <T>
    │   └── IImprimible.java                   Contrato de impresión en consola
    │
    ├── modelos/
    │   ├── Usuario.java                       Clase abstracta base de usuarios
    │   ├── Estudiante.java                    Max 3 libros, 7 días — implements IImprimible
    │   ├── Profesor.java                      Max 5 libros, 15 días — implements IImprimible
    │   ├── Autor.java                         Composición con Libro — método estático
    │   ├── Libro.java                         Composición (HAS-A Autor) — enum Genero
    │   └── Prestamo.java                      Composición (HAS-A Usuario + Libro)
    │
    ├── persistencia/
    │   └── FileManage.java                    Leer/escribir líneas en archivos .txt
    │
    ├── repositorios/
    │   ├── BaseRepository.java                Clase abstracta — hereda fileManage + delimiter
    │   ├── EstudianteRepository.java          CRUD sobre estudiantes.txt
    │   ├── ProfesorRepository.java            CRUD sobre profesores.txt
    │   ├── LibroRepository.java               CRUD sobre libros.txt + búsquedas
    │   └── PrestamoRepository.java            CRUD sobre prestamos.txt + resolución de IDs
    │
    ├── servicios/
    │   ├── EstudianteServicio.java            Lógica + MODO DUAL (archivo/arraylist)
    │   ├── ProfesorServicio.java              Lógica + MODO DUAL (archivo/arraylist)
    │   ├── LibroServicio.java                 Disponibilidad + búsquedas sobrecargadas
    │   └── PrestamoServicio.java              Validación de reglas de préstamo
    │
    ├── ui/
    │   ├── MenuPrincipal.java                 Menú raíz — inicializa todos los servicios
    │   ├── MenuEstudiantes.java               CRUD de estudiantes por consola
    │   ├── MenuProfesores.java                CRUD de profesores por consola
    │   ├── MenuLibros.java                    CRUD de libros + selección de Genero
    │   ├── MenuPrestamos.java                 Flujo de préstamo con validación
    │   └── DemoAlmacenamiento.java            Demostración educativa ArrayList vs Archivo
    │
    └── files/
        ├── estudiantes.txt                    19 registros semilla
        ├── profesores.txt                     6 registros semilla
        ├── libros.txt                         7 registros semilla
        └── prestamos.txt                      2 registros semilla
```

---

## Jerarquía de clases

```
java.lang.Object
│
├── modelos.Usuario (abstract) ─────────────────── implements: ninguna
│   ├── modelos.Estudiante ──────────────────────── implements: IImprimible
│   └── modelos.Profesor ────────────────────────── implements: IImprimible
│
├── modelos.Autor ───────────────────────────────── (composición con Libro)
├── modelos.Libro ───────────────────────────────── implements: IImprimible
├── modelos.Prestamo ────────────────────────────── (composición con Usuario + Libro)
│
├── repositorios.BaseRepository (abstract)
│   ├── repositorios.EstudianteRepository ─────── implements: IRepository<Estudiante>
│   ├── repositorios.ProfesorRepository ───────── implements: IRepository<Profesor>
│   ├── repositorios.LibroRepository ──────────── implements: IRepository<Libro>
│   └── repositorios.PrestamoRepository ───────── implements: IRepository<Prestamo>
│
├── persistencia.FileManage
│
├── servicios.EstudianteServicio ───────────────── implements: IServicio<Estudiante>
├── servicios.ProfesorServicio ─────────────────── implements: IServicio<Profesor>
├── servicios.LibroServicio ────────────────────── implements: IServicio<Libro>
├── servicios.PrestamoServicio ─────────────────── implements: IServicio<Prestamo>
│
├── ui.MenuPrincipal
├── ui.MenuEstudiantes
├── ui.MenuProfesores
├── ui.MenuLibros
├── ui.MenuPrestamos
└── ui.DemoAlmacenamiento

enums.Genero (enum)
interfaces.IRepository<T> (interface)
interfaces.IServicio<T> (interface)
interfaces.IImprimible (interface)
```

---

## Formato de archivos de datos

Todos los archivos usan el delimitador `|` (pipe). Cada línea es un registro.

### `estudiantes.txt`
```
id|nombre|telefono|email|programa|semestre
1|Pablo Picapiedra|3225634789|pablo@mail.com|Ingenieria informatica|3
2|Fernanda Martinez|324854121|fer@mail.com|Ingenieria informatica|7
```

### `profesores.txt`
```
id|nombre|telefono|email|departamento|especialidad
1|Carlos Mendez|3201234567|cmendez@unisabana.edu.co|Ingenieria de Sistemas|Inteligencia Artificial
```

### `libros.txt`
```
id|isbn|titulo|autorNombre|editorial|anio|cantidadEjemplares|genero
1|978-84-376-0494-7|Cien Anos de Soledad|Gabriel Garcia Marquez|Sudamericana|1967|3|NOVELA
2|978-0-13-468599-1|Clean Code|Robert C. Martin|Prentice Hall|2008|5|TECNOLOGIA
```

> **Nota sobre composición en archivos:** el archivo solo guarda el **nombre** del autor (no el objeto completo). Al leer el archivo, el repositorio reconstruye el objeto `Autor` en memoria. Esto ilustra la diferencia entre cómo se representan los datos en un archivo plano (texto) vs. en memoria (objetos con composición).

### `prestamos.txt`
```
id|idUsuario|tipoUsuario|idLibro|fechaInicio|fechaFin|estado
1|1|modelos.Estudiante|2|2025-05-01|2025-05-08|ACTIVO
2|1|modelos.Profesor|5|2025-05-10|2025-05-25|ACTIVO
```

> El archivo guarda los **IDs** del usuario y el libro (no los objetos). Al leer, `PrestamoRepository` busca los objetos completos en los otros repositorios y los ensambla usando polimorfismo (la variable `usuario` puede apuntar a un `Estudiante` o un `Profesor`).

---

## Cómo ejecutar el proyecto

**Requisitos:** Java 11 o superior, IntelliJ IDEA (o cualquier IDE con soporte Java).

1. Abrir el proyecto en IntelliJ IDEA
2. Verificar que el directorio de trabajo sea la raíz del proyecto (`SistemaGestionBiblioteca/`)
3. Build → Build Project (o `Ctrl+F9`)
4. Ejecutar `Main.java`

Al iniciar, aparece el menú principal:

```
╔════════════════════════════════════════════╗
║    SISTEMA DE GESTIÓN DE BIBLIOTECA        ║
║    Universidad de La Sabana - POO 2025     ║
╚════════════════════════════════════════════╝

┌────────────────────────────────────────────┐
│              MENÚ PRINCIPAL                │
├────────────────────────────────────────────┤
│  1. Gestión de Estudiantes                 │
│  2. Gestión de Profesores                  │
│  3. Gestión de Libros                      │
│  4. Gestión de Préstamos                   │
│  5. Demo: ArrayList vs Archivo [EDUCATIVO] │
│  0. Salir                                  │
└────────────────────────────────────────────┘
```

> **Para la demo educativa:** seleccione la opción **5**. La demo agrega un estudiante de prueba en ambos modos (archivo y ArrayList), mide los tiempos, verifica la persistencia y luego limpia el dato del archivo.

---

## ArrayList vs Archivo de texto

Esta es la comparación central del proyecto. `EstudianteServicio` puede operar en **dos modos** seleccionados en el constructor:

```java
// MODO ARCHIVO — los datos persisten en estudiantes.txt
EstudianteServicio servicioArchivo = new EstudianteServicio();

// MODO ARRAYLIST — los datos viven solo en RAM
ArrayList<Estudiante> lista = new ArrayList<>();
lista.add(new Estudiante(1, "Demo", "300", "demo@uni.co", "POO", 1));
EstudianteServicio servicioMemoria = new EstudianteServicio(lista);
```

El **mismo método** `getDataList()` se comporta diferente según el modo:

```java
// Si modoMemoria = false → lee desde estudiantes.txt (I/O de disco)
// Si modoMemoria = true  → retorna el ArrayList en RAM (solo memoria)
public ArrayList<Estudiante> getDataList() {
    if (modoMemoria) {
        return new ArrayList<>(listaMemoria);
    } else {
        return repositorio.getDataList();
    }
}
```

### Comparación

| Característica | ArrayList (memoria) | Archivo .txt (disco) |
|---|---|---|
| **Velocidad** | Muy rápido (nanosegundos) | Más lento (milisegundos) |
| **Persistencia** | No — desaparece al cerrar | Sí — sobrevive al cierre |
| **Complejidad** | Simple (add, get, remove) | Requiere parsear texto |
| **Capacidad** | Limitada por la RAM | Limitada por el disco |
| **Uso típico** | Caché, procesamiento temporal | Datos permanentes |
| **Equivalente en producción** | Cache (Redis, Memcached) | Base de datos (MySQL, etc.) |

### ¿Cuándo usar cada uno?

- **ArrayList en memoria:** cuando los datos son temporales (resultados de búsqueda, sesión de un usuario, datos de prueba en tests).
- **Archivo de texto:** cuando los datos deben sobrevivir al cierre del programa (configuraciones, registros, inventarios pequeños).
- **Base de datos (producción real):** combina velocidad y persistencia, con capacidades adicionales de consulta, transacciones y concurrencia.

---

## Reglas de negocio

Las reglas se validan en la capa de **Servicios**, específicamente en `PrestamoServicio.validarNuevoPrestamo()`:

| Regla | Descripción |
|---|---|
| Disponibilidad | El libro debe tener al menos 1 ejemplar disponible |
| Límite por usuario | El usuario no puede superar su `maxLibros` activo simultáneo |
| Duplicado | El usuario no puede tener prestado el mismo libro dos veces |

El límite varía por tipo de usuario gracias al **polimorfismo**:

```java
// El mismo código valida para Estudiante (max=3) y Profesor (max=5)
// sin necesitar saber el tipo concreto:
if (prestamosActivos >= usuario.getMaxLibros()) {
    return "Límite alcanzado: " + usuario.getMaxLibros() + " libro(s)";
}
```

Al registrar un préstamo exitoso, el sistema:
1. Reduce en 1 el `cantidadEjemplares` del libro (actualiza `libros.txt`)
2. Crea el registro del préstamo con estado `"ACTIVO"` (escribe en `prestamos.txt`)

Al registrar una devolución:
1. Cambia el estado del préstamo a `"DEVUELTO"` (actualiza `prestamos.txt`)
2. Aumenta en 1 el `cantidadEjemplares` del libro (actualiza `libros.txt`)

---

*Proyecto desarrollado con Java 11 — sin frameworks externos, sin base de datos.*
*El almacenamiento permanente se implementa con archivos de texto plano para exponer el mecanismo subyacente.*
