package ui.views;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ui.controllers.ControladorPrincipal;

public abstract class VistaBase {

    protected final ControladorPrincipal controlador;
    protected final VBox contenedor;

    public VistaBase(ControladorPrincipal controlador, String titulo, String subtitulo) {
        this.controlador = controlador;
        this.contenedor = new VBox(18);
        this.contenedor.setPadding(new Insets(26));

        Label lblTitulo = new Label(titulo);
        lblTitulo.getStyleClass().add("page-title");

        Label lblSubtitulo = new Label(subtitulo);
        lblSubtitulo.getStyleClass().add("page-subtitle");

        VBox header = new VBox(4, lblTitulo, lblSubtitulo);
        this.contenedor.getChildren().add(header);
    }

    public abstract void construirVista();

    public void actualizarDatos() {
        // Las subclases que muestran contadores o listas pueden sobreescribir este metodo
    }

    public Node getVistaConScroll() {
        ScrollPane scroll = new ScrollPane(contenedor);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: #F4F6FA;");
        return scroll;
    }
}