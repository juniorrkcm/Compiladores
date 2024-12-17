package com.mycompany.simbolos;

// Classe base para todos os tokens utilizados na análise léxica.
public class Token {
    protected int tag; // Representa o tipo do token

    /**
     * Construtor para inicializar o token com um tipo específico.
     * @param tag Tipo do token.
     */
    public Token(int tag) {
        this.tag = tag;
    }

    /**
     * Converte o token para uma representação em string baseada no seu tipo.
     * @return Representação em string do tipo do token.
     */
    @Override
    public String toString() {
        return String.valueOf((char) tag);
    }
}

//Classe para representar tokens numéricos inteiros.

class Num extends Token {
    private int value; // Valor numérico do token

    /**
     * Construtor para inicializar o token numérico com um valor inteiro.
     * @param value Valor inteiro do token.
     */
    public Num(int value) {
        super(257); // Tag para tokens numéricos (NUM)
        this.value = value;
    }

    /**
     * Converte o token numérico para uma representação em string do seu valor.
     * @return Representação em string do valor do token.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

//Classe para representar identificadores.

class Id extends Token {
    private String name; // Nome do identificador

    /**
     * Construtor para inicializar o token de identificador.
     * @param tag Tipo do token.
     * @param name Nome do identificador.
     */
    public Id(int tag, String name) {
        super(tag);
        this.name = name;
    }

    /**
     * Obtém o nome do identificador.
     * @return Nome do identificador.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Converte o identificador para uma representação em string do seu nome.
     * @return Representação em string do nome do identificador.
     */
    @Override
    public String toString() {
        return name;
    }
}

//Classe para representar tokens numéricos de ponto flutuante.
class FloatNum extends Token {
    private float value; // Valor numérico de ponto flutuante do token

    /**
     * Construtor para inicializar o token numérico de ponto flutuante com um valor.
     * @param value Valor de ponto flutuante do token.
     */
    public FloatNum(float value) {
        super(257); // Tag para tokens numéricos de ponto flutuante (FLOAT_NUM)
        this.value = value;
    }

    /**
     * Converte o token numérico de ponto flutuante para uma representação em string do seu valor.
     * @return Representação em string do valor do token.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
