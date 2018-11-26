package Controller;

import Model.dominio.Alerta;
import Model.dominio.Alimento;
import Model.DAO.AlimentoDAO;
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
import java.util.Date;

public class CNovoAlimento {

    ObservableList<Alimento> alimentos = FXCollections.observableArrayList();


    @FXML
    private TextField txtNomeAlimento;

    @FXML
    private DatePicker dataValidade;

    @FXML
    private Button btnadicionarAlimento;

    @FXML
    private TextField txtTipo;

    @FXML
    private Button voltaMenu;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtPesquisaUsuario1;

    @FXML
    private Button btnPesquisaEstoque;

    @FXML
    private TableView<Alimento> tableAlimento;



    @FXML
    void pesquisaEstoque() {

    }


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
    void adicionarAlimento() {

        Alimento a = new Alimento();
        AlimentoDAO dao = new AlimentoDAO();

        a.setNome(txtNomeAlimento.getText());
        a.setTipo(txtTipo.getText());
        a.setData_validade(dataValidade.getValue());

        dao.inserir(a);

        txtNomeAlimento.setText("");
        txtTipo.setText("");
        dataValidade.getEditor().clear();

        Alerta.infoAlert("Alimento cadastrado", "Alimento cadastrado com sucesso.");

        atualizaLista();
    }

    public void atualizaLista(){

        AlimentoDAO uDAO = new AlimentoDAO();
        ArrayList<Alimento> listaAlimento = uDAO.listarTodos();

        alimentos.clear();
        for(int i=0; i<listaAlimento.size(); i++){

            alimentos.add(listaAlimento.get(i));
        }

    }

    public void initialize(){



        TableColumn<Alimento , Integer> codigo  = new TableColumn<>("Código");
        codigo.setMinWidth(50);

        TableColumn<Alimento , String> nome  = new TableColumn<>("Nome");
        nome.setMinWidth(300);

        TableColumn<Alimento , Date> validade  = new TableColumn<>("Validade");
        validade.setMinWidth(122);

        tableAlimento.getColumns().addAll(codigo, nome, validade);

        codigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        nome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        validade.setCellValueFactory( new PropertyValueFactory<>("validade"));

        tableAlimento.setItems(alimentos);

        atualizaLista();


    }

}
