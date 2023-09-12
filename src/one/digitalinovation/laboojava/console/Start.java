package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.*;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);
    private static LivroNegocio livroNegocio = new LivroNegocio(banco);
    private static CadernoNegocio cadernoNegocio = new CadernoNegocio(banco);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";

        while(true) {

            if (clienteLogado == null) {
                menuInicializador();
            }

            System.out.println("--------------------------------");
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Excluir Livro");
            //TODO Desafio: Consultar Livro(nome)
            System.out.println("3 - Consultar Livro");
            System.out.println("4 - Cadastrar Caderno");
            System.out.println("5 - Excluir Caderno");
            //TODO Desafio: Consultar Caderno(matéria)
            System.out.println("6 - Consultar Caderno");
            System.out.println("7 - Fazer pedido");
            System.out.println("8 - Excluir pedido");
            //TODO Desafio: Consultar Pedido(código)
            System.out.println("9 - Consultar pedido");
            System.out.println("10 - Listar produtos");
            System.out.println("11 - Listar pedidos");
            System.out.println("12 - Deslogar");
            System.out.println("13 - Sair");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    System.out.println("Digite o código do livro");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro);
                    break;
                case "3":
                    System.out.println("Digite o nome do livro");
                    String nomeLivro = LeitoraDados.lerDado();
                    livroNegocio.consultar(nomeLivro).ifPresent(System.out::println);
                    break;
                case "4":
                    Caderno caderno = LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);
                    break;
                case "5":
                    System.out.println("Digite o código do caderno");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno);
                    break;
                case "6":
                    //TODO Desafio: Consultar Caderno(matéria)
                    System.out.println("Digite a quantidade de matérias");
                    String materiaCaderno = LeitoraDados.lerDado();
                    cadernoNegocio.consultar(materiaCaderno);
                    break;
                case "7":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get(), clienteLogado);
                    } else {
                        pedidoNegocio.salvar(pedido, clienteLogado);
                    }
                    break;
                case "8":
                    System.out.println("Digite o código do pedido");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "9":
                    //TODO Desafio: Consultar Pedido(código)
                    break;
                case "10":
                    produtoNegocio.listarTodos();
                    break;
                case "11":
                    pedidoNegocio.listarTodos();
                    break;
                case "12":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "13":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    /**
     * Procura o usuário na base de dados.
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isPresent()) {
            Cliente cliente = resultado.get();
            System.out.printf("Olá %s! Você está logado.%n", cliente.getNome());
            clienteLogado = cliente;
            return;
        }

        System.out.println("Usuário não cadastrado.");
        menuInicializador();
    }

    private static void menuInicializador() {
        System.out.println("--------------------------------");
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Realizar login");
        System.out.println("2 - Cadastrar-se");
        System.out.println("3 - Excluir conta");
        System.out.println("4 - Listar usuários");
        System.out.println("5 - Sair");

        String opcao = LeitoraDados.lerDado();

        switch (opcao) {
            case "1" -> realizarLogin();
            case "2" -> {
                Cliente cliente = LeitoraDados.lerCliente();
                clienteNegocio.salvar(cliente);
                menuInicializador();
            }
            case "3" -> {
                System.out.println("Digite o CPF da conta:");
                String cpf = LeitoraDados.lerDado();
                clienteNegocio.excluir(cpf);
                menuInicializador();
            }
            case "4" -> {
                clienteNegocio.listarTodos();
                menuInicializador();
            }
            case "5" -> {
                System.out.println("Aplicação encerrada.");
                System.exit(0);
            }
            default -> {
                System.out.println("Opção inválida.");
                menuInicializador();
            }
        }
    }

    private static void realizarLogin() {
        System.out.println("Digite o cpf:");

        String cpf = "";
        cpf = LeitoraDados.lerDado();

        identificarUsuario(cpf);
    }
}
