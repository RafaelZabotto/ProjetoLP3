package Model.DAO;

import Model.database.conexao;
import Model.dominio.Login;
import Model.dominio.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.dominio.Alerta;

import javax.swing.*;
import java.sql.*;

public class LoginDAO {

    private Connection connect;

    private boolean executou = false;

    public LoginDAO() {

        connect = new conexao().getConexao();


    }

//    public boolean ArrayList<Login> listaUsuarioSenhas() {
//
//        ArrayList<Login> list = new ArrayList<>();
//
//        String sql = "SELECT nome_login, senha_usuario FROM usuario";
//
//        try {
//
//            PreparedStatement statement = connect.prepareStatement(sql);
//
//            ResultSet resultado = statement.executeQuery();
//
//            while(resultado.next()) {
//                Login l = new Login();
//
//                l.setId(resultado.getInt("id"));
//                l.setUsuario(resultado.getString("usuario"));
//                l.setSenha(resultado.getString("senha"));
//
//                list.add(l);
//            }
//
//            resultado.close();
//
//            return list;
//        } catch (SQLException e) {
//
//            Alerta.errorAlert("Erro.", "Erro ao retornar a lista. \nLog de erro: " + e);
//
//        } finally {
//            .close();
//        }
//
//        return executou;
//    }

    public ArrayList<Login> listaUsuarioSenhas() {


        ArrayList<Login> listaLogin = new ArrayList<>();


        String sql = "SELECT nome_login, senha_usuario FROM usuario";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while(resultado.next()) {

                Login login = new Login();


                login.setUsuario(resultado.getString("nome_login"));
                login.setSenha(resultado.getString("senha_usuario"));


                listaLogin.add(login);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaLogin;
    }


}
