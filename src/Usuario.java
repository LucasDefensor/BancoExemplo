import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Usuario {
    private String CPF; // ID
    private String nome;
    private Date dataNascimento;
    private BigDecimal saldo = BigDecimal.ZERO;
    private BigDecimal valorFatura = BigDecimal.ZERO;
    private int diaFatura;
    private Boolean debitoAuto;

    public Boolean getDebitoAuto() {
        return debitoAuto;
    }

    public void setDebitoAuto(Boolean debitoAuto) {
        this.debitoAuto = debitoAuto;
    }

    public int getDiaFatura() {
        return diaFatura;
    }

    public void setDiaFatura(int diaFatura) {
        if (diaFatura < 1 || diaFatura > 31) {
            throw new IllegalArgumentException("Dia da fatura inválido. Deve estar entre 1 e 31.");
        }
        this.diaFatura = diaFatura;
    }

    public BigDecimal getValorFatura() {
        return valorFatura != null ? valorFatura : BigDecimal.ZERO;
    }

    public void setValorFatura(BigDecimal valorFatura) {
        this.valorFatura = valorFatura;
    }

    public BigDecimal getSaldo() {
        return saldo != null ? saldo : BigDecimal.ZERO;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public LocalDate gerarDataFatura() {
        LocalDate hoje = LocalDate.now();
        return hoje.withDayOfMonth(diaFatura); // Gera a data no mês/ano atual
    }

    public void pixEnviado(BigDecimal valorEnviado) {
        if (saldo.compareTo(valorEnviado) >= 0) {
            saldo = saldo.subtract(valorEnviado);
            System.out.println(valorEnviado + " Enviado com sucesso\n");
        } else {
            System.out.println("Saldo insuficiente para enviar " + valorEnviado + "\n");
        }
    }

    public void pixRecebido(BigDecimal valorRecebido){
        saldo = saldo.add(valorRecebido);
        System.out.println(valorRecebido + " Recebido com sucesso\n");
    }

    public void processarFatura() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataFatura = gerarDataFatura();
        long diasAteFatura = ChronoUnit.DAYS.between(hoje, dataFatura);

        if (getDebitoAuto()) {
            if (diasAteFatura == 0) {
                if (getSaldo().compareTo(getValorFatura()) >= 0) {
                    setSaldo(getSaldo().subtract(getValorFatura()));
                    System.out.println("Fatura de " + getValorFatura() + " descontada automaticamente.\n");
                } else {
                    System.out.println("Saldo insuficiente para debitar a fatura automaticamente.\n");
                }
            }
        } else {
            if (diasAteFatura <= 5 && diasAteFatura > 0) {
                System.out.println("Atenção! Sua fatura vence em " + diasAteFatura + " dias.\n");
            } else if (diasAteFatura == 0) {
                System.out.println("Atenção! Sua fatura vence hoje. " + hoje + " \n");
            }
        }
    }

    public void statusUsuario() {
        System.out.println("+------ Status do usuário ------+");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Data de Nascimento: " + (getDataNascimento() != null ? new SimpleDateFormat("dd/MM/yyyy").format(getDataNascimento()) : "N/A"));
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Valor da Fatura: " + getValorFatura());
        System.out.println("Dia da Fatura: " + getDiaFatura());
        System.out.println("Débito Automático: " + (getDebitoAuto() != null && getDebitoAuto() ? "Ativo" : "Inativo"));
        System.out.println("+-------------------------------+\n");
    }
}
