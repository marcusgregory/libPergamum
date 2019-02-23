/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.consulta;

import br.com.marcusgregory.libpergamum.exceptions.NenhumResultadoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vitoria
 */
public class ConsultaAcervo {

    private String termoBusca;
    private String tipoBusca;
    private final List<String> unidade;
    private final List<String> tipoObra;

    public ConsultaAcervo() {
        this.unidade = new ArrayList<>();
        this.tipoObra = new ArrayList<>();
    }

    public ConsultaAcervo termoBusca(String termoBusca) {
        this.termoBusca = termoBusca;
        return this;
    }

    public ConsultaAcervo tipoBusca(TipoBusca tipoBusca) {
        this.tipoBusca = tipoBusca.getValor();
        return this;
    }

    public ConsultaAcervo unidade(Unidade unidade) {
        switch (unidade.getValor()) {

            case 0:
                this.unidade.add("tudo");
                break;
            default:
                this.unidade.add(Integer.toString(unidade.getValor()));
        }
        return this;
    }

    public ConsultaAcervo obra(TipoObra tipoObra) {
        switch (tipoObra.getValor()) {
            case 0:
                this.tipoObra.add("tudo");
                break;
            default:
                this.tipoObra.add(Integer.toString(tipoObra.getValor()));
        }
        return this;
    }

    public ResultadoConsulta consultar() throws IOException, NenhumResultadoException {
        return br.com.marcusgregory.libpergamum.biblioteca.requests.RequestConsulta.request(this, 1);
    }

    public ResultadoConsulta consultar(int pagina) throws IOException, NenhumResultadoException {

        return br.com.marcusgregory.libpergamum.biblioteca.requests.RequestConsulta.request(this, pagina);
    }

    public String getTermoBusca() {
        return termoBusca;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public List<String> getUnidade() {
        return unidade;
    }

    public List<String> getTipoObra() {
        return tipoObra;
    }

    @Override
    public String toString() {
        return "Consulta{" + "termoBusca=" + termoBusca + ", tipoBusca=" + tipoBusca + ", unidade=" + unidade + ", tipoObra=" + tipoObra + '}';
    }

}
