package Controller;

import Model.DAO.UsuarioDAO;
import Model.dominio.Alerta;
import Model.dominio.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import java.util.Optional;

import javafx.scene.control.Button;



public class CCadastroUsuario {

    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    Usuario usuarioSelecionado = null;


    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private TextField txtEnderecoUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtNomeUsuarioLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Usuario> tableListaUsuario;

    @FXML
    private TabPane paneUsuario;

    @FXML
    private Tab tabConsultaUsuario;

    @FXML
    private Tab tabCadastraUsuario;

    @FXML
    private TextField txtPesquisaUsuario;


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

        if(usuarioSelecionado == null){


            if(dao.inserir(u)) {

                txtNomeUsuario.setText("");
                txtEnderecoUsuario.setText("");
                txtTelefone.setText("");
                txtCPF.setText("");
                txtNomeUsuarioLogin.setText("");
                txtSenha.setText("");
                Alerta.infoAlert("Usuário cadastrado", "Usuário cadastrado com sucesso.");
                atualizaLista();
            }
        }else{
                u.setCodigo(usuarioSelecionado.getCodigo());
                if(dao.atualizar(u)) {


                    txtNomeUsuario.setText("");
                    txtEnderecoUsuario.setText("");
                    txtTelefone.setText("");
                    txtCPF.setText("");
                    txtNomeUsuarioLogin.setText("");
                    txtSenha.setText("");

                    atualizaLista();
                    Alerta.infoAlert("Usuário atualizado", "Usuário atualizado com sucesso.");
                    paneUsuario.getSelectionModel().select(tabConsultaUsuario);
                    usuarioSelecionado = null;
                }
        }

    }

    @FXML
    public void atualizaUsuario(){

        usuarioSelecionado = tableListaUsuario.getSelectionModel().getSelectedItem();

        if(usuarioSelecionado == null){
            Alerta.errorAlert("Erro", "Selecione um usuário para ser atualizado.");
        }else{

            if(usuarioSelecionado != null) {
                paneUsuario.getSelectionModel().select(tabCadastraUsuario);

                txtNomeUsuario.setText(usuarioSelecionado.getNome());
                txtEnderecoUsuario.setText(usuarioSelecionado.getEndereco());
                txtCPF.setText(usuarioSelecionado.getCpf());
                txtTelefone.setText(usuarioSelecionado.getTelefone());
                txtNomeUsuarioLogin.setText(usuarioSelecionado.getNome_login_usuario());
                txtSenha.setText(usuarioSelecionado.getSenha_usuario());


            }
        }
        atualizaLista();

    }

    @FXML
    public void excluiUsuario(){

        UsuarioDAO usuarioRemovido = new UsuarioDAO();
        usuarioSelecionado = tableListaUsuario.getSelectionModel().getSelectedItem();

        if(usuarioSelecionado ==  null) {
            Alerta.errorAlert("Erro", "Selecione um usuário para excluir.");
        } else {
            Alert alert = Alerta.confirmationAlert("Deseja excluir o usuário?", "Você tem certeza que deseja excluir este Usuário?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                if(usuarioRemovido.remover(usuarioSelecionado)) {

                    Alerta.infoAlert("Usuário excluído", "Usuário excluído com sucesso.");
                }
            }
        }
        atualizaLista();
        usuarioSelecionado = null;

    }


    public void atualizaLista(){

        UsuarioDAO uDAO = new UsuarioDAO();
        ArrayList<Usuario>listaUsuario = uDAO.listarTodos();

        usuarios.clear();
        for(int i=0; i<listaUsuario.size(); i++){

            usuarios.add(listaUsuario.get(i));
        }

    }

    public void pesquisarUsuario() throws IOException{

        UsuarioDAO uDAO = new UsuarioDAO();
        ArrayList<Usuario>listaUsuario = uDAO.pesquisarUsuario(txtPesquisaUsuario.getText());


        usuarios.clear();
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
        cpf.setMinWidth(122);

        tableListaUsuario.getColumns().addAll(codigo, nome, cpf);

        codigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        nome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        cpf.setCellValueFactory( new PropertyValueFactory<>("cpf"));

        tableListaUsuario.setItems(usuarios);

        atualizaLista();


    }


}
