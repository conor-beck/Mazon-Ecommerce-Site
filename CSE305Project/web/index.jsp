<%-- 
    Document   : index
    Created on : Dec 3, 2017, 4:20:02 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mazon</title>
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
            <h1>Welcome to Mazon!</h1>
                <p>Please login below. If you do not have an account, please register by checking the box and entering your information.</p> 
        </div>
        <form name ="login" method="post" action="./userLoginServlet">
            <div class = "form-group">
                <label for="user">Username:</label>
                <input type="text" name="username">
            </div>
            <div class = "form-group">
                <label for="password">Password:</label>
                <input type="password" name="password">
            </div>
            <button type="submit" class="btn btn-default">Login</button>
        </form><br />
        <a href="employeeLogin.jsp">Employee Login</a> <br />
        <input type="checkbox" onclick="display(this)"> New Customer? </input>
        
        <div id="register" hidden> 
            <form name ="newUser" method="post" action="./newUserServlet">
                <div class = "form-group">
                    <label for="user">Email Address:</label>
                    <input type="text" name="username">
                </div>
                <div class = "form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password">
                </div>
                <div class = "form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" name="firstName">
                </div>
                <div class = "form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" name="lastName">
                </div>
                <div class = "form-group">
                    <label for="phoneNum">Phone Number:</label>
                    <input type="text" name="phoneNum">
                </div>
                <div class = "form-group">
                    <label for="address">Address:</label>
                    <input type="text" name="address">
                </div>
                <div class = "form-group">
                    <label for="zipCode">Zip Code:</label>
                    <input type="text" name="zipCode">
                </div>
                <div class = "form-group">
                    <label for="town">Town:</label>
                    <input type="text" name="town">
                </div>
                <button type="submit" class="btn btn-default">Sign up</button>
            </form>
        </div>
        </center>
    </body>
</html>
<script>
   function display(e){
    if (e.checked)
        document.getElementById('register').style.display = 'block';
    else
        document.getElementById('register').style.display = 'none';
    }
</script>
