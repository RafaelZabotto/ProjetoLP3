package Controller;

import Model.DAO.UsuarioDAO;
import Model.dominio.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CCadastroUsuario {

    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtNomeUsuario1;
    @FXML
    private TextField txtSenha;

    @FXML
    private Button btnVoltar;

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

    @FXML
    public void CadastraUsuario() throws IOException{

        Usuario u = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();

        u.setNome(txtNomeUsuario.getText());
        u.setCpf(txtCPF.getText());
        u.setTelefone(txtTelefone.getText());
        u.setNome_login_usuario(txtNomeUsuario1.getText());
        u.setSenha_usuario(txtSenha.getText());

        dao.inserir(u);
    }

    public void ConsultaUsuario() throws IOException{

    }

}
