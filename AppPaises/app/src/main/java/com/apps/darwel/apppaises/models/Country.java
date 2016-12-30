package com.apps.darwel.apppaises.models;

/**
 * Es un bean que representa los datos basicos de un Pais
 * Created by Darwel on 30/12/2016.
 */
public class Country {
    private String name;
    private String codeIso;
    private String GMT;
    private String ISD;
    private Currency currency;

    public Country() {
    }

    public Country(String name, String codeIso, String GMT, String ISD, Currency currency) {
        this.name = name;
        this.codeIso = codeIso;
        this.GMT = GMT;
        this.ISD = ISD;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getISD() {
        return ISD;
    }

    public void setISD(String ISD) {
        this.ISD = ISD;
    }

    public String getGMT() {
        return GMT;
    }

    public void setGMT(String GMT) {
        this.GMT = GMT;
    }

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String codeIso) {
        this.codeIso = codeIso;
    }
}
