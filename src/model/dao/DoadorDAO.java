package model.dao;

import model.vo.Doador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {

    public void salvarDoador(Doador d){
        String sql = "INSERT into DOADOR (ID, NOME, IDADE, CPF, DT_NASC, EMAIL, TELEFONE, ENDERECO) values (?,?,?,?,?,?,?,?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, d.getId());
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

    public void attDoador(Doador d){
        String sql = "UPDATE DOADOR SET NOME=?, IDADE=?, CPF=?, DT_NASC=?, EMAIL=?, TELEFONE=?, ENDERECO=? WHERE ID=?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, d.getNome());
            ps.setInt(2, d.getIdade());
            ps.setString(3, d.getCpf());
            ps.setDate(4, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getTel());
            ps.setString(7, d.getEndereco());
            ps.setInt(8, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerDoador(Doador d){
        String sql = "DELETE FROM DOADOR WHERE ID=?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Doador> buscarDoador(){
        String sql = "SELECT * FROM DOADOR";
        List<Doador> lista = new ArrayList<>();
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Doador d = new Doador(
                        rs.getString("NOME"),
                        rs.getInt("IDADE"),
                        rs.getString("CPF"),
                        rs.getDate("DT_NASC").toLocalDate(),
                        rs.getString("EMAIL"),
                        rs.getString("TELEFONE"),
                        rs.getString("ENDERECO")
                );
                d.setId(rs.getInt("ID"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int buscarMaxId(){
        String sql = "SELECT NVL(MAX(ID), 0) FROM DOADOR";
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
