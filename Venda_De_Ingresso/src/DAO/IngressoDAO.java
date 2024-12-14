package DAO;

import DTO.ClienteDTO;
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
        String sql = "INSERT INTO " + TABELA + " (evento_id, cliente_id, local_assento, pago) VALUES (?, ?, ?, ?);";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(ingresso.getEvento().getId()));  // Assumindo que evento.getId() retorna um número.
            ps.setString(2, ingresso.getCliente().getId()); // Assumindo que cliente.getCodigo() retorna um número.
            ps.setString(3, ingresso.getLocalAssento());
            ps.setBoolean(4, ingresso.isPago());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir ingresso: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(IngressoDTO ingresso) {
        String sql = "UPDATE " + TABELA + " SET evento_id = ?, cliente_id = ?, local_assento = ?, pago = ? WHERE evento_id = ? AND cliente_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(ingresso.getEvento().getId()));
            ps.setString(2, ingresso.getCliente().getId());
            ps.setString(3, ingresso.getLocalAssento());
            ps.setBoolean(4, ingresso.isPago());
            ps.setInt(5, Integer.parseInt(ingresso.getEvento().getId()));
            ps.setString(6, ingresso.getCliente().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao alterar ingresso: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(IngressoDTO ingresso) {
        String sql = "DELETE FROM " + TABELA + " WHERE evento_id = ? AND cliente_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(ingresso.getEvento().getId()));
            ps.setString(2, ingresso.getCliente().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir ingresso: " + e.getMessage());
            return false;
        }
    }

    public IngressoDTO procurarPorCodigo(int eventoId, int clienteId) {
        String sql = "SELECT * FROM " + TABELA + " WHERE evento_id = ? AND cliente_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            ps.setInt(2, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EventoDTO evento = procurarEventoPorId(eventoId);  // Buscar EventoDTO
                    ClienteDTO cliente = procurarClientePorId(clienteId);  // Buscar ClienteDTO
                    return new IngressoDTO(evento, cliente, rs.getString("local_assento"), rs.getBoolean("pago"));
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
                EventoDTO evento = procurarEventoPorId(rs.getInt("evento_id"));
                ClienteDTO cliente = procurarClientePorId(rs.getInt("cliente_id"));
                lista.add(new IngressoDTO(evento, cliente, rs.getString("local_assento"), rs.getBoolean("pago")));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ingressos: " + e.getMessage());
        }
        return lista;
    }

    public boolean existe(IngressoDTO ingresso) {
        String sql = "SELECT 1 FROM " + TABELA + " WHERE evento_id = ? AND cliente_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(ingresso.getEvento().getId()));
            ps.setString(2, ingresso.getCliente().getId());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar existência de ingresso: " + e.getMessage());
        }
        return false;
    }

    // Método auxiliar para procurar EventoDTO por ID
    private EventoDTO procurarEventoPorId(int eventoId) {
        String sql = "SELECT * FROM evento WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new EventoDTO(rs.getString("nome"), 
                                         String.valueOf(rs.getInt("id")), 
                                         rs.getString("data"), 
                                         rs.getString("local"), 
                                         rs.getInt("capacidade_maxima"),
                                         rs.getString("tipo_evento"), 
                                         rs.getString("descricao"), 
                                         rs.getDouble("valor_ingresso"));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por ID: " + e.getMessage());
        }
        return null;
    }

    // Método auxiliar para procurar ClienteDTO por ID
    private ClienteDTO procurarClientePorId(int clienteId) {
        String sql = "SELECT * FROM cliente WHERE codigo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClienteDTO();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar cliente por ID: " + e.getMessage());
        }
        return null;
    }
}
