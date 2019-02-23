/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.sigaa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gregory
 */
public class Regex {

    public static String idTurma(String script) {
        Pattern pattern = Pattern.compile("'([0-9]+?)'");
        Matcher comparator = pattern.matcher(script);
        if (comparator.find(0)) {
            return comparator.group(1);
        } else {
            return null;
        }

    }
    public static String idDoc(String script) {
        Pattern pattern = Pattern.compile("idInserirMaterialArquivo','id':'([0-9]+?)'");
        Matcher comparator = pattern.matcher(script);
        if (comparator.find(0)) {
            return comparator.group(1);
        } else {
            return null;
        }

    }
}
