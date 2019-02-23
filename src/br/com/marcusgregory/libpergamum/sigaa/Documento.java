/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa;

import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.sigaa.requests.RequestDownloadArquivo;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class Documento {
    
    private String nome;
    private String id;
    private String formAva;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormAva() {
        return formAva;
    }

    public void setFormAva(String formAva) {
        this.formAva = formAva;
    }
   public Arquivo download() throws IOException, UsuarioNaoLogadoException{
       return RequestDownloadArquivo.request(this);
   }
    @Override
    public String toString() {
        return "Documento{" + "nome=" + nome + ", id=" + id + ", formAva=" + formAva + '}';
    }

  

}
