/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa.requests;

import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.sigaa.Aula;
import br.com.marcusgregory.libpergamum.sigaa.Documento;
import br.com.marcusgregory.libpergamum.sigaa.Regex;
import br.com.marcusgregory.libpergamum.sigaa.ResponseAulas;
import br.com.marcusgregory.libpergamum.usuario.UsuarioSIGAA;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class RequestAulas {

    public static ResponseAulas request(String id) throws UsuarioNaoLogadoException, IOException, Exception {
        Connection.Response execute1 = Jsoup.connect("https://sig.unilab.edu.br/sigaa/portais/discente/discente.jsf")
                .method(Connection.Method.HEAD)
                .cookie("JSESSIONID", UsuarioSIGAA.getUsuarioSIGAAAtual().getCookie())
                .userAgent("Mozilla/5.0")
                .execute();

        Connection.Response execute2 = Jsoup.connect("https://sig.unilab.edu.br/sigaa/portais/discente/discente.jsf")
                .method(Connection.Method.POST).followRedirects(false)
                .header("Referer", "https://sig.unilab.edu.br/sigaa/portais/discente/discente.jsf")
                .data("form_acessarTurmaVirtual", "form_acessarTurmaVirtual")
                .data("idTurma", id)
                .data("javax.faces.ViewState", "j_id4")
                .data("form_acessarTurmaVirtual:turmaVirtual", "form_acessarTurmaVirtual:turmaVirtual")
                .cookie("JSESSIONID", UsuarioSIGAA.getUsuarioSIGAAAtual().getCookie())
                .userAgent("Mozilla/5.0")
                .execute();
        Elements topicos = execute2.parse().select("span[id=\"formAva:panelTopicosNaoSelecionados\"]  > span[id*=aulas] > div[id*=topico_aula]");
        ResponseAulas rspa = new ResponseAulas();
        for (Element topico : topicos) {
            Aula aula = new Aula();
            aula.setTitulo(topico.select(".titulo").text().trim());
            aula.setConteudo(topico.select(".conteudotopico > p,ul").text().replace("   ", "\n"));
            Elements arquivos = topico.select("div[id*=conteudo] > span[id*=listaMateriais]");
            for (Element arquivo : arquivos) {
                Documento doc = new Documento();
                Elements arquivo1 = arquivo.select("div[id*=listaMateriais]> span[id]:has(a[id*=idInserirMaterialArquivo]) > a");
                doc.setNome(arquivo1.text().trim());
                doc.setFormAva(arquivo1.attr("id"));
                doc.setId(Regex.idDoc(arquivo1.attr("onclick")));
                if (!(doc.getNome() == null || doc.getId() == null)) {
                    aula.addDocumento(doc);
                }
            }
            rspa.addAula(aula);

        }

        return rspa;

    }
}
