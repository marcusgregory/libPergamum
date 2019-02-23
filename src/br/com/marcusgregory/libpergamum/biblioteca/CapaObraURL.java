/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcusgregory.libpergamum.biblioteca;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gregory
 */
public class CapaObraURL {

    private static Map<String, String> CapaObraURL = new HashMap<>();

    ;
 
    public static String getCapaByCodAcervo(String CodAcervo) {
        if (CapaObraURL.isEmpty()) {
            Gson gson = new GsonBuilder().create();
            Reader reader = new InputStreamReader(CapaObraURL.class.getResourceAsStream("/br/com/libpergamum/resources/CapaObraURL.json"));
            CapaObraURL = gson.fromJson(reader, CapaObraURL.getClass());
        }
        return CapaObraURL.get(CodAcervo);
    }

}
