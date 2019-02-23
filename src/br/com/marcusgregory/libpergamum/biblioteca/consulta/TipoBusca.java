/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.consulta;

/**
 *
 * @author Gregory
 */
public enum TipoBusca {

    LIVRE("LIVRE"), TITULO("TITULO"), AUTOR("AUTOR"), ASSUNTO("ASSUNTO");

    private final String valor;

    TipoBusca(String valorOpcao) {
        valor = valorOpcao;
    }

    public String getValor() {
        return valor;
    }
}
