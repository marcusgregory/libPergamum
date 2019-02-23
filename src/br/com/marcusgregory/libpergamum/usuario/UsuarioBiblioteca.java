/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.usuario;

import br.com.marcusgregory.libpergamum.biblioteca.emprestimo.ResultadoEmprestimo;
import br.com.marcusgregory.libpergamum.biblioteca.requests.RequestEmprestimo;
import br.com.marcusgregory.libpergamum.biblioteca.requests.RequestReservas;
import br.com.marcusgregory.libpergamum.biblioteca.reserva.ResponseReservas;
import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Gregory
 */
public class UsuarioBiblioteca {

    private String nome;
    private Map cookies;
    private String cookie;
    private static UsuarioBiblioteca usuarioBiblioteca;

    public UsuarioBiblioteca() {
        usuarioBiblioteca = this;
    }

    public static UsuarioBiblioteca getUsuarioBibliotecaAtual() throws UsuarioNaoLogadoException {
        if (usuarioBiblioteca == null) {
            throw new UsuarioNaoLogadoException("Usuario não está logado no sistema!");
        } else {
            return usuarioBiblioteca;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map getCookies() {
        return cookies;
    }

    public void setCookies(Map cookie) {
        this.cookies = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public ResultadoEmprestimo emprestimos() throws UsuarioNaoLogadoException, IOException, UsuarioSenhaIncorretosException, ErroDesconhecidoLoginException {
        return RequestEmprestimo.request();
    }

    public ResponseReservas reservas() throws IOException, UsuarioNaoLogadoException {
        return RequestReservas.request();

    }

    @Override
    public String toString() {
        return "UsuarioBiblioteca{" + "nome=" + nome + '}';
    }

}
