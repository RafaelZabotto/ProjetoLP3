package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CNovoAlimento {

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtNomeAlimento;

    @FXML
    private Button btnCadastrar;

    @FXML
    private DatePicker dateBox;

    @FXML
    private ComboBox<?> checkTipo;

    @FXML
    public void voltaMenu() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrMenuPrincipal.fxml"));
        Pane root = loader.load();
        CMenuPrincipal controller = (CMenuPrincipal) loader.getController();
        // Fecha a tela
        ((Stage) btnVoltar.getScene().getWindow()).hide();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }


}
