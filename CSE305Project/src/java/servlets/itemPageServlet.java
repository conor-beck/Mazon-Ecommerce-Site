/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.customerBean;
import beans.itemBean;
import beans.reviewBean;
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
public class itemPageServlet extends HttpServlet {

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
        String id = request.getParameter("id");
        String query;
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
        itemBean currentItem = new itemBean();
        query = "SELECT * FROM inventory WHERE itemID = '" + id + "';";
        try {
                ResultSet result = stat.executeQuery(query) ;
                while (result.next()) {
                    currentItem.setName(result.getString("name"));
                    currentItem.setId(result.getInt("itemID"));
                    currentItem.setCategory(result.getString("category"));
                    currentItem.setPrice(result.getDouble("price"));
                    currentItem.setSeller(result.getString("seller"));
                    currentItem.setDescription(result.getString("description"));
                }
                session.setAttribute("currentItem", currentItem);
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        query = "SELECT * FROM reviews WHERE itemID = '" + id +"';";
        ArrayList<reviewBean> reviews = new ArrayList();
        try {
                ResultSet result = stat.executeQuery(query) ;
                while (result.next()) {
                    reviewBean rev = new reviewBean();
                    rev.setCustomerId(result.getInt("customerID"));
                    rev.setItemId(result.getInt("itemID"));
                    //rev.setSellerId(result.getInt("sellerID"));
                    rev.setScore(result.getInt("score"));
                    rev.setContent(result.getString("reviewMessage"));
                    reviews.add(rev);
                    
                }
                session.setAttribute("reviews", reviews);
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        catch (SQLException ex){
            Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher jsp = request.getRequestDispatcher("item.jsp");
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
