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
        AdministradorBO administradorBO = new AdministradorBO();
        CompraBO compraBO = new CompraBO();

        int opcao;
        
        do {
            System.out.println("=== Sistema de Venda de Ingressos ===");
            System.out.println("1. Logar");
            System.out.println("2. Cadastrar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    logarUsuario(scanner, clienteBO, eventoBO, ingressoBO, administradorBO, compraBO);
                    opcao = 0;
                    break;
                case 2:
                    cadastrarUsuario(scanner, clienteBO, eventoBO, ingressoBO, administradorBO);
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
    
    private static void cadastrarUsuario(Scanner scanner, ClienteBO clienteBO, EventoBO eventoBO, IngressoBO ingressoBO, AdministradorBO administradorBO) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.println("Tipo de usuário: ");
        System.out.println("1. Administrador ");
        System.out.println("2. Cliente ");
        System.out.print("Tipo: ");
        int tipo = scanner.nextInt();
        switch (tipo) {
	        case 1:
	        	AdministradorDTO administrador = new AdministradorDTO(nome, email, senha);
	        	if (administradorBO.inserir(administrador)) {
	                System.out.println("Administador cadastrado com sucesso!\n");
	                break;
	            } else {
	                System.out.println("Erro ao cadastrar cliente. Verifique se ja existe.\n");
	            }
	        case 2:
	        	scanner.nextLine();
	        	System.out.print("Telefone: ");
	        	String telefone = scanner.nextLine();
	        	System.out.print("Cpf: ");
	        	String cpf = scanner.nextLine();     
	        	ClienteDTO cliente = new ClienteDTO(nome, cpf, email, telefone, senha);
	        	if (clienteBO.inserir(cliente)) {
	                System.out.println("Cliente cadastrado com sucesso!\n");
	                break;
	            } else {
	                System.out.println("Erro ao cadastrar cliente. Verifique se ja existe.\n");
	            }        
        }
    }
    
    private static void logarUsuario(Scanner scanner, ClienteBO clienteBO, EventoBO eventoBO, IngressoBO ingressoBO, AdministradorBO administradorBO, CompraBO compraBO) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        ClienteDTO cliente = new ClienteDTO(email, senha);
        AdministradorDTO admim = new AdministradorDTO(email, senha);
        if (clienteBO.existe(cliente)) {
        	if (clienteBO.logar(cliente)) {
        		System.out.println("Logado com sucesso!\n");
        		int idCliente = clienteBO.pegarId(cliente);
        		sistemaCliente(scanner, clienteBO, eventoBO, ingressoBO, compraBO, idCliente);
        	}
        } else if (administradorBO.existe(admim)) {
        	if (administradorBO.logar(admim)) {
        		System.out.println("Logado com sucesso!\n");
        		sistemaAdmim(scanner, clienteBO, eventoBO, ingressoBO, compraBO);
        	}
        }
    }

    private static void sistemaCliente(Scanner scanner, ClienteBO clienteBO, EventoBO eventoBO, IngressoBO ingressoBO, CompraBO compraBO, int idCliente) {
    	int opcao;
    	do {
            System.out.println("=== Sistema de Venda de Ingressos ===");
            System.out.println("1. Listar Eventos");
            System.out.println("2. Comprar Ingressos");
            System.out.println("3. Listar Compras do Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    listarEventos(eventoBO);
                    break;
                case 2:
                    comprarIngressosEspecifico(scanner, clienteBO, eventoBO, ingressoBO, compraBO, idCliente);
                    break;
                case 3:
                	listarComprasClienteEspecifico(scanner, clienteBO, ingressoBO, compraBO, idCliente);
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
    
    private static void sistemaAdmim(Scanner scanner, ClienteBO clienteBO, EventoBO eventoBO, IngressoBO ingressoBO, CompraBO compraBO) {
    	int opcao;
    	do {
            System.out.println("=== Sistema de Venda de Ingressos ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Evento");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Listar Eventos");
            System.out.println("5. Listar Compras de um Cliente");
            System.out.println("6. Excluir Cliente");
            System.out.println("7. Alterar Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

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
                    listarComprasCliente(scanner, clienteBO, ingressoBO, compraBO);
                    break;
                case 6:
                    excluirCliente(scanner, clienteBO);
                    break;
                case 7:
                	alterarCliente(scanner, clienteBO);
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
        System.out.print("Cpf: ");
        cliente.setCpf(scanner.nextLine());

        if (clienteBO.inserir(cliente)) {
            System.out.println("Cliente cadastrado com sucesso!\n");
        } else {
            System.out.println("Erro ao cadastrar cliente. Verifique se ja existe.\n");
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
        scanner.nextLine(); 
        System.out.print("Tipo de evento: ");
        evento.setTipoEvento(scanner.nextLine());
        System.out.print("Descricao: ");
        evento.setDescricao(scanner.nextLine());
        System.out.print("Valor do ingresso: ");
        evento.setValorIngresso(scanner.nextDouble());
        scanner.nextLine(); 

        if (eventoBO.inserir(evento)) {
            System.out.println("Evento cadastrado com sucesso!\\n");
        } else {
            System.out.println("Erro ao cadastrar evento.");
        }
    }

    private static void listarClientes(ClienteBO clienteBO) {
        List<ClienteDTO> clientes = clienteBO.pesquisarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\n=== Lista de Clientes ===");
            for (ClienteDTO cliente : clientes) {
                System.out.println(" Nome: " + cliente.getNome() + ", Email: " + cliente.getEmail());
            }
            System.out.println("");
        }
    }
    
    private static void alterarCliente(Scanner scanner, ClienteBO clienteBO) {
    	System.out.println("ID do cliente a ser alterado: ");
    	int idCliente = scanner.nextInt();
    	scanner.nextLine(); 
    	
    	ClienteDTO cliente = clienteBO.procurarPorId(idCliente);
    	System.out.println("ID do cliente encontrado: " + cliente.getId());  
    	if (cliente != null) {
    	    System.out.println("Cliente encontrado: " + cliente.getNome() + ", Email: " + cliente.getEmail());

    	    if (clienteBO.alterar(cliente)) {
    	        System.out.println("Cliente atualizado com sucesso!");
    	    } else {
    	        System.out.println("Erro ao atualizar o cliente. Verifique os dados informados.");
    	    }
    	} else {
    	    System.out.println("Cliente não encontrado.");
    	}
    	
        System.out.println("Cliente encontrado: " + cliente.getNome() + ", Email: " + cliente.getEmail());

        System.out.println("Digite os novos dados do cliente (pressione Enter para manter o valor atual):");
        System.out.print("Novo Nome (Atual: " + cliente.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }

        System.out.print("Novo Email (Atual: " + cliente.getEmail() + "): ");
        String novoEmail = scanner.nextLine();
        if (!novoEmail.isEmpty()) {
            cliente.setEmail(novoEmail);
        }

        System.out.print("Novo Telefone (Atual: " + cliente.getTelefone() + "): ");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.isEmpty()) {
            cliente.setTelefone(novoTelefone);
        }

        System.out.print("Nova Senha (Atual: [Oculto]): ");
        String novaSenha = scanner.nextLine();
        if (!novaSenha.isEmpty()) {
            cliente.setSenha(novaSenha);
        }

        if (clienteBO.alterar(cliente)) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o cliente. Verifique os dados informados.");
        }
    }
    
    private static void excluirCliente(Scanner scanner, ClienteBO clienteBO) {
        System.out.print("ID do cliente a ser excluído: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); 

        ClienteDTO cliente = clienteBO.procurarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Confirma exclusão do cliente: " + cliente.getNome() + " (ID: " + idCliente + ")? (s/n)");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            if (clienteBO.excluir(idCliente)) {
                System.out.println("Cliente excluído com sucesso.");
            } else {
                System.out.println("Erro ao excluir cliente.");
            }
        } else {
            System.out.println("Operação de exclusão cancelada.");
        }
    }
    
    private static void listarEventos(EventoBO eventoBO) {
        List<EventoDTO> eventos = eventoBO.pesquisarTodos();
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            System.out.println("\n=== Lista de Eventos ===");
            for (EventoDTO evento : eventos) {
                System.out.println("Nome: " + evento.getNome() + ", Data: " + evento.getData() + ", Local: " + evento.getLocal());
            }
            System.out.println("");
        }
    }
    
    private static void comprarIngressosEspecifico(Scanner scanner, ClienteBO clienteBO, EventoBO eventoBO, IngressoBO ingressoBO, CompraBO compraBO, int idCliente) {
        ClienteDTO cliente = clienteBO.procurarPorId(idCliente);
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
        scanner.nextLine(); 

        List<IngressoDTO> ingressos = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            IngressoDTO ingresso = new IngressoDTO();
            ingresso.setIdCliente(idCliente);
            ingresso.setIdEvento(idEvento);
            ingresso.setLocalAssento((i + 1));
            ingressos.add(ingresso);
        }

        System.out.println("\nCompra realizada com sucesso!");
        for (IngressoDTO ingresso : ingressos) {
        	if (ingressoBO.inserir(ingresso)) {
        		System.out.println("Evento: " + evento.getNome() + ", Local: " + evento.getLocal());
        	}
        }
        System.out.println("");
        
        double valorTotal = quantidade * evento.getValorIngresso();
        
        CompraDTO compra = new CompraDTO(valorTotal, true);
        compraBO.inserir(compra, idEvento, idCliente);
    }

    private static void listarComprasCliente(Scanner scanner, ClienteBO clienteBO, IngressoBO ingressoBO, CompraBO compraBO) {
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); 

        ClienteDTO cliente = clienteBO.procurarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        List<CompraDTO> ingressosComprados = compraBO.pesquisarTodosPorCliente(idCliente);
        if (ingressosComprados.isEmpty()) {
            System.out.println("Nenhuma compra encontrada para este cliente.");
        } else {
            System.out.println("\n=== Compras do Cliente ===");
            for (CompraDTO compra : ingressosComprados) {
            	System.out.println("Data da compra: " + compra.getDataCompra() + ", Valor total: " + compra.getValorTotal());
            }
            System.out.println("");
        }
        
    }
    
    private static void listarComprasClienteEspecifico(Scanner scanner, ClienteBO clienteBO, IngressoBO ingressoBO, CompraBO compraBO, int idCliente) {
        ClienteDTO cliente = clienteBO.procurarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        
        List<CompraDTO> ingressosComprados = compraBO.pesquisarTodosPorCliente(idCliente);
        if (ingressosComprados.isEmpty()) {
            System.out.println("Nenhuma compra encontrada para este cliente.");
        } else {
            System.out.println("\n=== Compras do Cliente ===");
            for (CompraDTO compra : ingressosComprados) {
                System.out.println("Data da compra: " + compra.getDataCompra() + ", Valor total: " + compra.getValorTotal());
            }
            System.out.println("");
        }
        
    }
}