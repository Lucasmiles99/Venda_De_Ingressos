package Venda_de_Ingressos;

public class Reserva {
    private Cliente cliente;
    private Ingresso ingresso;
    private int quantidade;

    public Reserva(Cliente cliente, Ingresso ingresso, int quantidade) {
        this.cliente = cliente;
        this.ingresso = ingresso;
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente = " + cliente.getNome() +
                ", ingresso = " + ingresso +
                ", quantidade = " + quantidade +
                '}';
    }
}