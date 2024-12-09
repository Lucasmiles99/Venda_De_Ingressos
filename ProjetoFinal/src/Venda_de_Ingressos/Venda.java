package Venda_de_Ingressos;

public class Venda {
    private Cliente cliente;
    private Ingresso[] ingressos;
    private int ingressoIndex;
    private double valorTotal;
    private String dataVenda;

    public Venda(Cliente cliente, String dataVenda) {
        this.cliente = cliente;
        this.ingressos = new Ingresso[100];
        this.ingressoIndex = 0;
        this.valorTotal = 0.0;
        this.dataVenda = dataVenda;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        if (ingressoIndex < ingressos.length) {
            ingressos[ingressoIndex++] = ingresso;
            valorTotal += ingresso.getPreco();
        } else {
            System.out.println("Limite de ingressos atingido para a venda.");
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venda{cliente = ").append(cliente.getNome())
          .append(", dataVenda = '").append(dataVenda).append('\'')
          .append(", valorTotal = ").append(valorTotal).append(", ingressos =[");

        for (int i = 0; i < ingressoIndex; i++) {
            sb.append(ingressos[i]);
            if (i < ingressoIndex - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}