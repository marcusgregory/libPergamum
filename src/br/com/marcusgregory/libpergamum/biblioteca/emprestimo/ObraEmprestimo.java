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
public class ObraEmprestimo {

    private String titulo;
    private String dataDevolucao;
    private int numRenovacoes;
    private String codAcervo;
    private String codExemplar;
    private String CapaURL;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getNumRenovacoes() {
        return numRenovacoes;
    }

    public void setNumRenovacoes(int numRenovacoes) {
        this.numRenovacoes = numRenovacoes;
    }

    public String getCodAcervo() {
        return codAcervo;
    }

    public void setCodAcervo(String codAcervo) {
        this.codAcervo = codAcervo;
    }

    public String getCodExemplar() {
        return codExemplar;
    }

    public void setCodExemplar(String codExemplar) {
        this.codExemplar = codExemplar;
    }

    public String getCapaURL() {
        return CapaURL;
    }

    public void setCapaURL(String CapaURL) {
        this.CapaURL = CapaURL;
    }

    @Override
    public String toString() {
        return "ObraEmprestimo{" + "titulo=" + titulo + ", dataDevolucao=" + dataDevolucao + ", numRenovacoes=" + numRenovacoes + ", codAcervo=" + codAcervo + ", codExemplar=" + codExemplar + ", CapaURL=" + CapaURL + '}';
    }

}
