package DTO;

import java.util.List;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private List<IngressoDTO> ingressos;
    private String email;
    private String telefone;
    private String senha;

    public ClienteDTO() {}

    public ClienteDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public ClienteDTO(String nome, String cpf, String email, String telefone, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
                                         
    public ClienteDTO(String nome, String cpf, List<IngressoDTO> ingressos, String email, String telefone, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.ingressos = ingressos;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
    	return cpf;
    }
    
    public void setCpf(String cpf) {
    	this.cpf = cpf;
    }
    
    public List<IngressoDTO> getIngressos() {
		return ingressos;
    }
    
    public void setIngressos(List<IngressoDTO> ingressos) {
		this.ingressos = ingressos;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}