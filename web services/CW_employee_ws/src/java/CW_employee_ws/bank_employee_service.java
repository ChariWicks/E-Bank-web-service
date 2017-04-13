/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW_employee_ws;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
/**
 *
 * @author CHARITHA
 */
@WebService(serviceName = "bank_employee_service")
public class bank_employee_service {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
        /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "helloQ")
    public String helloQ(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "create")
    public boolean create(@WebParam(name = "name") String name, @WebParam(name = "position") String position, @WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "repassword") String repassword) {
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("insert into employee values(?,?,?,?,?)");//insert data
            state.setString(1, name);
            state.setString(2,position);
            state.setString(3, username);
            state.setString(4,password);
            state.setString(5,repassword);
            state.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
        
       // return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "delete")
    public boolean delete(@WebParam(name = "id") String id, @WebParam(name = "password") String password) {
        
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("delete from employee where id=? AND password=?"); //delete specific row
            state.setString(1, id);
            state.setString(2,password);
            state.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
        //return false;
    }
    
    
     /**
     * Web service operation
     */
    @WebMethod(operationName="display_delete_employee_details")
    public String[][] display_delete_employee_details(@WebParam(name = "id") String id, @WebParam(name = "password") String password){
         String [][]delEmpList = null;
        try {
            //try{
            Statement state = DatabaseConnectionTest.con.createStatement();//retrieves data
            String sql = "SELECT id, name, position, username FROM employee WHERE id =? AND password =?";
            ResultSet rs1 = state.executeQuery(sql);
            
            //ResultSet rs = state.executeQuery("SELECT * FROM sample");
            ResultSet rs = state.executeQuery("SELECT COUNT(*) FROM employee");
            rs.next();
            
            int rowCount = rs.getInt(1);
            delEmpList = new String[rowCount][4];
            
            while(rs.next()){
                for(int i=0; i<rowCount;i++){
                    for(int j=0; j<1;j++){
                        delEmpList[i][j] = rs1.getString(1);
                        delEmpList[i][j+1] = rs1.getString(2);
                        delEmpList[i][j+2] = rs1.getString(3);
                        delEmpList[i][j+3] = rs1.getString(4);
                    }       
                } 
            }
    
            return delEmpList;
            //}catch(Exception e){
            //  return e;
            //}
        } catch (SQLException ex) {
            Logger.getLogger(bank_employee_service.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //return delEmpList;
        
        
    }
    
    
        /**
     * Web service operation
     */
    @WebMethod(operationName = "edit")
    public boolean edit(@WebParam(name = "id") String id, @WebParam(name = "name") String name,@WebParam(name = "position") String position, @WebParam(name = "username") String username,@WebParam(name = "password") String password, @WebParam(name = "repassword") String repassword) {
        
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("UPDATE emplolyee set name=?, position=?, username=?, password=?, repassword=? where id=?"); //delete specific row
            state.setString(1, name);
            state.setString(2, position);
            state.setString(3, username); 
            state.setString(4,password);
            state.setString(5,repassword);
            state.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
        //return false;
    }
    
    
     /**
     * Web service operation
     */
    @WebMethod(operationName="display_edit_employee_details")
    public String[][] display_edit_employee_details(@WebParam(name = "id") String id, @WebParam(name = "password") String password){
         String [][]ediEmpList = null;
        try {
            //try{
            Statement state = DatabaseConnectionTest.con.createStatement();//retrieves data
            String sql = "SELECT id, name, position, username, password, repassword FROM customer WHERE id =? AND password =?";
            ResultSet rs1 = state.executeQuery(sql);
            
            //ResultSet rs = state.executeQuery("SELECT * FROM sample");
            ResultSet rs = state.executeQuery("SELECT COUNT(*) FROM employee");
            rs.next();
            
            int rowCount = rs.getInt(1);
            ediEmpList = new String[rowCount][6];
            
            while(rs.next()){
                for(int i=0; i<rowCount;i++){
                    for(int j=0; j<1;j++){
                        ediEmpList[i][j] = rs1.getString(1);
                        ediEmpList[i][j+1] = rs1.getString(2);
                        ediEmpList[i][j+2] = rs1.getString(3);
                        ediEmpList[i][j+3] = rs1.getString(4);
                        ediEmpList[i][j+4] = rs1.getString(5);
                        ediEmpList[i][j+5] = rs1.getString(6);
                    }       
                } 
            }
    
            return ediEmpList;
            //}catch(Exception e){
            //  return e;
            //}
        } catch (SQLException ex) {
            Logger.getLogger(bank_employee_service.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //return delEmpList;
        
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws InvalidInputException{
        
        try{
            PreparedStatement pst = DatabaseConnectionTest.con.prepareStatement("Select * from employee where username=? and password=?");
            pst.setString(1, username);
            //pst.setString(2, password);
            ResultSet rs = pst.executeQuery(); 
            
            if(rs.next()){
                return true;
            }
            else
                throw new InvalidInputException("Wrong input credentials!", username + "or" + password + "is incorrect");
               // return false;
        }catch(Exception e){
            return false;
        }
        

        //return false;
    }



}
