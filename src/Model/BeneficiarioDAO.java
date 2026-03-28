package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BeneficiarioDAO {

    public void salvarBenef(Beneficiario b){
        String sql = "INSERT into BENEFICIARIO (ID, NOME, IDADE, CPF, DT_NASC, EMAIL, TELEFONE, ENDERECO) values (?,?,?,?,?,?,?,?)";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, b.getId());
            ps.setString(2, b.getNome());
            ps.setInt(3, b.getIdade());
            ps.setString(4, b.getCpf());
            ps.setDate(5, java.sql.Date.valueOf(b.getDtNasc()));
            ps.setString(6, b.getEmail());
            ps.setString(7, b.getTel());
            ps.setString(8, b.getEndereco());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
