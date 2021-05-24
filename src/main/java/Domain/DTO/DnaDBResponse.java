/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.DTO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import java.util.List;

/**
 *
 * @author JONATHANBERNAL
 */

public class DnaDBResponse {
    @DynamoDBHashKey
    private int id;
    @DynamoDBAttribute
    private List<String> dna;
    @DynamoDBAttribute
    private int isMutant;
    
    public DnaDBResponse(){}
    
    public DnaDBResponse(int id,List<String> dna,int isMutant){
        this.id = id;
        this.dna = dna;
        this.isMutant = isMutant;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
     public List<String> getDna(){
        return dna;
    }
    
    public void setDna(List<String> dna){
        this.dna = dna;
    }
    
     public int getIsMutant(){
        return isMutant;
    }
    
    public void setIsMutant(int isMutant){
        this.isMutant = isMutant;
    }
}
