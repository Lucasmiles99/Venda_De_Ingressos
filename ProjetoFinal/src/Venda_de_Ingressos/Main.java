package Venda_de_Ingressos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
        HistoricoVendas historicoVendas = new HistoricoVendas();
        Cliente cliente = null;

        while (true) {
            System.out.println("\n=== Sistema de Venda de Ingressos ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Adicionar Evento");
            System.out.println("3. Listar Eventos");
            System.out.println("4. Criar Venda");
            System.out.println("5. Listar Histórico de Vendas");
            System.out.println("6. Cancelar Evento");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.println("=== Cadastrar Cliente ===");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    cliente = new Cliente(nome, cpf);
                    System.out.println("Cliente cadastrado com sucesso!");
                }
                case 2 -> {
                    if (cliente == null) {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    } else {
                        gerenciadorEventos.adicionarEvento(scanner);
                    }
                }
                case 3 -> gerenciadorEventos.listarEventos();
                case 4 -> {
                    if (cliente == null) {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    } else {
                        System.out.println("=== Criar Venda ===");
                        System.out.print("Digite o nome do evento para a venda: ");
                        String nomeEvento = scanner.nextLine();
                        Evento eventoSelecionado = gerenciadorEventos.buscarEventoPorNome(nomeEvento);

                        if (eventoSelecionado == null) {
                            System.out.println("Evento não encontrado.");
                        } else {
                            System.out.print("Quantidade de ingressos: ");
                            int quantidade = scanner.nextInt();
                            scanner.nextLine();

                            Venda venda = new Venda(cliente, "Data atual");
                            for (int i = 0; i < quantidade; i++) {
                                venda.adicionarIngresso(
                                        new Ingresso(TipoIngresso.COMUM, 100.0, eventoSelecionado)
                                );
                            }
                            historicoVendas.adicionarVenda(venda);
                            System.out.println("Venda registrada com sucesso!");
                        }
                    }
                }
                case 5 -> historicoVendas.listarVendas();
                case 6 -> {
                    System.out.print("Digite o nome do evento a ser cancelado: ");
                    String nomeEvento = scanner.nextLine();
                    Evento eventoParaCancelar = gerenciadorEventos.buscarEventoPorNome(nomeEvento);

                    if (eventoParaCancelar == null) {
                        System.out.println("Evento não encontrado.");
                    } else {
                        System.out.print("Motivo do cancelamento: ");
                        String motivo = scanner.nextLine();
                        eventoParaCancelar.cancelarEvento(motivo);
                        System.out.println("Evento cancelado com sucesso!");
                    }
                }
                case 7 -> {
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}