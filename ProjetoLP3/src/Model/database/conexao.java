package Model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conexao {

    private final String BD = "jdbc:mysql://localhost/hotel";
    private final String USER = "root";
    private final String PASSWORD = "";

    public Connection getConexao() {

        try {

            /* Retorna a conexão com o BD */
            return DriverManager.getConnection(BD, USER, PASSWORD);

        } catch (SQLException e) {

            /* Código de erro quando o bd especificado não existe */
            if (e.getErrorCode() == 1049) {

                /* Chama o método para criar o BD */
                criarBD();

                try {
                    /* Retorna a conexão com o BD criado */
                    return DriverManager.getConnection(BD, USER, PASSWORD);

                } catch (SQLException ex) {

                    throw new RuntimeException(ex);

                }
            } else {

                throw new RuntimeException(e);

            }
        }
    }

    private void criarBD() {

        try {

            /* Criação do BD */
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost", USER, PASSWORD);

            Statement statement = conexao.createStatement();

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS hotel;");

            /* Criação das tabelas */

            conexao = DriverManager.getConnection(BD, USER, PASSWORD);

            statement = conexao.createStatement();

            /* Cliente */
            String sql = "CREATE TABLE IF NOT EXISTS Cliente("
                    + "codigo INTEGER AUTO_INCREMENT NOT NULL,"
                    + "nome VARCHAR(64) NOT NULL,"
                    + "cpf VARCHAR(14) NOT NULL,"
                    + "telefone VARCHAR(19) NOT NULL,"
                    + "email VARCHAR(64) NOT NULL,"
                    + "CONSTRAINT pk_cliente PRIMARY KEY (codigo),"
                    + "CONSTRAINT un_cliente_cpf UNIQUE(cpf));";

            statement.executeUpdate(sql);

            /* Funcionario */
            sql = "CREATE TABLE IF NOT EXISTS Funcionario("
                    + "codigo INTEGER AUTO_INCREMENT NOT NULL,"
                    + "nome VARCHAR(64) NOT NULL,"
                    + "usuario VARCHAR(64) NOT NULL,"
                    + "senha VARCHAR(64) NOT NULL,"
                    + "funcao INTEGER NOT NULL,"
                    + "CONSTRAINT pk_funcionario PRIMARY KEY (codigo),"
                    + "CONSTRAINT un_funcionario_usuario UNIQUE (usuario));";

            statement.executeUpdate(sql);

            /* Adiciona um administrador padrão para o sistema */
            sql = "INSERT INTO Funcionario VALUES (null, 'ADMINISTRADOR', 'admin', '1234', 1);";

            statement.executeUpdate(sql);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
