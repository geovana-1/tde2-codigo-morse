import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArvoreMorse arvore = new ArvoreMorse();
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== CODIGO MORSE ===");
            System.out.println("1 - Decodificar Morse para texto");
            System.out.println("2 - Codificar texto para Morse");
            System.out.println("3 - Mostrar arvore");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            if (!entrada.hasNextLine()) {
                break;
            }
            String opcao = entrada.nextLine().trim();

            if (opcao.equals("1")) {
                System.out.println("Use espacos entre letras e / entre palavras.");
                System.out.print("Morse (exemplo: ... --- ...): ");
                if (!entrada.hasNextLine()) {
                    break;
                }
                String mensagem = entrada.nextLine();
                String texto = arvore.decodificar(mensagem);

                if (texto == null) {
                    System.out.println("Entrada invalida. Exemplo: ... --- ... / .- -...");
                } else {
                    System.out.println("Texto: " + texto);
                }
            } else if (opcao.equals("2")) {
                System.out.print("Texto (letras sem acentos e numeros): ");
                if (!entrada.hasNextLine()) {
                    break;
                }
                String texto = entrada.nextLine();
                String codigo = arvore.codificar(texto);

                if (codigo == null) {
                    System.out.println("Entrada invalida. Use A-Z, 0-9 e espacos.");
                } else {
                    System.out.println("Morse: " + codigo);
                }
            } else if (opcao.equals("3")) {
                arvore.imprimirArvore();
            } else if (opcao.equals("0")) {
                executando = false;
            } else {
                System.out.println("Opcao invalida.");
            }
        }

        entrada.close();
        System.out.println("Programa encerrado.");
    }
}
