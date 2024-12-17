/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simbolos;

/**
 *
 * @author hercules
 */
public class SyntaxError extends Exception {
    private int lineNumber;
    private String message;

    public SyntaxError(int lineNumber, String message) {
        super(message);
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "SyntaxError na linha " + lineNumber + ": " + super.getMessage();
    }
}

