/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.emprestimo;

/**
 *
 * @author Gregory
 */
public class EmprestimoInfo {

    private String titulo;
    private String autor;
    private String numeroChamada;
    private String dataEmprestimo;
    private String dataDevolucao;
    private int totalRenovacoes;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumeroChamada() {
        return numeroChamada;
    }

    public void setNumeroChamada(String numeroChamada) {
        this.numeroChamada = numeroChamada;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getTotalRenovacoes() {
        return totalRenovacoes;
    }

    public void setTotalRenovacoes(int totalRenovacoes) {
        this.totalRenovacoes = totalRenovacoes;
    }

    @Override
    public String toString() {
        return "RenovacaoInfo{" + "titulo=" + titulo + ", autor=" + autor + ", numeroChamada=" + numeroChamada + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + ", totalRenovacoes=" + totalRenovacoes + '}';
    }

}
