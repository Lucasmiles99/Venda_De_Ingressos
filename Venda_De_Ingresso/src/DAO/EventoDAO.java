package DAO;

import DTO.EventoDTO;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.PreparedStatement; 

public class EventoDAO {

    public boolean inserir(EventoDTO evento) {
        String sql = "INSERT INTO evento (nome, data, local, capacidade_maxima, tipo, descricao, valor) VALUES ('"
                + evento.getNome() + "', '"
                + evento.getData() + "', '"
                + evento.getLocal() + "', "
                + evento.getCapacidadeMaxima() + ", '"
                + evento.getTipoEvento() + "', '"
                + evento.getDescricao() + "', "
                + evento.getValorIngresso() + ");";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir evento: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(EventoDTO evento, int id) {
        String sql = "UPDATE evento SET nome = '" + evento.getNome()
                + "', data = '" + evento.getData()
                + "', local = '" + evento.getLocal()
                + "', capacidade_maxima = " + evento.getCapacidadeMaxima()
                + ", tipo = '" + evento.getTipoEvento()
                + "', descricao = '" + evento.getDescricao()
                + "', valor = " + evento.getValorIngresso()
                + " WHERE id = '" + id + "';";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao alterar evento: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM evento WHERE id = '" + id + "';";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir evento: " + e.getMessage());
            return false;
        }
    }

    public EventoDTO procurarPorId(int id) {
        String sql = "SELECT nome, data, local, capacidade_maxima, tipo, descricao, valor FROM evento WHERE id = '" + id + "';";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new EventoDTO(
                    rs.getString("nome"),
                    rs.getString("data"),
                    rs.getString("local"),
                    rs.getInt("capacidade_maxima"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por ID: " + e.getMessage());
        }
        return null;
    }
    
    public EventoDTO procurarPorCodigo(int codigo) {
        String sql = "SELECT nome, data, local, capacidade_maxima, tipo, descricao, valor FROM evento WHERE id = ?;";
        try (Connection conn = Conexao.getConnection();
        	java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new EventoDTO(
                		rs.getString("nome"),
                        rs.getString("data"),
                        rs.getString("local"),
                        rs.getInt("capacidade_maxima"),
                        rs.getString("tipo"),
                        rs.getString("descricao"),
                        rs.getDouble("valor")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por código: " + e.getMessage());
        }
        return null;
    }
    
    public EventoDTO procurarPorDescricao(String descricao) {
        String sql = "SELECT nome, id, descricao, data, local, capacidadeMaxima, tipoEvento, valorIngresso FROM evento WHERE descricao LIKE '%" + descricao + "%';";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new EventoDTO(
            		rs.getString("nome"),
                    rs.getString("data"),
                    rs.getString("local"),
                    rs.getInt("capacidade_maxima"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar evento por descrição: " + e.getMessage());
        }
        return null;
    }

    public boolean existe(EventoDTO evento, int id) {
        String sql = "SELECT COUNT(*) FROM evento WHERE id = '" + id + "' OR nome = '" + evento.getNome() + "';";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar se o evento existe: " + e.getMessage());
        }
        return false; 
    }

    public List<EventoDTO> pesquisarTodos() {
        String sql = "SELECT nome, data, local, capacidade_maxima, tipo, descricao, valor FROM evento;";
        List<EventoDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new EventoDTO(
            		rs.getString("nome"),
                    rs.getString("data"),
                    rs.getString("local"),
                    rs.getInt("capacidade_maxima"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                ));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar evento: " + e.getMessage());
        }
        return lista;
    }
}
