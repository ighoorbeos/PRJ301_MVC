<%-- 
    Document   : insertSupplier
    Created on : Apr 26, 2024, 10:04:20 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, java.util.HashSet, entity.Suppliers" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% 
           Vector<Suppliers> vector = (Vector<Suppliers>) request.getAttribute("vector");
            
        %>

        <form action="suppliercontroller" method="post">
            <table>
                <tr>
                    <td>companyName</td>
                    <td> <input type="text" name="companyName" ></td>
                </tr>

                <tr>
                    <td>contactName</td>
                    <td> <input type="text" name="contactName" ></td>
                </tr>
                <tr>
                    <td>contactTitle</td>
                    <td>
                        <input type="text" name="contactTitle" > 
                    </td>
                </tr>
                <tr>
                    <td>address</td>
                    <td>
                        <input type="text" name="address" > 
                    </td>
                </tr>
                <tr>
                    <td>city</td>
                    <td>
                        <input type="text" name="city" > 
                    </td>
                </tr>
                <tr>
                    <td>region</td>
                    <td>
                        <input type="text" name="region" > 
                    </td>
                </tr>
                <tr>
                    <td>postalCode</td>
                    <td>
                        <input type="text" name="postalCode" > 
                    </td>
                </tr>
                <tr>
                    <td>country</td>
                    <td>
                        <input type="text" name="country" > 
                    </td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td>
                        <input type="text" name="phone" > 
                    </td>
                </tr>
                <tr>
                    <td>fax</td>
                    <td>
                        <input type="text" name="fax" > 
                    </td>
                </tr>
                <tr>
                    <td>homePage</td>
                    <td>
                        <input type="text" name="homePage" > 
                    </td>
                </tr>
                
                <tr>
                    <td> <input type="submit" name="submit" value="insert Supplier"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertSupplier">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
