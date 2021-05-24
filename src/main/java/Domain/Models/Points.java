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
@Getter
@Setter
public class Points {
    int x;
    int y;
    
    public Points(int x, int y){
     this.x = x;
     this.y = y;
            
    }
}
