package Venda_de_Ingressos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HistoricoVendas historico = new HistoricoVendas();

        Cliente cliente = new Cliente("Lucas Miles", "123.456.789-00");
        Evento evento = new Evento("Show de Rock", "20/03/2025", "05/04/2025", "Estádio");
        Ingresso ingresso = new Ingresso(TipoIngresso.VIP, 250.0, evento);

        evento.cancelarEvento("Problemas técnicos");
        Cancelamento cancelamento = new Cancelamento("Problemas técnicos", evento);

        historico.listarVendas();
        System.out.println(cancelamento);

        scanner.close();
    }
}