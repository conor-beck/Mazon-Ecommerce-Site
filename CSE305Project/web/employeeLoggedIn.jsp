<%-- 
    Document   : employeeLoggedIn
    Created on : Dec 6, 2017, 11:03:47 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Dashboard</title>
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
            <h1>Mazon Employee Dashboard</h1>
        </div>
        <br>
        <h2>You are logged in as ${employee.firstName} ${employee.lastName}. What would you like to do?</h2>
        <br>
        <form name ="seller" method="post" action="./viewSellerServlet">
            <input type="submit" class="btn btn-default" value="View Sellers">
        </form>
        
        <form name ="viewOrders" method="post" action="./viewOrdersServlet">
            <input type="submit" class="btn btn-default" value="View Orders">
        </form>
        <form name ="viewEmployee" method="post" action="./employeeServlet">
            <input type="submit" class="btn btn-default" value="View employees">
        </form>
        <form name ="viewInventory" method="post" action="./viewItemsServlet">
            <input type="submit" class="btn btn-default" value="View Inventory">
        </form>
    </center>
    </body>
</html>
