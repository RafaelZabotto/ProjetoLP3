package Model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conexao {

//    private final String BD = "jdbc:mysql://localhost/instituicao";
//    private final String USER = "root";
//    private final String PASSWORD = "";

    private final String BD = "jdbc:mysql://localhost/instituicao";
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

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS instituicao;");

            /* Criação das tabelas */

            conexao = DriverManager.getConnection(BD, USER, PASSWORD);

            statement = conexao.createStatement();

            /* Usuario */

            String sql = "CREATE TABLE IF NOT EXISTS usuario("
                + "id_usuario INTEGER AUTO_INCREMENT NOT NULL,"
                + "nome_usuario VARCHAR(100) NOT NULL,"
                + "telefone_usuario VARCHAR(15),"
                + "cpf_usuario VARCHAR(15) NOT NULL,"
                + "nome_login_senha VARCHAR(20) NOT NULL,"
                + "senha_usuario VARCHAR(20) NOT NULL,"
                + "data_cadastro_user DATE,"
                + "CONSTRAINT pk usuario PRIMARY KEY (id_usuario)";

            statement.executeUpdate(sql);

            /* Alimento */

            sql = "CREATE TABLE IF NOT EXISTS alimento("
                +"id_alimento INTEGER AUTO INCREMENT NOT NULL,"
                +"nome_alimento VARCHAR(100) NOT NULL,"
                +"tipo VARCHAR(100),"
                +"data_validade DATE,"
                +"id_usuario INTEGER,"
                +"CONSTRAINT pk alimento PRIMARY KEY (id_alimento),"
                +"CONSTRAINT fk usuario FOREIGN KEY (id_usuario) references usuario(id_usuario));";

            statement.executeUpdate(sql);

            /* Beneficiado */

            sql = "CREATE TABLE IF NOT EXISTS beneficiado("
                +"id_beneficiado INTEGER AUTO INCREMENT NOT NULL,"
                +"nome_beneficiado VARCHAR(100) NOT NULL,"
                +"numero INTEGER,"
                +"rua VARCHAR(100),"
                +"bairro VARCHAR(100),"
                +"cidade VARCHAR(100),"
                +"estado VARCHAR(2),"
                +"telefone_beneficiado VARCHAR(20),"
                +"data_nasc DATE,"
                +"possui_dependente INTEGER(1),"
                +"data_cadastro_usuario DATE,"
                +"profissao VARCHAR(100),"
                +"id_usuario INTEGER(5),"
                +"CONSTRAINT pk beneficiado PRIMARY KEY (id_beneficiado),"
                +"CONSTRAINT fk usuario FOREIGN KEY (id_usuario) references usuario(id_usuario));";

            statement.executeUpdate(sql);

            /* Dependente */

            sql = "CREATE TABLE IF NOT EXISTS dependente("
                +"nome_dependente INTEGER,"
                +"parentesco VARCHAR(100),"
                +"data_nasc DATE,"
                +"profissao_dependente VARCHAR(100),"
                +"id_beneficiado INTEGER,"
                    +"CONSTRAINT fk usuario FOREIGN KEY (id_usuario) references usuario(id_usuario));";

            statement.executeUpdate(sql);

            /* Cesta */

            sql = "CREATE TABLE IF NOT EXISTS cesta("
                 +"id_cesta INTEGER AUTO_INCREMENT NOT NULL,"
                 +"id_usuario INTEGER NOT NULL,"
                 +"id_beneficiado INTEGER NOT NULL,"
                 +"data_montagem DATE NOT NULL,"
                 +"data_recebimento DATE NOT NULL,"
                 +"CONSTRAINT pk cesta PRIMARY KEY (id_cesta),"
                 +"CONSTRAINT fk usuario FOREIGN KEY (id_usuario) references usuario(id_usuario),"
                 +"CONSTRAINT fk beneficiado FOREIGN KEY (id_usuario) references usuario(id_usuario));";


            statement.executeUpdate(sql);

            /*Interação dos alimentos com a cesta */

            sql = "CREATE TABLE IF NOT EXISTS cesta_alimento("
                  +"id_cesta INTEGER NOT NULL,"
                  +"id_alimento INTERGER NOT NULL,"
                  +"quantidade INTEGER NOT NULL,"
                  +"CONSTRAINT fk cesta FOREIGN KEY (id_cesta) references cesta(id_cesta),"
                  +"CONSTRAINT fk alimento FOREIGN KEY (id_alimento) references alimento(id_alimento));";

            statement.executeUpdate(sql);

            /* Tipo de alimento */

            sql = "CREATE TABLE IF NOT EXISTS tipo("
                 +"id_tipo INTEGER AUTO_INCREMENT NOT NULL,"
                 +"nome_tipo VARCHAR NOT NULL,"
                 +"CONSTRAINT pk tipo PRIMARY KEY (id_tipo));";

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