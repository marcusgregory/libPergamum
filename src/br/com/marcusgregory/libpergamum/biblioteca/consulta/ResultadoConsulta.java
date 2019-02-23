/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.consulta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author unicafe
 */
public class ResultadoConsulta {

    private int totalDePaginas;
    private int paginaAtual;
    private List<ObraConsulta> Obras;

    public ResultadoConsulta() {
        Obras = new ArrayList();
    }

    public int getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setTotalDePaginas(int totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public List<ObraConsulta> getObras() {
        return Obras;
    }

    public void setObras(List<ObraConsulta> Obras) {
        this.Obras = Obras;
    }

    public void addObra(ObraConsulta obra) {
        this.Obras.add(obra);
    }

    @Override
    public String toString() {
        return "ResultadoConsulta{" + "totalDePaginas=" + totalDePaginas + ", paginaAtual=" + paginaAtual + ", Obras=" + Obras + '}';
    }

}
