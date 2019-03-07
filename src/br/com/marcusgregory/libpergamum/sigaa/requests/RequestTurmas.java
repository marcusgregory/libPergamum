/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa.requests;

import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.requests.RequestLoginSIGAA;
import br.com.marcusgregory.libpergamum.sigaa.Regex;
import br.com.marcusgregory.libpergamum.sigaa.ResponseTurmas;
import br.com.marcusgregory.libpergamum.sigaa.Turma;
import br.com.marcusgregory.libpergamum.usuario.Sistema;
import br.com.marcusgregory.libpergamum.usuario.UsuarioSIGAA;
import br.com.marcusgregory.libpergamum.util.Logger;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class RequestTurmas {
    private static Object response;
    private static int flagTentativa;
    public static ResponseTurmas request() throws UsuarioNaoLogadoException, IOException, UsuarioSenhaIncorretosException, ErroDesconhecidoLoginException {
        Thread AtestMat = new Thread(new AtestMat());
        AtestMat.start();
        Connection.Response turmas = RequestResponse();
        if (turmas.statusCode() == 200 && !turmas.parse().text().toUpperCase().contains("SESSÃO FOI EXPIRADA")) {
            ResponseTurmas rp = new ResponseTurmas();
            Elements elTurmas = turmas.parse().select("a[id*=form-turmas-discente:cmdEntrarTurma]");
            try {
                AtestMat.join();
            } catch (InterruptedException ex) {
                Logger.log(ex.getMessage());
            }
            if(response instanceof Map){
                Map<String, String> Docentes = (Map)response;
                Logger.log("Obtendo turmas do discente...");
            for (Element turma : elTurmas) {
                Turma trm = new Turma();
                String nomeTurmaComp = turma.ownText().trim();
                trm.setCodigo(nomeTurmaComp.contains("-") ? nomeTurmaComp.split("-")[0].trim() : "Código não definido");
                trm.setNomeTurma(nomeTurmaComp.contains("-") ? nomeTurmaComp.split("-")[1].trim() : "Nome não definido");
                trm.setLocal((turma.children().eq(1).text().contains(":")) ? turma.children().eq(1).text().split(":")[1].trim() : "Local não definido");
                trm.setHorario((turma.children().eq(3).text().contains(":")) ? turma.children().eq(3).text().split(":")[1].trim() : "Horário não definido");
                trm.setIdTurma(Regex.idTurma(turma.attr("onclick")));
                trm.setDocente(Docentes.get(trm.getCodigo()));
                Logger.log(trm.toString());
                rp.addTurma(trm);
            }
            }else if(response instanceof IOException){
                Logger.log(((IOException)response).toString());
                throw (IOException)response;
            }else if(response instanceof UsuarioNaoLogadoException){
                Logger.log(((UsuarioNaoLogadoException)response).toString());
                throw (UsuarioNaoLogadoException)response;
            }         
            return rp;

        } else {
            Logger.log("Erro no login SIGAA HTTP Status=" + turmas.statusCode());
            if (flagTentativa <= 1) {
                Logger.log("Tentativa=" + flagTentativa);
                RequestLoginSIGAA.request(Sistema.getSistemaAtual());
                flagTentativa++;
                return request();
            } else {
                flagTentativa = 0;
                Logger.log("Usuario não está logado no sistema");
                throw new UsuarioNaoLogadoException("Usuario não está logado no sistema!");
            }

        }

    }

    private static Connection.Response RequestResponse() throws IOException, UsuarioNaoLogadoException {
        
        Logger.log("Enviando POST para https://sig.unilab.edu.br/sigaa/mobile/touch/menu.jsf");
        try{
        Connection.Response turmas = Jsoup.connect("https://sig.unilab.edu.br/sigaa/mobile/touch/menu.jsf")
                .method(Connection.Method.POST)
                .followRedirects(false)
                .data("form-portal-discente", "form-portal-discente")
                .data("javax.faces.ViewState", "j_id1")
                .data("form-portal-discente:lnkMinhasTurmas", "form-portal-discente:lnkMinhasTurmas")
                .cookie("JSESSIONID", UsuarioSIGAA.getUsuarioSIGAAAtual().getCookie())
                .userAgent("Mozilla/5.0")
                .execute();
        return turmas;
        }catch(Exception e){
         Logger.log(e.toString());
         throw e;  
        }
        
    }

    public static class AtestMat implements Runnable {

        @Override
        public void run() {
            Connection.Response postAtestMat;
            try {
               
                Logger.log("Obtendo nome dos docentes...");
                Logger.log("Enviando POST para https://sig.unilab.edu.br/sigaa/mobile/touch/menu.jsf");
                postAtestMat = Jsoup.connect("https://sig.unilab.edu.br/sigaa/mobile/touch/menu.jsf")
                        .method(Connection.Method.POST).followRedirects(false)
                        .data("form-portal-discente", "form-portal-discente")
                        .data("javax.faces.ViewState", "j_id1")
                        .data("form-portal-discente:lnkAtestadoMatricula", "form-portal-discente:lnkAtestadoMatricula")
                        .cookie("JSESSIONID", UsuarioSIGAA.getUsuarioSIGAAAtual().getCookie())
                        .userAgent("Mozilla/5.0")
                        .execute();
                Map<String, String> docenteTurma = new HashMap<>();
                Elements turmas = postAtestMat.parse().select("#matriculas > tbody > tr");
                
                for (Element turma : turmas) {
                    docenteTurma.put(turma.select(".codigo").text(), turma.select(".docente").text());
                }
                
                response=docenteTurma;
            } catch (UsuarioNaoLogadoException | IOException ex) {
                Logger.logError(ex);
                response=ex;
            }

        }

    }

}
