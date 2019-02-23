/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.reserva;

import java.util.Objects;

/**
 *
 * @author Gregory
 */
public class ObraReserva {

    private String titulo;
    private Situacao situacao;
    private String codAcervo;
    private String CapaURL;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getCodAcervo() {
        return codAcervo;
    }

    public void setCodAcervo(String codAcervo) {
        this.codAcervo = codAcervo;
    }

    public String getCapaURL() {
        return CapaURL;
    }

    public void setCapaURL(String CapaURL) {
        this.CapaURL = CapaURL;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        final ObraReserva other = (ObraReserva) obj;

        if (Objects.equals(this.codAcervo, other.codAcervo) && this.situacao == other.situacao) {

            return true;
        } else if (Objects.equals(this.codAcervo, other.codAcervo) && this.situacao != other.situacao) {
            return false;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "ObraReserva{" + "titulo=" + titulo + ", situacao=" + situacao + ", codAcervo=" + codAcervo + ", CapaURL=" + CapaURL + '}';
    }

}
