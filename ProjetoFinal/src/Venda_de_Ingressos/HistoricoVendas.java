package Venda_de_Ingressos;

public class HistoricoVendas {
    private Venda[] vendas;
    private int vendasIndex;

    public HistoricoVendas() {
        this.vendas = new Venda[200];
        this.vendasIndex = 0;
    }

    public void adicionarVenda(Venda venda) {
        if (vendasIndex < vendas.length) {
            vendas[vendasIndex++] = venda;
        } else {
            System.out.println("Limite de histórico de vendas atingido.");
        }
    }

    public Venda[] getVendas() {
        return vendas;
    }

    public void listarVendas() {
        System.out.println("Histórico de Vendas:");
        for (int i = 0; i < vendasIndex; i++) {
            System.out.println(vendas[i]);
        }
    }

    @Override
    public String toString() {
        return "HistoricoVendas{" +
                "totalVendas = " + vendasIndex +
                '}';
    }
}