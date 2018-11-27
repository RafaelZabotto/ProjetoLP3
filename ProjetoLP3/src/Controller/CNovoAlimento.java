package Controller;

import Model.dominio.Estoque;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class CNovoAlimento {

    ObservableList<Alimento> alimentos = FXCollections.observableArrayList();
    ObservableList<Alimento> alimentosValidade = FXCollections.observableArrayList();
    ObservableList<Estoque> estoque = FXCollections.observableArrayList();


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
    private TableView<Alimento> tableValidade;

    @FXML
    private TableView<Estoque> tableEstoqueAlimentos;



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
        a.setData_validade(Date.from(dataValidade.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

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

        ArrayList<Alimento> listaAlimentoValidade = uDAO.listarValidadeTodos();

        alimentosValidade.clear();
        for(int i=0; i<listaAlimentoValidade.size(); i++){

            alimentosValidade.add(listaAlimentoValidade.get(i));
        }

        ArrayList<Estoque> listaEstoque = uDAO.listarEstoqueTodos();

        estoque.clear();
        for(int i=0; i<listaEstoque.size(); i++){

            estoque.add(listaEstoque.get(i));
        }

    }

    public void initialize(){

        TableColumn<Alimento , Integer> codigo  = new TableColumn<>("Código");
        codigo.setMinWidth(50);

        TableColumn<Alimento , String> nome  = new TableColumn<>("Nome");
        nome.setMinWidth(300);

        TableColumn<Alimento , Date> data_validade  = new TableColumn<>("Validade");
        data_validade.setMinWidth(122);

        tableAlimento.getColumns().addAll(codigo, nome, data_validade);
        tableValidade.getColumns().addAll(codigo, nome, data_validade);


        codigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        nome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        data_validade.setCellValueFactory( new PropertyValueFactory<>("data_validade"));


        TableColumn<Estoque , String> tipo  = new TableColumn<>("Tipo");
        tipo.setMinWidth(50);

        TableColumn<Estoque , Integer> quantidade  = new TableColumn<>("Quantidade");
        quantidade.setMinWidth(300);



        tableEstoqueAlimentos.getColumns().addAll(tipo,quantidade);

        tipo.setCellValueFactory( new PropertyValueFactory<>("tipo"));
        quantidade.setCellValueFactory( new PropertyValueFactory<>("quantidade"));

        tableAlimento.setItems(alimentos);
        tableValidade.setItems(alimentosValidade);
        tableEstoqueAlimentos.setItems(estoque);

        atualizaLista();


    }


}
