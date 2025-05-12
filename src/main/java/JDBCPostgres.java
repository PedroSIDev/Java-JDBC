import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class JDBCPostgres {
    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost:5432/rocket_db";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "postgres");
//            props.setProperty("ssl", "true");
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Conexao realizada com sucesso");

            String instrucaoSQL = "INSERT INTO public.tab_cadastro (nome, idade) VALUES(?,?);";
            String nome = "Josefino";
            Integer idade = 31;

            PreparedStatement pst = conn.prepareStatement(instrucaoSQL);
            pst.setString(1, nome);
            pst.setInt(2, idade);
            pst.execute();
            System.out.println("Dados inseridos com sucesso");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
