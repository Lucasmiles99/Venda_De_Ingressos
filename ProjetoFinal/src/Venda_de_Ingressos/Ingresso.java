package Venda_de_Ingressos;

public class Ingresso {
    private TipoIngresso tipo;
    private double preco;
    private boolean disponivel;
    private Evento evento;

    public Ingresso(TipoIngresso tipo, double preco, Evento evento) {
        this.tipo = tipo;
        this.preco = preco;
        this.disponivel = true;
        this.evento = evento;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "tipo = " + tipo +
                ", preco = " + preco +
                ", disponivel = " + disponivel +
                ", evento = " + evento.getNome() +
                '}';
    }
}