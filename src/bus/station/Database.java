/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus.station;

import java.sql.*;

/**
 *
 * @author user
 */
public class Database {
    
    private Connection connect(){
        String ur1="jdbc:sqlite:src/BusStation.db";
        Connection conn=null;
        try{
            conn=DriverManager.getConnection(ur1);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void insertCustomer(String username,String password, String name, int trips, double balance){
        String sq1="INSERT INTO customers(username,name,password,trips,balance) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sq1)) {
            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setInt(4, trips);
            pstmt.setFloat(5, (float) balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertVehicle(String vehID, String model, String type, int multiplier){
        String sq1="INSERT INTO vehicles(vehID,model,type,multiplier) VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sq1)) {
            pstmt.setString(1, vehID);
            pstmt.setString(2, model);
            pstmt.setString(3, type);
            pstmt.setInt(4, multiplier);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public void insertEmployee(String name,double salary, int type,String username, String password){
        String sq1="INSERT INTO employees(name,salary,type,username,password) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sq1)) {
            pstmt.setString(2, name);
            pstmt.setFloat(3, (float)salary);
            pstmt.setInt(4, type);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
        
    public void insertTrip(String date,String driver, String Destination, double price ){
        String tripID;
        String sq1="INSERT INTO trips(date,driver,Destination,price) VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sq1)) {
            pstmt.setString(2, date);
            pstmt.setString(3, driver);
            pstmt.setString(4, Destination);
            pstmt.setFloat(5, (float)price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectCustomers(){
        String sql = "SELECT username, name, password, trips, balance FROM customers";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getString("username") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getString("password")+ "\t" + rs.getInt("trips")+ "\t" + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectEmployees(){
        String sql = "SELECT empID, name, salary, type FROM employees";
        
        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
           
            while (rs.next()) {
                System.out.println(rs.getInt("empID") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getInt("type") + "\t" +
                                   rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selecttrips(){
        String sql = "SELECT tripID, date, driver, Destination, price FROM trips";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("tripID") +  "\t" + 
                                   rs.getString("date") +  "\t" + 
                                   rs.getString("driver") +  "\t" + 
                                   rs.getString("Destination") + "\t" +
                                   rs.getDouble("price"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectVehicles(){
        String sql = "SELECT vehID, model, type, multiplier FROM vehicles";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("vehID") +  "\t" + 
                                   rs.getString("model") + "\t" +
                                   rs.getString("type") + "\t" +
                                   rs.getInt("multiplier"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public int searchCredentialsCustomers(String username1,String password1){
       String sql = "SELECT username, password FROM customers";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            
            while (rs.next()) {
                if(rs.getString("username").equals(username1) && rs.getString("password").equals(password1)){
                    return 1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 0;
       }
    
    public int searchEmployees(String username, String password, int type)
    {
        String sql = "SELECT username, password, type FROM employees";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                if(type == 1)
                {                 if(rs.getString("username").equals(username) && rs.getString("password").equals(password) && rs.getInt("type")==1)
                                   {
                                       return 1;
                                   }
                }
                else if(type == 2)
                {                 if(rs.getString("username").equals(username) && rs.getString("password").equals(password) && rs.getInt("type")==2)
                                   {
                                       return 2;
                                   }                    
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
}
