package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DTO.ClienteDTO;
import Conexao.Conexao;

public class ClienteDAO {

    private static final String NOMEDATABELA = "cliente";

    public boolean inserir(ClienteDTO cliente) {
        String sql = "INSERT INTO " + NOMEDATABELA + " (nome, email, telefone, senha, cpf) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getSenha());
            ps.setString(5, cliente.getCpf());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(ClienteDTO cliente) {
        String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, email = ?, telefone = ?, senha = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getSenha());
            ps.setInt(5, cliente.getId());  

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true;  
            } else {
                System.out.println("Nenhum cliente foi alterado. Verifique se o cliente com ID " + cliente.getId() + " existe.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar cliente: " + e.getMessage());
            return false;  
        }
    }


    public boolean excluir(int id) {
        String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }

    public ClienteDTO procurarPorId(int id) {
        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSenha(rs.getString("senha"));
                return cliente;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao procurar cliente: " + e.getMessage());
        }
        return null;
    }


    public List<ClienteDTO> procurarPorNome(String nome) {
        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome LIKE ?";
        List<ClienteDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ClienteDTO(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("senha")
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar cliente por nome: " + e.getMessage());
        }
        return lista;
    }

    public List<ClienteDTO> pesquisarTodos() {
        String sql = "SELECT * FROM " + NOMEDATABELA;
        List<ClienteDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ClienteDTO(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("senha")
                ));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean existe(ClienteDTO cliente) {
        try {
        	String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE email = ?;";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public boolean logar(ClienteDTO cliente) {
        try {
        	String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE email = ? AND senha = ?;";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getEmail());
            ps.setString(2, cliente.getSenha());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public int pegarId(ClienteDTO cliente) {
        try {
        	String sql = "SELECT id FROM " + NOMEDATABELA + " WHERE email = ?;";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getEmail());
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
}