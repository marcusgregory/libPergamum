/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.usuario;

import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.sigaa.ResponseTurmas;
import br.com.marcusgregory.libpergamum.sigaa.requests.RequestTurmas;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class UsuarioSIGAA {

    private String nome;
    private String curso;
    private String numMatricula;
    private String urlImagemPerfil;
    private String nivel;
    private String situacao;
    private String semestreEntrada;
    private String IDE;
    private String integralizacao;
    private String cookie;
    private static UsuarioSIGAA usuarioSIGAA;

    public UsuarioSIGAA() {
        usuarioSIGAA = this;
    }

    public static UsuarioSIGAA getUsuarioSIGAAAtual() throws UsuarioNaoLogadoException {
        if (usuarioSIGAA == null) {
            throw new UsuarioNaoLogadoException("Usuario não está logado no SIGAA!");
        } else {
            return usuarioSIGAA;
        }
    }

    public ResponseTurmas turmas() throws UsuarioNaoLogadoException, IOException, UsuarioSenhaIncorretosException {
        return RequestTurmas.request();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getUrlImagemPerfil() {
        return urlImagemPerfil;
    }

    public void setUrlImagemPerfil(String urlImagemPerfil) {
        this.urlImagemPerfil = urlImagemPerfil;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSemestreEntrada() {
        return semestreEntrada;
    }

    public void setSemestreEntrada(String semestreEntrada) {
        this.semestreEntrada = semestreEntrada;
    }

    public String getIDE() {
        return IDE;
    }

    public void setIDE(String IDE) {
        this.IDE = IDE;
    }

    public String getIntegralizacao() {
        return integralizacao;
    }

    public void setIntegralizacao(String integralizacao) {
        this.integralizacao = integralizacao;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public String toString() {
        return "UsuarioSIGAA{" + "nome=" + nome + ", curso=" + curso + ", numMatricula=" + numMatricula + ", urlImagemPerfil=" + urlImagemPerfil + ", nivel=" + nivel + ", situacao=" + situacao + ", semestreEntrada=" + semestreEntrada + ", IDE=" + IDE + ", integralizacao=" + integralizacao + ", cookie=" + cookie + '}';
    }

}
