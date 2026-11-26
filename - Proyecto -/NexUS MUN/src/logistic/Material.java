package models;

// ══════════════════════════════════════════════════════════════
//  CLASE  ·  Material
// ══════════════════════════════════════════════════════════════
//  Elemento que compone un kit (escarapela, manual, lapicero...). Guarda cuántas
//  unidades se necesitan y cuántas hay disponibles.
//  * ENCAPSULACIÓN *
//    - Sus atributos son private y las cantidades se validan antes de guardarse.
// ══════════════════════════════════════════════════════════════
public class Material {

    // ─────────────────── ATRIBUTOS ───────────────────
    private String nombre;
    private int cantidadRequerida;
    private int cantidadDisponible;

    // ───────────────── CONSTRUCTORES ─────────────────
    // Material()
    //  - COMO FUNCIONA: Rechaza cantidades negativas con una excepción. Si son
    //    válidas, guarda el nombre y las dos cantidades.
    //  - PARA QUÉ SIRVE: Para que no existan materiales con inventario imposible.
    //  * CONCEPTO DE POO *
    //    - Encapsulación: la validación vive dentro de la clase.
    public Material(String nombre, int cantidadRequerida, int cantidadDisponible) {
        if (cantidadRequerida < 0 || cantidadDisponible < 0) {
            throw new IllegalArgumentException("Las cantidades no pueden ser negativas");
        }
        this.nombre = nombre;
        this.cantidadRequerida = cantidadRequerida;
        this.cantidadDisponible = cantidadDisponible;
    }
    // ─────────────── GETTERS & SETTERS ───────────────
    public String getNombre() { return nombre; }
    public int getCantidadRequerida() { return cantidadRequerida; }
    public int getCantidadDisponible() { return cantidadDisponible; }

    public void setCantidadRequerida(int cantidadRequerida) {
        if (cantidadRequerida < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidadRequerida = cantidadRequerida;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        if (cantidadDisponible < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidadDisponible = cantidadDisponible;
    }

    // ══════════════════════════════════════════════════════════════
    //  CONTROL DE INVENTARIO
    // ══════════════════════════════════════════════════════════════
    // faltante()
    //  - COMO FUNCIONA: Resta lo disponible a lo requerido. Si ya hay de sobra,
    //    devuelve 0 en vez de un número negativo.
    //  - PARA QUÉ SIRVE: Para saber cuántas unidades hay que conseguir.
    //  * CONCEPTO DE POO *
    //    - Encapsulación: el cálculo está dentro de la clase y no se repite afuera.
    public int faltante() {
        return Math.max(0, cantidadRequerida - cantidadDisponible);
    }

    // estaCompleto()
    //  - COMO FUNCIONA: Devuelve true si no falta ninguna unidad.
    //  - PARA QUÉ SIRVE: Para revisar rápido si el material alcanza para el evento.
    //  * CONCEPTO DE POO *
    //    - Reutilización: se apoya en faltante() en lugar de repetir la cuenta.
    public boolean estaCompleto() {
        return faltante() == 0;
    }
}