/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.customerBean;
import beans.employeeBean;
import beans.itemBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conor
 */
public class employeeLoginServlet extends HttpServlet {

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
        String query;
        String passwordCheck = new String();
        //java.sql.Date date;
        //java.util.Date sqlDate;
        try {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            } 
        catch (ClassNotFoundException ex) {
             Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        Connection co = null;
        try {
            co = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "password");
            } 
            catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        Statement stat = co.createStatement();
        
        query = "SELECT email, password FROM employee WHERE (email = '" + customerEmail + "' AND password = '" + password + "');";
        try {
                ResultSet result = stat.executeQuery(query) ;
                while (result.next()) {
                    passwordCheck = result.getString("password");
                }    
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (passwordCheck != null && passwordCheck.equals(password)) {
            employeeBean employee = new employeeBean();
            String dataQuery = "SELECT * FROM employee WHERE (email = '" + customerEmail + "' AND password = '" + password + "');";
            try {
                    ResultSet result = stat.executeQuery(dataQuery) ;
                    while (result.next()) {
                        employee.setId(result.getInt("employeeID"));
                        employee.setEmail(customerEmail);
                        employee.setPassword(password);
                        employee.setFirstName(result.getString("firstName"));
                        employee.setLastName(result.getString("lastName"));
                        employee.setPhoneNumber(result.getString("phoneNum"));
                        employee.setDateHired(result.getString("dateHired"));
                        employee.setRole(result.getString("role"));
                        employee.setSupId(result.getInt("supervisorID"));
                        
                    }
                
                   //System.out.println("yo");
                   //System.out.println(customer.getTown());
                    session.setAttribute("employee", employee);
                    
                } 
            catch (SQLException ex) {
                    Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        else{
            
        }
        }
        catch (SQLException ex){
            Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher jsp = request.getRequestDispatcher("employeeLoggedIn.jsp");
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
