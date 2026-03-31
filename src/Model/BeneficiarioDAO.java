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
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attBenef(Beneficiario b){
        String sql = "UPDATE BENEFICIARIO SET NOME=?, IDADE=?, CPF=?, DT_NASC=?, EMAIL=?, TELEFONE=?, ENDERECO=? WHERE ID=?";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getNome());
            ps.setInt(2, b.getIdade());
            ps.setString(3, b.getCpf());
            ps.setDate(4, java.sql.Date.valueOf(b.getDtNasc()));
            ps.setString(5, b.getEmail());
            ps.setString(6, b.getTel());
            ps.setString(7, b.getEndereco());
            ps.setInt(8, b.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerBenef(Beneficiario b){
        String sql = "DELETE FROM BENEFICIARIO WHERE ID=?";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, b.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void exibirBenefs(){
        String sql = "SELECT * FROM BENEFICIARIO";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
