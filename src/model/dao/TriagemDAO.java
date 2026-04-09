package model.dao;

import model.vo.Triagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TriagemDAO {

    public void salvarTriagem(Triagem t) {
        String sql = "INSERT INTO TRIAGEM (ID, ID_BENEFICIARIO, ID_VOLUNTARIO, DT_TRIAGEM, RESULTADO) VALUES (?,?,?,?,?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getIdBenef());
            ps.setInt(3, t.getIdVol());
            ps.setDate(4, java.sql.Date.valueOf(t.getDtTriagem()));
            ps.setString(5, t.getResultado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void attTriagem(Triagem t) {
        String sql = "UPDATE TRIAGEM SET ID_BENEFICIARIO=?, ID_VOLUNTARIO=?, DT_TRIAGEM=?, RESULTADO=? WHERE ID=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getIdBenef());
            ps.setInt(2, t.getIdVol());
            ps.setDate(3, java.sql.Date.valueOf(t.getDtTriagem()));
            ps.setString(4, t.getResultado());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerTriagem(Triagem t) {
        String sql = "DELETE FROM TRIAGEM WHERE ID=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Triagem> buscarTriagem() {
        String sql = "SELECT * FROM TRIAGEM";
        List<Triagem> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Triagem t = new Triagem(
                        rs.getInt("ID_BENEFICIARIO"),
                        rs.getInt("ID_VOLUNTARIO"),
                        rs.getDate("DT_TRIAGEM").toLocalDate(),
                        rs.getString("RESULTADO")
                );
                t.setId(rs.getInt("ID"));
                lista.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int buscarMaxId() {
        String sql = "SELECT NVL(MAX(ID), 0) FROM TRIAGEM";
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
