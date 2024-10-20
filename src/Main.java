import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Usuario pessoa = new Usuario();

        try {
            //Exemplo de pessoa para teste: todos os dados são fictícios e servem apenas para exemplificação.
            pessoa.setCPF("123.456.789-12");
            pessoa.setNome("Exemplo Nome Completo");
            pessoa.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
            pessoa.setSaldo(new BigDecimal("1015.75"));
            pessoa.setValorFatura(new BigDecimal("15.75"));
            pessoa.setDiaFatura(22);
            pessoa.setDebitoAuto(false);

            pessoa.statusUsuario();

            pessoa.processarFatura();
            pessoa.pixEnviado(new BigDecimal("14.25"));
            pessoa.pixRecebido(new BigDecimal("14.25"));

            pessoa.statusUsuario();
        } catch (ParseException e) {
            System.out.println("Erro ao analisar a data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
