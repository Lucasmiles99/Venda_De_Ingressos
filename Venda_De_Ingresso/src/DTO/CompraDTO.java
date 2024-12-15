package DTO;

public class CompraDTO {
    public String dataCompra;
    public double valorTotal;
    public boolean pago;
    
    public CompraDTO() {
		super();
	}
    
    public CompraDTO(double valorTotal, boolean pago) {
		super();
		this.valorTotal = valorTotal;
		this.pago = pago;
	}

    public CompraDTO(String dataCompra, double valorTotal, boolean pago) {
		super();
		this.dataCompra = dataCompra;
		this.valorTotal = valorTotal;
		this.pago = pago;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}