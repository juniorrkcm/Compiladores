package com.mycompany.simbolos;

//Classe principal que executa o analisador léxico e sintático.
public class Simbolos {
    public static void main(String[] args) {
        // Código fonte a ser analisado
        String code = "{" +
                "int x; " +
                "float y;" +
                "{ bool y; x; y; 2.5; }" +
                "x; 10; y; }";

        // Cria uma instância do analisador léxico com o código
        Lexer lexer = new Lexer(code);

        // Cria uma instância do analisador sintático com o analisador léxico
        Parser parser = new Parser(lexer);

        try {
            // Tenta analisar o programa
            parser.program();
            System.out.println("Análise concluida com sucesso!");
        } catch (SyntaxError e) {
            // Captura e exibe erros de sintaxe
            System.out.println(e);
        }
    }
}
