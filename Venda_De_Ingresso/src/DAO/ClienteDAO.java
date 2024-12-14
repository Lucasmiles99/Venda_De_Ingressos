package DAO;

import java.sql.Connection;
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
        String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, cpf = ? WHERE codigo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setInt(3, cliente.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao alterar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int codigo) {
        String sql = "DELETE FROM " + NOMEDATABELA + " WHERE codigo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }

    public ClienteDTO procurarPorCodigo(int codigo) {
        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE codigo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClienteDTO(
                        rs.getInt("codigo"),
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar cliente por código: " + e.getMessage());
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
                        rs.getInt("codigo"),
                        rs.getString("id"),
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
                    rs.getInt("codigo"),
                    rs.getString("id"),
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
}