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
    private Button btnRelatorioValidade;

    @FXML
    private Button btnRelatorioEstoque;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnConsultaAlimento;

    @FXML
    private Button btnConsultaFamilia;

    @FXML
    private Button btnConsultaUsuario;

    @FXML
    public void novoAlimento() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrCadastroConsultaAlimento.fxml"));
        Pane root = loader.load();
        Controller.CNovoAlimento controller = (Controller.CNovoAlimento) loader.getController();
        // Fecha a tela
        ((Stage) btnMenuAlimento.getScene().getWindow()).hide();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);      //Desabilita o redimensionamento
        stage.setTitle("Cadastro de Alimentos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void novaFamilia() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrCadastroConsultaFamilia.fxml"));
        Pane root = loader.load();
        Controller.CCadastroFamilia controller = (Controller.CCadastroFamilia) loader.getController();
        // Fecha a tela
        ((Stage) btnMenuFamilias.getScene().getWindow()).hide();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);      //Desabilita o redimensionamento
        stage.setTitle("Cadastro de Familias");
        stage.setScene(scene);
        stage.show();
    }

    //Botão Cadastrar Usuario
    @FXML
    public void novoUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrCadastraConsultaUsuario.fxml"));
        Pane root = loader.load();
        Controller.CCadastroUsuario controller = (Controller.CCadastroUsuario) loader.getController();
        // Fecha a tela
        ((Stage) btnMenuUsuarios.getScene().getWindow()).hide();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);      //Desabilita o redimensionamento
        stage.setTitle("Cadastro de Usuários");
        stage.setScene(scene);
        stage.show();
    }

    //Botão Sair
    @FXML
    public void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

}