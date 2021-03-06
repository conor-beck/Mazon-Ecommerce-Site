<%-- 
    Document   : sellers
    Created on : Dec 7, 2017, 1:02:49 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sellers</title>
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
            <h1>Sellers</h1>
        </div>
        <br>
        <a href="employeeLoggedIn.jsp" class="btn btn-default">back</a>
        <br>
        <table class="table table-striped table-hover">
        <thead>
            <tr>
                    <th>Seller ID</th>
                    <th>Seller Name</th>
            </tr> 
        </thead>
    <tbody>
    <c:forEach items="${sellers}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            
        </tr>
        </c:forEach>
    </tbody>
    </table> 
    </center>
    </body>
</html>
