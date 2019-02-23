/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class ResponseTurmas {

    private List<Turma> turmas;

    public ResponseTurmas() {
        turmas = new ArrayList<>();
    }

    public void addTurma(Turma trm) {
        turmas.add(trm);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "ResponseTurmas{" + "turmas=" + turmas + '}';
    }

}
