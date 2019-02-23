/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.consulta;

import br.com.marcusgregory.libpergamum.biblioteca.Obra;
import br.com.marcusgregory.libpergamum.exceptions.ReservaException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.biblioteca.requests.ResquestObra;
import br.com.marcusgregory.libpergamum.biblioteca.UnidadeReserva;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author unicafe
 */
public class ObraConsulta {

    private String titulo;
    private String localizacao;
    private String tipoObra;
    private String codAcervo;
    private String ObraURL;
    private String CapaURL;

    public ObraConsulta() {

    }

    public ObraConsulta(String titulo, String localizacao, String tipoObra, String codAcervo, String ObraURL) {
        this.titulo = titulo;
        this.localizacao = localizacao;
        this.tipoObra = tipoObra;
        this.codAcervo = codAcervo;
        this.ObraURL = ObraURL;

    }

    public Obra getObra() {

        try {
            return ResquestObra.request(this.codAcervo);
        } catch (IOException ex) {
            Logger.getLogger(ObraConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void reservar(UnidadeReserva unidadeReserva) throws UsuarioNaoLogadoException, IOException, ReservaException {

        br.com.marcusgregory.libpergamum.biblioteca.requests.RequestReservar.request(this.codAcervo, unidadeReserva);

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getObraURL() {
        return ObraURL;
    }

    public void setObraURL(String ObraURL) {
        this.ObraURL = ObraURL;
    }

    public String getCapaURL() {
        return CapaURL;
    }

    public void setCapaURL(String CapaURL) {
        this.CapaURL = CapaURL;
    }

    @Override
    public String toString() {
        return "ObraConsulta{" + "titulo=" + titulo + ", localizacao=" + localizacao + ", tipoObra=" + tipoObra + ", codAcervo=" + codAcervo + ", ObraURL=" + ObraURL + ", CapaURL=" + CapaURL + '}';
    }

}
