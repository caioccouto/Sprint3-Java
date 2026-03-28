package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection(){
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        String user = "rm563452";
        String password = "260506";

        try{
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
