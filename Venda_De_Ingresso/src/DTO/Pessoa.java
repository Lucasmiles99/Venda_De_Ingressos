package DTO;

public class Pessoa {
    private String nome;
    private String cpf;
    private String idade;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
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
    
	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
    
}