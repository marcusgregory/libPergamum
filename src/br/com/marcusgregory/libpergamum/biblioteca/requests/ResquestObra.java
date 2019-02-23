/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.requests;

import br.com.marcusgregory.libpergamum.biblioteca.Obra;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class ResquestObra {

    public static Obra request(String codAcervo) throws IOException {
        Connection.Response execute = Jsoup.connect("http://bibweb.unilab.edu.br/pergamum/mobile/resultado_info.php?cod_acervo=" + codAcervo)
                .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19")
                .header("Referer", "http://bibweb.unilab.edu.br/pergamum/mobile/consulta.php")
                .method(Connection.Method.GET)
                .execute();
        Obra obra = new Obra();
        obra.setCodAcervo(codAcervo);
        Elements selects = execute.parse().select("li[data-role=list-divider]");

        if (!selects.isEmpty() && selects.size() == 4) {
            obra.setTitulo(selects.get(0).text().replace("Título: ", ""));
            obra.setAutor(selects.get(1).text().replace("Autor:", "").replaceFirst(" ", ""));
            obra.setLocalizacao(selects.get(2).text().replace("Localização: ", ""));
            obra.setTipoObra(selects.get(3).text().replace("Tipo de obra: ", ""));
            Elements selectImagens = execute.parse().select("div[style]>img[src]");
            if (!selectImagens.isEmpty() && selectImagens.size() >= 1) {
                obra.setCapaURL(selectImagens.get(0).attr("src"));
            } else {
                obra.setCapaURL(null);
            }
            return obra;
        } else {
            return null;
        }
    }
}
