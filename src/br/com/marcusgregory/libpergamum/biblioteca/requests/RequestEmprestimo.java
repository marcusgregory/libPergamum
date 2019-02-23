/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.requests;

import br.com.marcusgregory.libpergamum.biblioteca.CapaObraURL;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.biblioteca.emprestimo.ObraEmprestimo;
import br.com.marcusgregory.libpergamum.biblioteca.emprestimo.ResultadoEmprestimo;
import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.requests.RequestLogin;
import br.com.marcusgregory.libpergamum.usuario.Sistema;
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
public class RequestEmprestimo {

    private static int flagTentativa;
    private boolean sucesso;

    public static ResultadoEmprestimo request() throws UsuarioNaoLogadoException, IOException, UsuarioSenhaIncorretosException, ErroDesconhecidoLoginException {
        Connection.Response renovacao = RequestResponse();
        if (renovacao.statusCode() == 200) {
            Elements selects = renovacao.parse().select("li>a[href]");
            ResultadoEmprestimo rr = new ResultadoEmprestimo();
            for (Element element : selects) {
                ObraEmprestimo obr = new ObraEmprestimo();
                obr.setTitulo(element.select("h2").text());
                obr.setDataDevolucao(element.select("p").first().ownText());
                obr.setNumRenovacoes(Integer.parseInt(element.select("p").last().ownText()));
                String urlobr = element.select("a[href]").attr("abs:href");
                Pattern pattern = Pattern.compile("cod_acervo=(.+?)&cod_exemplar=(.+)");
                Matcher comparator = pattern.matcher(urlobr);
                if (comparator.find(0)) {
                    obr.setCodAcervo(comparator.group(1));
                    obr.setCapaURL(CapaObraURL.getCapaByCodAcervo(comparator.group(1)));
                    obr.setCodExemplar(comparator.group(2));
                }
                rr.addObraRenovacao(obr);
            }
            return rr;
        } else {
            System.err.println("Erro no login status=" + renovacao.statusCode());
            if (flagTentativa <= 1) {
                System.err.println("Tentativa=" + flagTentativa);
                RequestLogin.request(Sistema.getSistemaAtual());
                flagTentativa++;
                return request();
            } else {
                flagTentativa = 0;
                throw new UsuarioNaoLogadoException("Usuario não está logado no sistema!");
            }

        }

    }

    private static Connection.Response RequestResponse() throws IOException, UsuarioNaoLogadoException {
        Connection.Response renovacao = Jsoup.connect("http://bibweb.unilab.edu.br/pergamum/mobile/renovacao.php")
                .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19")
                .cookie("PHPSESSID", UsuarioBiblioteca.getUsuarioBibliotecaAtual().getCookie())
                .method(Connection.Method.GET).followRedirects(false)
                .execute();
        return renovacao;
    }
}
