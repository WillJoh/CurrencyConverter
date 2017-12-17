/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currency_converter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(
            name = "findConversionRate",
            query = "SELECT rate FROM ConversionRate rate WHERE rate.fromCurrency LIKE :from AND rate.toCurrency LIKE :to"
    )
})

@Entity
public class ConversionRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fromCurrency;
    private String toCurrency;
    private double conversionRate;
    
    public ConversionRate() {
        
    }
    
    public ConversionRate(String fromCurrency, String toCurrency, double coversionRate) {
        this.fromCurrency = fromCurrency;   
        this.toCurrency = toCurrency;
        this.conversionRate = coversionRate;
    }
    
    public String getFromCurrency() {
        return fromCurrency;
    }
    
    public String getToCurrency() {
        return toCurrency;
    }
    
    public double getConversionRate() {
        return conversionRate;
    }
}
