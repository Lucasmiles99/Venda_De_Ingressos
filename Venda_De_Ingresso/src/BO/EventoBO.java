package BO;

import DTO.EventoDTO;
import DAO.EventoDAO;
import java.util.List;

public class EventoBO {

    public boolean inserir(EventoDTO evento) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.inserir(evento);
    }

    public boolean alterar(EventoDTO evento, int id) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.alterar(evento, id);
    }

    public boolean excluir(int id) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.excluir(id);
    }

    public EventoDTO procurarPorId(int id) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.procurarPorId(id);
    }
    
    public EventoDTO procurarPorCodigo(int codigo) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.procurarPorCodigo(codigo); 
    }

    public EventoDTO procurarPorDescricao(String descricao) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.procurarPorDescricao(descricao);
    }

    public boolean existe(EventoDTO evento, int id) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.existe(evento, id);
    }

    public List<EventoDTO> pesquisarTodos() {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.pesquisarTodos();
    }
}