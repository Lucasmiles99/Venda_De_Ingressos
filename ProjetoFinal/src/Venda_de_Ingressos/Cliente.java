package Venda_de_Ingressos;

public class Cliente {
    private String nome;
    private String cpf;
    private Venda[][] vendas; 
    private Reserva[][] reservas; 
    private int vendasLinha; 
    private int reservasLinha; 

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.vendas = new Venda[10][10]; 
        this.reservas = new Reserva[10][10]; 
        this.vendasLinha = 0;
        this.reservasLinha = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void adicionarVenda(Venda venda) {
        if (vendasLinha < vendas.length) {
            for (int i = 0; i < vendas[vendasLinha].length; i++) {
                if (vendas[vendasLinha][i] == null) {
                    vendas[vendasLinha][i] = venda;
                    return;
                }
            }
            vendasLinha++;
            if (vendasLinha < vendas.length) {
                vendas[vendasLinha][0] = venda;
            } else {
                System.out.println("Limite de vendas atingido para o cliente.");
            }
        }
    }

    public void adicionarReserva(Reserva reserva) {
        if (reservasLinha < reservas.length) {
            for (int i = 0; i < reservas[reservasLinha].length; i++) {
                if (reservas[reservasLinha][i] == null) {
                    reservas[reservasLinha][i] = reserva;
                    return;
                }
            }
            reservasLinha++;
            if (reservasLinha < reservas.length) {
                reservas[reservasLinha][0] = reserva;
            } else {
                System.out.println("Limite de reservas atingido para o cliente.");
            }
        }
    }

    public Venda[][] getVendas() {
        return vendas;
    }

    public Reserva[][] getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome ='" + nome + '\'' +
                ", cpf ='" + cpf + '\'' +
                ", vendas =" + vendasLinha +
                ", reservas =" + reservasLinha +
                '}';
    }
}