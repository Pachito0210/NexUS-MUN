# NexUS MUN — Sistema de Gestión de Modelos de Naciones Unidas

Proyecto académico para el curso de **Programación Orientada a Objetos (POO)** — Universidad de La Sabana, 2026-2.

El objetivo principal es construir un sistema que facilite la organización del Modelo de Naciones Unidas de la Universidad de la Sabana (MUN), aplicando principios POO y utilizando almacenamiento **dinámico en memoria** (ArrayList) y almacenamiento **permanente en archivos de texto** (.txt).

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

El sistema modela la gestión de un **Modelo de Naciones Unidas universitario**. Los principales actores y entidades son:

| Entidad | Descripción |
|---|---|
| `Delegado` | Participante que representa un país u organización en un comité. |
| `Sponsor` | Participante vinculado a una institución. |
| `Presidente` | Participante encargado de dirigir un comité. |
| `MiembroOrganizacion` | Participante con un cargo dentro del equipo organizador. |
| `CentroCrisis` | Participante perteneciente al equipo de crisis. |
| `JuntaDirectiva` | Participante con un cargo dentro de la dirección del evento. |
| `Institucion` | Entidad a la que pueden pertenecer participantes. |
| `Comite` | Espacio de debate con nombre, fecha, tema, cupo y delegaciones. |
| `Delegacion` | Representación que puede estar libre o asignada a un delegado. |
| `Salon` | Espacio físico con capacidad y posibilidad de asignación a un comité. |
| `Insumo` | Elemento entregable a un delegado (kit o comida). |
| `SolicitudLogistica` | Solicitud de atención logística de un salón. |

La versión actual permite registrar y consultar participantes, instituciones, comités y salones. La gestión completa de asignaciones, logística y reportes se encuentra en desarrollo.

---

## Conceptos POO demostrados

Cada concepto se puede rastrear a una o más clases concretas del proyecto:

### Herencia (IS-A)

```
Participante (abstracto)
├── Delegado
├── Sponsor
├── Presidente
├── MiembroOrganizacion
├── CentroCrisis
└── JuntaDirectiva

Insumo
├── Kit (Clase Anidada)
└── Comida (Clase Anidada)

BaseRepositorioTxt<T> (abstracto)
├── ParticipanteRepositorioTxt
├── InstitucionRepositorioTxt
├── ComiteRepositorioTxt
└── SalonRepositorioTxt
```

`Delegado` **es un** `Participante`. Hereda los atributos comunes (`idParticipante`, `nombre`, `correoElectronico`) y agrega su relación con una institución.

### Abstracción

- `Participante` es una clase **abstracta**: no se puede instanciar. Define `getRol()`, que identifica el tipo de participante.
- `BaseRepositorioTxt<T>` define las operaciones comunes de almacenamiento y delega a sus subclases la implementación de `getClave()`, `toLinea()` y `fromLinea()`.
- `Insumo` define el método abstracto `getDescripcion()` para sus subclases. Su lógica logística aún está en desarrollo.

### Polimorfismo

El mismo código funciona diferente según el tipo real del objeto en tiempo de ejecución:

```java
Participante p1 = new Delegado();
Participante p2 = new Sponsor();

p1.getRol(); // → Delegado
p2.getRol(); // → Sponsor
```

`ParticipanteRepositorioTxt` trabaja con objetos de tipo `Participante`, independientemente de su rol concreto. Al recuperar los registros, reconstruye la subclase correspondiente.

### Encapsulación

Los atributos del dominio se encapsulan y se accede a ellos mediante **getters** y **setters**. Algunos setters incluyen validaciones:

```java
Salon salon = new Salon();
salon.setCapacidad(30); // → true
salon.setCapacidad(-5); // → false
```

La capacidad no puede ser negativa ni cero.

### Interfaces

| Interfaz | Propósito |
|---|---|
| `IRepositorio<T>` | Contrato CRUD y búsqueda por clave para los repositorios TXT. |

El contrato de repositorios está implementado. Los demás forman parte de módulos en desarrollo.

### Herencia e implementación de interfaces

Una clase puede heredar de otra e implementar interfaces. En este proyecto, `Insumo` implementa `IEntregable`, contrato que también heredan `Kit` y `Comida`:

```java
Kit kit = new Kit();
Insumo insumo = kit;
IEntregable entregable = kit;
```

El ejemplo demuestra la compatibilidad de tipos, no que la entrega logística esté terminada. No se presenta la implementación simultánea de varias interfaces como una funcionalidad existente.

### Composición y relaciones HAS-A

```
Comite       HAS-A  ArrayList<Delegacion>
Comite       HAS-A  Salon
Delegacion   HAS-A  Delegado
Delegado     HAS-A  Institucion
```

