package Venda_de_Ingressos;

public class Evento {
    private String nome;
    private String dataInicio;
    private String dataFim;
    private String local;
    private boolean cancelado;

    public Evento(String nome, String dataInicio, String dataFim, String local) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.cancelado = false;
    }

    public String getNome() {
        return nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public String getLocal() {
        return local;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void cancelarEvento(String motivo) {
        this.cancelado = true;
        System.out.println("Evento cancelado: " + nome + ". Motivo: " + motivo);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome = '" + nome + '\'' +
                ", dataInicio = '" + dataInicio + '\'' +
                ", dataFim = '" + dataFim + '\'' +
                ", local = '" + local + '\'' +
                ", cancelado = " + cancelado +
                '}';
    }
}