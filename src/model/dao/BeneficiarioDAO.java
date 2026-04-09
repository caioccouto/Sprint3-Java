package model.dao;

import model.vo.Beneficiario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDAO {

    public void salvarBenef(Beneficiario b){
        String sql = "INSERT into BENEFICIARIO (ID, NOME, IDADE, CPF, DT_NASC, EMAIL, TELEFONE, ENDERECO) values (?,?,?,?,?,?,?,?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
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

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
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

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, b.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 2292){
                throw new RuntimeException("FK_VIOLATION", e);
            }
            throw new RuntimeException(e);
        }
    }

    public List<Beneficiario> buscarBenefs(){
        String sql = "SELECT * FROM BENEFICIARIO";
        List<Beneficiario> lista = new ArrayList<>();
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Beneficiario b = new Beneficiario(
                        rs.getString("NOME"),
                        rs.getInt("IDADE"),
                        rs.getString("CPF"),
                        rs.getDate("DT_NASC").toLocalDate(),
                        rs.getString("EMAIL"),
                        rs.getString("TELEFONE"),
                        rs.getString("ENDERECO")
                );
                b.setId(rs.getInt("ID"));
                lista.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int buscarMaxId(){
        String sql = "SELECT NVL(MAX(ID), 0) FROM BENEFICIARIO";
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return 0;
    }
}
