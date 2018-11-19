package Controller;

import Model.DAO.UsuarioDAO;
import com.mysql.cj.xdevapi.Table;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class CConsultaUsuario {

    @FXML
    private Button btnPesquisaUsuario;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnEditar;

    @FXML
    private Table tblConsultaUsuario;


    public void pesquisaUsuario(){


    }

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

    public void listarTodos(){

        UsuarioDAO uDAO = new UsuarioDAO();

        uDAO.listarTodos();
    }


}
