/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currency_converter.controller;

import com.mycompany.currency_converter.integration.ConverterDAO;
import com.mycompany.currency_converter.model.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ConverterFacade {
  @EJB ConverterDAO converterDB;
  
  public List<String> getCurrencies() {
      List<String> currencies = new ArrayList<>();
      for (Currency curr : converterDB.findAllCurrencies()) {
          currencies.add(curr.getName());
      }
      
      return currencies;
  }
  
  public double getConversionRate(String from,String to) {
      return converterDB.findConversionRate(from, to).getConversionRate();
  }
}
