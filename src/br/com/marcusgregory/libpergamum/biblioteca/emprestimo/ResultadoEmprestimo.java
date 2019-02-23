/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.emprestimo;

import br.com.marcusgregory.libpergamum.biblioteca.consulta.ObraConsulta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class ResultadoEmprestimo {

    private List<ObraEmprestimo> ObraRenovacoes;

    public ResultadoEmprestimo() {
        ObraRenovacoes = new ArrayList();
    }

    public void addObraRenovacao(ObraEmprestimo obr) {
        this.ObraRenovacoes.add(obr);
    }

    public List<ObraEmprestimo> getObraRenovacoes() {
        return ObraRenovacoes;
    }

    public void setObraRenovacoes(List<ObraEmprestimo> ObraRenovacoes) {
        this.ObraRenovacoes = ObraRenovacoes;
    }

    @Override
    public String toString() {
        return "ResultadoRenovacao{" + "ObraRenovacoes=" + ObraRenovacoes + '}';
    }

}
