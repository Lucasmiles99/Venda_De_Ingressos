package DTO;

import java.util.List;

public class ClienteDTO {
	private int codigo;
    private String id;
    private String nome;
    private String cpf;
    private List<IngressoDTO> ingressos;
    private String email;
    private String telefone;
    private String senha;

    // Construtor padrão
    public ClienteDTO() {}

    public ClienteDTO(int codigo, String id, String nome, String cpf, String email, String telefone, String senha) {
    	this.codigo = codigo;
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
    
    // Construtor com parâmetros                                      
    public ClienteDTO(int codigo, String id, String nome, String cpf, List<IngressoDTO> ingressos, String email, String telefone, String senha) {
    	this.codigo = codigo;
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ingressos = ingressos;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    // Getters e setters
    
    public int getCodigo() {
    	return codigo;
    }
    
    public void setCodigo(int codigo) {
    	this.codigo = codigo;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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