/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.customerBean;
import beans.itemBean;
import beans.sellerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class checkOutServlet extends HttpServlet {

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
        
        String query;
        int id = 0;
        String fee = "2.99";
        String address = request.getParameter("address");
        String cNumber = request.getParameter("cNumber");
        String expiration = request.getParameter("expiration");
        customerBean customer = new customerBean();
        customer = (customerBean)session.getAttribute("customer");
        ArrayList<itemBean> items = (ArrayList<itemBean>)session.getAttribute("cart");
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();
        String date = format.format(d);
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
        
        query = "SELECT MAX(purchaseID) as ma FROM purchases";
        try {
                ResultSet result = stat.executeQuery(query) ;
                while (result.next()) {
                    id = result.getInt("ma");
                   
                }  
        }
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        id++;
        double price = 0;
        int times;
        for(int i = 0; i < items.size(); i++){
            itemBean be = items.get(i);
            times = be.getQuantity();
            price = price + (be.getPrice() * times);
        }
        String pri = Double.toString(price);
        for(int i = 0; i < items.size(); i++){
            itemBean ite = items.get(i);
            query = "INSERT INTO purchases(purchaseID, customerID, address, email, cName, itemID, cardNumber, cardExpirationDate, datePur, cost, shipmentFee) VALUES('" +
                    Integer.toString(id) + "', '"+Integer.toString(customer.getId())+"', '"+address+"', '"+customer.getEmail()+"', '"+customer.getFirstName() +" "+customer.getLastName()+ "', '"+
                    Integer.toString(ite.getId())+ "', '"+cNumber+"', '"+expiration+"', '"+date+"', '"+pri+"', '2.99');";
        }
        try {
                stat.executeUpdate(query) ;
                
        }
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        query = "DELETE FROM cart WHERE customerID = '"+ Integer.toString(customer.getId())+"';";
        try {
                stat.executeUpdate(query) ;
                session.removeAttribute("cart");
                
        }
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        catch (SQLException ex){
            Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
