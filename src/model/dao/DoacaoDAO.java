package model.dao;

import model.vo.Doacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {

    public void salvarDoacao(Doacao d) {
        String sql = "INSERT INTO DOACAO (ID, ID_DOADOR, VALOR, DT_DOACAO, DESCRICAO) VALUES (?,?,?,?,?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getId());
            ps.setInt(2, d.getIdDoador());
            ps.setDouble(3, d.getValor());
            ps.setDate(4, java.sql.Date.valueOf(d.getDtDoacao()));
            ps.setString(5, d.getDescricao());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attDoacao(Doacao d) {
        String sql = "UPDATE DOACAO SET ID_DOADOR=?, VALOR=?, DT_DOACAO=?, DESCRICAO=? WHERE ID=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getIdDoador());
            ps.setDouble(2, d.getValor());
            ps.setDate(3, java.sql.Date.valueOf(d.getDtDoacao()));
            ps.setString(4, d.getDescricao());
            ps.setInt(5, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerDoacao(Doacao d) {
        String sql = "DELETE FROM DOACAO WHERE ID=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Doacao> buscarDoacao() {
        String sql = "SELECT * FROM DOACAO";
        List<Doacao> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Doacao d = new Doacao(
                        rs.getInt("ID_DOADOR"),
                        rs.getDouble("VALOR"),
                        rs.getDate("DT_DOACAO").toLocalDate(),
                        rs.getString("DESCRICAO")
                );
                d.setId(rs.getInt("ID"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int buscarMaxId() {
        String sql = "SELECT NVL(MAX(ID), 0) FROM DOACAO";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
