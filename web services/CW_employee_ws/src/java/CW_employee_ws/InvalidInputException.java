/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW_employee_ws;

/**
 *
 * @author CHARITHA
 */
class InvalidInputException extends Exception {
    
    private String info;
    
    public InvalidInputException(String reason, String info){
        super(reason);
        this.info = info;
    }
    
    public String showErrorInfo(){
        return info;
    }
    
}
