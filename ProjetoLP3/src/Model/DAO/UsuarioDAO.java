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


    /* Atributo para estabelecer a conexao */
    private Connection connect;

    /* Atributo para indicar se o comando foi executado corretamente */
    private boolean executou = false;

    public UsuarioDAO() {

        /* Armazena a instância de conexão */
        connect = new conexao().getConexao();
    }

    /*Inicio do CRUD, método de inserção*/

    public boolean inserir(Usuario usuario) {

        /*SQL de inserção*/

        String sql = "INSERT INTO usuario (nome_usuario, telefone_usuario, cpf_usuario, nome_login, senha_usuario)" +
                "VALUES(?,?,?,?,?);";

        try {

            /* Prepara o comando e insere os parâmetros */
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getCpf());
            statement.setString(3, usuario.getTelefone());
            statement.setString(4, usuario.getNome_login_usuario());
            statement.setString(5, usuario.getSenha_usuario());

            /* Executa o comando */
            statement.execute();

            statement.close();

            JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");

            /* Indica que o comando foi executado */
            executou = true;


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,"Erro: Não foi possível inserir o usuário."+ e.getMessage());
        }

        return executou;
    }

    /* Método para devolver um array com todos os usuario no banco de dados */
    public ArrayList<Usuario> listarTodos() {

        /* Array que receberá os usuario */
        ArrayList<Usuario> listaUsuario = new ArrayList<>();

        /* Comando SQL */
        String sql = "SELECT * FROM Cliente;";

        try {

            /* Prepara o comando */
            PreparedStatement statement = connect.prepareStatement(sql);

            /* Recebe o resultado da consulta */
            ResultSet resultado = statement.executeQuery();

            /* Percorre as linhas do resultado da consulta */
            while(resultado.next()) {

                /* Cria um objeto usuario e passa as informações lidas para ele */
                Usuario usuario = new Usuario();

                usuario.setNome(resultado.getString(1));
                usuario.setNome(resultado.getString(2));
                usuario.setCpf(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setNome(resultado.getString(5));

                /* Adiciona o cliente no array list */
                listaUsuario.add(usuario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        /* Retorna a lista preenchida com os clientes presentes no banco de dados */
        return listaUsuario;
    }

    /* Método para fazer update de usuario */
    public boolean atualizar(Usuario usuario) {

        /* Comando SQL */
        String sql = "UPDATE usuario SET nome = ?, cpf = ?,"
                + "telefone = ?, email = ? WHERE codigo = ?;";

        try {

            /* Prepara o comando e insere os comandos */
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getCpf());
            statement.setString(3, usuario.getTelefone());
            statement.setString(4, usuario.getNome_login_usuario());
            statement.setString(5, usuario.getSenha_usuario());

            /* Executa o comando */
            statement.execute();

            statement.close();

            /* Indica que o comando foi executado */
            executou = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return executou;
    }

    /* Método que mascara a deleção do usuario, mudando seu usuario_excluido de 0 para 1  */
    public void remover(Usuario usuario) {

        /* Comando SQL */
        String sql = "UPDATE usuario SET usuario_excluido = 1 WHERE id_usuario = ?;";

        try {

            /* Prepara o comando e insere o parâmetro */
            PreparedStatement statement = connect.prepareStatement(sql);
           /* statement.setInt(1, usuario.getTelefone());*/

            /* Executa o comando */
            statement.execute();

            JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso");

            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: não foi possível excluir o usuário" + e.getMessage());
        }
    }

}
