/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.util;

import br.com.marcusgregory.libpergamum.biblioteca.reserva.ObraReserva;
import br.com.marcusgregory.libpergamum.biblioteca.reserva.Situacao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class Diferenciador {

    public static ArrayList<ObraReserva> diferenciarReservas(ArrayList<ObraReserva> reservas, ArrayList<ObraReserva> reservasAtualizada) {
        ArrayList<ObraReserva> disponiveisAntigo = new ArrayList<>();
        for (ObraReserva obr : reservas) {
            if (obr.getSituacao() == Situacao.DISPONIVEL) {
                disponiveisAntigo.add(obr);
            }
        }
        ArrayList<ObraReserva> disponiveisAtuais = new ArrayList<>();
        for (ObraReserva obr : reservasAtualizada) {
            if (obr.getSituacao() == Situacao.DISPONIVEL) {
                disponiveisAtuais.add(obr);
            }
        }
        disponiveisAtuais.removeAll(disponiveisAntigo);
        return disponiveisAtuais;

    }
}
