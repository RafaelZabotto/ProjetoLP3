package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CMenuPrincipal {

    @FXML
    private Button btnMenuAlimento;

    @FXML
    private Button btnMenuUsuarios;

    @FXML
    private Button btnMenuFamilias;

    @FXML
    private Button btnMenuRelatorio;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnConsulta;


    @FXML
    public void novoAlimento() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrNovoAlimento.fxml"));
        Pane root = loader.load();
        Controller.CNovoAlimento controller = (Controller.CNovoAlimento) loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void novaFamilia() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrNovaFamilia.fxml"));
        Pane root = loader.load();
        Controller.CCadastroFamilia controller = (Controller.CCadastroFamilia) loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void novoUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FrmNovoUsuario.fxml"));
        Pane root = loader.load();
        Controller.CCadastroUsuario controller = (Controller.CCadastroUsuario) loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void consultaAlimento() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrConsultaAlimento.fxml"));
        Pane root = loader.load();
        Controller.CConsultaAlimento controller = (Controller.CConsultaAlimento) loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

}