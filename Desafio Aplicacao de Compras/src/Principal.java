import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {
    public static void main(String[] args) {
        Scanner leitor = new Scanner((System.in));
        //Leitura do limite do usuario
        System.out.printf("Digite o limite do usuario: R$");
        double limite = leitor.nextDouble();
        leitor.nextLine();
        CartaoDeCredito cartao = new CartaoDeCredito(limite);

        Usuario cadastro = new Usuario();
        while(true) {
            //Solicitar o nome do usuario
            System.out.printf("Digite o nome: ");
            cadastro.setNome(leitor.nextLine());
            //Expressao regular para validar o nome
            String padrao = "^[a-zA-Z\\s]+$";

            //Compilar a expressao regular em um padrao
            Pattern pattern = Pattern.compile(padrao);

            //Criar um objeto matcher para aplicar o padrao ao nome
            Matcher matcher = pattern.matcher(cadastro.getNome());

            //Verificar se o nome corresponde ao padrao
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Nome invalido! O nome deve conter apenas letras e espacos!");
            }
        }

        while (true) {
            //Insercao da idade
            System.out.printf(String.format("%s, digite a sua idade: ", cadastro.getNome()));
            int idade = leitor.nextInt();
            //Forma de validar idade
            if (idade > 0) {
                cadastro.setIdade(idade);
                break;
            } else {
                System.out.println("Idade invalida! A idade deve ser o numero inteiro positivo!");
            }
        }
        //Ficha de cadastro do usuario.
        System.out.println("******************************************************************");
        System.out.println(String.format("Usuario cadastrado: %s, %d anos e possui limite de R$%.2f", cadastro.getNome(), cadastro.getIdade(), limite));
        System.out.println("******************************************************************");

        boolean continuar = true;
        while (continuar) {
            //Solicitando a descricao do produto
            System.out.printf("Digite a descricao da compra: ");
            String descricao = leitor.next();

            //Solicitando o valor do produto
            System.out.printf("Digite o valor da compra: R$");
            double valor = leitor.nextDouble();
            leitor.nextLine();

            Compra compra = new Compra(descricao, valor);
            boolean compraRealizada = cartao.lancaCompra(compra);

            if (compraRealizada) {
                System.out.println("Compra realizada!");
                System.out.printf("Deseja continuar? [S/N] ");
                String resposta = leitor.nextLine();
                continuar = resposta.equalsIgnoreCase("S");
            } else {
                System.out.println("Compra nao realizada");
                continuar = false;
            }
        }
        System.out.println("*************************");
        System.out.println("    COMPRAS REALIZADAS   \n");

        //Ordenacao das compras e exibicao
        Collections.sort(cartao.getCompras());
        for(Compra c: cartao.getCompras()) {
            System.out.println(String.format("%s - R$%.2f",c.getDescricao(), c.getValor()));
        }
        System.out.println("\n*************************");
        System.out.println(String.format("\nSaldo do cartao: R$%.2f", cartao.getSaldo()));
    }
}
