<%-- 
    Document   : employeeList
    Created on : Dec 7, 2017, 1:58:07 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script>
    function display(){
        document.getElementById('employeeAdd').style.display = 'block';
    }
            </script>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employees</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <style>
        body {background-color: #FAFAFA;}
    </style>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand" href="#">Mazon</a>
              </div>
              <ul class="nav navbar-nav">
                <li class="active"><a href="employeeloggedIn.jsp">Home</a></li>
                <li>.   .</li>
              </ul>
              <div class="navbar-header"><form method="post" action="./viewSellerServlet"><input class="navbar-brand" type="submit" value="Sellers"></form></div>
              <div class="navbar-header"><form method="post" action="./viewOrdersServlet"><input class="navbar-brand" type="submit" value="Orders"></form></div>
              <div class="navbar-header"><form method="post" action="./employeeServlet"><input class="navbar-brand" type="submit" value="Employees"></form></div>
              <div class="navbar-header"><form method="post" action="./viewItemsServlet"><input class="navbar-brand" type="submit" value="Inventory"></form></div>
              <div class="navbar-header"><form method="post" action="./userLogOutServlet"><input class="navbar-brand" type="submit" value="Sign Out"></form></div>
            </div>
        </nav>
    <center>
        <div class="jumbotron">
            <h1>Employees</h1>
        </div>
        <br>
        <a href="employeeLoggedIn.jsp" class="btn btn-default">back</a>
        <br>
        <table class="table table-striped table-hover">
        <thead>
            <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Date Hired</th>
                    <th>Position</th>
                    <th>Supervisor ID</th>
                    <th></th>
            </tr> 
        </thead>
    <tbody>
    <c:forEach items="${employees}" var="e">
        <tr>
            <td>${e.id}</td>
            <td>${e.firstName}</td>
            <td>${e.lastName}</td>
            <td>${e.email}</td>
            <td>${e.phoneNumber}</td>
            <td>${e.dateHired}</td>
            <td>${e.role}</td>
            <td>${e.supId}</td>
            <td><form name="remove" method="post" action="./fireServlet"> <input type="text" name= "id" value="${e.id}" hidden> <input type="submit" class="btn btn-default" value="Remove Employee"></form></td>
            
            
            
        </tr>
        </c:forEach>
    </tbody>
    </table> 
     <button type="button" onclick="display()" class="btn btn-default">Add Employee</button> 
     <div id="employeeAdd" hidden>
             <br>
            <form  method="post" action="./addEmployeeServlet" >
                <div class = "form-group">
                    <label for="fName">First Name:</label>
                    <input type="text" name="fName">
                </div>
                <div class = "form-group">
                    <label for="lName">Last Name:</label>
                    <input type="text" name="lName">
                </div>
                <div class = "form-group">
                    <label for="email">Email:</label>
                    <input type="text" name="email">
                </div>
                <div class = "form-group">
                    <label for="password">Password:</label>
                    <input type="text" name="password">
                </div>
                <div class = "form-group">
                    <label for="sId">Supervisor:</label>
                    <input type="text" name="sId">
                </div>
                <div class = "form-group">
                    <label for="position">Position:</label>
                    <input type="text" name="position">
                </div>
                <div class = "form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="text" name="phone">
                </div>            
           
            <input type="submit" value="Add Employee" class="btn btn-default"><br>
            
        </form>
     </div>
    </center>
    </body>
</html>
