<%-- 
    Document   : loginEmployee
    Created on : Apr 22, 2024, 1:43:06 PM
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

        <form action="employeecontroller" method="post">
            <p>username<input type="text" name="username"></p><!-- comment -->
            <p>password<input type="password" name="password"></p><!-- comment -->
            <input type="hidden" name="service" value="login"><!-- comment -->
            <p> <input type="submit" name="submit" value="login"></p><!-- comment -->
            <input type="reset" name="Reset"><!-- comment -->
        </form>

    </body>
</html>
