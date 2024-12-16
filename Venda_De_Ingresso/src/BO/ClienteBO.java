package BO;

import DTO.ClienteDTO;
import DAO.ClienteDAO;
import java.util.List;

public class ClienteBO {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public boolean inserir(ClienteDTO clienteDTO) {
        if (!existe(clienteDTO)) {
            return clienteDAO.inserir(clienteDTO);
        }
        return false;
    }

    public boolean alterar(ClienteDTO clienteDTO) {
        if (clienteDTO.getNome() == null || clienteDTO.getNome().isEmpty()) {
            System.out.println("O nome não pode ser vazio.");
            return false;
        }
        if (clienteDTO.getEmail() == null || clienteDTO.getEmail().isEmpty()) {
            System.out.println("O email não pode ser vazio.");
            return false;
        }
        
        return clienteDAO.alterar(clienteDTO);
    }

    public boolean excluir(int id) {
        return clienteDAO.excluir(id);
    }

    public ClienteDTO procurarPorId(int id) {
        return clienteDAO.procurarPorId(id);
    }

    public boolean procurarPorNome(String nome) {
        return true; 
    }

    public boolean existe(ClienteDTO cliente) {
    	ClienteDAO clientesDAO = new ClienteDAO();
        return clientesDAO.existe(cliente);
    }

    public List<ClienteDTO> pesquisarTodos() {
        return clienteDAO.pesquisarTodos();
    }
    
    public boolean logar(ClienteDTO cliente) {
    	ClienteDAO clientesDAO = new ClienteDAO();
        return clientesDAO.logar(cliente);
    }
    
    public int pegarId(ClienteDTO cliente) {
    	ClienteDAO clientesDAO = new ClienteDAO();
        return clientesDAO.pegarId(cliente);
    }
}