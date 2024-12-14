package DTO;

public class IngressoDTO {
    private EventoDTO evento;
    private ClienteDTO cliente;
    private String localAssento;
    private boolean pago;
    private double preco;

    public IngressoDTO() {
    	
    }

    public IngressoDTO(EventoDTO evento, ClienteDTO cliente, String localAssento, boolean pago) {
		super();
		this.evento = evento;
		this.cliente = cliente;
		this.localAssento = localAssento;
		this.pago = pago;
	}

	public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public String getLocalAssento() {
        return localAssento;
    }

    public void setLocalAssento(String localAssento) {
        this.localAssento = localAssento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
    
}
