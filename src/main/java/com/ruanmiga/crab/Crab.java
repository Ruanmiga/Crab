/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ruanmiga.crab;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class Crab {

    public static void main(String[] args) {
        System.out.println("Hola mundo!");
        
        List<String> lista = new ArrayList<>();
        lista.add("Mito");
        lista.add("MitoCode");
        lista.add("Code");
        
        lista.sort((a, b) -> a.compareTo(b));
        
        for(String element : lista){
            System.out.println(element);
        }
    }
}
