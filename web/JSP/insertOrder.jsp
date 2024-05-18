<%-- 
    Document   : insertOrder
    Created on : Apr 26, 2024, 9:20:59 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, java.util.HashSet, entity.Orders" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
           Vector<Orders> vector = (Vector<Orders>) request.getAttribute("vector");
            
        %>

        <form action="ordercontroller" method="post">
            <table>
                <tr>
                    <td>customerID</td>
                    <td> <input type="text" name="customerID" ></td>
                </tr>

                <tr>
                    <td>employeeID</td>
                    <td> <input type="text" name="employeeID" ></td>
                </tr>
                <tr>
                    <td>orderDate</td>
                    <td>
                        <input type="text" name="orderDate" > 
                    </td>
                </tr>
                <tr>
                    <td>requiredDate</td>
                    <td>
                        <input type="text" name="requiredDate" > 
                    </td>
                </tr>
                <tr>
                    <td>shippedDate</td>
                    <td>
                        <input type="text" name="shippedDate" > 
                    </td>
                </tr>
                <tr>
                    <td>shipVia</td>
                    <td>
                        <input type="text" name="shipVia" > 
                    </td>
                </tr>
                <tr>
                    <td>freight</td>
                    <td>
                        <input type="text" name="freight" > 
                    </td>
                </tr>
                <tr>
                    <td>shipName</td>
                    <td>
                        <input type="text" name="shipName" > 
                    </td>
                </tr>
                <tr>
                    <td>shipAddress</td>
                    <td>
                        <input type="text" name="shipAddress" > 
                    </td>
                </tr>
                <tr>
                    <td>shipCity</td>
                    <td>
                        <input type="text" name="shipCity" > 
                    </td>
                </tr>
                <tr>
                    <td>shipRegion</td>
                    <td>
                        <input type="text" name="shipRegion" > 
                    </td>
                </tr>
                <tr>
                    <td>shipPostalCode</td>
                    <td>
                        <input type="text" name="shipPostalCode" > 
                    </td>
                </tr>
                 <tr>
                    <td>shipCountry</td>
                    <td>
                        <input type="text" name="shipCountry" > 
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="submit" value="insert Order"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertOrder">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