```java
// Comite contiene una colección de delegaciones
private ArrayList<Delegacion> delegaciones;
```

**Diferencia con herencia:**
- Herencia (IS-A): `Delegado ES UN Participante`.
- Composición (HAS-A): `Comite TIENE delegaciones`, gestionadas mediante su colección.
- Asociación: `Delegado` se relaciona con una `Institucion`, que puede existir independientemente. No toda relación HAS-A implica composición estricta.

### Sobreescritura de métodos (`@Override`)

Las subclases de `Participante` sobreescriben `getRol()`. También se sobreescriben métodos heredados de `Object`, como `equals()` y `hashCode()` en `Delegacion`:

```java
Delegacion d1 = new Delegacion();
d1.setNombre("Colombia");
Delegacion d2 = new Delegacion();
d2.setNombre("colombia");

d1 == d2      // false — referencias distintas
d1.equals(d2) // true  — mismo nombre, sin distinguir mayúsculas
```

### Métodos estáticos (`static`)

Pertenecen a la **clase**, no a una instancia específica:

```java
Participante.esCorreoValido("delegado@ejemplo.com");
```

El método permite comprobar un correo sin crear un participante. `Main.main()` también es estático y constituye el punto de entrada del programa.

### Genericidad (`<T>`)

Permite escribir código que funciona con diferentes tipos de datos:

```java
IRepositorio<Institucion> repoInst = new InstitucionRepositorioTxt();
IRepositorio<Salon> repoSalon = new SalonRepositorioTxt();

// Ambos tienen getDataList(), insertData(), updateData(), deleteData()
```

La clase `BaseRepositorioTxt<T>` evita repetir la lógica CRUD en cada repositorio.

### Otros conceptos

La sobrecarga de métodos y constructores, los enum y la implementación múltiple de interfaces no se presentan como conceptos desarrollados de manera específica en esta versión. La carpeta `enums/` se encuentra vacía.

---

## Arquitectura del sistema

El proyecto se organiza en **4 capas**, con paquetes auxiliares de persistencia e interfaces:

```
┌──────────────────────────────────────────────────────────┐
│ CAPA UI (ui/)                                            │
│ MenuPrincipal — interfaz gráfica Java Swing              │
├──────────────────────────────────────────────────────────┤
│ CAPA SERVICIOS (services/)                               │
│ GestorComites, GestorLogistica, GestorReportes             │
│ Responsabilidad: coordinación y lógica de negocio.       │
│ Actualmente en desarrollo.                              │
├──────────────────────────────────────────────────────────┤
│ CAPA REPOSITORIOS (repositories/)                        │
│ BaseRepositorioTxt y repositorios especializados          │
│ Responsabilidad: CRUD y conversión objeto ↔ texto.        │
├──────────────────────────────────────────────────────────┤
│ CAPA MODELOS (entities/, model/, logistic/)               │
│ Responsabilidad: representar las entidades del dominio.  │
└──────────────────────────────────────────────────────────┘
  persistence/ — lectura y escritura de archivos
  interfaces/  — contratos del sistema
```

En la implementación actual, la interfaz utiliza directamente los repositorios. La capa de servicios está prevista para centralizar la coordinación de operaciones entre entidades y repositorios.

**Flujo de una operación** (ejemplo: registrar una institución):

```
Usuario introduce los datos en una ventana Swing
    ↓
MenuPrincipal.registrarInstitucion()      [UI]
    ↓
InstitucionRepositorioTxt.insertData()   [Repositorios]
    ↓
BaseRepositorioTxt / GestorArchivoTxt     [CRUD / Persistencia]
    ↓
src/files/instituciones.txt              [Archivo de texto]
```

---

## Estructura del proyecto

```
NexUS MUN/
└── src/
    ├── Main.java
    ├── entities/
    │   ├── CentroCrisis.java
    │   ├── Delegado.java
    │   ├── Institucion.java
    │   ├── JuntaDirectiva.java
    │   ├── MiembroOrganizacion.java
    │   ├── Participante.java
    │   ├── Presidente.java
    │   └── Sponsor.java
    ├── interfaces/
    │   ├── IConsultaReportes.java
    │   ├── IEntregable.java
    │   ├── IExportable.java
    │   ├── IGestionSolicitudes.java
    │   ├── IImportable.java
    │   └── IRepositorio.java
    ├── logistic/
    │   ├── Comida.java
    │   ├── Insumo.java
    │   ├── Kit.java
    │   ├── Material.java
    │   ├── PreferenciaComite.java
    │   ├── ResultadoOperacion.java
    │   ├── SolicitudLogistica.java
    │   └── Sugerencia.java
    ├── model/
    │   ├── Comite.java
    │   ├── Delegacion.java
    │   ├── EdicionMUN.java
    │   └── Salon.java
    ├── persistence/
    │   ├── ExportadorExcel.java
    │   ├── GestorArchivoTxt.java
    │   └── ImportadorExcel.java
    ├── repositories/
    │   ├── BaseRepositorioTxt.java
    │   ├── ComiteRepositorioTxt.java
    │   ├── InstitucionRepositorioTxt.java
    │   ├── LogisticaRepositorioTxt.java
    │   ├── ParticipanteRepositorioTxt.java
    │   └── SalonRepositorioTxt.java
    ├── services/
    │   ├── GestorComites.java
    │   ├── GestorLogistica.java
    │   └── GestorReportes.java
    ├── ui/
    │   └── MenuPrincipal.java
    └── files/
        ├── instituciones.txt
        ├── participantes.txt
        ├── comites.txt
        └── salones.txt
```

