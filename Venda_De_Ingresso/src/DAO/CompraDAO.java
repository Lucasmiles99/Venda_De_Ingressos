package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;
import DTO.CompraDTO;

public class CompraDAO {
	private static final String TABELA = "compra";

    public boolean inserir(CompraDTO compra, int eventoId, int clienteId) {
        String sql = "INSERT INTO " + TABELA + " (id_cliente, id_evento, valor_total) VALUES (?, ?, ?);";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.setInt(2, eventoId);
            ps.setDouble(3, compra.getValorTotal());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir compra: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(CompraDTO compra, int eventoId, int clienteId) {
        String sql = "UPDATE " + TABELA + " SET valor_total = ? WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, compra.getValorTotal());
            ps.setInt(2, clienteId);
            ps.setInt(3, eventoId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao alterar ingresso: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int eventoId, int clienteId) {
        String sql = "DELETE FROM " + TABELA + " WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, clienteId);
            ps.setInt(2, eventoId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir ingresso: " + e.getMessage());
            return false;
        }
    }

    public CompraDTO procurarPorCodigo(int eventoId, int clienteId) {
        String sql = "SELECT * FROM " + TABELA + " WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.setInt(2, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	if (rs.getString("status_compra").equals("Pago")) {
                		return new CompraDTO(rs.getTimestamp("data_compra").toString(), rs.getDouble("valor_total"), true);
                	} else if (rs.getString("status_compra").equals("Reembolsado")) {
                		return new CompraDTO(rs.getTimestamp("data_compra").toString(), rs.getDouble("valor_total"), false);
                	}
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar ingresso por código: " + e.getMessage());
        }
        return null;
    }

    public List<CompraDTO> pesquisarTodos() {
        String sql = "SELECT * FROM " + TABELA;
        List<CompraDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	if (rs.getString("status_compra").equals("Pago")) {
            		lista.add(new CompraDTO(rs.getTimestamp("data_compra").toString(), rs.getDouble("valor_total"), true));
            	} else if (rs.getString("status_compra").equals("Reembolsado")) {
            		lista.add(new CompraDTO(rs.getTimestamp("data_compra").toString(), rs.getDouble("valor_total"), false));
            	}
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ingressos: " + e.getMessage());
        }
        return lista;
    }

    public boolean existe(int eventoId, int clienteId) {
        String sql = "SELECT * FROM " + TABELA + " WHERE id_cliente = ? AND id_evento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, clienteId);
            ps.setInt(2, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar existência de ingresso: " + e.getMessage());
        }
        return false;
    }
    
    public List<CompraDTO> pesquisarTodosPorCliente(int idCliente) {
        String sql = "SELECT * FROM " + TABELA + " WHERE id_cliente = " + idCliente + ";";
        List<CompraDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	if (rs.getString("status_compra").equals("Pago")) {
            		lista.add(new CompraDTO(rs.getTimestamp("data_compra").toString(), rs.getDouble("valor_total"), true));
            	} else if (rs.getString("status_compra").equals("Reembolsado")) {
            		lista.add(new CompraDTO(rs.getTimestamp("data_compra").toString(), rs.getDouble("valor_total"), false));
            	}
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ingressos: " + e.getMessage());
        }
        return lista;
    }
    /*
    public int pegarIdEvento(int idCliente, String data) {
        try {
        	String sql = "SELECT id_evento FROM " + TABELA + " WHERE id_cliente = ? AND compra.data_compra = '" + data + "';";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	return rs.getInt(1);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
    public int pegarQuantIngressos(int idEvento, int idCliente) {
        try {
        	String sql = "SELECT count(ingresso.id) FROM vendaingressos.ingresso, vendaingressos.evento, " + TABELA + " WHERE compra.id_evento = ? AND compra.id_cliente = ? AND compra.id_evento = evento.id AND ingresso.id_evento = compra.id_evento;";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEvento);
            ps.setInt(2, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	return rs.getInt(1);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           e.printStackTrace();
            return 0;
        }
        return 0;
    }*/
}
