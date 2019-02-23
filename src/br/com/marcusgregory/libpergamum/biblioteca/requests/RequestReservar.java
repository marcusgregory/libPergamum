/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.requests;

import br.com.marcusgregory.libpergamum.biblioteca.Obra;
import br.com.marcusgregory.libpergamum.exceptions.ReservaException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.biblioteca.UnidadeReserva;
import br.com.marcusgregory.libpergamum.usuario.Sistema;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class RequestReservar {

    public static void request(Obra obra, UnidadeReserva unidadeReserva) throws UsuarioNaoLogadoException, IOException, ReservaException {
        requestReservar(obra.getCodAcervo(), unidadeReserva);
    }

    public static void request(String codAcervo, UnidadeReserva unidadeReserva) throws UsuarioNaoLogadoException, IOException, ReservaException {
        requestReservar(codAcervo, unidadeReserva);
    }

    private static void requestReservar(String CodAcervo, UnidadeReserva unidadeReserva) throws UsuarioNaoLogadoException, IOException, ReservaException {
        Connection.Response execute = Jsoup.connect("http://bib.unilab.edu.br/pergamum/biblioteca/index.php?rs=ajax_dados_reserva&rsargs[]=" + CodAcervo + "&rsargs[]=" + unidadeReserva.getValor() + "&rsargs[]=Unico&rsargs[]=99999&rsargs[]=99999&rsargs[]=" + Sistema.getSistemaAtual().getLogin() + "&rsargs[]=" + Sistema.getSistemaAtual().getSenha() + "&rsargs[]=0&rsargs[]=v&rsargs[]=-1&rsargs[]=,&rsargs[]=0&rsargs[]=1")
                .userAgent("Mozilla/5.0")
                .header("Referer", "")
                .method(Connection.Method.GET)
                .execute();
        String avisoReserva = execute.parse().select("p").text();
        if (avisoReserva.contains("Reserva concluída")) {
            System.out.println("Reserva concluída");
        } else if (avisoReserva.isEmpty()) {
            throw new ReservaException(execute.parse().select("font").text());
        } else {
            throw new ReservaException("Erro desconhecido ao Reservar Obra!");
        }

    }

}
