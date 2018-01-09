/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.customerBean;
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
public class removeServlet extends HttpServlet {

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
        
        String id = request.getParameter("iId");
        customerBean cus = (customerBean)session.getAttribute("customer");
        String cId = Integer.toString(cus.getId());
        String query;
        
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
        ArrayList<itemBean> items = new ArrayList();
        query = "DELETE FROM cart WHERE itemID ="+id+" AND customerID = "+cId+";";
        
        try {
            stat.executeUpdate(query) ;
            
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        query = "SELECT * FROM cart WHERE customerID = '" + cId+"';";
        ArrayList<Integer> idList = new ArrayList();
        ArrayList<itemBean> cart = new ArrayList();
        ArrayList<Integer> quan = new ArrayList();
        try {
                ResultSet result = stat.executeQuery(query) ;
                while (result.next()) {
                    idList.add(result.getInt("itemID"));
                    quan.add(result.getInt("quantity"));
                } 
                for(int i = 0; i < idList.size(); i++){
                    int index = idList.get(i);
                    String str = Integer.toString(index);
                    query = "SELECT * FROM inventory WHERE itemID = " + str +";";
                    ResultSet res = stat.executeQuery(query);
                    while (res.next()) {
                        itemBean it = new itemBean();
                        it.setName(res.getString("name"));
                        it.setId(res.getInt("itemID"));
                        it.setCategory(res.getString("category"));
                        it.setPrice(res.getDouble("price"));
                        it.setSeller(res.getString("seller"));
                        it.setDescription(res.getString("description"));
                        it.setQuantity(quan.get(i));
                        cart.add(it);
                    } 
                }
                session.setAttribute("cart", cart);
                double total = 0;
                int times = 0;
                for(int i = 0; i < cart.size(); i++){
                    times = cart.get(i).getQuantity();
                    total = total + (cart.get(i).getPrice() * times);
                }
                session.setAttribute("total", total);
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        catch (SQLException ex){
            Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher jsp = request.getRequestDispatcher("cart.jsp");
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
