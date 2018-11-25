package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.database.conexao;
import Model.dominio.Usuario;

public class UsuarioDAO {

    private Connection connect;

    private boolean executou = false;

    public UsuarioDAO() {


        connect = new conexao().getConexao();
    }


    public boolean inserir(Usuario usuario) {



        String sql = "INSERT INTO usuario (nome_usuario, endereco_usuario, telefone_usuario, cpf_usuario, nome_login, senha_usuario)" +
                "VALUES(?,?,?,?,?,?);";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEndereco());
            statement.setString(3, usuario.getTelefone());
            statement.setString(4, usuario.getCpf());
            statement.setString(5, usuario.getNome_login_usuario());
            statement.setString(6, usuario.getSenha_usuario());

            statement.execute();

            statement.close();

            executou = true;


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,"Erro: Não foi possível inserir o usuário."+ e.getMessage());
        }

        return executou;
    }

    public ArrayList<Usuario> listarTodos() {


        ArrayList<Usuario> listaUsuario = new ArrayList<>();


        String sql = "SELECT * FROM usuario WHERE usuario_excluido = 0 ORDER BY nome_usuario ASC";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while(resultado.next()) {

                Usuario usuario = new Usuario();

                usuario.setCodigo(resultado.getInt(1));
                usuario.setNome(resultado.getString(2));
                usuario.setEndereco(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setCpf(resultado.getString(5));
                usuario.setNome_login_usuario(resultado.getString(6));
                usuario.setSenha_usuario(resultado.getString(7));

                listaUsuario.add(usuario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaUsuario;
    }


    public boolean atualizar(Usuario usuario) {


        String sql = "UPDATE usuario SET nome_usuario = ?, endereco_usuario = ?, cpf_usuario = ?, telefone_usuario = ?,"  +
                " nome_login = ?, senha_usuario = ? WHERE id_usuario = ?;";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);


            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEndereco());
            statement.setString(3, usuario.getCpf());
            statement.setString(4, usuario.getTelefone());
            statement.setString(5, usuario.getNome_login_usuario());
            statement.setString(6, usuario.getSenha_usuario());
            statement.setInt(7,usuario.getCodigo());


            statement.execute();

            statement.close();


            executou = true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return executou;
    }

    public boolean remover(Usuario usuario) {

        String sql = "UPDATE usuario SET usuario_excluido = 1 WHERE id_usuario = ?;";

        try {

            PreparedStatement statement = connect.prepareStatement(sql);

            statement.setInt(1, usuario.getCodigo());

            statement.execute();

            statement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,  e.getMessage());
        }

        return executou;
    }

    public ArrayList<Usuario> pesquisarUsuario(String nome) {

        PreparedStatement stmt = null;
        ResultSet resultado = null;


        ArrayList<Usuario> listaUsuario = new ArrayList<>();

        try {

            String sql = "SELECT * FROM usuario WHERE nome_usuario LIKE ? AND usuario_excluido = 0 ORDER BY nome_usuario ASC";

            stmt = connect.prepareStatement(sql);

            stmt.setString(1,"%"+nome+"%");

            resultado = stmt.executeQuery();


            while(resultado.next()) {

                Usuario usuario = new Usuario();


                usuario.setCodigo(resultado.getInt(1));
                usuario.setNome(resultado.getString(2));
                usuario.setEndereco(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setCpf(resultado.getString(5));
                usuario.setNome_login_usuario(resultado.getString(6));
                usuario.setSenha_usuario(resultado.getString(6));


                listaUsuario.add(usuario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return listaUsuario;
    }


}
