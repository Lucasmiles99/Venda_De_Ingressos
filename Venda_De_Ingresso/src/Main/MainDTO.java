package Main;
import BO.*;
import DTO.*;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;

public class MainDTO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteBO clienteBO = new ClienteBO();
        EventoBO eventoBO = new EventoBO();
        IngressoBO ingressoBO = new IngressoBO();

        int opcao;
        do {
            System.out.println("=== Sistema de Venda de Ingressos ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Evento");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Listar Eventos");
            System.out.println("5. Comprar Ingressos");
            System.out.println("6. Listar Compras de um Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner, clienteBO);
                    break;
                case 2:
                    cadastrarEvento(scanner, eventoBO);
                    break;
                case 3:
                    listarClientes(clienteBO);
                    break;
                case 4:
                    listarEventos(eventoBO);
                    break;
                case 5:
                    comprarIngressos(scanner, clienteBO, eventoBO, ingressoBO);
                    break;
                case 6:
                    listarComprasCliente(scanner, clienteBO);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarCliente(Scanner scanner, ClienteBO clienteBO) {
        ClienteDTO cliente = new ClienteDTO();
        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());
        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());
        System.out.print("Telefone: ");
        cliente.setTelefone(scanner.nextLine());
        System.out.print("Senha: ");
        cliente.setSenha(scanner.nextLine());
        System.out.print("Id: ");
        cliente.setId(scanner.nextLine());
        System.out.print("Cpf: ");
        cliente.setCpf(scanner.nextLine());

        if (clienteBO.inserir(cliente)) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar cliente. Verifique se ja existe.");
        }
    }

    private static void cadastrarEvento(Scanner scanner, EventoBO eventoBO) {
        EventoDTO evento = new EventoDTO();
        System.out.print("Nome do evento: ");
        evento.setNome(scanner.nextLine());
        System.out.print("Data (DD/MM/AAAA): ");
        evento.setData(scanner.nextLine());
        System.out.print("Local: ");
        evento.setLocal(scanner.nextLine());
        System.out.print("Capacidade maxima: ");
        evento.setCapacidadeMaxima(scanner.nextInt());
        scanner.nextLine(); // Clear buffer
        System.out.print("Tipo de evento: ");
        evento.setTipoEvento(scanner.nextLine());
        System.out.print("Descricao: ");
        evento.setDescricao(scanner.nextLine());
        System.out.print("Valor do ingresso: ");
        evento.setValorIngresso(scanner.nextDouble());
        scanner.nextLine(); // Clear buffer

        if (eventoBO.inserir(evento)) {
            System.out.println("Evento cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar evento.");
        }
    }

    private static void listarClientes(ClienteBO clienteBO) {
        List<ClienteDTO> clientes = clienteBO.pesquisarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("=== Lista de Clientes ===");
            for (ClienteDTO cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", Email: " + cliente.getEmail());
            }
        }
    }

    private static void listarEventos(EventoBO eventoBO) {
        List<EventoDTO> eventos = eventoBO.pesquisarTodos();
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            System.out.println("=== Lista de Eventos ===");
            for (EventoDTO evento : eventos) {
                System.out.println("ID: " + evento.getId() + ", Nome: " + evento.getNome() + ", Data: " + evento.getData() + ", Local: " + evento.getLocal());
            }
        }
    }

    private static void comprarIngressos(Scanner scanner, ClienteBO clienteBO, EventoBO eventoBO, IngressoBO ingressoBO) {
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        ClienteDTO cliente = clienteBO.procurarPorCodigo(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        System.out.print("ID do evento: ");
        int idEvento = scanner.nextInt();
        EventoDTO evento = eventoBO.procurarPorCodigo(idEvento);
        if (evento == null) {
            System.out.println("Evento nao encontrado.");
            return;
        }

        System.out.print("Quantidade de ingressos: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        List<IngressoDTO> ingressos = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            IngressoDTO ingresso = new IngressoDTO();
            ingresso.setCliente(cliente);
            ingresso.setEvento(evento);
            ingresso.setPago(true);
            ingressos.add(ingresso);
        }

        System.out.println("Compra realizada com sucesso!");
        for (IngressoDTO ingresso : ingressos) {
            System.out.println("Evento: " + ingresso.getEvento().getNome() + ", Local: " + ingresso.getEvento().getLocal());
        }
    }

    private static void listarComprasCliente(Scanner scanner, ClienteBO clienteBO) {
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        ClienteDTO cliente = clienteBO.procurarPorCodigo(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        List<IngressoDTO> ingressosComprados = cliente.getIngressos();
        if (ingressosComprados.isEmpty()) {
            System.out.println("Nenhuma compra encontrada para este cliente.");
        } else {
            System.out.println("=== Compras do Cliente ===");
            for (IngressoDTO ingresso : ingressosComprados) {
                System.out.println("Evento: " + ingresso.getEvento().getNome() + ", Local: " + ingresso.getEvento().getLocal());
            }
        }
    }
}