/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca;

/**
 *
 * @author Gregory
 */
public enum UnidadeReserva {
    CAMPUS_LIBERDADE(1), CAMPUS_PALMARES(2), CAMPUS_MALES(3);

    private final int valor;

    UnidadeReserva(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
