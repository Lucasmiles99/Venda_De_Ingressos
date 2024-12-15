package DAO;
import DTO.EventoDTO;
import DTO.IngressoDTO;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {

    private static final String TABELA = "ingresso";

    public boolean inserir(IngressoDTO ingresso) {
        String sql = "INSERT INTO " + TABELA + " (id_cliente, id_evento, numero_assento, disponivel) VALUES (?, ?, ?, ?);";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ingresso.getIdCliente());  
            ps.setInt(2, ingresso.getIdEvento()); 
            ps.setInt(3, ingresso.getLocalAssento());
            ps.setBoolean(4, ingresso.isPago());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir ingresso: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(IngressoDTO ingresso) {
        String sql = "UPDATE " + TABELA + " SET id_cliente = ?, id_evento = ?, numero_assento = ?, disponivel = ? WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ingresso.getIdCliente());
            ps.setInt(2, ingresso.getIdEvento());
            ps.setInt(3, ingresso.getLocalAssento());
            ps.setBoolean(4, ingresso.isPago());
            ps.setInt(5, ingresso.getIdCliente());
            ps.setInt(6, ingresso.getIdEvento());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao alterar ingresso: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(IngressoDTO ingresso) {
        String sql = "DELETE FROM " + TABELA + " WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, ingresso.getIdCliente());
            ps.setInt(2, ingresso.getIdEvento());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir ingresso: " + e.getMessage());
            return false;
        }
    }

    public IngressoDTO procurarPorCodigo(int eventoId, int clienteId) {
        String sql = "SELECT * FROM " + TABELA + " WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.setInt(2, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new IngressoDTO(rs.getInt("id_cliente"), rs.getInt("id_evento"), rs.getInt("numero_assento"), rs.getBoolean("disponivel"));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar ingresso por código: " + e.getMessage());
        }
        return null;
    }

    public List<IngressoDTO> pesquisarTodos() {
        String sql = "SELECT * FROM " + TABELA;
        List<IngressoDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new IngressoDTO(rs.getInt("id_cliente"), rs.getInt("id_evento"), rs.getInt("numero_assento"), rs.getBoolean("disponivel")));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ingressos: " + e.getMessage());
        }
        return lista;
    }

    public boolean existe(IngressoDTO ingresso) {
        String sql = "SELECT 1 FROM " + TABELA + " WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, ingresso.getIdCliente());
            ps.setInt(2, ingresso.getIdEvento());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar existência de ingresso: " + e.getMessage());
        }
        return false;
    }
    
    public List<EventoDTO> pegarPorCliente(int id) {
        String sql = "SELECT nome, local FROM " + TABELA + ", evento WHERE id_cliente = " + id + " AND ingresso.id_evento = evento.id;";
        List<EventoDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new EventoDTO(rs.getString("nome"), rs.getString("local")));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ingressos: " + e.getMessage());
        }
        return lista;
    }
}
