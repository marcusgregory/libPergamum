/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa;

import java.util.ArrayList;

/**
 *
 * @author Gregory
 */
public class Aula {

    private String titulo;
    private String conteudo;
    private ArrayList<Documento> documentos;

    public Aula() {
        this.documentos = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }

    public void addDocumento(Documento documento) {
        documentos.add(documento);
    }

    @Override
    public String toString() {
        return "Aula{" + "titulo=" + titulo + ", conteudo=" + conteudo + ", documentos=" + documentos + '}';
    }

}
