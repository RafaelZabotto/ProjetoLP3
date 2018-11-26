package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import Model.database.conexao;
import Model.dominio.Beneficiado;
import java.time.LocalDate;
import java.sql.Date;

public class BeneficiadoDAO {

    private Connection connect;

    private boolean executou = false;

    public BeneficiadoDAO() {


        connect = new conexao().getConexao();
    }


    public boolean inserir(Beneficiado beneficiado) {



        String sql = "INSERT INTO beneficiado (nome_beneficiado, numero, rua, bairro, cidade, telefone_beneficiado, data_nasc, profissao, beneficiado_descricao) " +
                "VALUES(?,?,?,?,?,?,?,?,?);";


        try {


            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, beneficiado.getNome());
            statement.setString(2, beneficiado.getNumero());
            statement.setString(3, beneficiado.getRua());
            statement.setString(4, beneficiado.getBairro());
            statement.setString(5, beneficiado.getCidade());
            statement.setString(6, beneficiado.getTelefone_beneficiado());

            Date dataNascimento = convertToDate(beneficiado.getData_nasc());

            statement.setDate(7, dataNascimento);

            statement.setString(8, beneficiado.getProfissao_beneficiado());
            statement.setString(9, beneficiado.getDescricao());


            statement.execute();

            statement.close();

            executou = true;


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,"Erro: Não foi possível inserir o usuário."+ e.getMessage());
        }

        return executou;
    }

    public ArrayList<Beneficiado> listarTodos() {


        ArrayList<Beneficiado> listaBeneficiado = new ArrayList<>();


        String sql = "SELECT * FROM beneficiado WHERE beneficiado_excluido = 0 ORDER BY nome_beneficiado ASC";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while(resultado.next()) {

               Beneficiado beneficiado = new Beneficiado();

               // beneficiado.setCodigo(resultado.getInt(1));
                beneficiado.setNome(resultado.getString(2));
                beneficiado.setNumero(resultado.getString(3));
                beneficiado.setRua(resultado.getString(4));
                beneficiado.setBairro(resultado.getString(5));
                beneficiado.setCidade(resultado.getString(6));
                beneficiado.setTelefone_beneficiado(resultado.getString(7));
                beneficiado.setProfissao_beneficiado(resultado.getString(8));
                beneficiado.setDescricao(resultado.getString(9));
                //beneficiado.setData_nasc(resultado.getDate(9).toLocalDate());

                listaBeneficiado.add(beneficiado);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaBeneficiado;
    }

    public boolean atualizar(Beneficiado beneficiado) {


        String sql = "UPDATE beneficiado SET nome_beneficiado = ?, profissao_beneficiado = ?, telefone_beneficiado = ?, data_nasc = ?, rua = ?, numero = ?,bairro = ?, cidade = ?," +
                " beneficiado_descricao = ? WHERE id_beneficiado = ?;";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);


            statement.setString(1, beneficiado.getNome());
            statement.setString(2,beneficiado.getProfissao_beneficiado());
            statement.setString(3, beneficiado.getTelefone_beneficiado());

            Date dataNascimento = convertToDate(beneficiado.getData_nasc());


            //statement.setDate(4,dataNascimento);
            statement.setString(4, beneficiado.getRua());
            statement.setString(5, beneficiado.getNumero());
            statement.setString(6, beneficiado.getBairro());
            statement.setString(7, beneficiado.getCidade());
            statement.setString(8,beneficiado.getDescricao());
            statement.setInt(9,beneficiado.getCodigo());


            statement.execute();

            statement.close();


            executou = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return executou;
    }

    public boolean remover(Beneficiado beneficiado) {

        String sql = "UPDATE beneficiado SET beneficiado_excluido = 1 WHERE id_beneficiado = ?;";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setInt(1, beneficiado.getCodigo());

            statement.execute();

            statement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,  e.getMessage());
        }

        return executou;
    }

    public ArrayList<Beneficiado> pesquisarBeneficiado(String nome) {

        PreparedStatement stmt = null;
        ResultSet resultado = null;


        ArrayList<Beneficiado> listaUsuario = new ArrayList<>();

        try {

            String sql = "SELECT * FROM beneficiado WHERE nome_beneficiado LIKE ? AND beneficiado_excluido = 0 ORDER BY nome_beneficiado ASC";

            stmt = connect.prepareStatement(sql);

            stmt.setString(1,"%"+nome+"%");

            resultado = stmt.executeQuery();


            while(resultado.next()) {

                Beneficiado beneficiado = new Beneficiado();


                beneficiado.setCodigo(resultado.getInt(1));
                beneficiado.setNome(resultado.getString(2));
                beneficiado.setData_nasc(resultado.getDate(3).toLocalDate());
                beneficiado.setProfissao_beneficiado(resultado.getString(4));
                beneficiado.setRua(resultado.getString(5));
                beneficiado.setNumero(resultado.getString(6));
                beneficiado.setCidade(resultado.getString(7));
                beneficiado.setDescricao(resultado.getString(8));
                beneficiado.setTelefone_beneficiado(resultado.getString(9));


                listaUsuario.add(beneficiado);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaUsuario;
    }

    private static Date convertToDate(LocalDate locDate) {

        return (locDate == null ? null : Date.valueOf(locDate));
    }



}
