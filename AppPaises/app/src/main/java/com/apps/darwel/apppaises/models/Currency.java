package com.apps.darwel.apppaises.models;

/**
 * Es un bean que representa un la moneda de un Pais
 * Created by Darwel on 30/12/2016.
 */
public class Currency {
    private String name;
    private String code;

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Currency() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
