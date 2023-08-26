package com.ruanmiga.crab;

/**
 *
 * @author anton
 */
public enum HttpMethod {
    
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    PATH("PATH"),
    DELETE("DELETE");
    
    private final String name;
    
    private HttpMethod(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
}
