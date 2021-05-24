/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Models;

/**
 *
 * @author JONATHANBERNAL
 */
public class Request {
    
    private String httpMethod;
    private DnaSecuence dnaSecuence;
    
    public String getHttpMethod(){
        return httpMethod;
    }
    
    public void setHttpMethod(String httpMethod){
        this.httpMethod = httpMethod;
    }
    
    public DnaSecuence getDnaSecuence(){
        return dnaSecuence;
    }
    
    public void setDnaSecuence(DnaSecuence dnaSecuence){
        this.dnaSecuence = dnaSecuence;
    }
    
    
    
}
