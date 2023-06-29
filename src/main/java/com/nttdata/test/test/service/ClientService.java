/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.test.test.service;

import com.nttdata.test.test.model.Client;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author jdteheran
 */
@Component
@Primary
public class ClientService implements IClientService {
    
    @Value("${type.cedula}")
    String typeCedula;
    
    @Value("${cedula.id}")
    String id;
    
    @Value("${response.message}")
    String message;
    
    @Value("${response.error}")
    String error;

    @Override
    public Client getSpecificClient(String type, String id) {

        if (!type.equalsIgnoreCase(typeCedula)) {
            return null;
        }

        if (!id.equals(this.id)) {
            return null;
        }

        Client client = new Client();

        client.setFirstName("Juan");
        client.setSecondName("Diego");
        client.setFirstSurName("Teheran");
        client.setSecondSurName("Olmos");
        client.setTelphone(new BigInteger("3215447184"));
        client.setAddress("NTT DATA");
        client.setCityResidence("Barranquilla");

        return client;
    }

    @Override
    public Boolean bigIntegerParse(String number) {
        try {

            BigInteger bigInt = new BigInteger(number);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, String> responseJSON(String message, String error) {
        Map map = new HashMap<String, String>();
        
        map.put(this.message, message);
        map.put(this.error, error);
        
        return map;
    }

}
