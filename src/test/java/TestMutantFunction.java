

import Application.DnaValidateApplicationImpl;
import DistributedServices.Handlers.MutantHandler;
import Domain.DTO.DnaDB;
import Domain.DTO.DnaDBResponse;
import Domain.Models.DnaSecuence;
import Domain.Models.MutantReport;
import Domain.Models.Request;
import Infraestructure.Data.DnaDataHandler;
import Utilities.Exceptions.DnaExceptions;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import com.amazonaws.services.lambda.runtime.Context;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JONATHANBERNAL
 */
public class TestMutantFunction extends TestCase{
    private DnaValidateApplicationImpl dnaValidateApplicationImpl;
    private Request request;
    
    public void ConstDnaValidateApplicationImpl(){
        dnaValidateApplicationImpl = new DnaValidateApplicationImpl();
    }
    
    //TestDnaExceptions
    public void testModelDnaExceptions(){
        assertNotNull(new DnaExceptions("prueba",200));
    }
    
    //TestMutantReport
    public void testModelMutantReport(){
        assertNotNull(new MutantReport(1,0));
    }
    
    
    //TestDnaDataHandler
    public void testAddDNA() {
        try{            
           List<String> dna = new ArrayList();
           dna.add("AT");
           dna.add("CG");
           DnaDB dnaDB = new DnaDB();
           dnaDB.setDna(dna);
           dnaDB.setId(1234);
           dnaDB.setIsMutant(true);           
           assertTrue(DnaDataHandler.addDNA(dnaDB));
           dnaDB = null;
           assertTrue(!DnaDataHandler.addDNA(dnaDB));
        }catch(Exception ex){
             assert false;
        }
    }    
    public void testGetDnaSecuence(){
        try{          
                assertEquals(1234,DnaDataHandler.getDnaSecuence(1234).getId());
                    
        }catch(Exception ex){
             assertTrue(false);
        }   
      
    }    
    public void testGetReport(){
        try{          
                assertNotNull(DnaDataHandler.getReport());
                    
        }catch(Exception ex){
             assertTrue(false);
        } 
    }
    public void testModelDnaDB(){
        assertNotNull(new DnaDB(1234,null,true));
    }   
    
    public void testGetteSetterDnaDbReport(){
        DnaDB dnaDB = new DnaDB(1234,null,true);
        assertNotNull(dnaDB.getId());
        assertNotNull(dnaDB.getIsMutant());
        assertNull(dnaDB.getDna());
    }
    public void testModelDnaDBResponse(){
        assertNotNull(new DnaDBResponse(1234,null,1));
    }
    
    //TestDnaValidateApplication
    public void testValidateDna() throws DnaExceptions {
        ConstDnaValidateApplicationImpl();
        DnaSecuence dnaInput = new DnaSecuence();
        List<String> adn = new ArrayList<String>() {
            {
                add("ATGCG");
                add("CAGTG");
                add("TTATG");
                add("AGAAG");
                add("AGAAT");
            }
        };
        dnaInput.setDna(adn);
        assertTrue(dnaValidateApplicationImpl.validateDna(dnaInput));
        assertEquals(0,dnaValidateApplicationImpl.validatePositionMove(0, 0, 1, 1));
        dnaInput = new DnaSecuence();
        adn = new ArrayList<String>() {
            {
                add("ATGCG");
                add("CCGTG");
                add("TTATA");
                add("AGAAG");
                add("AGAAT");
            }
        };
        dnaInput.setDna(adn);
        ConstDnaValidateApplicationImpl();
        assertTrue(!dnaValidateApplicationImpl.validateDna(dnaInput));
       
        
        
        
    }
    public void testValidateNitrogenBase() throws DnaExceptions{
        try{
            ConstDnaValidateApplicationImpl();  
            DnaSecuence dnaInput = new DnaSecuence();
            List<String> adn = new ArrayList<String>() {
                {
                    add("ATGCG");
                    add("CAZTG");
                    add("TTATG");
                    add("AGAAG");
                    add("AGAAT");
                }
            };
            dnaInput.setDna(adn);
            dnaValidateApplicationImpl.validateNitrogenBase(dnaInput);
        }catch(Exception Ex){
            assert true;
        }
        
    }
    public void testValidateDnaIfNotExist() throws DnaExceptions{
        ConstDnaValidateApplicationImpl();  
            DnaSecuence dnaInput = new DnaSecuence();
            List<String> adn = new ArrayList<String>() {
                {
                    add("ATGCG");
                    add("CATTG");
                    add("TTATG");
                    add("AGAAG");
                    add("AGAAT");
                }
            };
            dnaInput.setDna(adn);
            int id = dnaInput.getDna().hashCode() & 0x7fffffff;
            assertTrue(dnaValidateApplicationImpl.validateDnaIfNotExist(dnaInput,id));
    }
    
    
    //TestRequest Model
    public void RequestModel(){
        request = new Request();
        request.setHttpMethod("POST");
        DnaSecuence dnaInput = new DnaSecuence();
            List<String> adn = new ArrayList<String>() {
                {
                    add("ATGCG");
                    add("CAZTG");
                    add("TTATG");
                    add("AGAAG");
                    add("AGAAT");
                }
            };
            dnaInput.setDna(adn);
        request.setDnaSecuence(dnaInput);
    }    
    public void testGetHttpMethod(){
        RequestModel();
        assertEquals("POST",request.getHttpMethod());
    }    
    public void testSetHttpMethod(String httpMethod){
        RequestModel();
        request.setHttpMethod("GET");
        if(request.getHttpMethod().equals("GET")){
            assert true;
        }else{
            assert false;
        }
    }    
    public void testGetDnaSecuenceModel(){
        RequestModel();
        assertNotNull(request.getDnaSecuence());
    }
    
    //TestModelDnaDbReport    
    public void testGetId(){
        DnaDBResponse dnaDB = new DnaDBResponse(1234,null,1);
        assertEquals(1234,dnaDB.getId());
    }    
    public DnaDBResponse SetDnaDBRespose(){
        DnaDBResponse dnaDB = new DnaDBResponse();
        List<String> adn = new ArrayList<String>() {
                {
                    add("ATGCG");
                    add("CATTG");
                    add("TTATG");
                    add("AGAAG");
                    add("AGAAT");
                }
            };
        dnaDB.setId(1234);
        dnaDB.setIsMutant(1);
        dnaDB.setDna(adn);
        return dnaDB;
    }    
     public void testGetDna(){
        DnaDBResponse dnaDB = new DnaDBResponse(1234,null,1);
        assertNull(dnaDB.getDna());
    }    
    public void testSetDnaDBRespose(){
        assertNotNull(SetDnaDBRespose());
    }    
     public void testGetIsMutant(){
        DnaDBResponse dnaDB = new DnaDBResponse(1234,null,1);
        assertEquals(1,dnaDB.getIsMutant());
    }
    
     
     //TestMutantHandler
     
     public void testMutantHandler() throws DnaExceptions{
         Request request = new Request();
         request.setHttpMethod("GET");
         assertNotNull(MutantHandler.validateIsMutant(request));
         request.setHttpMethod("POST");
         List<String> adn = new ArrayList<String>() {
                {
                    add("ATGCG");
                    add("CATTG");
                    add("TTATG");
                    add("AGAAG");
                    add("AGAAT");
                }
            };
         DnaSecuence dnaSecuence = new DnaSecuence();
         dnaSecuence.setDna(adn);
         request.setDnaSecuence(dnaSecuence);
         assertNotNull(MutantHandler.validateIsMutant(request));
     }
    
    
     
   
    
    
}
