/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW_customer_ws;

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
@WebService(serviceName = "bank_customer_service")
public class bank_customer_service {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "create_customer")
    public boolean create_customer(@WebParam(name = "name") String name, @WebParam(name = "birthdate") String birthdate, @WebParam(name = "address") String address, @WebParam(name = "mobile") String mobile, @WebParam(name = "eMail") String eMail) {
        
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("insert into customer values(?,?,?,?,?)");//insert data
            state.setString(1, name);
            state.setString(2,birthdate);
            state.setString(3, address);
            state.setString(4,mobile);
            state.setString(5,eMail);

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
    @WebMethod(operationName = "create_account")
    public boolean create_account(@WebParam(name = "accType") String accType, @WebParam(name = "accNum") String accNum, @WebParam(name = "sortCode") String sortCode, @WebParam(name = "balance") String balance, @WebParam(name = "card") String card) {
        
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("insert into accounts values(?,?,?,?,?)");//insert data
            state.setString(1, accType);
            state.setString(2,accNum);
            state.setString(3, sortCode);
            state.setString(4,balance);
            state.setString(5,card);

            state.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
        //return false;
    }
    
    @WebMethod(operationName="display_cus_list")
    public String[][] display_cus_list(){
         String [][]cList = null;
        try {
            //try{
            Statement state = DatabaseConnectionTest.con.createStatement();//retrieves data
            String sql = "SELECT name FROM customer";
            ResultSet rs1 = state.executeQuery(sql);
            
            String sql2 = "SELECT accNum FROM accounts";
            ResultSet rs2 = state.executeQuery(sql);
            
            //ResultSet rs = state.executeQuery("SELECT * FROM sample");
            ResultSet rs = state.executeQuery("SELECT COUNT(*) FROM customer");
            rs.next();
            
            int rowCount = rs.getInt(1);
            cList = new String[rowCount][2];
            
            while(rs.next()){
                for(int i=0; i<rowCount;i++){
                    for(int j=0; j<1;j++){
                        cList[i][j] = rs1.getString(1);
                        cList[i][j+1] = rs2.getString(1);
                    }
                        
                }
                
            }
    
            return cList;
            //}catch(Exception e){
            //  return e;
            //}
        } catch (SQLException ex) {
            Logger.getLogger(bank_customer_service.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //return cList;
        
        
    }
    
     
    @WebMethod(operationName = "edit")
    public boolean edit(@WebParam(name = "cusId") String cusId, @WebParam(name = "cusAcc") String cusAcc,@WebParam(name = "name") String name, @WebParam(name = "birthday") String birthday,@WebParam(name = "address") String address, @WebParam(name = "mobile") String mobile, @WebParam(name = "eMail") String eMail) {
        
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("UPDATE customer set name=?, birthday=?, address=?, mobile=?, eMail=? where cusId=?"); //edit specific row
            PreparedStatement state1 = DatabaseConnectionTest.con.prepareStatement("UPDATE sample set cusAcc=? where cusId=?");
            //state.setString(1, cusId);
            //state.setString(1,cusAcc);
            //state.setString(1,name);
            //state.setString(2,birthday);
            //state.setString(3,address);
            //state.setString(4,mobile);
            //state.setString(5,eMail);
            //state1.setString(1, cusAcc);
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
    @WebMethod(operationName = "dis_edit_cus")
    public String[][] dis_edit_cus(@WebParam(name = "cusAcc") String cusAcc) throws InvalidDataException {
        
        String [][]ediCusList = null;
        
        try {
            //try{
            Statement state = DatabaseConnectionTest.con.createStatement();//retrieves data
            String sql = "SELECT cusId, name, birthday, address, mobile, eMail FROM customer WHERE cusId =?";
            String sql1 = "SELECT cusAcc FROM accounts WHERE cusId =?";
            
            ResultSet rs1 = state.executeQuery(sql);
            ResultSet rs2 = state.executeQuery(sql1);
            
            if(rs1.next() && rs2.next()){
                
                            //String sqlQ = rs1.getString(1);
            
            //ResultSet rs = state.executeQuery("SELECT * FROM sample");
            ResultSet rs = state.executeQuery("SELECT COUNT(*) FROM customer");
            rs.next();
            
            int rowCount = rs.getInt(1);
            ediCusList = new String[rowCount][7];
            
            while(rs.next()){
                for(int i=0; i<rowCount;i++){
                    for(int j=0; j<1;j++){
                        ediCusList[i][j] = rs1.getString(1);
                        ediCusList[i][j+1] = rs2.getString(1);
                        ediCusList[i][j+2] = rs1.getString(2);
                        ediCusList[i][j+3] = rs1.getString(3);
                        ediCusList[i][j+4] = rs1.getString(4);
                        ediCusList[i][j+5] = rs1.getString(5);
                        ediCusList[i][j+6] = rs1.getString(6);
                    }       
                } 
            }
    
            return ediCusList;
            //}catch(Exception e){
            //  return e;
            //}

                
            }
            else
                throw new InvalidDataException("Wrong input data!", cusAcc  + "is incorrect");
               // return false;
            
            } catch (SQLException ex) {
            Logger.getLogger(bank_customer_service.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
        
    
    
     @WebMethod(operationName = "delete")
    public boolean delete(@WebParam(name = "cusId") String cusId, @WebParam(name = "cusAcc") String cusAcc,@WebParam(name = "name") String name, @WebParam(name = "birthday") String birthday,@WebParam(name = "address") String address, @WebParam(name = "mobile") String mobile, @WebParam(name = "eMail") String eMail) {
        
        try{
            PreparedStatement state = DatabaseConnectionTest.con.prepareStatement("DELETE from customer where cusId=?"); //delete specific row
            PreparedStatement state2 = DatabaseConnectionTest.con.prepareStatement("DELETE from accounts where cusId=?"); //delete specific row
            //state.setString(1, cusId);
            //state.setString(2,cusAcc);
            //state.setString(3,name);
            //state.setString(4,birthday);
            //state.setString(5,address);
            //state.setString(6,mobile);
            //state.setString(7,eMail);
            //state.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
        //return false;
    }
    
    


    /**
     * Web service operation
     */
    @WebMethod(operationName = "dis_del_cus")
    public String[][] dis_del_cus(@WebParam(name = "cusAcc") String cusAcc) throws InvalidDataException {
        
        String [][]ediCusList = null;
        
        try {
            //try{
            Statement state = DatabaseConnectionTest.con.createStatement();//retrieves data
            String sql = "SELECT cusId, name, birthday, address, mobile, eMail FROM customer WHERE cusId =?";
            String sql1 = "SELECT cusAcc FROM accounts WHERE cusId =?";
            
            ResultSet rs1 = state.executeQuery(sql);
            ResultSet rs2 = state.executeQuery(sql1);
            
            if(rs1.next() && rs2.next()){
                
                            //String sqlQ = rs1.getString(1);
            
            //ResultSet rs = state.executeQuery("SELECT * FROM sample");
            ResultSet rs = state.executeQuery("SELECT COUNT(*) FROM accounts");
            rs.next();
            
            int rowCount = rs.getInt(1);
            ediCusList = new String[rowCount][7];
            
            while(rs.next()){
                for(int i=0; i<rowCount;i++){
                    for(int j=0; j<1;j++){
                        ediCusList[i][j] = rs1.getString(1);
                        ediCusList[i][j+1] = rs2.getString(1);
                        ediCusList[i][j+2] = rs1.getString(2);
                        ediCusList[i][j+3] = rs1.getString(3);
                        ediCusList[i][j+4] = rs1.getString(4);
                        ediCusList[i][j+5] = rs1.getString(5);
                        ediCusList[i][j+6] = rs1.getString(6);
                    }       
                } 
            }
    
            return ediCusList;
            //}catch(Exception e){
            //  return e;
            //}

                
            }
            else
                throw new InvalidDataException("Wrong input data!", cusAcc  + "is incorrect");
               // return false;
            
            } catch (SQLException ex) {
            Logger.getLogger(bank_customer_service.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }



    
    

}
