/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nttdata.test.test.service;

import com.nttdata.test.test.model.Client;
import java.util.Map;

/**
 *
 * @author jdteheran
 */
public interface IClientService {
    
    public Client getSpecificClient(String type, String id);
    
    public Boolean bigIntegerParse(String number);
    
    public Map<String, String> responseJSON(String message, String error);
}
