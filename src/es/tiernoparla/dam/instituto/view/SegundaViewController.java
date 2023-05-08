package es.tiernoparla.dam.instituto.view;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SegundaViewController extends ViewController {

    @FXML
    private Button btnVolver;

    @FXML
    void irViewPrincipal(MouseEvent event) throws IOException{
        institutoController.cargarVista(IViewFile.VIEW_PRINCIPAL);
    }
}
