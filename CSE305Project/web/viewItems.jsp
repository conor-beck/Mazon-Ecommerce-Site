<%-- 
    Document   : viewItems
    Created on : Dec 7, 2017, 4:09:54 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script>
         function display(){
    
        document.getElementById('itemAdd').style.display = 'block';
    
        
    }
            </script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
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
            <h1>Inventory</h1>
        </div>
        <br>
        <a href="employeeLoggedIn.jsp" class="btn btn-default">back</a>
        <br>
        <button type="button" class="btn btn-default" onclick="display()">Add Item</button> <br>
        <div id ="itemAdd" hidden>
        <form   method="post" action="./addItemServlet">
            <div class = "form-group">
                <label for="name">Item Name:</label>
                <input type="text" name="name">
            </div>
            <div class = "form-group">
                <label for="price">Price:</label>
                <input type="text" name="price">
            </div>
            <div class = "form-group">
                <label for="seller">Seller:</label>
                <input type="text" name="seller">
            </div>
            <div class = "form-group">
                <label for="quantity">Quantity:</label>
                <input type="text" name="quantity">
            </div>
            <div class = "form-group">
                <label for="category">Category:</label>
                <input type="text" name="category">
            </div>
            Description: <br>
            <textarea name= "content"> 
            </textarea><br>

            <input type="submit" class="btn btn-default" value="Add Item"><br>
        </form>
    </div>
        <br>
        <table class="table table-striped table-hover">
        <thead>
            <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Seller</th>
                    <th>Category</th>
                    <th>Stock</th>
                    <th></th>
            </tr> 
        </thead>
    <tbody>
    <c:forEach items="${items}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.name}</td>
            <td>${i.price}</td>
            <td>${i.seller}</td>
            <td>${i.category}</td>
            <td>Card Number ${i.quantity}</td>
            <td><form name="set" method="post" action="./updateServlet"> <input type="text" name= "id" value="${i.id}" hidden> <input type="text" name="amount"><input type="submit" class="btn btn-default" value="Update Stock"></form></td>

        </tr>
        </c:forEach>
    </tbody>
    </table> 
     
             <br>
    </center>
    </body>
</html>