Los archivos TXT se crean al guardar registros. Se omiten las carpetas vacías auxiliares del diagrama.

---

## Jerarquía de clases

```
java.lang.Object
│
├── entities.Participante (abstract)
│   ├── entities.Delegado
│   ├── entities.Sponsor
│   ├── entities.Presidente
│   ├── entities.MiembroOrganizacion
│   ├── entities.CentroCrisis
│   └── entities.JuntaDirectiva
├── entities.Institucion
├── model.Comite
├── model.Delegacion
├── model.Salon
├── model.EdicionMUN
├── logistic.Insumo (abstract) ───── implements: IEntregable
│   ├── logistic.Kit
│   └── logistic.Comida
├── logistic.Material
├── logistic.PreferenciaComite
├── logistic.ResultadoOperacion
├── logistic.SolicitudLogistica
├── logistic.Sugerencia
├── repositories.BaseRepositorioTxt<T> ─ implements: IRepositorio<T>
│   ├── repositories.InstitucionRepositorioTxt
│   ├── repositories.ParticipanteRepositorioTxt
│   ├── repositories.ComiteRepositorioTxt
│   └── repositories.SalonRepositorioTxt
├── repositories.LogisticaRepositorioTxt
├── persistence.GestorArchivoTxt
├── persistence.ImportadorExcel ──── implements: IImportable
├── persistence.ExportadorExcel ──── implements: IExportable
├── services.GestorComites
├── services.GestorLogistica ─────── implements: IGestionSolicitudes
├── services.GestorReportes ──────── implements: IConsultaReportes
├── Main
└── ... javax.swing.JFrame
        └── ui.MenuPrincipal
```

---

## Formato de archivos de datos

Todos los archivos utilizan el delimitador `|` (pipe). Cada línea representa un registro. Los siguientes datos son ejemplos ilustrativos; la primera línea de cada bloque describe los campos y no se guarda como encabezado.

### `instituciones.txt`

```
nombre|tipo
Universidad de La Sabana|Universidad
```

### `participantes.txt`

```
id|rol|nombre|correoElectronico|cargo|nombreInstitucion
DE001|Delegado|Ana Perez|ana@ejemplo.com||Universidad de La Sabana
US001|MiembroOrganizacion|Juan Ruiz|juan@ejemplo.com|Logistica|
```

Los campos que no aplican al rol se guardan vacíos. El repositorio genera identificadores según el tipo de participante y reconstruye los objetos utilizando el rol almacenado.

### `salones.txt`

```
nombre|capacidad|nombreComiteAsignado
A101|30|
A102|40|DISEC
```

Un campo de comité vacío representa un salón disponible.

### `comites.txt`

```
nombre|fecha|tema|cupoMaximo|nombreSalon|delegaciones
DISEC|2026-11-27|Seguridad internacional|30|A102|Colombia:DE001;Brasil:
```

Las delegaciones se separan con `;`. Dentro de cada delegación, `:` separa su nombre del ID del delegado. Si no tiene delegado asignado, el ID queda vacío.

> **Nota sobre las relaciones en archivos:** se guardan nombres e identificadores, no objetos completos. Al leer, `ComiteRepositorioTxt` consulta los repositorios de salones y participantes para reconstruir las relaciones. La asignación coordinada y su actualización desde la interfaz aún están pendientes.

---

## Cómo ejecutar el proyecto

**Requisitos:** JDK 21 (entorno utilizado en el proyecto), IntelliJ IDEA o cualquier IDE con soporte Java.

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que `src/` esté marcado como Sources Root y que el directorio de trabajo sea la raíz del proyecto (`NexUS MUN/`).
3. Build → Build Project (o `Ctrl+F9`).
4. Ejecutar `Main.java`.

`Main` inicia `MenuPrincipal` mediante `SwingUtilities.invokeLater()`. La interfaz utiliza `javax.swing` y no requiere instalar JavaFX ni configurar módulos adicionales.

