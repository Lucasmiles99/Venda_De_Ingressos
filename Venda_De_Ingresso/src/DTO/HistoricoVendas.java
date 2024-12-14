package DTO;

public class HistoricoVendas {
    private VendaDTO[][] vendas; 
    private int linhaAtual; 

    public HistoricoVendas() {
        this.vendas = new VendaDTO[10][10]; 
        this.linhaAtual = 0;           
    }

    public void adicionarVenda(VendaDTO venda) {
        if (linhaAtual >= vendas.length) {
            System.out.println("Limite de histórico de vendas atingido.");
            return;
        }

        boolean vendaAdicionada = false;
        for (int coluna = 0; coluna < vendas[linhaAtual].length; coluna++) {
            if (vendas[linhaAtual][coluna] == null) {
                vendas[linhaAtual][coluna] = venda;
                vendaAdicionada = true;
                break;
            }
        }

        if (!vendaAdicionada) {
            linhaAtual++;
            if (linhaAtual < vendas.length) {
                vendas[linhaAtual][0] = venda;
            } else {
                System.out.println("Limite de histórico de vendas atingido.");
            }
        } else {
            System.out.println("Venda adicionada com sucesso!");
        }
    }

    public VendaDTO[][] getVendas() {
        return vendas;
    }

    public void listarVendas() {
        System.out.println("=== Histórico de Vendas ===");
        for (int linha = 0; linha <= linhaAtual; linha++) {
            for (int coluna = 0; coluna < vendas[linha].length; coluna++) {
                if (vendas[linha][coluna] != null) {
                    System.out.println(vendas[linha][coluna]);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HistoricoVendas{vendas = [");
        for (int linha = 0; linha <= linhaAtual; linha++) {
            for (int coluna = 0; coluna < vendas[linha].length; coluna++) {
                if (vendas[linha][coluna] != null) {
                    sb.append(vendas[linha][coluna]);
                    if (linha < linhaAtual || (linha == linhaAtual && coluna < vendas[linha].length - 1)) {
                        sb.append(", ");
                    }
                }
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}