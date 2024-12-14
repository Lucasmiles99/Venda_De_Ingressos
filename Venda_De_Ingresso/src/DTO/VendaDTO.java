package DTO;

public class VendaDTO {
    public ClienteDTO cliente;
    public IngressoDTO[][] ingressos; 
    public int linhaAtual; 
    public int colunaAtual; 
    public double valorTotal;
    public String dataVenda;

    public VendaDTO(ClienteDTO cliente, String dataVenda) {
        this.cliente = cliente;
        this.ingressos = new IngressoDTO[10][10]; 
        this.linhaAtual = 0;
        this.colunaAtual = 0;
        this.valorTotal = 0.0;
        this.dataVenda = dataVenda;
    }

    public void adicionarIngresso(IngressoDTO ingresso) {
        if (linhaAtual >= ingressos.length) {
            System.out.println("Limite de ingressos atingido para a venda.");
            return;
        }

        ingressos[linhaAtual][colunaAtual] = ingresso;
        valorTotal += ingresso.getPreco();

        colunaAtual++;
        if (colunaAtual >= ingressos[linhaAtual].length) {
            colunaAtual = 0;
            linhaAtual++;
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

        for (int linha = 0; linha < ingressos.length; linha++) {
            for (int coluna = 0; coluna < ingressos[linha].length; coluna++) {
                if (ingressos[linha][coluna] != null) {
                    sb.append(ingressos[linha][coluna]);
                    sb.append(", ");
                }
            }
        }

        if (sb.lastIndexOf(", ") == sb.length() - 2) {
            sb.setLength(sb.length() - 2);
        }

        sb.append("]}");
        return sb.toString();
    }
}