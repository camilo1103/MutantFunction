/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestructure.Data;

import Domain.DTO.DnaDB;
import Domain.DTO.DnaDBResponse;
import Domain.Models.MutantReport;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author JONATHANBERNAL
 */
public class DnaDataHandler {

    private static final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    private static final DynamoDBMapper dynamoDB= new DynamoDBMapper(client);
    private static final DynamoDB dynamoDB2 = new DynamoDB(client);

    public static DnaDB getDnaSecuence(int Key) {
        DnaDB response = dynamoDB.load(DnaDB.class, Key);
        return response;
    }

    public static boolean addDNA(DnaDB dnaDB) {
        try{
            dynamoDB.save(dnaDB);
            return true;
        }catch(Exception ex){
            return false;
        }
        
    }
    
   
    public static MutantReport getReport() {
        Table table = dynamoDB2.getTable("TABLE_DNA");
        int count_mutant_dna = 0;
        int count_human_dna = 0;
        Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
        expressionAttributeValues.put(":isM", 1);
        expressionAttributeValues.put(":isM2", 0);
        ItemCollection<ScanOutcome> items = table.scan("isMutant = :isM or isMutant = :isM2", 
                "id, dna, isMutant", 
                null, 
                expressionAttributeValues);
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            DnaDBResponse dnaresp = new Gson().fromJson(iterator.next().toJSONPretty(), DnaDBResponse.class);
            if(dnaresp.getIsMutant() == 1){
                count_mutant_dna++; 
            }else {
                count_human_dna++;
            }
        }
        MutantReport mutantReport = new MutantReport(
                count_mutant_dna,
                count_human_dna
        );
        return mutantReport;
    }

}
