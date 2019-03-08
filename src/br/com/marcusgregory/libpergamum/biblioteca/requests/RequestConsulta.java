/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.requests;

import br.com.marcusgregory.libpergamum.biblioteca.CapaObraURL;
import br.com.marcusgregory.libpergamum.biblioteca.consulta.ConsultaAcervo;
import br.com.marcusgregory.libpergamum.biblioteca.consulta.ObraConsulta;
import br.com.marcusgregory.libpergamum.biblioteca.consulta.ResultadoConsulta;
import br.com.marcusgregory.libpergamum.exceptions.NenhumResultadoException;
import br.com.marcusgregory.libpergamum.util.ObraConsultaVerificador;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author unicafe
 */
public class RequestConsulta {

    public static ResultadoConsulta request(ConsultaAcervo ca, int pagina) throws IOException, NenhumResultadoException {

        ResultadoConsulta rc = new ResultadoConsulta();
        Connection busca = Jsoup.connect("http://bibweb.unilab.edu.br/pergamum/mobile/resultado.php?pg=" + pagina)
                .userAgent("Mozilla/5.0")
                .header("Referer", "http://bibweb.unilab.edu.br/pergamum/mobile/consulta.php")
                .data("termo_busca", ca.getTermoBusca())
                .data("buscar_por", ca.getTipoBusca())
                .method(Connection.Method.GET).timeout(10000);
        for (String unidade : ca.getUnidade()) {
            busca.data("consulta_unidade[]", unidade);
        }
        for (String consultaTipo : ca.getTipoObra()) {
            busca.data("consulta_tipo[]", consultaTipo);
        }
        Connection.Response execute = null;

        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println("tentando " + i);
                execute = busca.execute();
                break;
            }
            if (execute.parse() == null) {
                throw new IOException("Erro na conexão");
            } else {
                Elements selectsObras = execute.parse().select("li[data-icon=plus]");
                if (!selectsObras.isEmpty()) {
                    if (execute.parse().select("li[data-icon=plus]").isEmpty()) {

                        rc.setPaginaAtual(0);
                        rc.setTotalDePaginas(0);
                        return rc;
                    }
                    String result = execute.parse().select("li[data-role=list-divider]").get(0).text();

                    try {
                        int quantResultado = Integer.parseInt(result.substring(0, result.indexOf(" ")));
                        if (quantResultado > 5) {
                            String paginas = execute.parse().select("li[data-role=list-divider]").get(1).text();
                            rc.setPaginaAtual(Integer.parseInt(paginas.substring(paginas.indexOf(" ") + 1, paginas.indexOf("de") - 1)));
                            rc.setTotalDePaginas(Integer.parseInt(paginas.substring(paginas.indexOf("de ") + 3, paginas.length())));
                        } else {
                            rc.setPaginaAtual(1);
                            rc.setTotalDePaginas(1);
                        }
                    } catch (NumberFormatException ex) {
                        rc.setPaginaAtual(1);
                        rc.setTotalDePaginas(1);
                    }

                    for (Element select : selectsObras) {
                        ObraConsulta ob = new ObraConsulta();
                        ob.setTitulo(select.select("h2").text());
                        ob.setLocalizacao(select.select("p").first().text().replace("Localização: ", ""));
                        ob.setTipoObra(select.select("p").eq(1).text().replace("Tipo de obra: ", ""));
                        String urlobra = select.select("a[href]").attr("abs:href");
                        ob.setObraURL(urlobra);
                        URL URLobra = new URL(urlobra);
                        String[] codAcervo = URLobra.getQuery().split("=");
                        if ("cod_acervo".equals(codAcervo[0])) {
                            ob.setCodAcervo(codAcervo[1]);
                            ob.setCapaURL(CapaObraURL.getCapaByCodAcervo(codAcervo[1]));
                        } else {
                            ob.setCodAcervo(null);
                        }
                        if (ObraConsultaVerificador.isValido(ob)) {
                            rc.addObra(ob);
                        }

                    }
                } else {
                    throw new NenhumResultadoException("Nenhum resultado!");
                }

                return rc;
            }
        } catch (IOException ex) {
            Logger.getLogger(RequestConsulta.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex.getMessage(), ex);
        }

    }
}
