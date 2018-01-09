<%-- 
    Document   : checkOut
    Created on : Dec 7, 2017, 5:53:08 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out</title>
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
                <li class="active"><a href="loggedIn.jsp">Home</a></li>
                <li><a href="cart.jsp">Cart</a></li>
                <li>.   .</li>
              </ul>
              <div class="navbar-header"><form method="post" action="./userLogOutServlet"><input class="navbar-brand" type="submit" value="Sign Out"></form></div>
            </div>
        </nav>
    <center>
        <div class="jumbotron">
            <h1>Card Information</h1>
        </div>
        <form name ="checkout" method="post" action="./checkOutServlet">
            <div class = "form-group">
                <label for="cNumber">Card Number:</label>
                <input type="text" name="cNumber">
            </div>
            <div class = "form-group">
                <label for="expiration">Expiration Date:</label>
                <input type="text" name="expiration">
            </div>
            <div class = "form-group">
                <label for="address">Shipping Address:</label>
                <input type="text" name="address">
            </div>
            <input type="submit" class="btn btn-default" value="Order"><br>
        </form>
    </center>
    </body>
</html>
