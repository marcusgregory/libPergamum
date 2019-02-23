/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.requests;

import br.com.marcusgregory.libpergamum.biblioteca.CapaObraURL;
import br.com.marcusgregory.libpergamum.biblioteca.reserva.ObraReserva;
import br.com.marcusgregory.libpergamum.biblioteca.reserva.ResponseReservas;
import br.com.marcusgregory.libpergamum.biblioteca.reserva.Situacao;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.usuario.UsuarioBiblioteca;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class RequestReservas {

    public static ResponseReservas request() throws IOException, UsuarioNaoLogadoException {
        Connection.Response execute = Jsoup.connect("http://bibweb.unilab.edu.br/pergamum/mobile/minhas_reservas.php")
                .userAgent("Mozilla/5.0")
                .method(Connection.Method.GET)
                .cookie("PHPSESSID", UsuarioBiblioteca.getUsuarioBibliotecaAtual().getCookie())
                .execute();

        Elements selects = execute.parse().select("ul[id=conteudo_reservado] > li:has(a):has(h2):has(p)");
        ResponseReservas rr = new ResponseReservas();
        for (Element element : selects) {
            ObraReserva obr = new ObraReserva();

            obr.setTitulo(element.select("h2").text());

            obr.setSituacao(Situacao.lookupByName(element.select("p").text().split(":")[1].trim()));
            Pattern pattern = Pattern.compile("apagaReserva\\('(\\d+)'");
            Matcher comparator = pattern.matcher(element.select("a[href]").attr("href"));
            if (comparator.find(0)) {
                obr.setCodAcervo(comparator.group(1));
                obr.setCapaURL(CapaObraURL.getCapaByCodAcervo(obr.getCodAcervo()));
            }
            rr.addObraReserva(obr);
        }
        return rr;

    }
}
