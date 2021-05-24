/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Models;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author JONATHANBERNAL
 */
@Setter
@Getter
public class MutantReport {
    private int count_mutant_dna;    
    private int count_human_dna;    
    private double ratio;
    
    public MutantReport(int count_mutant_dna,int count_human_dna){
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        if(count_human_dna == 0){
            this.ratio = 0;
        }else{
            this.ratio = (count_mutant_dna/(double)count_human_dna);
        }
        
    }
}
