import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        // 3.1 - Inserir todos os funcionários ok
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter),
                new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter),
                new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter),
                new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter),
                new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter),
                new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter),
                new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter),
                new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter),
                new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter),
                new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter),
                new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista ok
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas as informações ok
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
            System.out.println("Salário: R$ " + decimalFormat.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }

        // 3.4 - Os funcionários receberam 10% de aumento de salário ok
        for (Funcionario funcionario : funcionarios) {
            funcionario.aumentarSalario(10);
        }

        // 3.5 - Agrupar os funcionários por função em um MAP ok
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            funcionariosPorFuncao.putIfAbsent(funcionario.getFuncao(), new ArrayList<>());
            funcionariosPorFuncao.get(funcionario.getFuncao()).add(funcionario);
        }

        // 3.6 - Imprimir os funcionários agrupados por função ok
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Funcionários da função " + funcao + ":");
            for (Funcionario funcionario : funcionariosPorFuncao.get(funcao)) {
                System.out.println(" - " + funcionario.getNome());
            }
            System.out.println();
        }

        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12 ok
        System.out.println("Funcionários que fazem aniversário em outubro (mês 10) e dezembro (mês 12):");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println(" - " + funcionario.getNome());
            }
        }
        System.out.println();

        // 3.9 - Imprimir o funcionário com a maior idade ok
        LocalDate dataAtual = LocalDate.now();
        Funcionario maisVelho = funcionarios.get(0);
        int idadeMaisVelho = dataAtual.getYear() - maisVelho.getDataNascimento().getYear();
        for (Funcionario funcionario : funcionarios) {
            int idadeAtual = dataAtual.getYear() - funcionario.getDataNascimento().getYear();
            if (idadeAtual > idadeMaisVelho) {
                maisVelho = funcionario;
                idadeMaisVelho = idadeAtual;
            }
        }
        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + " (Idade: " + idadeMaisVelho + " anos)");
        System.out.println();

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética ok
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Lista de funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(" - " + funcionario.getNome());
        }
        System.out.println();

        // 3.11 - Imprimir o total dos salários dos funcionários ok
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total dos salários dos funcionários: R$ " + decimalFormat.format(totalSalarios));
        System.out.println();

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário ok
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Quantidade de salários mínimos de cada funcionário:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + decimalFormat.format(quantidadeSalariosMinimos));
        }
    }

}
