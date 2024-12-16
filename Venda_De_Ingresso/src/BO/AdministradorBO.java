package BO;

import java.util.List;
import DAO.AdministradorDAO;
import DTO.AdministradorDTO;

public class AdministradorBO {
	private final AdministradorDAO administradorDAO = new AdministradorDAO();

    public boolean inserir(AdministradorDTO administradorDTO) {
        if (!existe(administradorDTO)) {
            return administradorDAO.inserir(administradorDTO);
        }
        return false;
    }

    public boolean alterar(AdministradorDTO administradorDTO, int idUsuario) {
        return administradorDAO.alterar(administradorDTO, idUsuario);
    }

    public boolean excluir(int codigo) {
        return administradorDAO.excluir(codigo);
    }

    public AdministradorDTO procurarPorCodigo(int codigo) {
        return administradorDAO.procurarPorCodigo(codigo);
    }

    public boolean procurarPorNome(String nome) {
        return true; 
    }

    public boolean existe(AdministradorDTO administradorDTO) {
    	AdministradorDAO administradorDAO = new AdministradorDAO();
        return administradorDAO.existe(administradorDTO);
    }

    public List<AdministradorDTO> pesquisarTodos() {
        return administradorDAO.pesquisarTodos();
    }
    
    public boolean logar(AdministradorDTO administradorDTO) {
    	AdministradorDAO administradorDAO = new AdministradorDAO();
        return administradorDAO.logar(administradorDTO);
    }
}
