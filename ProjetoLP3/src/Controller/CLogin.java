package Controller;

import Model.database.conexao;
import Model.dominio.Alerta;
import Model.dominio.Login;
import Model.DAO.LoginDAO;
import Model.dominio.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.sql.SQLException;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.sql.Connection;

public class CLogin {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCancelar;

    // Botão Cancelar
    @FXML
    public void cancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }



    //Botão de Login
    @FXML
    public void login() throws IOException {

        LoginDAO lDAO = new LoginDAO();
        ArrayList<Login> list = lDAO.listaUsuarioSenhas();

        for (Login l : list) {

            if (txtUsuario.getText().equals(l.getUsuario()) &&
                    txtSenha.getText().equals(l.getSenha())) {

                Connection conn = new conexao().getConexao();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrMenuPrincipal.fxml"));
                Pane root = loader.load();
                CMenuPrincipal controller = (CMenuPrincipal) loader.getController();
                ((Stage) btnLogin.getScene().getWindow()).hide();   // Fecha a tela
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);      //Desabilita o redimensionamento
                stage.setTitle("Menu Principal");
                stage.setScene(scene);
                stage.show();

            } else {

                Alerta.errorAlert("Erro", "Usuário ou senha inválidos.");
            }
        }

    }


}




