package com.mycompany.simbolos;

// Classe Parser para análise sintática
public class Parser {
    private Lexer lexer; // Analisador léxico
    private SymTable symtable; // Tabela de símbolos
    private Token lookahead; // Token atual (olhar para frente)

    // Construtor do Parser
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.symtable = null; // Inicializa a tabela de símbolos como nula
        this.lookahead = lexer.scan(); // Inicia o lookahead com o primeiro token
    }

    // Método para iniciar o programa
    public void program() throws SyntaxError {
        block(); // Processa um bloco de código
    }

    // Método para processar um bloco de declarações e comandos
    private void block() throws SyntaxError {
        match('{'); // Verifica e consome '{'

        // Salva a tabela de símbolos atual e cria uma nova com escopo local
        SymTable saved = symtable;
        symtable = new SymTable(symtable);

        // Processamento de declarações e comandos
        decls();
        stmts();

        match('}'); // Verifica e consome '}'

        // Restaura a tabela de símbolos ao escopo anterior
        symtable = saved;
    }

    // Método para processar declarações
    private void decls() throws SyntaxError {
        // Loop para processar declarações de tipos básicos
        while (lookahead instanceof Id && (
                ((Id) lookahead).toString().equals("int") ||
                        ((Id) lookahead).toString().equals("float") ||
                        ((Id) lookahead).toString().equals("bool") ||
                        ((Id) lookahead).toString().equals("char")))
        {
            String type = lookahead.toString(); // Captura o tipo da variável
            match(256); // Verifica e consome o token de tipo

            String name = lookahead.toString(); // Captura o nome da variável
            match(258); // Verifica e consome o token de identificador

            Symbol symbol = new Symbol(name, type); // Cria um novo símbolo
            if (!symtable.insert(name, symbol)) {
                throw new SyntaxError(lexer.getLineNumber(), "Variavel " + name + " ja definida");
            }
            match(';'); // Verifica e consome ';'
        }
    }

    // Método para processar comandos
    private void stmts() throws SyntaxError {
        // Processa comandos até encontrar um '}' (fim do bloco)
        while (lookahead.tag != '}') {
            stmt();
        }
    }

    // Método para processar um único comando
    private void stmt() throws SyntaxError {
        if (lookahead.tag == '{') {
            System.out.print("{ ");
            block();
            System.out.print("} ");
        } else if (lookahead instanceof Id) {
            String name = lookahead.toString();
            Symbol symbol = symtable.find(name);
            if (symbol == null) {
                throw new SyntaxError(lexer.getLineNumber(), "Variavel " + name + " nao declarada");
            }

            match(258); // Consome o identificador

            // Processa atribuições se houver um '='
            if (lookahead.tag == '=') {
                match('=');
                if (lookahead instanceof IntNum || lookahead instanceof FloatNum) {
                    System.out.print(name + ":" +
                            (lookahead instanceof IntNum ? "int" : "float") + "; ");
                    match(257);  // Aceita números inteiros ou floats
                } else {
                    throw new SyntaxError(lexer.getLineNumber(), "Valor esperado após '='");
                }
            } else {
                System.out.print(symbol + "; ");
            }
            match(';'); // Consome ponto e vírgula
        } else if (lookahead instanceof IntNum || lookahead instanceof FloatNum) {
            System.out.print(lookahead.toString() + ":" +
                    (lookahead instanceof IntNum ? "int" : "float") + "; ");
            match(257); // Consome número
            match(';'); // Consome ponto e vírgula após número
        } else {
            throw new SyntaxError(lexer.getLineNumber(), "Comando inválido");
        }
    }

    // Método para verificar e consumir um token esperado
    private void match(int tag) throws SyntaxError {
        if (lookahead.tag == tag) {
            lookahead = lexer.scan(); // Avança para o próximo token
        } else {
            throw new SyntaxError(lexer.getLineNumber(), "Esperado " + (char) tag);
        }
    }
}
