package Model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conexao {

    private final String BD = "jdbc:mysql://localhost/instituicao";
    private final String USER = "";
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
                + "nome_login VARCHAR(20) NOT NULL,"
                + "senha_usuario VARCHAR(20) NOT NULL,"
                + "data_cadastro_user timestamp default current_timestamp(),"
                + "usuario_excluido INTEGER DEFAULT 0 NOT NULL,"
                + "CONSTRAINT pk_usuario PRIMARY KEY (id_usuario));";

            statement.executeUpdate(sql);

            /* Alimento */

            sql = "CREATE TABLE IF NOT EXISTS alimento("
                +"id_alimento INTEGER AUTO_INCREMENT NOT NULL,"
                +"nome_alimento VARCHAR(100) NOT NULL,"
                +"tipo VARCHAR(100),"
                +"data_validade DATE,"
                +"id_usuario INTEGER,"
                +"alimento_doado INTEGER DEFAULT 0 NOT NULL,"
                +"CONSTRAINT pk_alimento PRIMARY KEY (id_alimento),"
                +"CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) references usuario(id_usuario));";


            statement.executeUpdate(sql);

            /* Beneficiado */

            sql = "CREATE TABLE IF NOT EXISTS beneficiado("
                +"id_beneficiado INTEGER AUTO_INCREMENT NOT NULL,"
                +"nome_beneficiado VARCHAR(100) NOT NULL,"
                +"numero INTEGER NOT NULL,"
                +"rua VARCHAR(100) NOT NULL,"
                +"bairro VARCHAR(100) NOT NULL,"
                +"cidade VARCHAR(100) NOT NULL,"
                +"estado VARCHAR(2) NOT NULL,"
                +"telefone_beneficiado VARCHAR(20),"
                +"data_nasc DATE,"
                +"possui_dependente INTEGER(1) NOT NULL DEFAULT 0,"
                +"data_cadastro_beneficiado timestamp default current_timestamp(),"
                +"profissao VARCHAR(100),"
                +"beneficiado_excluido INTEGER DEFAULT 0 NOT NULL,"
                +"beneficiado_descricao VARCHAR(255),"
                +"id_usuario INTEGER(5),"
                +"CONSTRAINT pk_beneficiado PRIMARY KEY (id_beneficiado),"
                +"CONSTRAINT fk_usuario2 FOREIGN KEY (id_usuario) references usuario(id_usuario));";


            statement.executeUpdate(sql);

            /* Cesta */

            sql = "CREATE TABLE IF NOT EXISTS cesta("
                 +"id_cesta INTEGER AUTO_INCREMENT NOT NULL,"
                 +"id_usuario INTEGER NOT NULL,"
                 +"id_beneficiado INTEGER NOT NULL,"
                 +"data_montagem DATE NOT NULL,"
                 +"data_recebimento DATE NOT NULL,"
                 +"CONSTRAINT pk_cesta1 PRIMARY KEY (id_cesta),"
                 +"CONSTRAINT fk_usuario1 FOREIGN KEY (id_usuario) references usuario(id_usuario),"
                 +"CONSTRAINT fk_beneficiado1 FOREIGN KEY (id_beneficiado) references beneficiado(id_beneficiado));";


            statement.executeUpdate(sql);

            /*Interação dos alimentos com a cesta */

            sql = "CREATE TABLE IF NOT EXISTS cesta_alimento("
                  +"id_cesta INTEGER NOT NULL,"
                  +"id_alimento INTEGER NOT NULL,"
                  +"quantidade INTEGER NOT NULL,"
                  +"CONSTRAINT fk_cesta2 FOREIGN KEY (id_cesta) references cesta(id_cesta),"
                  +"CONSTRAINT fk_alimento2 FOREIGN KEY (id_alimento) references alimento(id_alimento));";

            statement.executeUpdate(sql);

            /* Tipo de alimento */

            sql = "CREATE TABLE IF NOT EXISTS tipo("
                 +"id_tipo INTEGER AUTO_INCREMENT NOT NULL,"
                 +"nome_tipo VARCHAR(100) NOT NULL,"
                 +"CONSTRAINT pk_tipo PRIMARY KEY (id_tipo));";

            statement.executeUpdate(sql);

            /* Adiciona um administrador padrão para o sistema */
            //sql = "INSERT INTO  VALUES (null, 'ADMINISTRADOR', 'admin', '1234', 1);";

            statement.executeUpdate(sql);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
