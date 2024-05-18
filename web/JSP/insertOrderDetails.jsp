<%-- 
    Document   : insertOrderDetails
    Created on : Apr 25, 2024, 1:31:31 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.OrderDetails" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       

        <% 
            Vector<OrderDetails> vector = (Vector<OrderDetails>) request.getAttribute("vector");
        %>

        <form action="orderdetailscontroller" method="post">
            <table>
                
                <tr>
                    <td>orderID</td>
                    <td> <input type="text" name="orderID" ></td>
                </tr>
                
                <tr>
                    <td>productID</td>
                    <td> <input type="text" name="productID" ></td>
                </tr>
                
                <tr>
                    <td>unitPrice</td>
                    <td> <input type="text" name="unitPrice" ></td>
                </tr>

                <tr>
                    <td>quantity</td>
                    <td>
                        <input type="text" name="quantity" > 
                    </td>
                </tr>
                <tr>
                    <td>discount</td>
                    <td>
                        <input type="text" name="discount" > 
                    </td>
                </tr>
               
                <tr>
                    <td> <input type="submit" name="submit" value="insert OrderDetails"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertOrderDetails">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
