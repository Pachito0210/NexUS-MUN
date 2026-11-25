package persistence;

import interfaces.IExportable;
import models.ResultadoOperacion;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExportadorExcel implements IExportable {

    public static final char DELIMITADOR_POR_DEFECTO = ';';
    private static final String BOM_UTF8 = "﻿";
    private static final String FIN_DE_LINEA = "\r\n"; // el que espera Excel

    private char delimitador;

    public ExportadorExcel() {
        this(DELIMITADOR_POR_DEFECTO);
    }

    public ExportadorExcel(char delimitador) {
        setDelimitador(delimitador);
    }

    public char getDelimitador() {
        return delimitador;
    }

    public void setDelimitador(char delimitador) {
        if (delimitador == '"' || delimitador == '\n' || delimitador == '\r') {
            throw new IllegalArgumentException("Delimitador no permitido: " + delimitador);
        }
        this.delimitador = delimitador;
    }

    @Override
    public ResultadoOperacion exportar(String ruta, List<String> encabezados, List<List<String>> filas) {
        if (ruta == null || ruta.isBlank()) {
            return new ResultadoOperacion(false, "La ruta del archivo no puede estar vacía.");
        }
        if (encabezados == null || encabezados.isEmpty()) {
            return new ResultadoOperacion(false, "Debe haber al menos un encabezado.");
        }
        for (int i = 0; i < encabezados.size(); i++) {
            if (encabezados.get(i) == null || encabezados.get(i).isBlank()) {
                return new ResultadoOperacion(false, "El encabezado de la columna " + (i + 1) + " está vacío.");
            }
        }
        if (filas == null) {
            return new ResultadoOperacion(false, "La lista de filas no puede ser nula (use una lista vacía).");
        }
        for (int i = 0; i < filas.size(); i++) {
            List<String> fila = filas.get(i);
            if (fila == null) {
                return new ResultadoOperacion(false, "La fila " + (i + 1) + " es nula.");
            }
            if (fila.size() > encabezados.size()) {
                return new ResultadoOperacion(false, "La fila " + (i + 1) + " tiene " + fila.size()
                        + " columnas, pero solo hay " + encabezados.size() + " encabezados.");
            }
        }

        String rutaFinal = ruta.trim();
        String minuscula = rutaFinal.toLowerCase();
        if (minuscula.endsWith(".xlsx") || minuscula.endsWith(".xls")) {
            return new ResultadoOperacion(false,
                    "Este exportador genera CSV (compatible con Excel). Use una ruta terminada en .csv.");
        }
        boolean extensionAgregada = false;
        if (!minuscula.endsWith(".csv")) {
            rutaFinal += ".csv";
            extensionAgregada = true;
        }

        try {
            Path destino = Paths.get(rutaFinal).toAbsolutePath();
            if (destino.getParent() != null) {
                Files.createDirectories(destino.getParent());
            }
            StringBuilder contenido = new StringBuilder(BOM_UTF8);
            escribirLinea(contenido, encabezados, encabezados.size());
            for (List<String> fila : filas) {
                escribirLinea(contenido, fila, encabezados.size());
            }
            Files.writeString(destino, contenido, StandardCharsets.UTF_8);
            return new ResultadoOperacion(true, "Se exportaron " + filas.size() + " filas a " + destino
                    + (extensionAgregada ? " (se agregó la extensión .csv)." : "."));
        } catch (Exception e) {
            return new ResultadoOperacion(false, "No se pudo exportar el archivo: " + e.getMessage());
        }
    }

    private void escribirLinea(StringBuilder salida, List<String> celdas, int columnas) {
        for (int i = 0; i < columnas; i++) {
            if (i > 0) {
                salida.append(delimitador);
            }
            salida.append(escapar(i < celdas.size() ? celdas.get(i) : ""));
        }
        salida.append(FIN_DE_LINEA);
    }

    private String escapar(String valor) {
        if (valor == null) {
            return "";
        }
        boolean necesitaComillas = valor.indexOf(delimitador) >= 0
                || valor.indexOf(',') >= 0 || valor.indexOf(';') >= 0
                || valor.indexOf('"') >= 0 || valor.indexOf('\n') >= 0 || valor.indexOf('\r') >= 0
                || (!valor.isEmpty() && (Character.isWhitespace(valor.charAt(0))
                || Character.isWhitespace(valor.charAt(valor.length() - 1))));
        if (!necesitaComillas) {
            return valor;
        }
        return "\"" + valor.replace("\"", "\"\"") + "\"";
    }
}