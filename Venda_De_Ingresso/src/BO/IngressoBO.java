package BO;

import DTO.EventoDTO;
import DTO.IngressoDTO;
import DAO.IngressoDAO;
import java.util.List;

public class IngressoBO {

    public boolean inserir(IngressoDTO ingresso) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.inserir(ingresso);
    }

    public boolean alterar(IngressoDTO ingresso) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.alterar(ingresso);
    }

    public boolean excluir(IngressoDTO ingresso) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.excluir(ingresso);
    }

    public IngressoDTO procurarPorCodigo(int eventoId, int clienteId) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.procurarPorCodigo(eventoId, clienteId);
    }

    public boolean existe(IngressoDTO ingresso) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.existe(ingresso);
    }

    public List<IngressoDTO> pesquisarTodos() {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.pesquisarTodos();
    }
    
    public List<EventoDTO> pegarPorCliente(int id) {
        IngressoDAO ingressoDAO = new IngressoDAO();
        return ingressoDAO.pegarPorCliente(id);
    }
}
