/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca;

import br.com.marcusgregory.libpergamum.exceptions.ReservaException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author unicafe
 */
public class Obra {

    private String titulo;
    private String autor;
    private String localizacao;
    private String tipoObra;
    private String codAcervo;
    private String CapaURL;

    public Obra() {

    }

    public Obra(String titulo, String autor, String localizacao, String tipoObra, String codAcervo, String capaURL) {
        this.titulo = titulo;
        this.autor = autor;
        this.localizacao = localizacao;
        this.tipoObra = tipoObra;
        this.codAcervo = codAcervo;
        this.CapaURL = capaURL;
    }

    public void reservar(UnidadeReserva unidadeReserva) throws UsuarioNaoLogadoException, IOException, ReservaException {
        br.com.marcusgregory.libpergamum.biblioteca.requests.RequestReservar.request(this, unidadeReserva);
    }

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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
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
    public String toString() {
        return "Obra{" + "titulo=" + titulo + ", autor=" + autor + ", localizacao=" + localizacao + ", tipoObra=" + tipoObra + ", codAcervo=" + codAcervo + ", CapaObraURL=" + CapaURL + '}';
    }

}
