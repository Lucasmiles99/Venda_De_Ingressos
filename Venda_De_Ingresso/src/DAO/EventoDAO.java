package DAO;

import DTO.EventoDTO;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.PreparedStatement; vou deixar comentado pq n ta usando mas deviria estar usando, mas se n der problema blz, time q ta ganhando n se mexe

public class EventoDAO {

    // Método para inserir evento
    public boolean inserir(EventoDTO evento) {
        String sql = "INSERT INTO eventos (nome, id, descricao, data, local, capacidadeMaxima, tipoEvento, valorIngresso) VALUES ('"
                + evento.getNome() + "', '"
                + evento.getId() + "', '"
                + evento.getDescricao() + "', '"
                + evento.getData() + "', '"
                + evento.getLocal() + "', "
                + evento.getCapacidadeMaxima() + ", '"
                + evento.getTipoEvento() + "', "
                + evento.getValorIngresso() + ")";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir evento: " + e.getMessage());
            return false;
        }
    }

    // Método para alterar evento
    public boolean alterar(EventoDTO evento) {
        String sql = "UPDATE eventos SET nome = '" + evento.getNome()
                + "', descricao = '" + evento.getDescricao()
                + "', data = '" + evento.getData()
                + "', local = '" + evento.getLocal()
                + "', capacidadeMaxima = " + evento.getCapacidadeMaxima()
                + ", tipoEvento = '" + evento.getTipoEvento()
                + "', valorIngresso = " + evento.getValorIngresso()
                + " WHERE id = '" + evento.getId() + "'";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao alterar evento: " + e.getMessage());
            return false;
        }
    }

    // Método para excluir evento
    public boolean excluir(String id) {
        String sql = "DELETE FROM eventos WHERE id = '" + id + "'";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir evento: " + e.getMessage());
            return false;
        }
    }

    // Método para procurar evento por ID
    public EventoDTO procurarPorId(String id) {
        String sql = "SELECT nome, id, descricao, data, local, capacidadeMaxima, tipoEvento, valorIngresso FROM eventos WHERE id = '" + id + "'";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new EventoDTO(
                    rs.getString("nome"),
                    rs.getString("id"),
                    rs.getString("data"),
                    rs.getString("local"),
                    rs.getInt("capacidadeMaxima"),
                    rs.getString("tipoEvento"),
                    rs.getString("descricao"),
                    rs.getDouble("valorIngresso")
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por ID: " + e.getMessage());
        }
        return null;
    }
    
    public EventoDTO procurarPorCodigo(int codigo) {
        String sql = "SELECT nome, id, descricao, data, local, capacidadeMaxima, tipoEvento, valorIngresso FROM eventos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
        	java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new EventoDTO(
                        rs.getString("nome"),
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("local"),
                        rs.getInt("capacidadeMaxima"),
                        rs.getString("tipoEvento"),
                        rs.getString("descricao"),
                        rs.getDouble("valorIngresso")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por código: " + e.getMessage());
        }
        return null;
    }

    // Método para procurar evento por Descrição
    public EventoDTO procurarPorDescricao(String descricao) {
        String sql = "SELECT nome, id, descricao, data, local, capacidadeMaxima, tipoEvento, valorIngresso FROM eventos WHERE descricao LIKE '%" + descricao + "%'";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new EventoDTO(
                    rs.getString("nome"),
                    rs.getString("id"),
                    rs.getString("data"),
                    rs.getString("local"),
                    rs.getInt("capacidadeMaxima"),
                    rs.getString("tipoEvento"),
                    rs.getString("descricao"),
                    rs.getDouble("valorIngresso")
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por descrição: " + e.getMessage());
        }
        return null;
    }

    // Método para verificar se um evento já existe
    public boolean existe(EventoDTO evento) {
        String sql = "SELECT COUNT(*) FROM eventos WHERE id = '" + evento.getId() + "' OR nome = '" + evento.getNome() + "'";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o evento existe
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar se o evento existe: " + e.getMessage());
        }
        return false; // Retorna false se o evento não existir
    }

    // Método para listar todos os eventos
    public List<EventoDTO> pesquisarTodos() {
        String sql = "SELECT nome, id, descricao, data, local, capacidadeMaxima, tipoEvento, valorIngresso FROM eventos";
        List<EventoDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new EventoDTO(
                    rs.getString("nome"),
                    rs.getString("id"),
                    rs.getString("data"),
                    rs.getString("local"),
                    rs.getInt("capacidadeMaxima"),
                    rs.getString("tipoEvento"),
                    rs.getString("descricao"),
                    rs.getDouble("valorIngresso")
                ));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar eventos: " + e.getMessage());
        }
        return lista;
    }
}
