package Conexao;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class GerenciadorPessoas {
    private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private Connection connection;

    public GerenciadorPessoas() throws SQLException {
        OracleDataSource ods = new OracleDataSource();

        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        connection = ods.getConnection();

        System.out.println("Conexão com Banco de Dados Estabelecida!");
    }
}
