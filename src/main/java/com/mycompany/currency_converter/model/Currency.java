/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currency_converter.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(
            name = "findAllCurrencies",
            query = "SELECT curr FROM Currency curr"
    )
})

@Entity
public class Currency implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    
    public Currency(){
        
    }
    
    public Currency(String name) {
        this.name = name;                
    }
    
    public String getName() {
        return name;
    }
}
