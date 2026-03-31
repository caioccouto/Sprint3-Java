package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DentistaDAO {
    public void salvarDent(Dentista d){
        String sql = "INSERT into DENTISTA (CRO, NOME, IDADE, CPF, DT_NASC, EMAIL, TELEFONE, ENDERECO) values (?,?,?,?,?,?,?,?)";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, d.getCro());
            ps.setString(2, d.getNome());
            ps.setInt(3, d.getIdade());
            ps.setString(4, d.getCpf());
            ps.setDate(5, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(6, d.getEmail());
            ps.setString(7, d.getTel());
            ps.setString(8, d.getEndereco());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attDent(Dentista d){
        String sql = "UPDATE DENTISTA SET NOME=?, IDADE=?, CPF=?, DT_NASC=?, EMAIL=?, TELEFONE=?, ENDERECO=? WHERE CRO=?";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getNome());
            ps.setInt(2, d.getIdade());
            ps.setString(3, d.getCpf());
            ps.setDate(4, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getTel());
            ps.setString(7, d.getEndereco());
            ps.setInt(8, d.getCro());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerDent(Dentista d){
        String sql = "DELETE FROM DENTISTA WHERE CRO=?";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, d.getCro());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void exibirDents(){
        String sql = "SELECT * FROM DENTISTA";

        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
