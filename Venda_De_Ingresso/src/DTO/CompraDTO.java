package DTO;

import java.util.List;

public class CompraDTO {
    public ClienteDTO cliente;
    public List<IngressoDTO> ingressos;
    public String dataCompra;
    public boolean reembolsado;

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<IngressoDTO> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<IngressoDTO> ingressos) {
        this.ingressos = ingressos;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public boolean isReembolsado() {
        return reembolsado;
    }

    public void setReembolsado(boolean reembolsado) {
        this.reembolsado = reembolsado;
    }
}