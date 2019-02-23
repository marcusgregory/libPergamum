/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca.consulta;

/**
 *
 * @author Vitoria
 */
public enum TipoObra {
    TODAS_AS_OBRAS(0),
    FOLHETO(2),
    LIVROS(1),
    CATALOGO(3),
    ARTIGOS(4),
    DISSERTACOES(6),
    TCC_GRADUACAO(7),
    NORMAS(8),
    TESES(9),
    TCC_POS_GRADUACAO(10),
    MUSICA(11),
    BASE_DE_DADOS(14),
    DVD(18),
    CAPITULO_DE_LIVROS(19),
    GRAVACAO_DE_VIDEO(22),
    MAPAS(23),
    CD_ROM(24),
    DVD_ROM(26),
    GRAVACAO_DE_SOM(27);

    private final int valor;

    TipoObra(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
