/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simbolos;

/**
 *
 * @author hercules
 */
public class Symbol {
    private String var;
    private String type;

    public Symbol(String var, String type) {
        this.var = var;
        this.type = type;
    }

    public String getVar() {
        return var;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Simbolo{var='" + var + "', tipo='" + type + "'}";
    }
}
