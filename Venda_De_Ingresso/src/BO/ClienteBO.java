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
        return clienteDAO.alterar(clienteDTO);
    }

    public boolean excluir(int codigo) {
        return clienteDAO.excluir(codigo);
    }

    public ClienteDTO procurarPorCodigo(int codigo) {
        return clienteDAO.procurarPorCodigo(codigo);
    }

    public boolean procurarPorNome(String nome) {
        return true; // Modificado para procurar um único cliente
    }

    public boolean existe(ClienteDTO cliente) {
    	ClienteDAO clientesDAO = new ClienteDAO();
        return clientesDAO.existe(cliente);
    }

    public List<ClienteDTO> pesquisarTodos() {
        return clienteDAO.pesquisarTodos();
    }
}