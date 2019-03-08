/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.requests;

import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.usuario.Sistema;
import br.com.marcusgregory.libpergamum.usuario.UsuarioBiblioteca;
import br.com.marcusgregory.libpergamum.util.Logger;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class RequestLogin {

    private static Connection.Response RequestResponse(Sistema sistema) throws IOException {
        Logger.log("Efetuando login...");
        Logger.log("Enviando POST para http://bibweb.unilab.edu.br/pergamum/mobile/login.php");
        Connection login = Jsoup.connect("http://bibweb.unilab.edu.br/pergamum/mobile/login.php")
                            .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19")
                .header("Referer", "http://bibweb.unilab.edu.br/pergamum/mobile/login.php")
                .data("flag", "renovacao.php")
                .data("login", sistema.getLogin())
                .data("password", sistema.getSenha())
                .data("button", "Acessar")
                .method(Connection.Method.POST);

        Connection.Response loginExecute = login.followRedirects(false).execute();
        Connection.Response loginExecute2 = login.cookies(loginExecute.cookies()).followRedirects(true).execute();
        return loginExecute;
    }

    public static UsuarioBiblioteca request(Sistema sistema) throws UsuarioSenhaIncorretosException, IOException, ErroDesconhecidoLoginException {
        Connection.Response login = RequestResponse(sistema);

        if (login.statusCode() == 302) {
            Logger.log("HTTP status:"+login.statusCode()+" | "+login.statusMessage());
            String header = login.header("Location");
            String decode = URLDecoder.decode(header, "ISO-8859-1");

            UsuarioBiblioteca usuarioBiblioteca = new UsuarioBiblioteca();
            Pattern pattern = Pattern.compile("nomepessoa2=(.+?)&");
            Matcher comparator = pattern.matcher(decode);
            if (comparator.find(0)) {

                usuarioBiblioteca.setNome(comparator.group(1).trim());
            } else {
                ErroDesconhecidoLoginException erro = new ErroDesconhecidoLoginException("Ocorreu um erro desconhecido no Login!");
                Logger.logError(erro);
                throw erro;
            }

            usuarioBiblioteca.setCookie(login.cookie("PHPSESSID"));
            Logger.log("Usuário efetuou login com sucesso!");
            Logger.log(usuarioBiblioteca.toString());
            return usuarioBiblioteca;

        } else {
            UsuarioSenhaIncorretosException erro=new UsuarioSenhaIncorretosException("Usuário ou senha incorretos!");
            Logger.logError(erro);
            throw erro;
        }

    }

    public static boolean verificarLogin(Sistema siatema) throws IOException {
        Connection.Response login = RequestResponse(siatema);
        if (login.statusCode() == 302) {
            String header = login.header("Location");
            String decode = URLDecoder.decode(header, "ISO-8859-1");
            UsuarioBiblioteca usuarioBiblioteca = new UsuarioBiblioteca();
            Pattern pattern = Pattern.compile("nomepessoa2=(?<nome>.+?)&");
            Matcher comparator = pattern.matcher(decode);
            if (comparator.find(0)) {
                usuarioBiblioteca.setNome(comparator.group("nome"));
            }
            usuarioBiblioteca.setCookie(login.cookie("PHPSESSID"));
            return true;

        } else {
            return false;
        }

    }
}
