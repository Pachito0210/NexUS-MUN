package ui.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ui.controllers.ControladorPrincipal;

public class VistaGuiaDidactica extends VistaBase {

    private Label lblRespuestaSimulador;

    public VistaGuiaDidactica(ControladorPrincipal controlador) {
        super(
                controlador,
                "Academia MUNUS - Guia Interactiva para Delegados",
                "Aprende los roles, reglas parlamentarias y flujos del Modelo de Naciones Unidas de La Sabana."
        );
        construirVista();
    }

    @Override
    public void construirVista() {
        // Tarjetas de actores del modelo
        GridPane gridActores = new GridPane();
        gridActores.setHgap(14);
        gridActores.setVgap(14);

        gridActores.add(crearTarjetaRol(
                "Delegado",
                "Investiga la postura de su pais, debate en sesiones formales y redacta Hojas de Trabajo y Proyectos de Resolucion."
        ), 0, 0);

        gridActores.add(crearTarjetaRol(
                "Mesa Directiva",
                "Compuesta por dos presidentes: moderan el debate, toman lista, evaluan desempeno y velan por el reglamento."
        ), 1, 0);

        gridActores.add(crearTarjetaRol(
                "Sponsor",
                "Docente o lider acompanante de cada delegacion institucional; vela por el bienestar y preparacion de sus estudiantes."
        ), 0, 1);

        gridActores.add(crearTarjetaRol(
                "Comite de Logistica y Junta",
                "Gestionan inscripciones, cupos, entrega de kits, alimentacion y atencion inmediata de emergencias en sala."
        ), 1, 1);

        // Mini simulador didactico de mociones para novatos
        VBox cardSimulador = new VBox(12);
        cardSimulador.getStyleClass().add("card");

        Label titSim = new Label("Simulador Rapido: ¿Que mocion pedir en debate?");
        titSim.getStyleClass().add("card-title");

        ComboBox<String> cmbSituacion = new ComboBox<>();
        cmbSituacion.getItems().addAll(
                "Quiero debatir un subtema especifico con intervenciones cortas y dinamicas",
                "Necesito hablar libremente con otros delegados para redactar una resolucion",
                "Hay un error en el procedimiento parlamentario o falta de quorum",
                "Tengo una incomodidad fisica (aire acondicionado, ruido o salud) en el salon"
        );
        cmbSituacion.setPromptText("Selecciona una situacion de sala...");
        cmbSituacion.setMaxWidth(Double.MAX_VALUE);

        Button btnConsultar = new Button("Ver recomendacion parlamentaria");
        btnConsultar.getStyleClass().add("btn-gold");

        lblRespuestaSimulador = new Label("Selecciona una situacion arriba para practicar como intervenir.");
        lblRespuestaSimulador.setWrapText(true);
        lblRespuestaSimulador.setStyle("-fx-font-size: 13.5px; -fx-text-fill: #1E293B;");

        btnConsultar.setOnAction(e -> {
            int idx = cmbSituacion.getSelectionModel().getSelectedIndex();
            switch (idx) {
                case 0 -> lblRespuestaSimulador.setText(
                        "Mocion recomendada: Caucus Moderado. Debes indicar el tiempo total, el tiempo por orador y el subtema exacto."
                );
                case 1 -> lblRespuestaSimulador.setText(
                        "Mocion recomendada: Caucus Inmoderado. Permite levantarte de tu puesto para negociar bloques y redactar documentos."
                );
                case 2 -> lblRespuestaSimulador.setText(
                        "Intervencion recomendada: Punto de Orden. Puede interrumpir si la Mesa Directiva omitio una regla del manual."
                );
                case 3 -> lblRespuestaSimulador.setText(
                        "Intervencion recomendada: Punto de Privilegio Personal. Sirve para pedir mejor audio, ventilacion o permiso de salida."
                );
                default -> lblRespuestaSimulador.setText("Elige primero una situacion del menu desplegable.");
            }
        });

        cardSimulador.getChildren().addAll(titSim, cmbSituacion, btnConsultar, lblRespuestaSimulador);
        contenedor.getChildren().addAll(gridActores, cardSimulador);
    }

    private VBox crearTarjetaRol(String titulo, String descripcion) {
        VBox card = new VBox(8);
        card.getStyleClass().add("card");
        card.setPrefWidth(360);

        Label lblT = new Label(titulo);
        lblT.getStyleClass().add("card-title");

        Label lblD = new Label(descripcion);
        lblD.setWrapText(true);
        lblD.setStyle("-fx-text-fill: #475569; -fx-font-size: 13px;");

        card.getChildren().addAll(lblT, lblD);
        return card;
    }
}