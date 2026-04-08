package model.dao;

import model.vo.Doador;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {

    public void salvarDoador(Doador d){
        String sql = "INSERT into DOADOR (ID, NOME, IDADE, TIPO_PESSOA, CPF, CNPJ, DT_NASC, EMAIL, TELEFONE, ENDERECO) values (?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, d.getId());
            ps.setString(2, d.getNome());
            ps.setInt(3, d.getIdade());
            ps.setString(4, d.getTipoPessoa());
            ps.setString(5, d.getCpf());
            ps.setString(6, d.getCnpj());
            if (d.getDtNasc() != null){
                ps.setDate(7, java.sql.Date.valueOf(d.getDtNasc()));
            }else {
                ps.setNull(7, Types.DATE);
            }
            ps.setString(8, d.getEmail());
            ps.setString(9, d.getTel());
            ps.setString(10, d.getEndereco());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attDoador(Doador d){
        String sql = "UPDATE DOADOR SET NOME=?, IDADE=?, TIPO_PESSOA=?, CPF=?, CNPJ=?, DT_NASC=?, EMAIL=?, TELEFONE=?, ENDERECO=? WHERE ID=?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, d.getNome());
            ps.setInt(2, d.getIdade());
            ps.setString(3, d.getTipoPessoa());
            ps.setString(4, d.getCpf());
            ps.setString(5, d.getCnpj());
            ps.setDate(6, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(7, d.getEmail());
            ps.setString(8, d.getTel());
            ps.setString(9, d.getEndereco());
            ps.setInt(10, d.getId());
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

                java.sql.Date data = rs.getDate("DT_NASC");
                LocalDate dtNasc = (data != null) ? data.toLocalDate() : null;

                Doador d = new Doador(
                        rs.getString("NOME"),
                        rs.getInt("IDADE"),
                        rs.getString("TIPO_PESSOA"),
                        rs.getString("CPF"),
                        rs.getString("CNPJ"),
                        dtNasc,
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
