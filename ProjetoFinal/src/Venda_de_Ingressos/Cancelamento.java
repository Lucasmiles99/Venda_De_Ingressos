package Venda_de_Ingressos;

public class Cancelamento {
    private String motivo;
    private Venda vendaCancelada;
    private Evento eventoCancelado;

    public Cancelamento(String motivo, Venda venda) {
        this.motivo = motivo;
        this.vendaCancelada = venda;
    }

    public Cancelamento(String motivo, Evento evento) {
        this.motivo = motivo;
        this.eventoCancelado = evento;
    }

    public String getMotivo() {
        return motivo;
    }

    public Venda getVendaCancelada() {
        return vendaCancelada;
    }

    public Evento getEventoCancelado() {
        return eventoCancelado;
    }

    @Override
    public String toString() {
        if (vendaCancelada != null) {
            return "Cancelamento{" +
                    "motivo = '" + motivo + '\'' +
                    ", vendaCancelada = " + vendaCancelada +
                    '}';
        } else if (eventoCancelado != null) {
            return "Cancelamento{" +
                    "motivo = '" + motivo + '\'' +
                    ", eventoCancelado = " + eventoCancelado +
                    '}';
        }
        return "Cancelamento{" + "motivo = '" + motivo + '\'' + '}';
    }
}