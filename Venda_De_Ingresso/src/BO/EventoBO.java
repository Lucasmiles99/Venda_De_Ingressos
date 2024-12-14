package BO;

import DTO.EventoDTO;
import DAO.EventoDAO;
import java.util.List;

public class EventoBO {

    // Método para inserir evento
    public boolean inserir(EventoDTO evento) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.inserir(evento);
    }

    // Método para alterar evento
    public boolean alterar(EventoDTO evento) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.alterar(evento);
    }

    // Método para excluir evento
    public boolean excluir(String id) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.excluir(id);
    }

 // Método para procurar evento por ID
    public EventoDTO procurarPorId(String id) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.procurarPorId(id);
    }
    
    public EventoDTO procurarPorCodigo(int codigo) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.procurarPorCodigo(codigo); // Reutiliza o método do DAO
    }

    // Método para procurar evento por Descrição
    public EventoDTO procurarPorDescricao(String descricao) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.procurarPorDescricao(descricao);
    }

    // Método para verificar se um evento já existe
    public boolean existe(EventoDTO evento) {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.existe(evento);
    }

    // Método para listar todos os eventos
    public List<EventoDTO> pesquisarTodos() {
        EventoDAO eventoDAO = new EventoDAO();
        return eventoDAO.pesquisarTodos();
    }
}