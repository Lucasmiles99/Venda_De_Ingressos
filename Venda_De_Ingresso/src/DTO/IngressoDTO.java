package DTO;

public class IngressoDTO {
    private int idCliente;
    private int idEvento;
    private int localAssento;
    private boolean pago;
    private double preco;

    public IngressoDTO() {
    	
    }

    public IngressoDTO(int idCliente, int idEvento, int localAssento, boolean pago) {
		super();
		this.idCliente = idCliente;
		this.idEvento = idEvento;
		this.localAssento = localAssento;
		this.pago = pago;
	}

    public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getLocalAssento() {
		return localAssento;
	}

	public void setLocalAssento(int localAssento) {
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
