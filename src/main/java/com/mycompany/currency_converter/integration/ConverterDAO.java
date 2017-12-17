/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currency_converter.integration;

import com.mycompany.currency_converter.model.ConversionRate;
import com.mycompany.currency_converter.model.Currency;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;


@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ConverterDAO {
    @PersistenceContext(unitName = "converterPU")
    private EntityManager entityMngr;

    public List<Currency> findAllCurrencies() {
        List<Currency> currencies = entityMngr.createNamedQuery("findAllCurrencies", Currency.class).getResultList();
        if (currencies == null) {
            throw new EntityNotFoundException("No currencies found");
        }
        return currencies;
    }
    
    public void storeCurrency(Currency currency) {
        entityMngr.persist(currency);
    }
    
    public ConversionRate findConversionRate(String from, String to) {
        ConversionRate rate = entityMngr.createNamedQuery("findConversionRate", ConversionRate.class).setParameter("from", from).setParameter("to", to).getSingleResult();
        if (rate == null) {
            throw new EntityNotFoundException("No such rate found for: " + from + ", " + to);
        }
        return rate;
    }
    
    public void storeConversionRate(ConversionRate rate) {
        entityMngr.persist(rate);
    }
}