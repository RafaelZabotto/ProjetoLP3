package Controller;

import Model.dominio.Beneficiado;
import Model.DAO.BeneficiadoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import Model.dominio.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;




public class CCadastroFamilia {

    ObservableList<Beneficiado> beneficiado = FXCollections.observableArrayList();
    Beneficiado beneficiadoSelecionado = null;

    @FXML
    private TextField txtNomeBeneficiado;

    @FXML
    private TextField txtProfissao;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    @FXML
    private DatePicker dataNasc;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private Button btnVoltar1;

    @FXML
    private TabPane paneBeneficiado;

    @FXML
    private Tab tabCadastraBeneficiado;

    @FXML
    private Tab tabConsultaBeneficiado;

    @FXML
    private TableView<Beneficiado> tableListaBeneficiado;


    @FXML
    public void voltaMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FmrMenuPrincipal.fxml"));
        Pane root = loader.load();
        CMenuPrincipal controller = (CMenuPrincipal) loader.getController();
        // Fecha a tela
        ((Stage) btnVoltar1.getScene().getWindow()).hide();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);      //Desabilita o redimensionamento
        stage.setTitle("Tela 2");
        stage.setScene(scene);
        stage.show();
    }

    public void salvarBeneficiado(){

        Beneficiado b = new Beneficiado();
        BeneficiadoDAO dao = new BeneficiadoDAO();

        b.setNome(txtNomeBeneficiado.getText());
        b.setProfissao_beneficiado(txtProfissao.getText());
        b.setData_nasc(dataNasc.getValue());
        b.setRua(txtEndereco.getText());
        b.setTelefone_beneficiado(txtTelefone.getText());
        b.setNumero(Integer.parseInt(txtNumero.getText()));
        b.setBairro(txtBairro.getText());
        b.setCidade(txtCidade.getText());
        b.setDescricao(txtDescricao.getText());


        if(beneficiadoSelecionado == null){


            if(dao.inserir(b)) {

                txtNomeBeneficiado.setText("");
                txtEndereco.setText("");
                txtTelefone.setText("");
                txtProfissao.setText("");
                //dataNasc.setValue("");
                txtNumero.setText("");
                txtBairro.setText("");
                txtCidade.setText("");
                txtDescricao.setText("");
                Alerta.infoAlert("Beneficiado cadastrado", "Beneficiado cadastrado com sucesso.");
                atualizaLista();
            }
        }else{
            b.setCodigo(beneficiadoSelecionado.getCodigo());
            if(dao.atualizar(b)) {


                txtNomeBeneficiado.setText("");
                txtEndereco.setText("");
                txtTelefone.setText("");
                txtProfissao.setText("");
                //dataNasc.setValue("");
                txtNumero.setText("");
                txtBairro.setText("");
                txtCidade.setText("");
                txtDescricao.setText("");

                atualizaLista();
                Alerta.infoAlert("Beneficiado atualizado", "Beneficiado atualizado com sucesso.");
                paneBeneficiado.getSelectionModel().select(tabConsultaBeneficiado);
                beneficiadoSelecionado = null;
           }
       }
    }

    public  void atualizaBeneficiado(){


        beneficiadoSelecionado = tableListaBeneficiado.getSelectionModel().getSelectedItem();

        if(beneficiadoSelecionado == null){
            Alerta.errorAlert("Erro", "Selecione um beneficiado para ser atualizado.");
        }else{

            if(beneficiadoSelecionado != null) {
                paneBeneficiado.getSelectionModel().select(tabCadastraBeneficiado);

                txtNomeBeneficiado.setText(beneficiadoSelecionado.getNome());
                txtEndereco.setText(beneficiadoSelecionado.getRua());
                txtNumero.setText(Integer.parseInt(beneficiadoSelecionado.getText()));
                txtTelefone.setText(beneficiadoSelecionado.getTelefone());
                txtNomebeneficiadoLogin.setText(beneficiadoSelecionado.getNome_login_beneficiado());
                txtSenha.setText(beneficiadoSelecionado.getSenha_beneficiado());


            }
        }
        atualizaLista();
    }

    public void excluiBeneficiado(){}

    public void pesquisaBeneficiado(){}

    public void historicoBeneficiado(){}

    public void atualizaLista(){

        BeneficiadoDAO uDAO = new BeneficiadoDAO();
        ArrayList<Beneficiado>listaBeneficiado = uDAO.listarTodos();

        beneficiado.clear();
        for(int i=0; i<listaBeneficiado.size(); i++){

            beneficiado.add(listaBeneficiado.get(i));
        }

    }

    public void initialize(){



        TableColumn<Beneficiado , Integer> codigo  = new TableColumn<>("Código");
        codigo.setMinWidth(50);

        TableColumn<Beneficiado , String> nome  = new TableColumn<>("Nome");
        nome.setMinWidth(430);

        tableListaBeneficiado.getColumns().addAll(codigo, nome);

        codigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        nome.setCellValueFactory( new PropertyValueFactory<>("nome"));


        tableListaBeneficiado.setItems(beneficiado);

        atualizaLista();


    }

}
