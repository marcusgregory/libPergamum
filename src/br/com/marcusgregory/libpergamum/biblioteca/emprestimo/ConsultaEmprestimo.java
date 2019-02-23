/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.emprestimo;

import br.com.marcusgregory.libpergamum.exceptions.ErroDesconhecidoLoginException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioNaoLogadoException;
import br.com.marcusgregory.libpergamum.exceptions.UsuarioSenhaIncorretosException;
import br.com.marcusgregory.libpergamum.biblioteca.requests.RequestEmprestimo;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class ConsultaEmprestimo {

    public static ResultadoEmprestimo getResultadoEmprestimo() throws UsuarioNaoLogadoException, IOException, UsuarioSenhaIncorretosException, ErroDesconhecidoLoginException {

        return RequestEmprestimo.request();

    }
}
