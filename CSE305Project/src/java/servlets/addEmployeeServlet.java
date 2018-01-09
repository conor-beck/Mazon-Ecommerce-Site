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
public class addEmployeeServlet extends HttpServlet {

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
       int i = 0;
        String id = new String();
        String firstName = request.getParameter("fName");
        String lastName = request.getParameter("lName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String sID = request.getParameter("sId");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String query;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();
        String date = format.format(d);
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
        
        query = "SELECT MAX(employeeID) as ma FROM employee;";
        
        try {
                ResultSet result = stat.executeQuery(query) ;
                while (result.next()) {
                    i = result.getInt("ma");
                }   
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        i++;
        id = Integer.toString(i);
        ArrayList<employeeBean> employees = (ArrayList<employeeBean>)session.getAttribute("employees");
        employeeBean em = new employeeBean();
        em.setDateHired(date);
        em.setEmail(email);
        em.setFirstName(firstName);
        em.setLastName(lastName);
        em.setPassword(password);
        em.setId(i);
        em.setPhoneNumber(phone);
        em.setSupId(Integer.parseInt(sID));
        employees.add(em);
        session.setAttribute("employees", employees);
        query = "INSERT INTO employee(email, password, firstName, LastName, phoneNum, supervisorID, employeeID, dateHired, role)"+
                "VALUES('"+email+"', '"+password+"', '" +firstName+"', '"+lastName+"', '"+phone+"', '"+sID+"', '"+i+"', "+date+"', '"+position+"');";
        try {
                stat.executeUpdate(query) ;
                
            } 
        catch (SQLException ex) {
                Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        catch (SQLException ex){
            Logger.getLogger(userLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher jsp = request.getRequestDispatcher("employeeList.jsp");
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
