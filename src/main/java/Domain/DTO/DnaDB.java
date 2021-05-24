/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.DTO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.List;

/**
 *
 * @author JONATHANBERNAL
 */

@DynamoDBTable(tableName = "TABLE_DNA")
public class DnaDB {
    @DynamoDBHashKey
    private int id;
    @DynamoDBAttribute
    private List<String> dna;
    @DynamoDBAttribute
    private boolean isMutant;
    
    public DnaDB(){}
    
    public DnaDB(int id,List<String> dna,boolean isMutant){
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
    
     public boolean getIsMutant(){
        return isMutant;
    }
    
    public void setIsMutant(boolean isMutant){
        this.isMutant = isMutant;
    }
}
