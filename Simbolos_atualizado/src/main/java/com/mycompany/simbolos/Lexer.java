package com.mycompany.simbolos;

import java.util.HashMap;
import java.util.Map;

// Classe Lexer para análise léxica
public class Lexer {
    // Constantes para tipos de tokens
    private static final int TYPE = 256, NUM = 257, ID = 258;

    // Variáveis de instância
    private String input; // Entrada de texto a ser analisada
    private int index = 0; // Índice atual no input
    private int line = 1; // Contador de linhas
    private char peek; // Próximo caractere a ser analisado
    private Map<String, Integer> idTable; // Tabela de identificadores e palavras reservadas

    // Construtor
    public Lexer(String input) {
        this.input = input + '\0'; // Adiciona terminador de string
        this.peek = input.charAt(index);
        idTable = new HashMap<>();

        // Inicialização de palavras reservadas
        idTable.put("int", TYPE);
        idTable.put("char", TYPE);
        idTable.put("bool", TYPE);
        idTable.put("float", TYPE);
    }

    // Método para obter o número da linha atual
    public int getLineNumber() {
        return line;
    }

    // Método principal para análise léxica
    public Token scan() {
        // Pula espaços em branco e trata novas linhas
        while (Character.isWhitespace(peek)) {
            if (peek == '\n') {
                line++;
            }
            nextChar();
        }

        // Tratamento de números (inteiros e pontos flutuantes)
        if (Character.isDigit(peek)) {
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(peek)) {
                num.append(peek);
                nextChar();
            }
            if (peek == '.') {
                num.append(peek);
                nextChar();
                while (Character.isDigit(peek)) {
                    num.append(peek);
                    nextChar();
                }
                return new FloatNum(Float.parseFloat(num.toString()));
            }
            return new IntNum(Integer.parseInt(num.toString()));
        }

        // Tratamento de identificadores e palavras reservadas
        if (Character.isLetter(peek)) {
            StringBuilder sb = new StringBuilder();
            while (Character.isLetterOrDigit(peek)) {
                sb.append(peek);
                nextChar();
            }
            String lexeme = sb.toString();
            int tag = idTable.getOrDefault(lexeme, ID);
            return new Id(tag, lexeme);
        }

        // Tratamento de outros caracteres (tokens simples)
        Token token = new Token(peek);
        nextChar();
        return token;
    }

    // Método para avançar para o próximo caractere
    private void nextChar() {
        index++;
        if (index < input.length()) {
            peek = input.charAt(index);
        } else {
            peek = '\0'; // Usado para indicar o fim da entrada
        }
    }
}

// Classe para representar números inteiros como tokens
class IntNum extends Token {
    private int value;

    public IntNum(int value) {
        super(257); // Tag para números inteiros
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
