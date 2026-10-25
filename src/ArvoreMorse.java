public class ArvoreMorse {
    private No raiz;

    public ArvoreMorse() {
        // Espaco representa um no sem letra. A raiz nao tem codigo Morse.
        raiz = new No(' ');
        carregarCaracteres();
    }

    private void carregarCaracteres() {
        // Estes dados montam a arvore; as conversoes depois percorrem os nos.
        inserir('A', ".-");
        inserir('B', "-...");
        inserir('C', "-.-.");
        inserir('D', "-..");
        inserir('E', ".");
        inserir('F', "..-.");
        inserir('G', "--.");
        inserir('H', "....");
        inserir('I', "..");
        inserir('J', ".---");
        inserir('K', "-.-");
        inserir('L', ".-..");
        inserir('M', "--");
        inserir('N', "-.");
        inserir('O', "---");
        inserir('P', ".--.");
        inserir('Q', "--.-");
        inserir('R', ".-.");
        inserir('S', "...");
        inserir('T', "-");
        inserir('U', "..-");
        inserir('V', "...-");
        inserir('W', ".--");
        inserir('X', "-..-");
        inserir('Y', "-.--");
        inserir('Z', "--..");
        inserir('0', "-----");
        inserir('1', ".----");
        inserir('2', "..---");
        inserir('3', "...--");
        inserir('4', "....-");
        inserir('5', ".....");
        inserir('6', "-....");
        inserir('7', "--...");
        inserir('8', "---..");
        inserir('9', "----.");
    }

    private void inserir(char caractere, String codigo) {
        No atual = raiz;

        for (int i = 0; i < codigo.length(); i++) {
            char sinal = codigo.charAt(i);

            if (sinal == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new No(' ');
                }
                atual = atual.esquerda;
            } else if (sinal == '-') {
                if (atual.direita == null) {
                    atual.direita = new No(' ');
                }
                atual = atual.direita;
            }
        }

        // O caminho inteiro foi percorrido. Gravamos o caractere no destino.
        atual.caractere = caractere;
    }

    private char decodificarLetra(String codigo) {
        No atual = raiz;

        for (int i = 0; i < codigo.length(); i++) {
            char sinal = codigo.charAt(i);

            if (sinal == '.') {
                atual = atual.esquerda;
            } else if (sinal == '-') {
                atual = atual.direita;
            } else {
                return ' ';
            }

            if (atual == null) {
                return ' ';
            }
        }

        // Se o no nao tem caractere cadastrado, tambem devolvemos espaco.
        return atual.caractere;
    }

    // Retorna null quando a mensagem e vazia ou contem codigo invalido.
    public String decodificar(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            return null;
        }

        String[] partes = mensagem.trim().split(" ");
        String resultado = "";
        boolean ultimaFoiBarra = false;

        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i];

            // Ignoramos partes vazias causadas por espacos repetidos.
            if (!parte.isEmpty()) {
                if (parte.equals("/")) {
                    if (resultado.isEmpty() || ultimaFoiBarra) {
                        return null;
                    }
                    resultado = resultado + " ";
                    ultimaFoiBarra = true;
                } else {
                    char letra = decodificarLetra(parte);
                    if (letra == ' ') {
                        return null;
                    }
                    resultado = resultado + letra;
                    ultimaFoiBarra = false;
                }
            }
        }

        if (ultimaFoiBarra) {
            return null;
        }
        return resultado;
    }

    // Retorna null se nao encontrar o caractere nesta subarvore.
    private String buscarCodigo(No no, char caractere, String caminho) {
        if (no == null) {
            return null;
        }

        if (no.caractere == caractere) {
            return caminho;
        }

        String codigo = buscarCodigo(no.esquerda, caractere, caminho + ".");
        if (codigo != null) {
            return codigo;
        }

        return buscarCodigo(no.direita, caractere, caminho + "-");
    }

    // Retorna null quando ha caractere nao cadastrado ou texto vazio.
    public String codificar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }

        String[] palavras = texto.trim().split(" ");
        String resultado = "";

        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];

            if (!palavra.isEmpty()) {
                if (!resultado.isEmpty()) {
                    resultado = resultado + " / ";
                }

                for (int j = 0; j < palavra.length(); j++) {
                    char letra = Character.toUpperCase(palavra.charAt(j));
                    String codigo = buscarCodigo(raiz, letra, "");

                    if (codigo == null) {
                        return null;
                    }
                    if (j > 0) {
                        resultado = resultado + " ";
                    }
                    resultado = resultado + codigo;
                }
            }
        }

        return resultado;
    }

    public void imprimirArvore() {
        System.out.println("Arvore deitada: raiz a esquerda da tela.");
        System.out.println("Filho direito (-) acima; filho esquerdo (.) abaixo.");
        System.out.println("Colchetes mostram o caminho. * indica no sem caractere.");
        imprimirArvore(raiz, 0, "");
    }

    private void imprimirArvore(No no, int nivel, String caminho) {
        if (no == null) {
            return;
        }

        imprimirArvore(no.direita, nivel + 1, caminho + "-");

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        if (no == raiz) {
            System.out.println("RAIZ");
        } else if (no.caractere == ' ') {
            System.out.println("* [" + caminho + "]");
        } else {
            System.out.println(no.caractere + " [" + caminho + "]");
        }

        imprimirArvore(no.esquerda, nivel + 1, caminho + ".");
    }
}
