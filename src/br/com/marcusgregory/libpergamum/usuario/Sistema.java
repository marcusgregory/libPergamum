/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.usuario;

import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.requests.RequestLogin;
import br.com.marcusgregory.libpergamum.requests.RequestLoginSIGAA;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class Sistema {

    private String login;
    private String senha;
    private static Sistema sistema;

    public static Sistema getSistemaAtual() {
        return sistema;
    }

    public Sistema(String login, String senha) {
        this.login = login;
        this.senha = senha;
        sistema = this;
    }

    public UsuarioBiblioteca logar() throws UsuarioSenhaIncorretosException, IOException, ErroDesconhecidoLoginException {
        return RequestLogin.request(this);
    }

    public UsuarioSIGAA logarSIGAA() throws IOException, UsuarioSenhaIncorretosException {
        return RequestLoginSIGAA.request(this);
    }

    public boolean exists() throws IOException {
        return RequestLogin.verificarLogin(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Sistema{" + "login=" + login + ", senha=" + senha + '}';
    }

}
