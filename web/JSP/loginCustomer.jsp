<%-- 
    Document   : loginCustomer
    Created on : Apr 26, 2024, 1:44:33 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <body>
        <form action="customercontroller" method="post">
            <p>username:  <input type="text" name="username"></p>
            <p>password: <input type="password" name="password"></p> 
            <input type="hidden" name="service" value="login">
            <p><input type="submit" name="submit" value="login"></p>
            <p><input type="reset" value="Clear"></p>
        </form>
    </body>
    </body>
</html>
