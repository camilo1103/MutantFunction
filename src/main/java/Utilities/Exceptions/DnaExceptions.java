/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.Exceptions;

import java.net.URI;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author JONATHANBERNAL
 */
@Getter
@Setter
public class DnaExceptions extends Exception {

    /**
     * @param args the command line arguments
     */
   private static final long serialVersionUID = 1L;
	private String uri;
	private Date exceptionTime;
        private int statusCode;
        private String personalMessage;

	public DnaExceptions(String message, int statusCode) {
		super(message);
                this.personalMessage = message;
		this.uri = uri;
		this.exceptionTime = new Date();
                this.statusCode = statusCode;
	}






    
}
