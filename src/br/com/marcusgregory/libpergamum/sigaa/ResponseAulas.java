/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa;

import java.util.ArrayList;

/**
 *
 * @author Gregory
 */
public class ResponseAulas {
    private ArrayList<Aula> aulas;

    public ResponseAulas() {
        this.aulas= new ArrayList<>();
    }

    public ArrayList<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(ArrayList<Aula> aulas) {
        this.aulas = aulas;
    }
    public void addAula(Aula aula){
        this.aulas.add(aula);
    }

    @Override
    public String toString() {
        return "ResponseAulas{" + "aulas=" + aulas + '}';
    }
    
}
