package model.dao;

import model.vo.Dentista;
import model.vo.Voluntario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO {
    public void salvarVol(Voluntario v){
        String sql = "INSERT into VOLUNTARIO (CRO, NOME, IDADE, CPF, DT_NASC, EMAIL, TELEFONE, ENDERECO, DT_CADASTRO) values (?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, v.getCro());
            ps.setString(2, v.getNome());
            ps.setInt(3, v.getIdade());
            ps.setString(4, v.getCpf());
            ps.setDate(5, java.sql.Date.valueOf(v.getDtNasc()));
            ps.setString(6, v.getEmail());
            ps.setString(7, v.getTel());
            ps.setString(8, v.getEndereco());
            ps.setDate(9, Date.valueOf(v.getDtCadastro()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attVol(Voluntario v){
        String sql = "UPDATE VOLUNTARIO SET NOME=?, IDADE=?, CPF=?, DT_NASC=?, EMAIL=?, TELEFONE=?, ENDERECO=?, WHERE CRO=?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, v.getNome());
            ps.setInt(2, v.getIdade());
            ps.setString(3, v.getCpf());
            ps.setDate(4, java.sql.Date.valueOf(v.getDtNasc()));
            ps.setString(5, v.getEmail());
            ps.setString(6, v.getTel());
            ps.setString(7, v.getEndereco());
            ps.setInt(8, v.getCro());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerVol(Voluntario v){
        String sql = "DELETE FROM VOLUNTARIO WHERE CRO=?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, v.getCro());
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 2292){
                throw new RuntimeException("FK_VIOLATION", e);
            }
            throw new RuntimeException(e);
        }
    }

    public List<Voluntario> buscarVols(){
        String sql = "SELECT * FROM VOLUNTARIO";
        List<Voluntario> lista = new ArrayList<>();
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Voluntario v = new Voluntario(
                        rs.getString("NOME"),
                        rs.getInt("IDADE"),
                        rs.getString("CPF"),
                        rs.getDate("DT_NASC").toLocalDate(),
                        rs.getString("EMAIL"),
                        rs.getString("TELEFONE"),
                        rs.getString("ENDERECO"),
                        rs.getInt("CRO"),
                        rs.getDate("DT_CADASTRO").toLocalDate()
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
