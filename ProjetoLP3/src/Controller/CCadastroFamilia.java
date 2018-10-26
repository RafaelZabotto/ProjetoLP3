package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CCadastroFamilia {

    @FXML
    private TextField txtNomeMembro;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEnderecoFamilia;

    @FXML
    private CheckBox checkBoxDependente;

    @FXML
    private TextArea txtAreaComentario;

    @FXML
    private DatePicker dataFamiliaNasc;

    @FXML
    private TextField txtTelefoneFamilia;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnCadastrarFamilia;

}
