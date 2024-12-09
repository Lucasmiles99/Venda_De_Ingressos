package Venda_de_Ingressos;

public class Cliente {
    private String nome;
    private String cpf;
    private Venda[] vendas;
    private Reserva[] reservas;
    private int vendasIndex;
    private int reservasIndex;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.vendas = new Venda[100];
        this.reservas = new Reserva[100]; 
        this.vendasIndex = 0;
        this.reservasIndex = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void adicionarVenda(Venda venda) {
        if (vendasIndex < vendas.length) {
            vendas[vendasIndex++] = venda;
        } else {
            System.out.println("Limite de vendas atingido para o cliente.");
        }
    }

    public void adicionarReserva(Reserva reserva) {
        if (reservasIndex < reservas.length) {
            reservas[reservasIndex++] = reserva;
        } else {
            System.out.println("Limite de reservas atingido para o cliente.");
        }
    }

    public Venda[] getVendas() {
        return vendas;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome = '" + nome + '\'' +
                ", cpf = '" + cpf + '\'' +
                ", vendas = " + vendasIndex +
                ", reservas = " + reservasIndex +
                '}';
    }
}