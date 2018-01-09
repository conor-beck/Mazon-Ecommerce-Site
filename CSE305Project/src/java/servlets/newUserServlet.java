/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import beans.customerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conor
 */
public class newUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String customerEmail = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNum = request.getParameter("phoneNum");
        String address = request.getParameter("address");
        String zip = request.getParameter("zipCode");
        String town = request.getParameter("town");
        String emailCheck = null;
       
        int customerID = 0; 
        String query;
        try {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            } 
        catch (ClassNotFoundException ex) {
             Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        Connection co = null;
        try {
            co = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "password");
            } 
            catch (SQLException ex) {
                Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        Statement stat = co.createStatement();
        
        
        
        query = "SELECT * FROM customer WHERE(email = '" + customerEmail + "');";

        try{
            ResultSet result = stat.executeQuery(query);
            while (result.next()) {
                emailCheck = result.getString("password");
            }    
        }
        catch (SQLException ex) {
            Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(emailCheck == null){
            query = "SELECT MAX(customerID) as maxId FROM customer";
            try {
                ResultSet result = stat.executeQuery(query);
                while (result.next()) {
                    customerID = result.getInt("maxId") + 1;
                }
            }
            catch (SQLException ex) {
                 Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //INSERT INTO SQL
            query = "INSERT INTO customer(customerID, firstName, lastName, phoneNum, password, zipCode, address, email, town) VALUES('" + customerID + "', '" + firstName + "', '" + lastName + "', '" + phoneNum + "', '" + password + "', '" + zip + "', '" + address + "', '" + customerEmail  + "', '" + town + "');";
            try {
                stat.executeUpdate(query);
            }
            catch (SQLException ex) {
                 Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //create BEAN
            customerBean customer = new customerBean();
            String dataQuery = "SELECT * FROM customer WHERE (email = '" + customerEmail + "' AND password = '" + password + "');";
            try {
                    ResultSet result = stat.executeQuery(dataQuery) ;
                    while (result.next()) {
                        customer.setId(result.getInt("customerID"));
                        customer.setEmail(customerEmail);
                        customer.setPassword(password);
                        customer.setFirstName(result.getString("firstName"));
                        customer.setLastName(result.getString("lastName"));
                        customer.setPhoneNumber(result.getString("phoneNum"));
                        customer.setZipCode(result.getInt("zipCode"));
                        customer.setAddress(result.getString("address"));
                        customer.setTown(result.getString("town"));
                        
                    }
                
                   //System.out.println("yo");
                   //System.out.println(customer.getTown());
                    session.setAttribute("customer", customer);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
        }
        
        

        
        }
        catch (SQLException ex){
            Logger.getLogger(newUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher jsp = request.getRequestDispatcher("loggedIn.jsp");
        jsp.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
