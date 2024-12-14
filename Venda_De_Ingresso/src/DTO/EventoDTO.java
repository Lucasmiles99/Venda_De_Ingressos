package DTO;

public class EventoDTO {
    public String nome;
    public String id;
    public String data;
    public String local;
    public int capacidadeMaxima;
    public String tipoEvento;
    public String descricao;
    public double valorIngresso;

    // Construtor para inicializar todos os campos
    public EventoDTO() {
    	
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public EventoDTO(String nome, String id, String data, String local, int capacidadeMaxima, String tipoEvento,
			String descricao, double valorIngresso) {
		super();
		this.nome = nome;
		this.id = id;
		this.data = data;
		this.local = local;
		this.capacidadeMaxima = capacidadeMaxima;
		this.tipoEvento = tipoEvento;
		this.descricao = descricao;
		this.valorIngresso = valorIngresso;
	}

	public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
