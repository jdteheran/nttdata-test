/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.test.test.controller;

import com.nttdata.test.test.model.Client;
import com.nttdata.test.test.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jdteheran
 */
@RestController
@RequestMapping("/api")
public class ClientController {
    
    @Value("${type.cedula}")
    String typeCedula;
    
    @Value("${type.passport}")
    String typePassport;
    
    @Value("${bad.request.message}")
    String badRequestMessage;
    
    @Value("${bad.request.error}")
    String badRequestError;
    
    @Value("${internal.server.message}")
    String internalServerMessage;
    
    @Value("${internal.server.error}")
    String internalServerError;
    
    @Value("${not.found.message}")
    String notFoundMessage;
    
    @Value("${not.found.error}")
    String notFoundError;
    

    @Autowired
    private IClientService clientService;

    @GetMapping("/getClient")
    public ResponseEntity<Object> index(@RequestParam(defaultValue = "", required = true) String type, @RequestParam(defaultValue = "", required = true) String id) {

        if (type.isEmpty() || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(clientService.responseJSON(badRequestMessage, badRequestError));
        }

        if ((!type.equalsIgnoreCase(typeCedula) && !type.equalsIgnoreCase(typePassport)) || !clientService.bigIntegerParse(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(clientService.responseJSON(internalServerMessage, internalServerError));
        }

        Client client = clientService.getSpecificClient(type, id);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(clientService.responseJSON(notFoundMessage, notFoundError));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(client);
    }

}
