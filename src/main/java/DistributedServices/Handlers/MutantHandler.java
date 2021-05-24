/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistributedServices.Handlers;

/**
 *
 * @author JONATHANBERNAL
 */
import Application.DnaValidateApplicationImpl;
import Domain.Models.*;
import Utilities.Exceptions.DnaExceptions;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutantHandler {

    static final Logger log = LoggerFactory.getLogger(MutantHandler.class);

    public static Object isMutant(Request request, Context context) throws ResourceNotFoundException, DnaExceptions {
         return validateIsMutant(request);  
    }

    public static Object validateIsMutant(Request request) throws DnaExceptions {
        DnaValidateApplicationImpl dnaValidateApplicationImpl = new DnaValidateApplicationImpl();       
      
        switch (request.getHttpMethod()) {
            case "POST":
                if (dnaValidateApplicationImpl.validateDna(request.getDnaSecuence())) {
                    return "OK";
                } else {
                    throw new DnaExceptions("Forbidden", 403);
                }     
                
            case "GET":
                    return dnaValidateApplicationImpl.getReport();
            default:
                    throw new DnaExceptions("Method Not Allowed", 406);
        }
    }
    
    
    

}
