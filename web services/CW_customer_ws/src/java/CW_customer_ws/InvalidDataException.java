/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW_customer_ws;

/**
 *
 * @author CHARITHA
 */
public class InvalidDataException extends Exception{
    
    private String info;
    
    public InvalidDataException(String reason, String info){
        super(reason);
        this.info = info;
    }
    
    public String showErrorInfo(){
        return info;
    }
}