Al iniciar, aparece un menú gráfico con seis módulos:

```
┌──────────────────────────────────────────────────┐
│                    NexUS MUN                     │
├────────────────────────┬─────────────────────────┤
│ PARTICIPANTES          │ INSTITUCIONES           │
│ COMITÉS                │ SALONES                 │
│ LOGÍSTICA              │ REPORTES                │
└────────────────────────┴─────────────────────────┘
```

| Módulo | Operaciones disponibles | Pendiente |
|---|---|---|
| Participantes | Registrar y listar | Buscar por ID, editar y eliminar |
| Instituciones | Registrar, listar y buscar | Editar y eliminar |
| Comités | Crear, listar y buscar | Delegaciones y asignaciones |
| Salones | Registrar, listar y buscar | Asignar y liberar desde la interfaz |
| Logística | Navegar por el módulo | Kits, comidas, materiales, solicitudes y entregas |
| Reportes | Navegar por el módulo | Generación de reportes |

Las opciones pendientes muestran un mensaje informativo. La importación y exportación Excel tampoco están implementadas. El diagrama del menú es esquemático, no una captura de la aplicación.

---

## ArrayList vs Archivo de texto

El proyecto utiliza ambas formas de almacenamiento de manera complementaria. Los objetos se manipulan en memoria y los repositorios permiten conservar sus datos en disco.

```java
// MODO MEMORIA — colección temporal de delegaciones
ArrayList<Delegacion> delegaciones = new ArrayList<>();
Delegacion delegacion = new Delegacion();
delegacion.setNombre("Colombia");
delegaciones.add(delegacion);

// MODO ARCHIVO — consulta de instituciones almacenadas
InstitucionRepositorioTxt repositorio = new InstitucionRepositorioTxt();
ArrayList<Institucion> instituciones = repositorio.getDataList();
```

`getDataList()` lee las líneas del archivo, convierte cada registro en un objeto mediante `fromLinea()` y devuelve un `ArrayList<T>`. Modificar la lista recuperada no actualiza automáticamente el archivo; se deben utilizar las operaciones del repositorio.

A diferencia de un servicio con dos modos seleccionables, esta versión no implementa un modo dual de almacenamiento ni una demo de medición de tiempos.

### Comparación

| Característica | ArrayList (memoria) | Archivo .txt (disco) |
|---|---|---|
| Velocidad | Acceso directo en memoria | Requiere operaciones de entrada/salida |
| Persistencia | No — desaparece al cerrar | Sí — sobrevive al cierre |
| Complejidad | add, get, remove | Lectura, escritura y conversión de texto |
| Capacidad | Limitada por la RAM | Limitada por el disco |
| Uso típico | Colecciones y resultados temporales | Registros permanentes del evento |

### ¿Cuándo usar cada uno?

- **ArrayList en memoria:** para manipular delegaciones y resultados de consultas durante la ejecución.
- **Archivo de texto:** para conservar participantes, instituciones, salones y comités entre ejecuciones.
- **Base de datos (producción real):** para sistemas que requieran consultas complejas, transacciones y concurrencia.

---

## Reglas de negocio

Las reglas implementadas actualmente se distribuyen entre los modelos y los repositorios:

| Regla | Descripción |
|---|---|
| Clave única | El repositorio rechaza registros con claves duplicadas. |
| Correo | `Participante` dispone de una validación de formato de correo. |
| Capacidad del salón | Debe ser mayor que cero. |
| Disponibilidad del salón | No se puede asignar un salón ocupado por otro comité mediante `Comite.setSalon()`. |
| Cupo del comité | No se pueden agregar delegaciones si se alcanza el cupo máximo. |
| Delegación duplicada | No se admite otra delegación con el mismo nombre dentro del comité. |
| Delegado asignado | Una delegación debe estar libre para asignarle un delegado mediante `asignarDelegado()`. |
| Formato de datos | Los repositorios validan campos para evitar caracteres que interfieran con la serialización. |

Por ejemplo, el cupo se consulta antes de agregar una delegación:

```java
if (comite.hayCupo()) {
    comite.asignarDelegacion(delegacion);
}
```

`asignarDelegacion()` realiza sus propias validaciones, incluyendo la comprobación de nombres duplicados.

Al registrar una entidad desde la interfaz, el repositorio valida los datos, convierte el objeto en texto y guarda el registro. Las reglas de coordinación entre comités y salones, preferencias de delegados, entregas logísticas y reportes requieren completar la capa de servicios. No se garantiza todavía la sincronización automática de asignaciones entre archivos.

---

*Proyecto desarrollado en Java con interfaz gráfica Swing — sin frameworks externos ni base de datos.*

*El almacenamiento permanente se implementa mediante archivos de texto plano.*
