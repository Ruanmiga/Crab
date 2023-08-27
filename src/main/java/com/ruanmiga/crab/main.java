package com.ruanmiga.crab;

/**
 *
 * @author anton
 */
public class main {

public static void main(String... args){

Crab crab = new Crab("https://google.com/code foo");

    System.out.println(crab.getUrl().getEncodeUrl());
    System.out.println(crab.getUrl().getPort());
    System.out.println(crab.getUrl().getHost());
}  

}
