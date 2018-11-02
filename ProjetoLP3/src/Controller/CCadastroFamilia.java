package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CCadastroFamilia {

    @FXML
    private TextField txtNomeMembro;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEnderecoFamilia;

    @FXML
    private CheckBox checkBoxDependente;

    @FXML
    private TextArea txtAreaComentario;

    @FXML
    private DatePicker dataFamiliaNasc;

    @FXML
    private TextField txtTelefoneFamilia;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnCadastrarFamilia;

    //
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
