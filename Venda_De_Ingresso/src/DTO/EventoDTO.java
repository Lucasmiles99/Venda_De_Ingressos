package DTO;

public class EventoDTO {
    public String nome;
    public String data;
    public String local;
    public int capacidadeMaxima;
    public String tipoEvento;
    public String descricao;
    public double valorIngresso;

    public EventoDTO() {
    	
    }

    public EventoDTO(String nome, String local) {
		super();
		this.nome = nome;
		this.local = local;
	}
    
    public EventoDTO(String nome, String data, String local, int capacidadeMaxima, String tipoEvento, String descricao, double valorIngresso) {
		super();
		this.nome = nome;
		this.data = data;
		this.local = local;
		this.capacidadeMaxima = capacidadeMaxima;
		this.tipoEvento = tipoEvento;
		this.descricao = descricao;
		this.valorIngresso = valorIngresso;
	}

    public String getNome() {
        return nome;
    }
    
	public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }
}
