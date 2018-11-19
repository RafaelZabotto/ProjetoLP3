package Controller;

import Model.DAO.UsuarioDAO;
import Model.dominio.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CCadastroUsuario {

    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEnderecoUsuario;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtNomeUsuarioLogin;

    @FXML
    private TextField txtSenha;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Usuario> tableListaUsuario;

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
        u.setEndereco(txtEnderecoUsuario.getText());
        u.setTelefone(txtTelefone.getText());
        u.setCpf(txtCPF.getText());
        u.setNome_login_usuario(txtNomeUsuarioLogin.getText());
        u.setSenha_usuario(txtSenha.getText());

        if(dao.inserir(u)){
            JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
            txtNomeUsuario.setText("");
            txtEnderecoUsuario.setText("");
            txtTelefone.setText("");
            txtCPF.setText("");
            txtNomeUsuarioLogin.setText("");
            txtSenha.setText("");

            atualizaLista();

        }else{

        }

    }

    @FXML
    public void atualizaUsuario(){

    }

    @FXML
    public void excluiUsuario(){

    }


    public void atualizaLista(){

        UsuarioDAO uDAO = new UsuarioDAO();
        ArrayList<Usuario>listaUsuario = uDAO.listarTodos();

        for(int i=0; i<listaUsuario.size(); i++){

            usuarios.add(listaUsuario.get(i));
        }

    }

    public void initialize(){



        TableColumn<Usuario , Integer> codigo  = new TableColumn<>("Código");
        codigo.setMinWidth(50);

        TableColumn<Usuario , String> nome  = new TableColumn<>("Nome");
        nome.setMinWidth(300);

        TableColumn<Usuario , String> cpf  = new TableColumn<>("CPF");
        cpf.setMinWidth(100);

        tableListaUsuario.getColumns().addAll(codigo, nome, cpf);

        codigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        nome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        cpf.setCellValueFactory( new PropertyValueFactory<>("cpf"));

        tableListaUsuario.setItems(usuarios);

        atualizaLista();


        //System.out.println("Imprimiu");

    }


}
