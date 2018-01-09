<%-- 
    Document   : item
    Created on : Dec 5, 2017, 2:40:43 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
   function display(){
    
        document.getElementById('reviewMaker').style.display = 'block';
    
        
    }
</script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${currentItem.name}</title>
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
            <h1>${currentItem.name}</h1>
        </div>
        $${currentItem.price}    Sold by: ${currentItem.seller}
        <br>
        ${currentItem.description}
        <br>
        <form name ="cart" method="post" action="./addToCartServlet">
            <input type="text" name= "id" value="${currentItem.id}" hidden>
            <input type="text" name= "name" value="${currentItem.name}" hidden>
            <input type="text" name= "price" value="${currentItem.price}" hidden>
            <input type="submit" class="btn btn-default" value="Add to cart"><br>
        </form>
            
            
            <br>
            <button type="button" class="btn btn-default" onclick="display(this)">Write Review</button> 
            <div id="reviewMaker" hidden>
             
             <br>
            <form  name ="reviewer" method="post" action="./reviewServlet">
                 <select name="score">
                <option value="5">5</option>
                <option value="4">4</option>
                 <option value="3">3</option>
                  <option value="2">2</option>
                  <option value="1">1</option>
                </select> 
            <input type="text" name= "id" value="${currentItem.id}" hidden>
            <textarea name= "content"> 
            </textarea>
           
            <input type="submit" class="btn btn-default" value="Submit Review!"><br>
        </form>
            </div>
        <table>
        <thead>
        <h2>Reviews</h2>
        </thead>
    <tbody>
    <c:forEach items="${reviews}" var="r">
        <tr>
   
    
        <br>
        Score ${r.score}
        <br>
        ${r.content}       
            
        </tr>
        </c:forEach>
    </tbody>
    </table>
    </center>
    </body>
</html>
