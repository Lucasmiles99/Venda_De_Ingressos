package DTO;

public class Reserva {
    private ClienteDTO cliente;
    private IngressoDTO ingresso;
    private int quantidade;

    public Reserva(ClienteDTO cliente, IngressoDTO ingresso, int quantidade) {
        this.cliente = cliente;
        this.ingresso = ingresso;
        this.quantidade = quantidade;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public IngressoDTO getIngresso() {
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