package Venda_de_Ingressos;

import java.util.Scanner;

public class GerenciadorDeEventos {
    private Evento[] eventos;
    private int eventosIndex;

    public GerenciadorDeEventos() {
        eventos = new Evento[50]; 
        eventosIndex = 0;
    }

    public void adicionarEvento(Scanner scanner) {
        if (eventosIndex >= eventos.length) {
            System.out.println("Limite de eventos atingido.");
            return;
        }

        System.out.println("=== Adicionar Evento ===");
        System.out.print("Nome do Evento: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Início (dd/mm/yyyy): ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data de Término (dd/mm/yyyy): ");
        String dataTermino = scanner.nextLine();
        System.out.print("Local do Evento: ");
        String local = scanner.nextLine();

        Evento evento = new Evento(nome, dataInicio, dataTermino, local);
        eventos[eventosIndex++] = evento;
        System.out.println("Evento adicionado com sucesso!");
    }

    public void listarEventos() {
        System.out.println("=== Lista de Eventos ===");
        for (int i = 0; i < eventosIndex; i++) {
            System.out.println(eventos[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GerenciadorDeEventos{eventos = [");
        for (int i = 0; i < eventosIndex; i++) {
            sb.append(eventos[i]);
            if (i < eventosIndex - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}