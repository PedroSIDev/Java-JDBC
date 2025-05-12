import java.util.List;

public class SistemaCadastro {
    public static void main(String[] args) {
        FabricaConexao.conectar();
        CadastroRespostitory repository = new CadastroRespostitory();
        Cadastro cadastro = repository.buscar(3);
        if (cadastro!=null){
            System.out.println(cadastro.getId() + " " + cadastro.getNome() + " " + cadastro.getIdade());
        }else{
            System.out.println("Nao foi possivel localizar um cadastro pelo ID");
        }
    }
}
