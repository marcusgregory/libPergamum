package br.com.marcusgregory.libpergamum.requests;

import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.usuario.Sistema;
import br.com.marcusgregory.libpergamum.usuario.UsuarioSIGAA;
import br.com.marcusgregory.libpergamum.util.Logger;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * @author Gregory
 */
public class RequestLoginSIGAA {
    
    private static final String host = "https://sig.unilab.edu.br";
    private static final String url1 = "https://sig.unilab.edu.br/sigaa/logar.do?dispatch=logOn";
    private static final String url2 = "https://sig.unilab.edu.br/sigaa/mobile/touch/menu.jsf";
    
    public static UsuarioSIGAA request(Sistema sistema) throws IOException, UsuarioSenhaIncorretosException, ErroDesconhecidoLoginException {
        
        Logger.log("Preparando login...");
        Logger.log("Enviando GET para https://sig.unilab.edu.br/sigaa/mobile/touch/login.jsf");
        
        UsuarioSIGAA usuarioSIGAA = new UsuarioSIGAA();
        try {
            Connection.Response execute = Jsoup.connect("https://sig.unilab.edu.br/sigaa/mobile/touch/login.jsf")
                    .method(Connection.Method.GET)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0")
                    .execute();
            Logger.log("HTTP status="+execute.statusCode()+" "+execute.statusMessage());
            String formLoginUsuario = execute.parse().select("input[type=text]").attr("name");
            String formLoginSenha = execute.parse().select("input[type=password]").attr("name");
            Logger.log("formLoginUsuario=" + formLoginUsuario + ", formLoginSenha=" + formLoginSenha);
            
            Logger.log("Efetuando login...");
            Logger.log("Enviando POST para https://sig.unilab.edu.br/sigaa/mobile/touch/login.jsf");
            Connection.Response execute2 = Jsoup.connect("https://sig.unilab.edu.br/sigaa/mobile/touch/login.jsf")
                    .method(Connection.Method.POST)
                    .data("form-login", "form-login")
                    .data(formLoginUsuario, sistema.getLogin())
                    .data(formLoginSenha, sistema.getSenha())
                    .data("form-login:entrar", "Entrar")
                    .data("javax.faces.ViewState", "j_id1").cookies(execute.cookies())
                    .userAgent("Mozilla/5.0")
                    .execute();
            Logger.log("HTTP status="+execute2.statusCode()+" "+execute2.statusMessage());
            if (execute2.cookie("JSESSIONID") == null || execute2.cookie("JSESSIONID").isEmpty()) {
               ErroDesconhecidoLoginException erroDesconhecido = new ErroDesconhecidoLoginException("Ocorreu um erro inesperado no servidor");
               Logger.logError(erroDesconhecido);
               throw erroDesconhecido;
                
            }
            String cookie = execute2.cookie("JSESSIONID");
            Logger.log("Obteve o cookie: JSESSIONID=" + cookie);
            Logger.log("Enviando GET para https://sig.unilab.edu.br/sigaa/portais/discente/discente.jsf");
            Connection.Response execute4 = Jsoup.connect("https://sig.unilab.edu.br/sigaa/portais/discente/discente.jsf")
                    .method(Connection.Method.GET)
                    .cookie("JSESSIONID", cookie)
                    .userAgent("Mozilla/5.0")
                    .execute();
            Logger.log("HTTP status="+execute4.statusCode()+" "+execute4.statusMessage());
            if (!execute2.parse().text().toUpperCase().contains("SAIR")) {
                Logger.log("SIGAA: Usuário ou senha incorretos");
                throw new UsuarioSenhaIncorretosException("SIGAA: Usuário ou senha incorretos");
                
            } else {
                Logger.log("Obtendo dados do perfil...");
                Element perfil = execute4.parse().select("#perfil-docente").first();
                usuarioSIGAA.setNome(perfil.select(".nome > small:nth-child(1) > b:nth-child(1)").text().trim());
                usuarioSIGAA.setNumMatricula(perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)").text().trim());
                usuarioSIGAA.setCurso(perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text().trim());
                usuarioSIGAA.setNivel(perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2)").text().trim());
                usuarioSIGAA.setSituacao(perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)").text().trim());
                usuarioSIGAA.setSemestreEntrada(perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2)").text().trim());
                usuarioSIGAA.setIDE(perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(9) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(4) > div:nth-child(1)").text().trim());
                String[] integ = perfil.select("div:nth-child(7) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(10) > td:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(1)").text().trim().split("%");
                usuarioSIGAA.setIntegralizacao(integ[0]);
                String foto = perfil.select(".foto > img:nth-child(1)").attr("abs:src");
                if (foto.contains("no_picture")) {
                    foto = "http://sig.unilab.edu.br/sigaa/img/no_picture.png";
                }
                usuarioSIGAA.setUrlImagemPerfil(foto);
                usuarioSIGAA.setCookie(cookie);
                Logger.log(usuarioSIGAA.toString());
                
            }
        } catch (UsuarioSenhaIncorretosException | IOException e) {
            Logger.logError(e);
            throw e;
        }
        return usuarioSIGAA;
    }
}
