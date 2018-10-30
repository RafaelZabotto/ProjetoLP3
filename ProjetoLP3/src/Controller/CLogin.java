package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CLogin {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCancelar;

    @FXML
    public void cancelar() {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void logim() throws IOException {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrMenuPrincipal.fxml"));
            Pane root = loader.load();
            CMenuPrincipal controller = (CMenuPrincipal) loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Tela 2");
            stage.setScene(scene);
            stage.show();
                //Coemntario Genial
    }
}




