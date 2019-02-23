/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.reserva;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gregory
 */
public enum Situacao {
    AGUARDANDO("Aguardando"), DISPONIVEL("Disponível"), DESCONHECIDO("Desconhecido");

    private final String valor;

    Situacao(String valorOpcao) {
        valor = valorOpcao;
    }

    public String getValor() {
        return valor;
    }
    private static final Map<String, Situacao> nameIndex
            = new HashMap(Situacao.values().length);

    static {
        for (Situacao situacao : Situacao.values()) {
            nameIndex.put(situacao.getValor(), situacao);
        }
    }

    public static Situacao lookupByName(String name) {

        return nameIndex.get(name);
    }
}
