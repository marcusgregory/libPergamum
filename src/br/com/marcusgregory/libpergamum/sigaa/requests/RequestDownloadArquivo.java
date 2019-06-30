/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa.requests;

import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.sigaa.Arquivo;
import br.com.marcusgregory.libpergamum.sigaa.Documento;
import br.com.marcusgregory.libpergamum.usuario.UsuarioSIGAA;
import br.com.marcusgregory.libpergamum.util.Logger;
import java.io.IOException;
import java.net.HttpURLConnection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class RequestDownloadArquivo {

    public static Arquivo request(Documento doc) throws IOException, UsuarioNaoLogadoException {
        Logger.log("Baixando arquivo...");
        Logger.log("Enviando POST para https://sig.unilab.edu.br/sigaa/ava/index.jsf");
        Logger.log(doc.toString());
        Connection.Response execute = Jsoup.connect("https://sig.unilab.edu.br/sigaa/ava/index.jsf")
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .cookie("JSESSIONID", UsuarioSIGAA.getUsuarioSIGAAAtual().getCookie())
                .data("formAva", "formAva")
                .data("formAva:idTopicoSelecionado", "0")
                .data(doc.getFormAva(), doc.getFormAva())
                .data("id", doc.getId())
                .data("javax.faces.ViewState", "j_id5")
                .execute();

        // always check HTTP response code first
        if (execute.statusCode() == HttpURLConnection.HTTP_OK) {
            Logger.log("HTTP status = 200 OK");
            String fileName = "";
            String disposition = execute.header("Content-Disposition");
            String contentType = execute.contentType();
            int contentLength = execute.bodyAsBytes().length;

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                throw new IOException();
            }

            Logger.log("Content-Type = " + contentType);
            Logger.log("Content-Disposition = " + disposition);
            Logger.log("Content-Length = " + contentLength);
            Logger.log("fileName = " + fileName);
            Arquivo arq = new Arquivo();
            arq.setNomeArquivo(fileName);
            arq.setTamanho(contentLength);
            arq.setBytes(execute.bodyAsBytes());

            
            return arq;
        } else {
            Logger.log("Não foi possivel realizar o download. Servidor retornou HTTP code: " + execute.statusCode());
            throw new IOException("Não foi possivel realizar o download. Servidor retornou HTTP code: " + execute.statusCode());
        }

    }
}
