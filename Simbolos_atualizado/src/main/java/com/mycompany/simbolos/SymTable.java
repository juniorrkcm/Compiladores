/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simbolos;

/**
 *
 * @author hercules
 */
import java.util.HashMap;
import java.util.Map;

public class SymTable {
    private Map<String, Symbol> table;
    private SymTable prev;

    public SymTable() {
        this.table = new HashMap<>();
        this.prev = null;
    }

    public SymTable(SymTable prev) {
        this.table = new HashMap<>();
        this.prev = prev;
    }

    public boolean insert(String key, Symbol symbol) {
        if (table.containsKey(key)) {
            return false; // Símbolo já existe na tabela
        }
        table.put(key, symbol);
        return true;
    }

    public Symbol find(String key) {
        for (SymTable st = this; st != null; st = st.prev) {
            if (st.table.containsKey(key)) {
                return st.table.get(key);
            }
        }
        return null;
    }
}

