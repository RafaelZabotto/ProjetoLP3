package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CConsultaAlimento {

    @FXML
    private TextField txtConsultaAlimento;

    @FXML
    private Button btnPesquisarAlimento;

    @FXML
    private TableView<?> tableConsultaAlimento;

    @FXML
    private Button btnVoltar;

    //Ação do botão voltar
    @FXML
    public void voltaLogin() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrMenuPrincipal.fxml"));
        Pane root = loader.load();
        Controller.CMenuPrincipal controller = (Controller.CMenuPrincipal) loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

    //Botão de voltar
    @FXML
    public void voltaMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrMenuPrincipal.fxml"));
        Pane root = loader.load();
        CMenuPrincipal controller = (CMenuPrincipal) loader.getController();
        // Fecha a tela
        ((Stage) btnVoltar.getScene().getWindow()).hide();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);      //Desabilita o redimensionamento
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

}