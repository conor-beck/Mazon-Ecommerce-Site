<%-- 
    Document   : loggedIn
    Created on : Dec 2, 2017, 5:57:22 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
        function goToItemPage(id) {
            var f = document.getElementById(id);
                $(f).submit();
            }
            </script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <title>Mazon</title>
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
            <h1>Welcome ${customer.firstName}!</h1>
        </div>
        
        <a href="cart.jsp" class="btn btn-default">cart</a><br />
        Show: 
    <form method="post" action="./sortServlet">
        <select name="type">
                <option value="All">All</option>
                <option value="Home">Home</option>
                 <option value="Electronics">Electronics</option>
                  <option value="Office">Office</option>
                  <option value="Clothes">Clothes</option>
                </select>
        <input type="submit" class="btn btn-default" value="Filter"><br>
    </form>
        <br>
        <table class="table">
        <thead>
        <h2>Items for Sale</h2>
        </thead>
    <tbody>
    <c:forEach items="${items}" var="i">
        <tr>
    <form method="post" action="./itemPageServlet" id="${i.id}"><input type="text" name= "id" value="${i.id}" hidden></form>
    <h3><span onclick="goToItemPage(${i.id})">${i.name} </span></h3>
        <br>
        Price: $ ${i.price}
        <br>
        ${i.description} <br />     <a href="item.jsp" class="btn btn-default">Reviews</a> 
            <form method="post" action="./addToCartServlet">
            
                <input type="text" name= "id" value="${i.id}" hidden>
                <input type="submit" class="btn btn-default" value="Add to cart">
            
            </form>
        </tr>
        </c:forEach>
    </tbody>
    </table> 
       </center>
     
    </body>
</html>
