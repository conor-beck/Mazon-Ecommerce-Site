<%-- 
    Document   : employeeLogin
    Created on : Dec 6, 2017, 9:30:10 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <style>
        body {background-color: #FAFAFA;}
    </style>
    <body>
    <center>
        <div class="jumbotron">
            <h1>Employee Login</h1>
        </div>
        <form name ="login" method="post" action="./employeeLoginServlet">
            <div class = "form-group">
                <label for="username">Username:</label>
                <input type="text" name="username">
            </div>
            <div class = "form-group">
                <label for="password">Password:</label>
                <input type="password" name="password">
            </div>
            <input type="submit" class="btn btn-default" value="Login"><br>
        </form>
    </center>
    </body>
</html>
