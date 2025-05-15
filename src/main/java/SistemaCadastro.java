import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SistemaCadastro {
    public static void main(String[] args) {
        FabricaConexao.conectar();
        CadastroRespostitory repository = new CadastroRespostitory();
        Scanner sc = new Scanner(System.in);

        int opcao = 0;
        int id = 0;

        while (opcao != 5) {
            System.out.println("\nIniciando o sistema de cadastro");
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar");
            System.out.println("5 - Listar todos");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    Cadastro inserir = new Cadastro();

                    System.out.println("\nPainel de inclusão de cadastro!");

                    System.out.print("Digite o nome do cadastro: ");
                    String nome = sc.nextLine();
                    inserir.setNome(nome);

                    System.out.print("Digite a idade do cadastro: ");
                    int idade = sc.nextInt();
                    inserir.setIdade(idade);

                    repository.incluir(inserir);
                    break;

                case 2:
                    System.out.println("\nPainel de alteração de cadastro!");

                    System.out.println("Digite o ID do cadastro: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    Cadastro existente = repository.buscar(id);

                    if(existente != null){
                        System.out.println("Digite o novo nome do cadastro: ");
                        String nomeAlterar = sc.nextLine();
                        existente.setNome(nomeAlterar);

                        System.out.println("Digite a nova idade do cadastro: ");
                        int idadeAlterar = sc.nextInt();
                        existente.setIdade(idadeAlterar);

                        repository.alterar(existente);
                    }else{
                        System.out.println("Cadastro com ID " + id + " não encontrado.");
                    }
                    break;

                case 3:
                    System.out.println("\nPainel de exlcusão de cadastro!");

                    System.out.println("Digite o ID do cadastro a ser deletado: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    Cadastro cadastroExcluir = repository.buscar(id);

                    if(cadastroExcluir != null){
                        repository.excluir(id);
                    }else {
                        System.out.println("Cadastro com ID " + id + " não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\nPainel de buscar cadastro!");
                    System.out.print("Digite o ID do cadastro: ");
                    id = sc.nextInt();

                    Cadastro cadastro = repository.buscar(id);

                    String resultado = (cadastro != null)
                            ? cadastro.getId() + " " + cadastro.getNome() + " " + cadastro.getIdade()
                            : "Não foi possível localizar um cadastro pelo ID";
                    System.out.println(resultado);
                    break;

                case 5:
                    System.out.println("\nPainel de listagem de cadastro!");
                    List<Cadastro> cadastros = repository.listar();

                    cadastros.sort(Comparator.comparing(Cadastro::getId));
                    for(Cadastro c : cadastros){
                        System.out.println(c.getId() + " | " + c.getNome() + " | " + c.getIdade());
                    }

                case 6:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }
}
