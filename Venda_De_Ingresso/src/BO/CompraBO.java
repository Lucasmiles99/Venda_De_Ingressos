package BO;

import java.util.List;

import DAO.CompraDAO;
import DTO.CompraDTO;

public class CompraBO {
	public boolean inserir(CompraDTO compra, int eventoId, int clienteId) {
		CompraDAO compraDAO = new CompraDAO();
        return compraDAO.inserir(compra, eventoId, clienteId);
    }

    public boolean alterar(CompraDTO compra, int eventoId, int clienteId) {
    	CompraDAO compraDAO = new CompraDAO();
        return compraDAO.alterar(compra, eventoId, clienteId);
    }

    public boolean excluir(int eventoId, int clienteId) {
    	CompraDAO compraDAO = new CompraDAO();
        return compraDAO.excluir(eventoId, clienteId);
    }

    public CompraDTO procurarPorCodigo(int eventoId, int clienteId) {
    	CompraDAO compraDAO = new CompraDAO();
        return compraDAO.procurarPorCodigo(eventoId, clienteId);
    }

    public boolean existe(int eventoId, int clienteId) {
    	CompraDAO compraDAO = new CompraDAO();
        return compraDAO.existe(eventoId, clienteId);
    }

    public List<CompraDTO> pesquisarTodos() {
    	CompraDAO compraDAO = new CompraDAO();
        return compraDAO.pesquisarTodos();
    }
    
    public List<CompraDTO> pesquisarTodosPorCliente(int idCliente) {
    	CompraDAO compraDAO = new CompraDAO();
        return compraDAO.pesquisarTodosPorCliente(idCliente);
    }
}