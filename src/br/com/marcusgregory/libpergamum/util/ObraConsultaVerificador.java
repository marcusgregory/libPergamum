/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.util;

import br.com.marcusgregory.libpergamum.biblioteca.consulta.ObraConsulta;

/**
 *
 * @author Gregory
 */
public class ObraConsultaVerificador {

    public static boolean isValido(ObraConsulta obc) {
        if (obc.getCodAcervo() != null && obc.getLocalizacao() != null && obc.getObraURL() != null && obc.getTitulo() != null) {
            boolean codAcervo = obc.getCodAcervo().matches("([0-9]{1,})");
            boolean localizacao = !obc.getLocalizacao().trim().isEmpty();
            boolean obraURL = URLVerificador.isValidURL(obc.getObraURL());
            boolean titulo = !obc.getTitulo().trim().isEmpty();

            return codAcervo && localizacao && obraURL && titulo;
        } else {
            return false;
        }
    }
}
