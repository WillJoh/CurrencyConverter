/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currency_converter.view;

import com.mycompany.currency_converter.controller.ConverterFacade;
import com.sun.javafx.css.parser.StopConverter;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("converterMngr")
@ConversationScoped
public class ConverterMngr implements Serializable{
    @EJB
    private ConverterFacade converterFacade;
    private Exception failure;
    private List<String> currencies;
    private String from;
    private String to;
    private double rate;
    private double conversionRate;
    private double amount;
    @Inject
    private Conversation conversation;
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo (String to) {
        this.to = to;
    }
    
    public double getRate() {
        return rate;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    private void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    } 
    
    private void endConversation() {
        if(!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private void handleException(Exception e) {
        endConversation();
        failure = e;
    }
    
    private boolean getSuccess() {
        return failure == null;
    }
    
    public List<String> getCurrencies() {
        try {
            initConversation();
            currencies = converterFacade.getCurrencies();
        } catch (Exception e) {
            handleException(e);
        }
        return currencies;
    }
    
    public void getConversionRate() {
        try {
            initConversation();
            rate = converterFacade.getConversionRate(from, to);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
