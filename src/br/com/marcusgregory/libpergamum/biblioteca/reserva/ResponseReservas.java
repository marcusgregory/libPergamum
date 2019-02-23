/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.reserva;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class ResponseReservas {

    private List<ObraReserva> obrasReserva;

    public ResponseReservas() {
        obrasReserva = new ArrayList<>();
    }

    public void addObraReserva(ObraReserva obr) {
        this.obrasReserva.add(obr);
    }

    public List<ObraReserva> getObrasReserva() {
        return obrasReserva;
    }

    public void setObrasReserva(List<ObraReserva> obrasReserva) {
        this.obrasReserva = obrasReserva;
    }

    @Override
    public String toString() {
        return "ResponseReservas{" + "obrasReserva=" + obrasReserva + '}';
    }

}
