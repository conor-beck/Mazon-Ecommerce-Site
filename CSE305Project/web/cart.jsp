<%-- 
    Document   : cart
    Created on : Dec 6, 2017, 1:25:08 PM
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
        <title>Cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <style>
        body {background-color: #FAFAFA;}
    </style>
    <center>
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
        <div class="jumbotron">
            <h1>Cart</h1>
        </div>
        <table class="table">
        <thead>
        <a href="loggedIn.jsp" class="btn btn-default">Back</a>
        </thead>
    <tbody>
    <c:forEach items="${cart}" var="d">
        <tr>
    <form method="post" action="./itemPageServlet" id="${d.id}"><input type="text" name= "id" value="${d.id}" hidden></form>
    <h3><span onclick="goToItemPage(${d.id})">${d.name} </span></h3>
        <br>
        Price: $ ${d.price}
        <br>
        ${d.quantity}
        <br>
        ${d.description}   
        <br>
        <form method="post" action="./removeServlet"> <input type="text" name= "iId" value="${d.id}" hidden>
            <input type="submit" value="Remove Item"><br> 
        </tr>
        </c:forEach>
    </tbody>
    </table> 
        $ ${total}<br>
        <a href="checkOut.jsp" class="btn btn-default">Check Out</a>
    </body>
    </center>
</html>
