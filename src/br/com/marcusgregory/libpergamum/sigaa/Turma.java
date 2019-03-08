/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa;

import br.com.marcusgregory.libpergamum.sigaa.requests.RequestAulas;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class Turma {

    private String codigo;
    private String nomeTurma;
    private String docente;
    private String local;
    private String horario;
    private String idTurma;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(String idTurma) {
        this.idTurma = idTurma;
    }

    public ResponseAulas aulas() throws IOException, Exception {
        return RequestAulas.request(this.idTurma);
    }

    @Override
    public String toString() {
        return "Turma{" + "codigo=" + codigo + ", nomeTurma=" + nomeTurma + ", docente=" + docente + ", local=" + local + ", horario=" + horario + ", idTurma=" + idTurma + '}';
    }

}
