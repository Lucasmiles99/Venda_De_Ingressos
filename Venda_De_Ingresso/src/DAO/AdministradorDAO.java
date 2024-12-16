package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;
import DTO.AdministradorDTO;
public class AdministradorDAO {
	private static final String NOMEDATABELA = "administrador";

    public boolean inserir(AdministradorDTO administradorDTO) {
        String sql = "INSERT INTO " + NOMEDATABELA + " (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, administradorDTO.getNome());
            ps.setString(2, administradorDTO.getEmail());
            ps.setString(3, administradorDTO.getSenha());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(AdministradorDTO administradorDTO, int idUsuario) {
        String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, email = ? WHERE id = " + idUsuario + ";";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, administradorDTO.getNome());
            ps.setString(2, administradorDTO.getEmail());
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

    public AdministradorDTO procurarPorCodigo(int codigo) {
        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AdministradorDTO(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar cliente por código: " + e.getMessage());
        }
        return null;
    }

    public List<AdministradorDTO> procurarPorNome(String nome) {
        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome LIKE ?";
        List<AdministradorDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new AdministradorDTO(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar cliente por nome: " + e.getMessage());
        }
        return lista;
    }

    public List<AdministradorDTO> pesquisarTodos() {
        String sql = "SELECT * FROM " + NOMEDATABELA;
        List<AdministradorDTO> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new AdministradorDTO(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean existe(AdministradorDTO administradorDTO) {
        try {
        	String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE email = ?;";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, administradorDTO.getEmail());
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
    
    public boolean logar(AdministradorDTO administradorDTO) {
        try {
        	String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE email = ? AND senha = ?;";
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, administradorDTO.getEmail());
            ps.setString(2, administradorDTO.getSenha());
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
