package Model.DAO;

import Model.dominio.Alimento;
import Model.DAO.AlimentoDAO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.database.conexao;
import Model.dominio.Alimento;
import java.sql.Date;


public class AlimentoDAO {

    private Connection connect;

    private boolean executou = false;

    public AlimentoDAO() {


        connect = new conexao().getConexao();
    }


    public boolean inserir(Alimento alimento) {



        String sql = "INSERT INTO alimento (nome_alimento, tipo, data_validade)" +
                "VALUES(?,?,?);";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, alimento.getNome());
            statement.setString(2, alimento.getTipo());

            Date dataValidade = convertToDate(alimento.getData_validade());

            statement.setDate(3, dataValidade);

            statement.execute();

            statement.close();

            executou = true;


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,"Erro: Não foi possível inserir o alimento."+ e.getMessage());
        }

        return executou;
    }

    public ArrayList<Alimento> listarTodos() {


        ArrayList<Alimento> listaAlimento = new ArrayList<>();


        String sql = "SELECT * FROM alimento WHERE alimento_doado = 0 ORDER BY nome_alimento DESC";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while(resultado.next()) {

                Alimento alimento = new Alimento();

                alimento.setCodigo(resultado.getInt(1));
                alimento.setNome(resultado.getString(2));
                alimento.setTipo(resultado.getString(3));
                alimento.setData_validade(resultado.getDate(4).toLocalDate());


                listaAlimento.add(alimento);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaAlimento;
    }

    private static Date convertToDate(LocalDate locDate) {

        return (locDate == null ? null : Date.valueOf(locDate));
    }
}
